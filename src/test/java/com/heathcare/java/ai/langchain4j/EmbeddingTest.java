package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = VitaApp.class)
public class EmbeddingTest {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbeddingModel(){
        Response<Embedding> embed = embeddingModel.embed("Hello");

        System.out.println("vector dimension：" + embed.content().vector().length);
        System.out.println("vector output：" + embed.toString());
    }

    @Autowired
    private EmbeddingStore embeddingStore;


    @Test
    public void testPineconeEmbedded() {

        TextSegment segment1 = TextSegment.from("I like pingpong");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("good weather today");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }


    @Test
    public void embeddingSearch() {

        Embedding queryEmbedding = embeddingModel.embed("what's your favorite sport？").content();
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1) // the closest one
                //.minScore(0.8)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        // similarity score
        System.out.println(embeddingMatch.score());

        // text response
        System.out.println(embeddingMatch.embedded().text());
    }

    @Test
    public void testUploadKnowledgeLibrary() {

        Document document1 = FileSystemDocumentLoader.loadDocument("RAGDocuments/MayoClinic.md");

        List<Document> documents = Arrays.asList(document1);

        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
    }


}