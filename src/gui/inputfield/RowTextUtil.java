package gui.inputfield;

import logic.AppConfig;

final class RowTextUtil {

  private RowTextUtil() {}

  static String rowText(SixRowWordInput host, int row) {
    StringBuilder wordBuilder = new StringBuilder(AppConfig.WORD_LENGTH);

    // Read each cell and build the complete word for the selected row.
    for (int column = 0; column < AppConfig.WORD_LENGTH; column++) {
      String cellText = host.cells[row][column].getText();
      wordBuilder.append(
          cellText == null || cellText.isEmpty() ? ' ' : cellText.charAt(0));
    }

    return wordBuilder.toString();
  }
}