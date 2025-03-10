package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Zephyr using FXML.
 */
public class Main extends Application {

    private Zephyr zephyr = new Zephyr("data/tasks.md");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Zephyr | thou Personal Assistant");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setZephyr(zephyr);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
