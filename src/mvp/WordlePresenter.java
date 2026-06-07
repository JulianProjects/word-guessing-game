package mvp;

import javax.swing.SwingUtilities;


public class WordlePresenter {

    private final WordleView view;

    public WordlePresenter(WordleView view) {
        this.view = view;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> view.show());
    }
}
