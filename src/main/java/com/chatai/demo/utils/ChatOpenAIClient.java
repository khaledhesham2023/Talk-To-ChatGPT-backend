package com.chatai.demo.utils;

import com.chatai.demo.model.AnswerFile;
import com.chatai.demo.model.Message;
import com.chatai.demo.model.request.ChatCompletionRequest;
import com.chatai.demo.model.request.TextToSpeechRequest;
import com.chatai.demo.model.response.ChatCompletionResponse;
import com.chatai.demo.model.response.SpeechResponse;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

@Configuration
public class ChatOpenAIClient {
    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private Gson gson;

    public SpeechResponse getSpeechText(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        // Create RequestBody for the file
        RequestBody fileRequestBody = RequestBody.create(convFile, MediaType.parse("multipart/form-data"));

        // Build multipart form-data request
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", convFile.getName(), fileRequestBody)
                .addFormDataPart("model", openAIConfig.getTranscriptionModel())
                .build();

        // Build the request
        Request request = new Request.Builder()
                .url(openAIConfig.getUrl() + "audio/transcriptions")
                .addHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .post(requestBody)
                .build();

        // Execute the request and handle the response
        try (Response response = okHttpClient.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), SpeechResponse.class);
        }
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
        assert response.body() != null;
        String responseBody = response.body().string();
        return gson.fromJson(responseBody, ChatCompletionResponse.class);
    }

    public AnswerFile getAnswerVoiceFile(String answerText) throws IOException {
        String answerFileName = getAnswerFileName();
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
            try (InputStream inputStream = response.byteStream()){
                byte[] buffer = inputStream.readAllBytes();
                MultipartFile multipartFile = new InMemoryMultipartFile(answerFileName,answerFileName,"audio/mp3",buffer);
                return new AnswerFile(multipartFile,answerFileName);
                }
            } else {
            throw new IOException("Failed to get response from the server");
        }
    }

    private String getAnswerFileName() {
        return "answer-" + System.currentTimeMillis() + new Random().nextInt(1000000000) + ".mp3";
    }
}
