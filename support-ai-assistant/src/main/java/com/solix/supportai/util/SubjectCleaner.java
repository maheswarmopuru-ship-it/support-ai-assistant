package com.solix.supportai.util;

import org.springframework.stereotype.Component;

@Component
public class SubjectCleaner {

    private final StopWordLoader stopWordLoader;

    public SubjectCleaner(StopWordLoader stopWordLoader) {
        this.stopWordLoader = stopWordLoader;
    }

    public String clean(String subject) {

        if (subject == null) {
            return "";
        }

        String cleaned = subject;

        cleaned = cleaned.replaceAll("\\[.*?\\]", " ");

        cleaned = cleaned.replace("|", " ");

        cleaned = cleaned.replaceAll("\\s+", " ");

        StringBuilder builder = new StringBuilder();

        for (String word : cleaned.split(" ")) {

            if (!stopWordLoader.getStopWords().contains(word.toUpperCase())) {

                builder.append(word).append(" ");

            }

        }

        return builder.toString().trim();

    }

}