package main;

import mvp.WordlePresenter;
import mvp.WordleSwingView;
import ui.LookAndFeelUtil;

/** Starts the Wordle application. */
public class Main {

  public static void main(String[] args) {
    // Configure the interface before creating the application's MVP components.
    LookAndFeelUtil.applyPreferred();
    new WordlePresenter(new WordleSwingView()).start();
  }
}