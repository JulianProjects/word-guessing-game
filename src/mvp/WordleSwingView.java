package mvp;

import ui.WordleUI;


public class WordleSwingView implements WordleView {
    private final WordleUI ui;

    public WordleSwingView() {
        this.ui = new WordleUI();
    }

    @Override
    public void show() {
        ui.show();
    }
}
