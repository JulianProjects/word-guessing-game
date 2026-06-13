package gui.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class ImageScaler extends ImageComponent {

  /**
   * Scales an image to the specified dimensions using high-quality rendering.
   *
   * @param sourceImage the image to scale
   * @param targetWidth the target width
   * @param targetHeight the target height
   * @return the scaled image, or {@code null} if the input is invalid
   */
  public Image scaleHQ(
      BufferedImage sourceImage,
      int targetWidth,
      int targetHeight) {
    if (sourceImage == null || targetWidth <= 0 || targetHeight <= 0) {
      return null;
    }

    int imageType =
        sourceImage.getType() == 0
            ? BufferedImage.TYPE_INT_ARGB
            : sourceImage.getType();

    BufferedImage scaledImage =
        new BufferedImage(targetWidth, targetHeight, imageType);

    Graphics2D graphics = scaledImage.createGraphics();
    graphics.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics.setRenderingHint(
        RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
    graphics.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    graphics.drawImage(
        sourceImage,
        0,
        0,
        targetWidth,
        targetHeight,
        null);
    graphics.dispose();

    return scaledImage;
  }

  @Override
  public JComponent getComponent() {
    return null;
  }

  @Override
  public void setImage(BufferedImage image) {
  }

  @Override
  public void load(String path) {
  }

  @Override
  public void onResize() {
  }
}