// File: gui/eingabefeld/TargetWordDialog.java
package gui.eingabefeld;

import logic.AppConfig;
import logic.WordChecker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Locale;

final class TargetWordDialog {
    private TargetWordDialog() {}

    static boolean openAndSet(SixRowWordInput host) {
        Window owner = SwingUtilities.getWindowAncestor(host);
        JDialog dialog = new JDialog(owner, "Neues Zielwort setzen", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel container = new JPanel(new BorderLayout(12, 12));
        container.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel(new BorderLayout(8, 8));
        JLabel label = new JLabel("Zielwort (" + AppConfig.WORD_LENGTH + " Buchstaben):");
        JTextField input = new JTextField(AppConfig.WORD_LENGTH);
        form.add(label, BorderLayout.NORTH);
        form.add(input, BorderLayout.CENTER);

        JLabel error = new JLabel(" ");
        error.setForeground(new Color(0xB00020));
        error.setBorder(new EmptyBorder(6, 0, 0, 0));

        JPanel south = new JPanel(new BorderLayout(8, 8));
        south.add(error, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Abbrechen");
        buttons.add(cancel);
        buttons.add(ok);
        south.add(buttons, BorderLayout.SOUTH);

        container.add(form, BorderLayout.CENTER);
        container.add(south, BorderLayout.SOUTH);

        dialog.setContentPane(container);
        dialog.getRootPane().setDefaultButton(ok);
        dialog.pack();
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(owner);

        final boolean[] accepted = {false};

        ok.addActionListener(ev -> {
            String chosen = input.getText() == null ? "" : input.getText().trim().toUpperCase(Locale.ROOT);
            if (chosen.length() != AppConfig.WORD_LENGTH) {
                error.setText("Bitte genau " + AppConfig.WORD_LENGTH + " Buchstaben eingeben.");
                input.requestFocusInWindow();
                return;
            }
            if (!WordChecker.check(chosen)) {
                error.setText("Das Wort existiert nicht in der Wörterliste.");
                input.requestFocusInWindow();
                return;
            }
            host.targetWord = chosen;
            accepted[0] = true;
            dialog.dispose();
        });

        cancel.addActionListener(ev -> dialog.dispose());
        dialog.setVisible(true);

        return accepted[0];
    }
}
