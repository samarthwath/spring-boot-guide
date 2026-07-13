package com.example.multimodel.config;

import io.qdrant.client.QdrantClient;
import org.springframework.ai.chat.cache.semantic.SemanticCache;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.ai.vectorstore.redis.cache.semantic.DefaultSemanticCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.RedisClient;

@Configuration
public class SemanticCacheConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String host;
    @Value("${spring.data.redis.port:6379}")
    private int port;

    @Bean
    public RedisClient redisClient() {
        return
                RedisClient
                        .builder()
                        .hostAndPort(host, port)
                        .build();
    }

    @Bean("cacheVectorStore")
    public VectorStore vectorStore(QdrantClient qdrantClient, EmbeddingModel embeddingModel) {
        return
                QdrantVectorStore
                        .builder(qdrantClient, embeddingModel)
                        .collectionName("eazybytes-semantic-cache")
                        .initializeSchema(true)
                        .build();
    }

    @Bean
    public SemanticCache semanticCache(RedisClient redisClient, EmbeddingModel embeddingModel) {
        return
                DefaultSemanticCache
                        .builder()
                        .embeddingModel(embeddingModel)
                        .jedisClient(redisClient)
                        .similarityThreshold(0.9)
                        .indexName("eazy-bytes-semantic-cache")
                        .prefix("cache:")
                        .build();
    }

    @Bean("qdrantSemanticCache")
    public SemanticCache qdrantSemanticCache(@Qualifier("cacheVectorStore") VectorStore vectorStore, EmbeddingModel embeddingModel) {
        return
                DefaultSemanticCache
                        .builder()
                        .embeddingModel(embeddingModel)
                        .vectorStore(vectorStore)
                        .similarityThreshold(0.8)
                        .build();
    }


    @Bean
    public SemanticCacheAdvisor semanticCacheAdvisor(SemanticCache semanticCache) {
        return
                SemanticCacheAdvisor
                        .builder()
                        .cache(semanticCache)
                        .build();
    }

    @Bean("qdrantSemanticCacheAdvisor")
    public SemanticCacheAdvisor qdrantSemanticCacheAdvisor(@Qualifier("qdrantSemanticCache") SemanticCache semanticCache) {
        return
                SemanticCacheAdvisor
                        .builder()
                        .cache(semanticCache)
                        .build();
    }
}
