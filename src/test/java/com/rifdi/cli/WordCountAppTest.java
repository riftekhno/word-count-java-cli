package com.rifdi.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WordCountAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Mock
    private WordCountApp wordCountAppMock;

    @InjectMocks
    private WordCountApp wordCountApp;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testWordCountAppWithValidFile() throws IOException {
//        String fileName = "./files/example1.txt"; // Replace with your file path
//        ByteArrayInputStream in = new ByteArrayInputStream(fileName.getBytes());
//        System.setIn(in);
//
//        when(wordCountAppMock.readFileContent(anyString())).thenReturn("This is a sample text.");

        String sampleText = "This is a sample text.";

        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("sample", 1);
        wordCountMap.put("text", 1);
        wordCountMap.put("this", 1);
        wordCountMap.put("is", 1);
        wordCountMap.put("a", 1);

        when(wordCountAppMock.countWords(anyString())).thenReturn(wordCountMap);

        wordCountApp.main(new String[]{});

        assertEquals("Enter the file path (e.g., /path/to/file.txt): File Content:\nThis is a sample text.\n" +
                "sample: 1\ntext: 1\nthis: 1\nis: 1\na: 1\nTo end session enter \"exit\" \n", outContent.toString());
    }

    @Test
    public void testWordCountAppWithInvalidPathFormat() {
        String input = "/invalid/path\n/valid/path/file.txt\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        wordCountApp.main(new String[]{});

        assertEquals("Invalid path format: File path should end with '.txt'\r\n" +
                "Error reading the file: \\valid\\path\\file.txt (The system cannot find the path specified)\r\n", errContent.toString());
    }

    @Test
    public void testWordCountAppExitCommand() throws IOException {
        String input = "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        wordCountApp.main(new String[]{});

        assertEquals("Enter the file path (e.g., /path/to/file.txt): \r\n", outContent.toString());
        assertEquals("", errContent.toString());
    }

    // Add more tests as needed
}
