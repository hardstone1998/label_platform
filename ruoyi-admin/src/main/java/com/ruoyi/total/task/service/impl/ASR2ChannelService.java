package com.ruoyi.total.task.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.total.task.service.IASR2ChannelService;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.http.entity.ContentType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;

/**
 * @author：乔石磊
 * @date：14:57 2024/1/4
 */

@Service
public class ASR2ChannelService implements IASR2ChannelService {

    @Override
    public void handleAudioFile(Path filePath) throws IOException {
        String fileName = filePath.toString();
        String result = callWhisper(fileName);
        if (result==null){
            return;
        }
        getContent(result);
    }

    public static String getContent(String result) throws JsonProcessingException {
        int i1 = result.indexOf("\"text_sum\":\"");
        int i2 = result.indexOf("\"}]");
        String result1 = result.substring(i1+12,i2).replace(" ","");
        return result1;
    }

    public static String callWhisper(String audioFilePath) throws IOException {
        File audioFile = new File(audioFilePath);
        String whisperUrl = "http://36.112.150.185:8805/recognition";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(whisperUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.addTextBody("to_simple", "1");
        entityBuilder.addTextBody("remove_pun", "0");
        entityBuilder.addTextBody("language", "zh");
        entityBuilder.addTextBody("task", "transcribe");
        entityBuilder.addBinaryBody("audio", audioFile, ContentType.create("audio/wav"), audioFile.getName());

        HttpEntity multipartEntity = entityBuilder.build();

        httpPost.setEntity(multipartEntity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            String jsonResponse = EntityUtils.toString(responseEntity);
            return jsonResponse;
        }

        return null;
    }
}
