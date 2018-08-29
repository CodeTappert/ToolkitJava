package Hauptmenu;

import Einheitenumrechner.Methoden;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Klasse zur Steuerung des Hauptmenues
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class HauptmenuController {

    @FXML
    Button BtnZahlensystemumrechner, BtnExit, BtnEinheitenumrechner;

    /**
     * Methode zum Beenden des Programms
     */
    @FXML
    public void BtnExit() {
        System.exit(0);
    }

    /**
     * Methode zum Aufrufen der Übersicht der Einheitenumrechner
     */
    @FXML
    public void BtnEinheitenumrechner() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Start.class.getResource("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml"));
        AnchorPane root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Start.openLayer(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode zum Aufrufen des Zahlensystemsumrechner
     */
    @FXML
    public void BtnZahlensystemumrechner() {
        Methoden.FXMLLoad("../Zahlensystemumrechner/Zahlenumrechner.fxml");
    }

    /**
     * Methode zum Aufrufen des Internetgeschwindigkeitsumrechners
     */
    @FXML
    public void BtnInternet() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Start.class.getResource("../Internetgeschwindigkeitsrechner/Internet.fxml"));
        AnchorPane root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Start.openLayer(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


