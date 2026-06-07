package logic;

import java.nio.file.*;
import java.util.*;
import java.io.IOException;

public class WordChecker {
    private static final Set<String> DICT = new HashSet<>();

    static {
        // Robustes Laden: 1) Classpath-Resource, 2) src/words.txt, 3) bin/words.txt
        try {
            java.io.InputStream in = WordChecker.class.getClassLoader().getResourceAsStream("words.txt");
            java.util.List<String> lines = null;
            if (in != null) {
                lines = new java.util.ArrayList<>();
                try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in))) {
                    String s;
                    while ((s = br.readLine()) != null) { lines.add(s); }
                }
            }
            if (lines == null) {
                java.nio.file.Path p1 = java.nio.file.Paths.get("src/words.txt");
                if (java.nio.file.Files.exists(p1)) {
                    lines = java.nio.file.Files.readAllLines(p1);
                }
            }
            if (lines == null) {
                java.nio.file.Path p2 = java.nio.file.Paths.get("bin/words.txt");
                if (java.nio.file.Files.exists(p2)) {
                    lines = java.nio.file.Files.readAllLines(p2);
                }
            }
            if (lines != null) {
                for (String line : lines) {
                    if (line != null && !line.isBlank()) {
                        DICT.add(line.trim().toUpperCase(java.util.Locale.ROOT));
                    }
                }
            } else {
                System.err.println("words.txt nicht gefunden (classpath/src/bin). Wörterbuch bleibt leer.");
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Laden von words.txt: " + e.getMessage());
        }
    }

    public static boolean check(String input) {
        if (input == null || input.isBlank()) return false;
        return DICT.contains(input.trim().toUpperCase());
    }

    public static List<String> allWords() {
        return new ArrayList<>(DICT);
    }
}
