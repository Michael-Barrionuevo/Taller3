package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

record Persona(String nombre, int edad,String ciudad){

}
interface Extractor {
    @UserMessage("Extrear la informacion de contacto del siguiente texto: {{texto}}")
    Persona extraerInformacionContacto(@V("texto") String texto);
}

public class StructureOutputMain {
    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .apiKey(ChatModelMain.KEY)
                .modelName(ChatModelMain.MODEL)
                .baseUrl(ChatModelMain.BASE_URL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var extractor = AiServices.builder(Extractor.class)
                .chatModel(chatModel)
                .build();

        var texto = """
                Hola, mi nombre es Juan Perez. Tengo 30 años y vivo en la ciudad de Quito.
                Puedes contactarme al correo example@gmail.com
                """;
        var persona = extractor.extraerInformacionContacto(texto);
        System.out.println(persona);
    }
}
