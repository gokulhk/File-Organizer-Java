package org.fileorganizergui;

import org.fileorganizerjava.App;

public class Main {
  public static void main(String[] args) {
    System.out.println("Main: Hello and welcome!");

    App app = new App();

    System.out.println(app.getGreeting());
  }
}
