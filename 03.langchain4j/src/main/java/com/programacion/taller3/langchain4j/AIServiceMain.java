package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class AIServiceMain {
    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .baseUrl(ChatModelMain.BASE_URL)
                .apiKey(ChatModelMain.KEY)
                .modelName(ChatModelMain.MODEL)
                .logRequests(true)
                .build();

        var asistente = AiServices.create(AsistenteProgramacion.class,chatModel);

        var respuesta = asistente.responderPregunta("Qué es LangChain4j?");
        System.out.println(respuesta);
        System.out.println("---------------------------------");

        String contexto = """
                Para obtener el titulo de ingeniero en Sistemas Computacionales
                se debe cumplir los siguientes requisitos:
                1. Aprobar todas las materias del plan de estudios.
                2. Realizar y aprobar el servicio social.
                3. Presentar y aprobar el examen profesional.
                """;

        //var respuesta2 = asistente.consultar("Qué es SpringBoot?");
        var respuesta2 = asistente.consultar("Cuales son los requisitos de titulacion?", contexto);
        System.out.println(respuesta2);

    }
}
