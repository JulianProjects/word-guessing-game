package gui.mainmenu;

import gui.button.ButtonsBar;
import gui.image.ImagePanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenuView extends MainMenu {

  private final JPanel headerPanel;
  private final JLabel titleLabel;
  private final ImagePanel homeImagePanel;
  private final JPanel imageCard;
  private final JPanel southContainer;
  private final JLabel hintLabel;
  private final ButtonsBar buttonsBar;

  public MainMenuView(ButtonsBar buttonsBar, double scaleFactor) {
    // Build the title section at the top of the main menu.
    titleLabel = new JLabel("Wordle");
    titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 40.0f));

    headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    headerPanel.setBorder(new EmptyBorder(16, 20, 12, 20));
    headerPanel.add(titleLabel);

    // Place the home image inside the center card.
    homeImagePanel = new ImagePanel(scaleFactor);

    imageCard = new JPanel(new BorderLayout());
    imageCard.add(homeImagePanel.getComponent(), BorderLayout.CENTER);

    hintLabel = new JLabel("", SwingConstants.CENTER);
    hintLabel.setFont(hintLabel.getFont().deriveFont(Font.PLAIN, 16.0f));
    hintLabel.setBorder(new EmptyBorder(6, 0, 6, 0));

    this.buttonsBar = buttonsBar;

    // Combine the hint and button bar in the lower section.
    southContainer = new JPanel(new BorderLayout());
    southContainer.add(hintLabel, BorderLayout.NORTH);
    southContainer.add(buttonsBar.getComponent(), BorderLayout.SOUTH);
  }

  @Override
  public JComponent getHeader() {
    return headerPanel;
  }

  @Override
  public JComponent getCenterCard() {
    return imageCard;
  }

  @Override
  public JComponent getSouth() {
    return southContainer;
  }

  @Override
  public ButtonsBar getButtonsBar() {
    return buttonsBar;
  }

  @Override
  public void loadHomeImage(String path) {
    homeImagePanel.load(path);
  }

  @Override
  public void setHintHtml(String html) {
    hintLabel.setText(html);
  }

  @Override
  public void onResized() {
    homeImagePanel.onResize();
  }

  public void setTitle(String text, float sizeInPoints) {
    titleLabel.setText(text);
    titleLabel.setFont(
        titleLabel.getFont().deriveFont(Font.BOLD, sizeInPoints));
  }
}