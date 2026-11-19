package com.programacion.taller3.langchain4j.utils;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

@SystemMessage(
        """
                "Eres un asistente de programacion que responde preguntas sobre programacion y desarrollo de software.
                Proporciona respuestas claras, concisas y utiles
                Si no sabes la respuesta, di "No se la respuesta a esa pregunta."
                Responde maximo con un parrafo de 5 lineas
                Utiliza español latino para las respuestas
                """
)
public interface AsistenteProgramacion {
    String responderPregunta(String pregunta);


    @UserMessage("""
            Responde la pregunta {{pregunta}}
            Si no sabes la respuesta utiliza el contexto proporcionado:
            CONTEXTO:
            {{contexto}}
            """)
    String consultar(@V("pregunta") String pregunta,@V("contexto") String contexto);
    //@UserMessage("Responde a la pregunta: {{pregunta}}")
}
