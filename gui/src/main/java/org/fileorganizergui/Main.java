package org.fileorganizergui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fileorganizerjava.App;

public class Main extends Application {
  public static void main(String[] args) {
    System.out.println("Main: Hello and welcome!");
    App app = new App();
    System.out.println(app.getGreeting());
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("File Oraganizer");

    ProgressBar progressBar = new ProgressBar(0);

    VBox vBox = new VBox(progressBar);
    Scene scene = new Scene(vBox, 960, 600);

    primaryStage.setScene(scene);
    primaryStage.show();

    Thread taskThread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                double progress = 0;
                for (int i = 0; i < 10; i++) {
                  try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  progress += 0.1;

                  double reportedProgress = progress;
                  Platform.runLater(
                      new Runnable() {
                        @Override
                        public void run() {
                          progressBar.setProgress(reportedProgress);
                        }
                      });
                }
              }
            });

    taskThread.start();
  }
}
