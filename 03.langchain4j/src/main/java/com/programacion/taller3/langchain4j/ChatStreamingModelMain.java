package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

class MyStreamingChatResponseHandler implements StreamingChatResponseHandler {

    final AtomicInteger count;

    public MyStreamingChatResponseHandler(AtomicInteger count){
        this.count =  count;
    }

    @Override
    public void onPartialResponse(String partialResponse) {
        System.out.print(partialResponse);
        //System.out.println();
        //StreamingChatResponseHandler.super.onPartialResponse(partialResponse);
    }

    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        System.out.println();
        System.out.println("Complete response");
        count.getAndDecrement();
    }

    @Override
    public void onError(Throwable error) {
        System.out.println("Error" + error.getMessage());

    }

}

public class ChatStreamingModelMain {
    static void main() throws InterruptedException {
        var chatModel = OpenAiStreamingChatModel.builder()
                .apiKey(ChatModelMain.KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var count = new AtomicInteger(1);
        chatModel.chat("Qué es LangChain4j?", new MyStreamingChatResponseHandler(count));

        Thread.currentThread().join();
    }
}
