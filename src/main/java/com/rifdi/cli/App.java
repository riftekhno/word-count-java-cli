package com.rifdi.cli;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        String fileName = "./files/example1.txt"; // Replace with your file path
        try {
            Map<String, Integer> wordCountMap = countWords(fileName);
            displaySortedWordCounts(wordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> countWords(String fileName) throws IOException {
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

    private static void displaySortedWordCounts(Map<String, Integer> wordCountMap) {
        wordCountMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue())
                );
    }
}




