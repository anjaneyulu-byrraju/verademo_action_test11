package com.veracode.verademo.commands;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {

    public static void main(String[] args) {
        String filePath = "example.txt";
        readFile(filePath);
    }

    public static void readFile(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        // Vulnerability: Reader is not closed in the 'finally' block or using try-with-resources
    }
}
