package logic;

public class WordSplitter {

    public static char[] splitToChars(String word) {
        if (word == null) {
            return new char[0];
        }
        return word.toCharArray();
    }
}
