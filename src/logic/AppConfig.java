package logic;

/** Zentrale App-Konfiguration. */
public final class AppConfig {
    public static final String HOME_IMAGE  = "home.png"; // Startbild
    public static final double SCALE_FACTOR = 0.8;       // Bild-Skalierung (0..1)
    public static final int WORD_LENGTH     = 5;         // Wortlänge (z. B. Wordle)
    public static final boolean USE_PHYSICAL_KEYBOARD = true; // echte Tastatur zulassen?

    // CardLayout-Namen
    public static final String CARD_IMAGE = "card_image";
    public static final String CARD_INPUT = "card_input";

    private AppConfig() {}
}
