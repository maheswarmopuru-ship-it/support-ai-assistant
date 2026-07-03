package com.solix.supportai.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class StopWordLoader {

    private final Set<String> stopWords = new HashSet<>();

    public StopWordLoader() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    new ClassPathResource("stopwords.txt").getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {

                stopWords.add(line.trim().toUpperCase());

            }

            System.out.println("Stop Words Loaded : " + stopWords.size());

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public Set<String> getStopWords() {

        return stopWords;

    }

}