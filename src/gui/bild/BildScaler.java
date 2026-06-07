package gui.bild;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BildScaler extends Bild {

    /**
     * Skaliert proportional in bestmöglicher Qualität auf die Zielgröße.
     * Zielbreite/-höhe sind bereits der "Box"-Fit.
     */
    public Image scaleHQ(BufferedImage src, int targetW, int targetH) {
        if (src == null || targetW <= 0 || targetH <= 0) return null;
        int type = src.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : src.getType();
        BufferedImage dst = new BufferedImage(targetW, targetH, type);
        Graphics2D g = dst.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,     RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(src, 0, 0, targetW, targetH, null);
        g.dispose();
        return dst;
    }

    @Override public javax.swing.JComponent getComponent() { return null; }
    @Override public void setImage(BufferedImage img) {}
    @Override public void load(String path) {}
    @Override public void onResize() {}
}
