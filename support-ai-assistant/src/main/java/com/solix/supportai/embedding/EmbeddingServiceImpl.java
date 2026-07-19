package com.solix.supportai.embedding;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmbeddingServiceImpl implements EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingServiceImpl(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public List<Double> generateEmbedding(String text) {

        EmbeddingResponse response =
                embeddingModel.embedForResponse(List.of(text));

        float[] vector =
                response.getResults()
                        .get(0)
                        .getOutput();

        List<Double> embedding = new ArrayList<>();

        for (float value : vector) {
            embedding.add((double) value);
        }

        return embedding;
    }
}