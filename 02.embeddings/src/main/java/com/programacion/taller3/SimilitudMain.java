package com.programacion.taller3;

import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {
    static void main() {
        var pathToModel = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);
        var embedding1 = model.embed("perro").content();
        var embedding2 = model.embed("canino").content();
        var embedding3 = model.embed("clima").content();

        // Calcular la similitud entre los embeddings
        double sim12 = CosineSimilarity.between(embedding1,embedding2);
        double sim13 = CosineSimilarity.between(embedding1,embedding3);
        double sim23 = CosineSimilarity.between(embedding2,embedding3);

        System.out.println("similitud entre embbeding1 y embbeding2: " + sim12);
        System.out.println("similitud entre embbeding1 y embbeding3: " + sim13);
        System.out.println("similitud entre embbeding2 y embbeding3: " + sim23);

    }
}
