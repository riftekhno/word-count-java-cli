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

    while (true) {
      // Prompt the user to enter the file path
      logger.info("Enter the file path (e.g., /path/to/file.txt): ");
      String filePath = scanner.nextLine();

      if (filePath.equals("exit")) {
        break;
      }

      try {
        // Validate the file path format
        validatePathFormat(filePath);

        // Read the content of the file
        String fileContent = ReadFile.readFileContent(filePath);

        // Log the file content
        logger.info("File Content:\n{}", fileContent);

        Map<String, Integer> wordCountMap = CountWords.countWords(filePath);

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

  public static void validatePathFormat(String filePath) {
    // Add your own validation logic as needed
    // For simplicity, let's check if the path ends with ".txt"
    if (!filePath.endsWith(".txt")) {
      throw new IllegalArgumentException("File path should end with '.txt'");
    }
  }


  public static void displaySortedWordCounts(Map<String, Integer> wordCountMap) {
    wordCountMap.entrySet()
        .stream()
        .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
        .forEach(entry -> logger.info("Word Counts: {}", entry.getKey() + ": " + entry.getValue()));
  }
}
