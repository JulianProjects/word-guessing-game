package logic;

import java.security.SecureRandom;
import java.util.List;

/** Selects random words from the available word list. */
public class WordGenerator {

  private static final SecureRandom RNG = new SecureRandom();

  public static String randomWord() {
    List<String> words = WordChecker.allWords();

    // Use a fallback word when the word list cannot provide an entry.
    if (words == null || words.isEmpty()) {
      return "APFEL";
    }

    return words.get(RNG.nextInt(words.size()));
  }
}