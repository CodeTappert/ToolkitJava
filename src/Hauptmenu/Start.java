package Hauptmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasse zum Starten des Programms
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class Start extends Application {

    static Stage primaryStage;

    /**
     * Main Methode
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Methode um eine andere Scene in das Fenster zu laden. Aufgerufen von Einheitenumrechner.Methoden.FXMLLoad().
     * @see Einheitenumrechner.Methoden
     * @param scene Scene die geladen werden soll.
     */
    public static void openLayer(Scene scene) {
        primaryStage.setScene(scene);
    }

    /**
     * Methode die die Parameter des Startfensters festlegt und das Hauptmenü lädt.
     */
    public void start(Stage Stage) {
        primaryStage = Stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Hauptmenu.fxml"));
        try {
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // primaryStage.setResizable(false);
            primaryStage.setTitle("Toolkit");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
