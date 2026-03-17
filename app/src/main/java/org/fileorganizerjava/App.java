package org.fileorganizerjava;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println("Application started.");
    new DriverClass().showMenu();
    System.out.println("Application terminated.");
  }
}
