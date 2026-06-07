package gui.tastatur;

public abstract class Tastatur {
    public abstract void onLetter(char c);
    public abstract void onEnter();
    public abstract void onBackspace();
}
