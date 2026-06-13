package gui.inputfield;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

final class CellFactory {

  private CellFactory() {}

  static JLabel createCell() {
    JLabel label = new JLabel("", SwingConstants.CENTER);

    label.setOpaque(true);
    label.setFont(label.getFont().deriveFont(Font.BOLD, Theme.FONT_PT));
    label.setPreferredSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
    label.setMinimumSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
    label.setMaximumSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
    label.setBackground(Theme.CELL_BG_DEFAULT);
    label.setForeground(Color.BLACK);
    label.setBorder(
        new CompoundBorder(
            new LineBorder(Theme.CELL_BD_DEFAULT, 2, true),
            new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)));

    return label;
  }
}
