package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Creates the header component displayed at the top of the application. */
public final class HeaderFactory {

  private HeaderFactory() {}

  public static JComponent create(String titleText) {
    // Create and style the title before adding it to the header panel.
    JLabel titleLabel = new JLabel(titleText);
    titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 40.0f));

    JPanel headerPanel =
        new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
    headerPanel.add(titleLabel);

    return headerPanel;
  }
}