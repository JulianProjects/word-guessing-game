package gui.image;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/** Base class for image components. */
public abstract class ImageComponent {

  public abstract JComponent getComponent();

  public abstract void setImage(BufferedImage image);

  public abstract void load(String path);

  public abstract void onResize();
}