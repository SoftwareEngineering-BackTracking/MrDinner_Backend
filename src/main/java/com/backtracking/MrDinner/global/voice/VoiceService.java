package com.backtracking.MrDinner.global.voice;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VoiceService {
    class ModifiedDate implements Comparator {
        public int compare(Object o1, Object o2) {
            File f1 = (File)o1;
            File f2 = (File)o2;
            if (f1.lastModified() > f2.lastModified()) return -1;
            if (f1.lastModified() == f2.lastModified()) return 0; return 1;
        }
    }
    public String generateId(String token) throws IOException, InterruptedException {

        //File audio = new File(audioFile.getOriginalFilename());
        //try(OutputStream os = new FileOutputStream(audio)){
        //    os.write(audioFile.getBytes());
        //}
        Thread.sleep(10000);
        String DIR_DIRECTORY = "C:/Users/sktmd/Downloads/";
        File audio11 = new File(DIR_DIRECTORY);

        File[] list = audio11.listFiles();
        Arrays.sort(list, new ModifiedDate());
        //따로 하나 만들어둘 클래스
        // -1이면 내림차순, 1이면 오름차순, 0이면 동일
        System.out.println("kkk"+list[0]);
        System.out.println("kkk"+list[0].getName());


        File audio = new File("C://Users/sktmd/Downloads/" + list[0].getName());
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
            System.out.println(resultMap.keySet());
            System.out.println(resultMap.values());
            return resultMap.get("id");
        }
        catch (Exception e){
            System.out.println("음성인식 변환 실패:" + e.getClass().getName() +": " + e.getMessage());
        }

        return null;
    }

    public DinnerStyleVoice getMenu(String id, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        System.out.println("id and token: "+id+" "+token);
        Request request = new Request.Builder()
                .url("https://openapi.vito.ai/v1/transcribe"+"/"+id)
                .addHeader("Authorization","Bearer "+token)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> resultMap = objectMapper.readValue(response.body().string(), HashMap.class);
        System.out.println(resultMap.values());
        LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultMap.get("results");
        ArrayList<LinkedHashMap> arrayList = (ArrayList<LinkedHashMap>) linkedHashMap.get("utterances");
        String str = (String) arrayList.get(0).get("msg");

        
        String[] tempDinnerStyle = str.split(" ");
        Dinner dinner = Dinner.valueOf(tempDinnerStyle[0]);
        Style style = Style.valueOf(tempDinnerStyle[1]);

        DinnerStyleVoice dinnerStyle = new DinnerStyleVoice();
        dinnerStyle.setDinner(dinner);
        dinnerStyle.setStyle(style);
        return dinnerStyle;
    }
}