package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

public class ChatModelMain {

    final static String BASE_URL = "https://api.groq.com/openai/v1";
    final static String KEY = "GROQ_API_KEY";
    final static String MODEL = "qwen/qwen3.8-27b";

    // local
    /*final static String BASE_URL = "http://127.0.0.1:9931";
    final static String KEY = "123";
    final static String MODEL = "gpt-3.5-turbo";
*/
    //GROQ_API_KEY

    static void main() {
        System.getenv("OPENAI_API_URL");
        System.getenv("OPENAI_API_KEY");
        System.getenv("OPENAI_API_MODEL");

        OpenAiStreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                //.maxTokens(10)
                //.logRequests(true)
                //.logResponses(true)
                //.customHeaders(java.util.Map.of("x-opencode-session","123"))
                .build();

        var count = new AtomicInteger(1);

        chatModel.chat("Qué es LangChain4j?",
                new MyStreamingChatResponseHandler(count));
        //System.out.println(respuesta);

        while (count.get()>0){
            //Thread.currentThread().join();
        }
    }
}
