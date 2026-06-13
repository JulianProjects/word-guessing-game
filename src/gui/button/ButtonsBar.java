package gui.button;

import gui.keyboard.ButtonsFactory;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Displays the bottom button bar for playing, closing, and restarting the game. */
public class ButtonsBar extends Buttons {

  private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
  private final JButton playButton;
  private final JButton homeButton;
  private final JButton restartButton;
  private final ButtonsVisibility visibility;

  public ButtonsBar() {
    panel.setBorder(new EmptyBorder(6, 0, 14, 0));

    ButtonsFactory factory = new ButtonsFactory();

    playButton = factory.create("Play", this::onPlay);
    homeButton = factory.create("Close game", this::onHome);
    restartButton = factory.create("Restart", this::onRestart);

    panel.add(playButton);
    panel.add(homeButton);
    panel.add(restartButton);

    visibility = new ButtonsVisibility(playButton, homeButton, restartButton);
    visibility.showMainButtons();
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  public void showMainButtons() {
    visibility.showMainButtons();
  }

  public void showHomeButtonOnly() {
    visibility.showHomeButtonOnly();
  }

  @Override
  public void onPlay() {
  }

  @Override
  public void onHome() {
  }

  @Override
  public void onRestart() {
  }
}
