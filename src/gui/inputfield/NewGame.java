package gui.inputfield;

import logic.WordGenerator;

public class NewGame extends SixRowWordInput {

  private final SixRowWordInput host;

  public NewGame(SixRowWordInput host) {
    this.host = host;
  }

  public void run() {
    host.targetWord = WordGenerator.randomWord();
    new Reset(host).run();
  }
}