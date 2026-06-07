package gui.bild;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

/**
 * UI-Panel, das ein Bild anzeigt und beim Resizen proportional mit Vorfaktor skaliert.
 * Nutzt intern BildLoader + BildScaler (beides Unterklassen von Bild).
 */
public class BildPanel extends Bild {

    private final JPanel root = new JPanel(new BorderLayout());
    private final JLabel imageLabel = new JLabel("", SwingConstants.CENTER);

    private final double scaleFactor;
    private BufferedImage original;            // Originalbild (nie überschreiben)

    // Hilfsklassen als "Unterklassen von Bild":
    private final BildLoader loader = new BildLoader();
    private final BildScaler scaler = new BildScaler();

    public BildPanel(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        root.add(imageLabel, BorderLayout.CENTER);

        // Bei Größenänderung neu skalieren
        root.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) { onResize(); }
        });
    }

    @Override
    public JComponent getComponent() {
        return root;
    }

    @Override
    public void setImage(BufferedImage img) {
        this.original = img;
        onResize();
    }

    @Override
    public void load(String path) {
        try {
            setImage(loader.loadFile(path));
            imageLabel.setText(null);
        } catch (RuntimeException ex) {
            this.original = null;
            imageLabel.setIcon(null);
            imageLabel.setText("<html><div style='color:#a00; text-align:center;'>"
                    + "Bild konnte nicht geladen werden:<br>" + path + "<br>"
                    + ex.getMessage() + "</div></html>");
        }
    }

    @Override
    public void onResize() {
        if (original == null) return;

        int availW = (int) (imageLabel.getWidth() * scaleFactor);
        int availH = (int) (imageLabel.getHeight() * scaleFactor);
        if (availW <= 0 || availH <= 0) return;

        // Proportionalen Fit berechnen
        double imgAspect = (double) original.getWidth() / original.getHeight();
        double boxAspect = (double) availW / availH;

        int targetW, targetH;
        if (imgAspect > boxAspect) {
            targetW = availW;
            targetH = (int) (availW / imgAspect);
        } else {
            targetH = availH;
            targetW = (int) (availH * imgAspect);
        }

        Image scaled = scaler.scaleHQ(original, targetW, targetH);
        imageLabel.setIcon(scaled == null ? null : new ImageIcon(scaled));
        imageLabel.setText(null);
        root.revalidate();
        root.repaint();
    }
}
