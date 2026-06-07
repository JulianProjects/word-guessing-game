// File: src/gui/app/HeaderFactory.java
package ui;

import javax.swing.*;
import java.awt.*;

public final class HeaderFactory {
    private HeaderFactory() {}

    public static JComponent create(String titleText) {
        JLabel title = new JLabel(titleText);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 40f));
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        header.add(title);
        return header;
    }
}
