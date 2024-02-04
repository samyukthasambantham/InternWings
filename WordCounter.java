import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static Map<String, Integer> countWords(String text) {
        // Split the text into an array of words using space or punctuation as delimiters
        String[] words = text.toLowerCase().split("\\W+");

        // Count the words
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount;
    }

    public static void displayWordCount(Map<String, Integer> wordCount) {
        // Display the total count of words to the user
        int totalWords = wordCount.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total words: " + totalWords);

        // Display the frequency of each word
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }

        // Display the number of unique words
        int uniqueWords = wordCount.size();
        System.out.println("Unique words: " + uniqueWords);
    }

    public static String getUserInput() {
        try (// Prompt the user to enter text or provide a file
        Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter text or type 'file' to choose a file: ");
            String userInput = scanner.nextLine();

            // Handle file input
            if ("file".equalsIgnoreCase(userInput)) {
                System.out.print("Choose a text file: ");
                String filePath = scanner.nextLine();
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    StringBuilder text = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        text.append(line).append("\n");
                    }
                    return text.toString();
                } catch (IOException e) {
                    System.out.println("Error reading file. Please try again.");
                    return getUserInput();
                }
            }

            return userInput;
        }
    }

    public static void main(String[] args) {
        // Get user input
        String inputText = getUserInput();

        // Validate if input is empty
        if (inputText.trim().isEmpty()) {
            System.out.println("Input is empty. Please provide text or choose a valid file.");
            return;
        }

        // Count words
        Map<String, Integer> wordCount = countWords(inputText);

        // Display word count and additional statistics
        displayWordCount(wordCount);
    }
}
