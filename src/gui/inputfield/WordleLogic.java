package gui.inputfield;

import logic.AppConfig;

final class WordleLogic {

  private WordleLogic() {}

  enum Hit {
    GREEN,
    YELLOW,
    ABSENT
  }

  static Hit[] compareWordle(String guess, String target) {
    int wordLength = AppConfig.WORD_LENGTH;
    Hit[] results = new Hit[wordLength];
    boolean[] targetPositionsUsed = new boolean[wordLength];

    for (int index = 0; index < wordLength; index++) {
      results[index] = Hit.ABSENT;
    }

    // Mark letters that match at the same position first.
    for (int index = 0; index < wordLength; index++) {
      if (guess.charAt(index) == target.charAt(index)) {
        results[index] = Hit.GREEN;
        targetPositionsUsed[index] = true;
      }
    }

    // Check remaining letters without reusing target positions.
    for (int guessIndex = 0; guessIndex < wordLength; guessIndex++) {
      if (results[guessIndex] == Hit.GREEN) {
        continue;
      }

      char guessedLetter = guess.charAt(guessIndex);

      for (int targetIndex = 0; targetIndex < wordLength; targetIndex++) {
        if (targetPositionsUsed[targetIndex]) {
          continue;
        }

        if (guessedLetter == target.charAt(targetIndex)) {
          results[guessIndex] = Hit.YELLOW;
          targetPositionsUsed[targetIndex] = true;
          break;
        }
      }
    }

    return results;
  }

  static void colorizeRow(
      SixRowWordInput host, int row, String guess, String target) {
    Hit[] results = compareWordle(guess, target);

    for (int column = 0; column < AppConfig.WORD_LENGTH; column++) {
      switch (results[column]) {
        case GREEN:
          CellStyler.styleGreen(host, row, column);
          break;
        case YELLOW:
          CellStyler.styleYellow(host, row, column);
          break;
        case ABSENT:
          CellStyler.styleAbsent(host, row, column);
          break;
      }
    }
  }
}