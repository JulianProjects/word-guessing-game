package gui.image;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Displays an image and scales it proportionally when the panel is resized. */
public class ImagePanel extends ImageComponent {

  private final JPanel root = new JPanel(new BorderLayout());
  private final JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
  private final double scaleFactor;
  private final ImageLoader loader = new ImageLoader();
  private final ImageScaler scaler = new ImageScaler();

  private BufferedImage originalImage;

  /**
   * Creates an image panel with the specified scale factor.
   *
   * @param scaleFactor the factor applied to the available panel size
   */
  public ImagePanel(double scaleFactor) {
    this.scaleFactor = scaleFactor;

    root.add(imageLabel, BorderLayout.CENTER);
    root.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent event) {
            onResize();
          }
        });
  }

  @Override
  public JComponent getComponent() {
    return root;
  }

  @Override
  public void setImage(BufferedImage image) {
    originalImage = image;
    onResize();
  }

  @Override
  public void load(String path) {
    try {
      setImage(loader.loadFile(path));
      imageLabel.setText(null);
    } catch (RuntimeException exception) {
      originalImage = null;
      imageLabel.setIcon(null);
      imageLabel.setText(
          "<html><div style='color:#a00; text-align:center;'>"
              + "Image could not be loaded:<br>"
              + path
              + "<br>"
              + exception.getMessage()
              + "</div></html>");
    }
  }

  @Override
  public void onResize() {
    if (originalImage == null) {
      return;
    }

    int availableWidth = (int) (imageLabel.getWidth() * scaleFactor);
    int availableHeight = (int) (imageLabel.getHeight() * scaleFactor);

    if (availableWidth <= 0 || availableHeight <= 0) {
      return;
    }

    double imageAspectRatio =
        (double) originalImage.getWidth() / originalImage.getHeight();
    double containerAspectRatio = (double) availableWidth / availableHeight;

    int targetWidth;
    int targetHeight;

    if (imageAspectRatio > containerAspectRatio) {
      targetWidth = availableWidth;
      targetHeight = (int) (availableWidth / imageAspectRatio);
    } else {
      targetHeight = availableHeight;
      targetWidth = (int) (availableHeight * imageAspectRatio);
    }

    Image scaledImage =
        scaler.scaleHQ(originalImage, targetWidth, targetHeight);

    imageLabel.setIcon(
        scaledImage == null ? null : new ImageIcon(scaledImage));
    imageLabel.setText(null);

    root.revalidate();
    root.repaint();
  }
}
