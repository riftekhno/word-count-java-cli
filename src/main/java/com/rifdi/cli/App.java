package com.rifdi.cli;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  throws Exception
    {
        System.out.println( "Hello World!" );
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());

        try {
            int count =0;
            File file = new File("./files/example1.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] bytesArray = new byte[(int)file.length()];
            fis.read(bytesArray);
            String s = new String(bytesArray);
            String [] data = s.split(" ");
            for (int i=0; i<data.length; i++) {
                count++;
            }
            System.out.println("Number of characters in the given file are " +count);
        } catch (IOException e) {
            e.printStackTrace();
            // handle exception correctly.
        }
    }
}



