package com.solix.supportai.knowledge;

import com.solix.supportai.util.NoisePhraseLoader;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class KnowledgeExtractionServiceImpl
        implements KnowledgeExtractionService {
    private static final Logger logger =
            LoggerFactory.getLogger(KnowledgeExtractionServiceImpl.class);
    private final NoisePhraseLoader noisePhraseLoader;

    public KnowledgeExtractionServiceImpl(
            NoisePhraseLoader noisePhraseLoader) {

        this.noisePhraseLoader = noisePhraseLoader;

    }

    @Override
    public String extractTechnicalKnowledge(String supportComments) {

        if (supportComments == null || supportComments.isBlank()) {
            return "";
        }

        StringBuilder technicalNotes = new StringBuilder();

        String[] lines = supportComments.split("\\|");

        for (String line : lines) {

            String currentLine = line.trim();

            if (currentLine.isEmpty()) {
                continue;
            }

            boolean isNoise = false;

            for (String phrase : noisePhraseLoader.getNoisePhrases()) {

                if (currentLine.toLowerCase().contains(phrase)) {
                    isNoise = true;
                    break;
                }
            }

            if (!isNoise) {
                technicalNotes.append(currentLine)
                        .append("\n");
            }
        }
        logger.debug("Technical knowledge extracted successfully.");
        return technicalNotes.toString().trim();
    }
}