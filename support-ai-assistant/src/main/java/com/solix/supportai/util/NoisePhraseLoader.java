package com.solix.supportai.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class NoisePhraseLoader {

    private final Set<String> noisePhrases = new HashSet<>();

    @PostConstruct
    public void loadNoisePhrases() {

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     getClass().getResourceAsStream("/noise-phrases.txt")))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    noisePhrases.add(line.trim().toLowerCase());

                }
            }

            System.out.println("Noise Phrases Loaded : " + noisePhrases.size());

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    public Set<String> getNoisePhrases() {

        return noisePhrases;

    }

}