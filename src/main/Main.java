// File: src/main/Main.java
package main;

import ui.LookAndFeelUtil;
import mvp.WordlePresenter;
import mvp.WordleSwingView;


public class Main {
    public static void main(String[] args) {
        LookAndFeelUtil.applyPreferred();
        new WordlePresenter(new WordleSwingView()).start();
    }
}
