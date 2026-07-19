package com.solix.supportai.embedding;

import java.util.List;

public interface EmbeddingService {
    List<Double> generateEmbedding(String text);
}
