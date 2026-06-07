package gui.hauptmenue;

import javax.swing.*;

import gui.button.ButtonsBar;

public abstract class Hauptmenue {
    public abstract JComponent getHeader();       // oben (Titelzeile)
    public abstract JComponent getCenterCard();   // Center-Karte mit dem Bild
    public abstract JComponent getSouth();        // unten (Hint + Buttons)
    public abstract ButtonsBar getButtonsBar();   // Zugriff auf die Buttons-Leiste

    public abstract void loadHomeImage(String path); // Bild laden/anzeigen
    public abstract void setHintHtml(String html);   // Beschreibung setzen (HTML erlaubt)
    public abstract void onResized();                // bei Größenänderung nachskalieren
}
