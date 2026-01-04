package com.healthcare.java.ai.langchain4j.config;

import com.healthcare.java.ai.langchain4j.store.MongoChatMemoryStore;
import com.mongodb.client.MongoClient;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class VitaAgentConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProviderVita() {
         return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    @Bean
    ContentRetriever contentRetrieverVita() {
        // Use FileSystemDocumentLoader to load documents
        // Use default parser to parse documents
        Document document1 = FileSystemDocumentLoader.loadDocument("C:/Users/a/Desktop/小智医疗/knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("C:/Users/a/Desktop/小智医疗/knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("C:/Users/a/Desktop/小智医疗/knowledge/神经内科.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        return EmbeddingStoreContentRetriever.from(embeddingStore);

    }


}
