package com.solix.supportai.qdrant;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class QdrantServiceImpl implements QdrantService {

    private final RestClient restClient;

    public QdrantServiceImpl(RestClient restClient) {

        this.restClient = restClient;

    }

    @Override
    public void createCollection(String collectionName) {

        Map<String, Object> body = Map.of(
                "vectors",
                Map.of(
                        "size", 768,
                        "distance", "Cosine"
                )
        );

        restClient.put()
                .uri("/collections/{name}", collectionName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

    }
        @Override
        public boolean collectionExists(String collectionName) {

            try {

                restClient.get()
                        .uri("/collections/{name}", collectionName)
                        .retrieve()
                        .toBodilessEntity();

                return true;

            } catch (Exception ex) {

                return false;

            }

        }
    @Override
    public void upsertPoint(String collectionName,
                            long pointId,
                            List<Double> vector,
                            Map<String, Object> payload) {

        Map<String, Object> point = Map.of(
                "id", pointId,
                "vector", vector,
                "payload", payload
        );

        Map<String, Object> body = Map.of(
                "points", List.of(point)
        );

        restClient.put()
                .uri("/collections/{name}/points", collectionName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

    }
    @Override
    public int getPointCount(String collectionName) {

        Map<?, ?> response = restClient.get()
                .uri("/collections/{name}", collectionName)
                .retrieve()
                .body(Map.class);

        Map<?, ?> result = (Map<?, ?>) response.get("result");

        Map<?, ?> points = (Map<?, ?>) result.get("points_count");

        if (points == null) {
            Object count = result.get("points_count");
            return count == null ? 0 : ((Number) count).intValue();
        }

        return ((Number) points.get("count")).intValue();
    }

    }
