// File: gui/eingabefeld/Theme.java
package gui.eingabefeld;

import java.awt.Color;

final class Theme {
    private Theme() {}

    // Raster
    public static final int ROWS = 6;

    // Optik / Layout
    public static final int CELL_W  = 64;
    public static final int CELL_H  = 64;
    public static final int PAD_H   = 8;
    public static final int PAD_V   = 4;
    public static final int GAP     = 8;
    public static final float FONT_PT = 28f;

    // Farben
    public static final Color CELL_BG_DEFAULT = new Color(0xECECEC);
    public static final Color CELL_BG_GREEN   = new Color(0x6AAA64);
    public static final Color CELL_BG_YELLOW  = new Color(0xC9B458);
    public static final Color CELL_BG_ABSENT  = new Color(0x787C7E);
    public static final Color CELL_BD_DEFAULT = new Color(0xBCC0C4);
    public static final Color CELL_BD_GREEN   = new Color(0x4E8B4D);
    public static final Color CELL_BD_YELLOW  = new Color(0xA9963E);
    public static final Color CELL_BD_ABSENT  = new Color(0x5F6368);
}
