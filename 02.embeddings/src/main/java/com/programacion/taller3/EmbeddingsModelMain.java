package com.programacion.taller3;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Paths;

public class EmbeddingsModelMain {
    static void main() {
        var pathToModel = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);
        var text =  "Hola, como estàs?";

        Response<Embedding> response =  model.embed(text);
        Embedding embedding = response.content();

        System.out.println("Dimension: of the embbedings: " + embedding.dimension());
        System.out.println("Embbeding vector: " + embedding.vectorAsList());


    }
}
