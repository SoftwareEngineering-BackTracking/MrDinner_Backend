package com.backtracking.MrDinner.global.voice;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VoiceService {

    public String generateId(MultipartFile audioFile, String token) throws IOException {

        File audio = new File(audioFile.getOriginalFilename());
        try(OutputStream os = new FileOutputStream(audio)){
            os.write(audioFile.getBytes());
        }

        OkHttpClient client = new OkHttpClient();

        Map<String, Object> map = new HashMap<>();
        map.put("use_multi_channel", true);

        ObjectMapper objectMapper = new ObjectMapper();
        String configJson = objectMapper.writeValueAsString(map);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", audio.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), audio))
                .addFormDataPart("config", configJson)
                .build();

        Request request = new Request.Builder()
                .url("https://openapi.vito.ai/v1/transcribe")
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "multipart/form-data")
                .post(requestBody)
                .build();

        try{
            Response response = client.newCall(request).execute();
            HashMap<String, String> resultMap = objectMapper.readValue(response.body().string(), HashMap.class);
            //System.out.println(resultMap.keySet());
            //System.out.println(resultMap.values());
            return resultMap.get("id");
        }
        catch (Exception e){
            System.out.println("음성인식 변환 실패:" + e.getClass().getName() +": " + e.getMessage());
        }

        return null;
    }

    public DinnerStyleVoice getMenu(String id, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://openapi.vito.ai/v1/transcribe"+"/"+id)
                .addHeader("Authorization","Bearer "+token)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> resultMap = objectMapper.readValue(response.body().string(), HashMap.class);

        LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultMap.get("results");
        ArrayList<LinkedHashMap> arrayList = (ArrayList<LinkedHashMap>) linkedHashMap.get("utterances");
        String str = (String) arrayList.get(0).get("msg");

        // 반환값
        System.out.println(resultMap.values());
        
        String[] tempDinnerStyle = str.split(" ");
        Dinner dinner = Dinner.valueOf(tempDinnerStyle[0]);
        Style style = Style.valueOf(tempDinnerStyle[1]);

        DinnerStyleVoice dinnerStyle = new DinnerStyleVoice();
        dinnerStyle.setDinner(dinner);
        dinnerStyle.setStyle(style);
        return dinnerStyle;
    }
}