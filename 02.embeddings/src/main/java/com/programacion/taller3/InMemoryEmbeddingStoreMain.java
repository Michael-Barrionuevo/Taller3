package com.programacion.taller3;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Paths;
import java.util.List;

public class InMemoryEmbeddingStoreMain {
    static void main() {
        var pathToModel = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("/Users/veykerbarrionuevo/Taller3/taller3/modelos/multilingual-e5-small.json");
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel, pathToTokenizer, PoolingMode.MEAN);

        var documentos = List.of(
                "Java es un lenguaje de programaciòn orientado a objetos",
                "Python es un lenguaje popular en inteligencia artificial",
                "Los embeddings representan texto como vectores"
        );

        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        documentos.forEach(it -> {
            String key = "doc-" + (store.size() + 1);

            var embedding = model.embed(it);

            store.add(key, embedding.content(), TextSegment.from(it));
        });

        // Consulta
        var consulta = "programaciòn orientada a objetos";
        var searchQuery = EmbeddingSearchRequest.builder()
                .queryEmbedding(model.embed(consulta).content())
                .maxResults(2) // top-k
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = store.search(searchQuery);

        for (var it : searchResult.matches()) {
            System.out.printf("Score:  %.4f - %s%n", it.score(), it.embedded().text());
        }
    }
}
