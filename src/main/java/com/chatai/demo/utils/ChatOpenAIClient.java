package com.chatai.demo.utils;

import com.chatai.demo.model.AnswerFile;
import com.chatai.demo.model.CompletionObject;
import com.chatai.demo.model.Message;
import com.chatai.demo.model.request.ChatCompletionRequest;
import com.chatai.demo.model.request.TextToSpeechRequest;
import com.chatai.demo.model.response.ChatCompletionResponse;
import com.chatai.demo.model.response.SpeechResponse;
import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Configuration
public class ChatOpenAIClient {
    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private Gson gson;

    public SpeechResponse getSpeechText(File file) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .addFormDataPart("model", openAIConfig.getTranscriptionModel())
                .build();
        Request request = new Request.Builder()
                .url(openAIConfig.getUrl() + "audio/transcriptions")
                .addHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .addHeader("Content-Type", "multipart/form-data")
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        SpeechResponse responseBody = gson.fromJson(response.body().string(), SpeechResponse.class);
        return responseBody;
    }

    public ChatCompletionResponse getAnswerText(String text) throws IOException {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("user", text));
        String requestForChat = gson.toJson(new ChatCompletionRequest(openAIConfig.getChatCompletionModel(), messages));
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, requestForChat);
        Request request = new Request.Builder()
                .url(openAIConfig.getUrl() + "chat/completions")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        ChatCompletionResponse chatCompletionResponse = gson.fromJson(responseBody, ChatCompletionResponse.class);
        return chatCompletionResponse;
    }

    public AnswerFile getAnswerVoiceFile(String answerText) throws IOException {
        String answerFileName = getAnswerFileName();
        File file = new File(openAIConfig.getPathname() + answerFileName);
        System.out.println(file.getAbsolutePath());
        String request = gson.toJson(new TextToSpeechRequest(openAIConfig.getTextSpeechModel(), answerText, "alloy"));
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, request);
        Request request1 = new Request.Builder()
                .addHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .url(openAIConfig.getUrl() + "audio/speech")
                .build();
        ResponseBody response = okHttpClient.newCall(request1).execute().body();
        if (response != null) {
            try (InputStream inputStream = response.byteStream();
                 OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[4096];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
                outputStream.flush();
            }
        }
        return new AnswerFile(file,answerFileName);
    }

    private String getAnswerFileName() {
        return "answer-" + System.currentTimeMillis() + new Random().nextInt(1000000000) + ".mp3";
    }
}
