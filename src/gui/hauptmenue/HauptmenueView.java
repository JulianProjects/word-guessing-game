package gui.hauptmenue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.bild.BildPanel;
import gui.button.ButtonsBar;

import java.awt.*;

public class HauptmenueView extends Hauptmenue {

    private final JPanel headerPanel;
    private final JLabel titleLabel;

    private final BildPanel homeImage;
    private final JPanel imageCard;

    private final JPanel southContainer;
    private final JLabel hintLabel;

    private final ButtonsBar buttons;

    public HauptmenueView(ButtonsBar buttons, double scaleFactor) {
        // Header
        titleLabel = new JLabel("Wordle");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 40f));
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        headerPanel.setBorder(new EmptyBorder(16, 20, 12, 20));
        headerPanel.add(titleLabel);

        // Center (Bild)
        homeImage = new BildPanel(scaleFactor);
        imageCard = new JPanel(new BorderLayout());
        imageCard.add(homeImage.getComponent(), BorderLayout.CENTER);

        // South (Hint + Buttons)
        hintLabel = new JLabel("", SwingConstants.CENTER);
        hintLabel.setFont(hintLabel.getFont().deriveFont(Font.PLAIN, 16f));
        hintLabel.setBorder(new EmptyBorder(6, 0, 6, 0));

        this.buttons = buttons;

        southContainer = new JPanel(new BorderLayout());
        southContainer.add(hintLabel, BorderLayout.NORTH);
        southContainer.add(buttons.getComponent(), BorderLayout.SOUTH);
    }

    @Override public JComponent getHeader()      { return headerPanel; }
    @Override public JComponent getCenterCard()  { return imageCard; }
    @Override public JComponent getSouth()       { return southContainer; }
    @Override public ButtonsBar getButtonsBar()  { return buttons; }

    @Override public void loadHomeImage(String path) { homeImage.load(path); }
    @Override public void setHintHtml(String html)   { hintLabel.setText(html); }
    @Override public void onResized()                { homeImage.onResize(); }

    public void setTitle(String text, float sizePt) {
        titleLabel.setText(text);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, sizePt));
    }
}
