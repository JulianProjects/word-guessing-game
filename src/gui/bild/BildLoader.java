package gui.bild;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

/** Lädt BufferedImages von der Festplatte. */
public class BildLoader extends Bild {

    /** Lädt das Bild von Pfad. Wirf RuntimeException bei Fehler für einfache Handhabung. */
    public BufferedImage loadFile(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            if (img == null) throw new IllegalArgumentException("Kein unterstütztes Bildformat: " + path);
            return img;
        } catch (Exception ex) {
            throw new RuntimeException("Bild konnte nicht geladen werden: " + path + " -> " + ex.getMessage(), ex);
        }
    }

    // Abstrakte Methoden werden hier nicht genutzt (Hilfsklasse) -> No-ops
    @Override public javax.swing.JComponent getComponent() { return null; }
    @Override public void setImage(BufferedImage img) {}
    @Override public void load(String path) {}
    @Override public void onResize() {}
}
