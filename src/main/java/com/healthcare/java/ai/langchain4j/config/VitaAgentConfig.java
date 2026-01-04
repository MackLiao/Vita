package com.healthcare.java.ai.langchain4j.config;

import com.healthcare.java.ai.langchain4j.store.MongoChatMemoryStore;
import com.mongodb.client.MongoClient;
import dev.langchain4j.community.model.dashscope.QwenTokenizer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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

        Document document = FileSystemDocumentLoader.loadDocument("RAGDocuments/MayoClinic.md");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //QwenTokenizer tokenizer = new QwenTokenizer(System.getenv("DASH_SCOPE_API_KEY"), "qwen-max");

        DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                new HuggingFaceTokenizer());


        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentSplitter)
                .build()
                .ingest(document);

        return EmbeddingStoreContentRetriever.from(embeddingStore);

    }


}
