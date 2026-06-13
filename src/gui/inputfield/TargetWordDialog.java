package gui.inputfield;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import logic.AppConfig;
import logic.WordChecker;

final class TargetWordDialog {

  private TargetWordDialog() {}

  static boolean openAndSet(SixRowWordInput host) {
    Window owner = SwingUtilities.getWindowAncestor(host);
    JDialog dialog =
        new JDialog(
            owner,
            "Set new target word",
            Dialog.ModalityType.APPLICATION_MODAL);

    // Build the input section.
    JPanel container = new JPanel(new BorderLayout(12, 12));
    container.setBorder(new EmptyBorder(12, 12, 12, 12));

    JPanel formPanel = new JPanel(new BorderLayout(8, 8));
    JLabel inputLabel =
        new JLabel("Target word (" + AppConfig.WORD_LENGTH + " letters):");
    JTextField inputField = new JTextField(AppConfig.WORD_LENGTH);

    formPanel.add(inputLabel, BorderLayout.NORTH);
    formPanel.add(inputField, BorderLayout.CENTER);

    // The error label remains visible to prevent the layout from shifting.
    JLabel errorLabel = new JLabel(" ");
    errorLabel.setForeground(new Color(0xB00020));
    errorLabel.setBorder(new EmptyBorder(6, 0, 0, 0));

    JPanel lowerPanel = new JPanel(new BorderLayout(8, 8));
    lowerPanel.add(errorLabel, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    buttonPanel.add(cancelButton);
    buttonPanel.add(okButton);
    lowerPanel.add(buttonPanel, BorderLayout.SOUTH);

    container.add(formPanel, BorderLayout.CENTER);
    container.add(lowerPanel, BorderLayout.SOUTH);

    // Configure the modal dialog before displaying it.
    dialog.setContentPane(container);
    dialog.getRootPane().setDefaultButton(okButton);
    dialog.pack();
    dialog.setSize(400, 200);
    dialog.setLocationRelativeTo(owner);

    // The array allows the listener to update the returned result.
    boolean[] accepted = {false};

    okButton.addActionListener(
        event -> {
          String chosenWord =
              inputField.getText() == null
                  ? ""
                  : inputField.getText().trim().toUpperCase(Locale.ROOT);

          if (chosenWord.length() != AppConfig.WORD_LENGTH) {
            errorLabel.setText(
                "Please enter exactly " + AppConfig.WORD_LENGTH + " letters.");
            inputField.requestFocusInWindow();
            return;
          }

          if (!WordChecker.check(chosenWord)) {
            errorLabel.setText("The word does not exist in the word list.");
            inputField.requestFocusInWindow();
            return;
          }

          host.targetWord = chosenWord;
          accepted[0] = true;
          dialog.dispose();
        });

    cancelButton.addActionListener(event -> dialog.dispose());
    dialog.setVisible(true);

    return accepted[0];
  }
}
