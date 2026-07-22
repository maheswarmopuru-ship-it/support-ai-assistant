package com.solix.supportai.qdrant;

import java.util.List;
import java.util.Map;

public interface QdrantService {

    boolean collectionExists(String collectionName);

    void createCollection(String collectionName);

    void upsertPoint(String collectionName,
                     long pointId,
                     List<Double> vector,
                     Map<String, Object> payload);
    int getPointCount(String collectionName);
    List<String> searchSimilarTickets(
            String collectionName,
            List<Double> vector,
            int limit);

}