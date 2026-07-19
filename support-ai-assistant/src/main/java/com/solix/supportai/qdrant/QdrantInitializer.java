package com.solix.supportai.qdrant;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QdrantInitializer {

    private static final Logger logger =
            LoggerFactory.getLogger(QdrantInitializer.class);

    private static final String COLLECTION_NAME =
            "support_tickets";

    private final QdrantService qdrantService;

    public QdrantInitializer(QdrantService qdrantService) {
        this.qdrantService = qdrantService;
    }

    @PostConstruct
    public void initialize() {

        if (qdrantService.collectionExists(COLLECTION_NAME)) {

            logger.info("Qdrant collection '{}' already exists.",
                    COLLECTION_NAME);

        } else {

            logger.info("Creating Qdrant collection '{}'.",
                    COLLECTION_NAME);

            qdrantService.createCollection(COLLECTION_NAME);

            logger.info("Collection created successfully.");

        }

    }

}