package ui;

import gui.button.ButtonsBar;
import gui.image.ImagePanel;
import gui.inputfield.SixRowWordInput;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.KeyEventDispatcher;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.AppConfig;

/** Builds and displays the main Wordle user interface. */
public class WordleUI {

  private JFrame frame;

  public void show() {
    // Configure the main application window.
    frame = new JFrame("Wordle");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    frame.add(HeaderFactory.create("Wordle"), BorderLayout.NORTH);

    // Build the center cards and retrieve the required component references.
    CenterCards.Refs references = CenterCards.build();
    JPanel centerPanel = references.centerPanel;
    ImagePanel homeImagePanel = references.homeImagePanel;
    SixRowWordInput wordInput = references.wordInput;
    JLabel hintLabel = references.hintLabel;

    frame.add(centerPanel, BorderLayout.CENTER);

    // References allow the button callbacks to be assigned after creation.
    AtomicReference<Runnable> showMainMenuReference = new AtomicReference<>();
    AtomicReference<Runnable> showGameOnlyReference = new AtomicReference<>();

    ButtonsBar buttonsBar =
        ButtonsFactory.create(
            wordInput,
            showGameOnlyReference,
            showMainMenuReference);

    frame.add(buttonsBar.getComponent(), BorderLayout.SOUTH);

    CardLayout centerLayout = (CardLayout) centerPanel.getLayout();

    // Configure navigation to the main menu.
    Runnable showMainMenu =
        () -> {
          buttonsBar.showMainButtons();
          centerLayout.show(centerPanel, AppConfig.CARD_IMAGE);
          homeImagePanel.load(AppConfig.HOME_IMAGE);
          hintLabel.setText(
              "<html>Guess the Wordle in 6 attempts.<br>"
                  + "Each attempt must be a valid 5-letter word.<br>"
                  + "The color of the tiles changes<br>"
                  + "to show how close your guess is to the target word.</html>");
          wordInput.reset();
        };

    // Configure navigation to the game view.
    Runnable showGameOnly =
        () -> {
          buttonsBar.showHomeButtonOnly();
          centerLayout.show(centerPanel, AppConfig.CARD_INPUT);
          wordInput.reset();
          hintLabel.setText(" ");
        };

    showMainMenuReference.set(showMainMenu);
    showGameOnlyReference.set(showGameOnly);

    // Register physical keyboard controls and remove them when the window closes.
    KeyEventDispatcher physicalKeyboardDispatcher =
        PhysicalKeyboard.register(wordInput);

    frame.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosed(WindowEvent event) {
            PhysicalKeyboard.unregister(physicalKeyboardDispatcher);
          }
        });

    // Refresh the layout whenever the window size changes.
    frame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent event) {
            frame.revalidate();
            frame.repaint();
          }
        });

    // Display the application in its initial main-menu state.
    showMainMenu.run();

    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}