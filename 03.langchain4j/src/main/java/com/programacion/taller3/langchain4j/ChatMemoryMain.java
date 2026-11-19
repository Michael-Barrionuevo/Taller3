package com.programacion.taller3.langchain4j;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;

import java.util.Scanner;

@SystemMessage("Responde con un solo parrafo corto y claro")
interface MiChat{
    Result<String> chat(String mensaje);
}

public class ChatMemoryMain {
    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .apiKey(ChatModelMain.KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
                //.logResponses(true)
                .build();

        ChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        var chat = AiServices.builder(MiChat.class)
                .chatModel(chatModel)
                .chatMemory(memory)
                .build();

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("mensajes");
            String mensaje =  scanner.nextLine();

            if ("salir".equalsIgnoreCase(mensaje)){
                System.out.println("Adios, que tengas buen día.");
                break;
            }

            Result<String> respuesta = chat.chat(mensaje);

            var texto = "tokens: %s. respuesta= %s".formatted(
                    respuesta.tokenUsage(),
                    respuesta.content()
            );
            System.out.println("respuesta> " +texto);
        }
    }
}
