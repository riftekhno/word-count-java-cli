package com.rifdi.cli;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class WordCountAppTest {

    private static final String VALID_FILE_PATH = "./files/test.txt";
    private static final String INVALID_FILE_PATH = "/path/to/invalid/file.doc";
    private static final String EMPTY_FILE_PATH = "./files/empty.txt";

    @Before
    public void setUp() {
        // Any setup tasks, if needed
    }

    @Test
    public void validatePathFormat_validPath() {
        String validPath = "/path/to/file.txt";
        WordCountApp.validatePathFormat(validPath);
        // No exception should be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void validatePathFormat_invalidPath() {
        WordCountApp.validatePathFormat(INVALID_FILE_PATH);
    }

    @Test
    public void readFileContent_validFile() throws IOException {
        String content = ReadFile.readFileContent(VALID_FILE_PATH);
        assertNotNull(content);
        assertTrue(content.contains("The banana and apple"));
    }

    @Test(expected = IOException.class)
    public void readFileContent_invalidFile() throws IOException {
        ReadFile.readFileContent(INVALID_FILE_PATH);
    }

    @Test
    public void countWords_validFile() throws IOException {
        Map<String, Integer> wordCountMap = CountWords.countWords(VALID_FILE_PATH);
        assertNotNull(wordCountMap);
        assertEquals(9, wordCountMap.size());
        assertEquals(Integer.valueOf(2), wordCountMap.get("the"));
        assertEquals(Integer.valueOf(2), wordCountMap.get("banana"));
        assertEquals(Integer.valueOf(2), wordCountMap.get("apple"));
        assertEquals(Integer.valueOf(2), wordCountMap.get("and"));
        assertEquals(Integer.valueOf(2), wordCountMap.get("to"));
        assertEquals(Integer.valueOf(1), wordCountMap.get("market"));
        assertEquals(Integer.valueOf(1), wordCountMap.get("went"));
        assertEquals(Integer.valueOf(1), wordCountMap.get("buy"));
        assertEquals(Integer.valueOf(1), wordCountMap.get("mango"));
    }

    @Test
    public void countWords_emptyFile() throws IOException {
        Map<String, Integer> wordCountMap = CountWords.countWords(EMPTY_FILE_PATH);
        assertNotNull(wordCountMap);
        assertTrue(wordCountMap.isEmpty());
    }

    @Test(expected = IOException.class)
    public void countWords_invalidFile() throws IOException {
        CountWords.countWords(INVALID_FILE_PATH);
    }

    // Add more tests as needed for other methods
}
