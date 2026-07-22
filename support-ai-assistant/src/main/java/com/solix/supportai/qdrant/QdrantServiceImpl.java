package com.solix.supportai.qdrant;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.HashMap;

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

        Object pointsCount = result.get("points_count");

        if (pointsCount instanceof Number number) {
            return number.intValue();
        }

        if (pointsCount instanceof Map<?, ?> pointsMap) {

            Object count = pointsMap.get("count");

            if (count instanceof Number number) {
                return number.intValue();
            }
        }

        return 0;
    }
    @Override
    public List<String> searchSimilarTickets(
            String collectionName,
            List<Double> vector,
            int limit) {

        Map<String, Object> request = new HashMap<>();
        request.put("vector", vector);
        request.put("limit", limit);
        request.put("with_payload", true);

        Map<String, Object> response = restClient.post()
                .uri("/collections/{collectionName}/points/search", collectionName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(Map.class);

        List<Map<String, Object>> result =
                (List<Map<String, Object>>) response.get("result");

        List<String> ticketIds = new ArrayList<>();

        if (result != null) {

            for (Map<String, Object> point : result) {

                Map<String, Object> payload =
                        (Map<String, Object>) point.get("payload");

                if (payload != null && payload.containsKey("ticketId")) {

                    ticketIds.add(payload.get("ticketId").toString());

                }
            }
        }

        return ticketIds;
    }
    }
