package gui.hauptmenue;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.tastatur.Tastatur;

import java.awt.*;

public class TextFieldStyler extends Tastatur {

    private static final Color BG     = new Color(0x121213);
    private static final Color FG     = Color.WHITE;
    private static final Color BORDER = new Color(0x3A3A3C);

    public static void applyWordleLook(JTextField tf) {
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setFont(tf.getFont().deriveFont(Font.BOLD, 28f));
        tf.setForeground(FG);
        tf.setBackground(BG);
        tf.setCaretColor(BG);
        tf.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 2, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }

    @Override public void onLetter(char c) {}
    @Override public void onEnter() {}
    @Override public void onBackspace() {}
}
