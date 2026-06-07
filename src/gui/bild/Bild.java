package gui.bild;

import javax.swing.*;
import java.awt.image.BufferedImage;

/** Abstrakte Basis für Bild-Komponenten. */
public abstract class Bild {
    /** UI-Komponente (Panel o. ä.), die angezeigt werden soll. */
    public abstract JComponent getComponent();

    /** Ein bereits geladenes Bild setzen. */
    public abstract void setImage(BufferedImage img);

    /** Bild von Pfad laden (z. B. "home.png"). */
    public abstract void load(String path);

    /** Auf Größenänderung reagieren (neu skalieren). */
    public abstract void onResize();
}
