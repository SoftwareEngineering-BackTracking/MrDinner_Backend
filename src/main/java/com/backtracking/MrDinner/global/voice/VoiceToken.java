package com.backtracking.MrDinner.global.voice;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class VoiceToken {

    public String generateToken() throws IOException, ParseException {
        URL url = new URL("https://openapi.vito.ai/v1/authenticate");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setDoOutput(true);

        String data = "client_id=PVDX_rnJRNiMvYEYb3Hr&client_secret=crLCiMobFobFNUkLjjL2u5VFSXphnPlqQfVNc1_T";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = httpConn.getOutputStream();
        stream.write(out);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(response);
        JSONObject jsonObj = (JSONObject) obj;

        String accessToken = (String) jsonObj.get("access_token");
        //Date expireAt = (Date) jsonObj.get("expire_at");
        System.out.println(accessToken);
        //System.out.println(expireAt);
        return accessToken;
    }
}
