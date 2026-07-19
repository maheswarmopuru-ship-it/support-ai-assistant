package com.solix.supportai.controller;

import com.solix.supportai.embedding.EmbeddingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/embedding")
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    public EmbeddingController(
            EmbeddingService embeddingService) {

        this.embeddingService = embeddingService;

    }

    @PostMapping
    public List<Double> embedding(
            @RequestBody String text) {

        return embeddingService.generateEmbedding(text);

    }

}