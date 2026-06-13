package logic;

/** Converts words into arrays of individual characters. */
public class WordSplitter {

  public static char[] splitToChars(String word) {
    // Return an empty array when no word was provided.
    if (word == null) {
      return new char[0];
    }

    return word.toCharArray();
  }
}