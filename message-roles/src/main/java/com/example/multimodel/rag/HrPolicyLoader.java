package com.example.multimodel.rag;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrPolicyLoader {

    private static final Logger LOG = LoggerFactory.getLogger(HrPolicyLoader.class);

    private final VectorStore vectorStore;

    @Value("classpath:Eazybytes_HR_Policies.pdf")
    Resource eazyBytesHrPolicies;

    public HrPolicyLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadHrPolicyToVectorStore() {
        SearchRequest searchRequest = SearchRequest.builder().query("test").topK(1).build();
        List<Document> documentList = vectorStore.similaritySearch(searchRequest);
        if (documentList.isEmpty()) {
            TextSplitter tokenTextSplitter = TokenTextSplitter
                    .builder()
                    .withChunkSize(200)
                    .withMaxNumChunks(400)
                    .build();
            TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(eazyBytesHrPolicies);
            List<Document> documents = tikaDocumentReader.get();
            List<Document> documentsSplited = tokenTextSplitter.split(documents);
            vectorStore.add(documentsSplited);
            LOG.info("Loaded {} documents", documentsSplited.size());
        } else {
            LOG.info("Documents are already present");
        }

    }

}
