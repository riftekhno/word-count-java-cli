package com.rifdi.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * WordCountApp is a simple console application that computes
 * the word from a file and sorts by the number of occurrences.
 * The application takes a file of data as its single argument.
 */
public class WordCountApp {

  private static final Logger logger = LoggerFactory.getLogger(WordCountApp.class);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String filePath = null;

    while (true) {
      // Prompt the user to enter the file path
      logger.info("Enter the file path (e.g., /path/to/file.txt): ");
      filePath = scanner.nextLine();

      if (filePath.equals("exit")) {
        break;
      }

      try {
        // Validate the file path format
        validatePathFormat(filePath);

        // Read the content of the file
        String fileContent = readFileContent(filePath);

        // Log the file content
        logger.info("File Content:\n{}", fileContent);

        Map<String, Integer> wordCountMap = countWords(filePath);

        // Display the word counts
        displaySortedWordCounts(wordCountMap);

        // Set flag to exit the loop
        System.out.println("To end the session, enter 'exit'");
      } catch (IllegalArgumentException e) {
        // Log the error message
        logger.error("Invalid path format: {}", e.getMessage());
        // Print the error message and retry the input
        System.err.println("Invalid path format: " + e.getMessage());
      } catch (IOException e) {
        // Log the error message
        logger.error("Error reading the file: {}", e.getMessage());
        // Print the error message and retry the input
        System.err.println("Error reading the file: " + e.getMessage());
      }
    }

    // Close the scanner to avoid resource leaks
    scanner.close();
  }

  private static void validatePathFormat(String filePath) {
    // Add your own validation logic as needed
    // For simplicity, let's check if the path ends with ".txt"
    if (!filePath.endsWith(".txt")) {
      throw new IllegalArgumentException("File path should end with '.txt'");
    }
  }

  public static String readFileContent(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    }
    return content.toString();
  }

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

  private static void displaySortedWordCounts(Map<String, Integer> wordCountMap) {
    wordCountMap.entrySet()
        .stream()
        .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
        .forEach(entry -> logger.info("Word Counts: {}", entry.getKey() + ": " + entry.getValue()));
  }
}
