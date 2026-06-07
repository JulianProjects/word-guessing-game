package logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class WordGenerator {

    private static final SecureRandom RNG = new SecureRandom();

    public static String randomWord() {
        List<String> words = WordChecker.allWords();
        if (words == null || words.isEmpty()) {
            return "APFEL";
        }
        return words.get(RNG.nextInt(words.size()));
    }
}
