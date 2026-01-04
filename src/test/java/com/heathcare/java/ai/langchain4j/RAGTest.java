package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import dev.langchain4j.community.model.dashscope.QwenTokenizer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

@SpringBootTest(classes = VitaApp.class)
public class RAGTest {
    @Test
    public void testReadDocument() {

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("path", pathMatcher, new TextDocumentParser());
        for (Document document : documents) {
            System.out.println("=======================");
            System.out.println(document.metadata());
            System.out.println(document.text());
        }
    }


    @Test
    public void testParsePDF() {
        Document document = FileSystemDocumentLoader.loadDocument(
                "hospitals.pdf",
                new ApachePdfBoxDocumentParser()
        );
        System.out.println(document);
    }



    @Test
    public void testReadDocumentAndStore() {

        Document document = FileSystemDocumentLoader.loadDocument("AI.md");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        System.out.println(embeddingStore);
    }


    @Test
    public void testDocumentSplitter() {

        Document document = FileSystemDocumentLoader.loadDocument("RAGDocuments/MayoClinic.md");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

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
    }


    @Test
    public void testTokenCount() {
        String text = "This is a text demo";
        UserMessage userMessage = UserMessage.userMessage(text);

        QwenTokenizer tokenizer = new QwenTokenizer(System.getenv("DASH_SCOPE_API_KEY"), "qwen-max");
        int count = tokenizer.estimateTokenCountInMessage(userMessage);
        System.out.println("token length:" + count);
    }


}