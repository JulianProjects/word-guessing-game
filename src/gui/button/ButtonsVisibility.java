package gui.button;

import javax.swing.JButton;

/** Controls the visibility of the game buttons. */
public class ButtonsVisibility extends Buttons {

  private final JButton playButton;
  private final JButton homeButton;
  private final JButton restartButton;

  public ButtonsVisibility(
      JButton playButton,
      JButton homeButton,
      JButton restartButton) {
    this.playButton = playButton;
    this.homeButton = homeButton;
    this.restartButton = restartButton;
  }

  public void showMainButtons() {
    playButton.setVisible(true);
    homeButton.setVisible(false);
    restartButton.setVisible(false);
  }

  public void showHomeButtonOnly() {
    playButton.setVisible(false);
    homeButton.setVisible(true);
    restartButton.setVisible(true);
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