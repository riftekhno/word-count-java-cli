package com.rifdi.cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountWords {

  public static Map<String, Integer> countWords(String fileName) throws IOException {
    Map<String, Integer> wordCountMap = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Split the line into words using space as a delimiter
        String[] words = line.split("\\s+");

        // Count the occurrences of each word
        for (String word : words) {
          // Remove punctuation and convert to lowercase for better matching
          word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

          if (!word.isEmpty()) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
          }
        }
      }
    }

    return wordCountMap;
  }
}
