package logic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/** Loads and checks words from the application's word list. */
public class WordChecker {

  private static final Set<String> DICTIONARY = new HashSet<>();

  static {
    try {
      List<String> lines = null;

      // Load from the classpath before checking the source and binary directories.
      InputStream inputStream =
          WordChecker.class.getClassLoader().getResourceAsStream("words.txt");

      if (inputStream != null) {
        lines = new ArrayList<>();

        try (BufferedReader reader =
            new BufferedReader(new InputStreamReader(inputStream))) {
          String line;

          while ((line = reader.readLine()) != null) {
            lines.add(line);
          }
        }
      }

      if (lines == null) {
        Path sourcePath = Paths.get("src/words.txt");

        if (Files.exists(sourcePath)) {
          lines = Files.readAllLines(sourcePath);
        }
      }

      if (lines == null) {
        Path binaryPath = Paths.get("bin/words.txt");

        if (Files.exists(binaryPath)) {
          lines = Files.readAllLines(binaryPath);
        }
      }

      // Normalize valid entries before adding them to the dictionary.
      if (lines != null) {
        for (String line : lines) {
          if (line != null && !line.isBlank()) {
            DICTIONARY.add(line.trim().toUpperCase(Locale.ROOT));
          }
        }
      } else {
        System.err.println(
            "words.txt was not found in the classpath, src, or bin directory.");
      }
    } catch (Exception exception) {
      System.err.println(
          "Failed to load words.txt: " + exception.getMessage());
    }
  }

  public static boolean check(String input) {
    if (input == null || input.isBlank()) {
      return false;
    }

    return DICTIONARY.contains(input.trim().toUpperCase());
  }

  public static List<String> allWords() {
    return new ArrayList<>(DICTIONARY);
  }
}