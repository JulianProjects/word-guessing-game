package gui.image;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/** Loads buffered images from the file system. */
public class ImageLoader extends ImageComponent {

  /**
   * Loads an image from the specified path.
   *
   * @param path the path to the image
   * @return the loaded image
   */
  public BufferedImage loadFile(String path) {
    try {
      BufferedImage image = ImageIO.read(new File(path));

      if (image == null) {
        throw new IllegalArgumentException("Unsupported image format: " + path);
      }

      return image;
    } catch (Exception exception) {
      throw new RuntimeException(
          "Image could not be loaded: " + path + " -> " + exception.getMessage(),
          exception);
    }
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