package Einheitenumrechner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasse um die FXML Datei des Temperaturumrechners zu steuern.
 * <p></p>
 * Erklärung der Abkürzungen:
 * C = Celsius, F = Fahrenheit und K = Kelvin.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class TemperaturController implements Initializable {

    @FXML
    Button BtnBack, BtnCalc;
    @FXML
    TextField TFStart, TFC, TFF, TFK;
    @FXML
    Label LC, LF, LK;
    @FXML
    ComboBox CBEingabe;
    Object Celsius = "Celsius", Fahrenheit = "Fahrenheit", Kelvin = "Kelvin";
    ObservableList<String> options = FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin");

    /**
     * Methode welche den "Umrechnen" Knopf handelt.
     */
    @FXML
    public void BtnCalc() {
        Umrechnen(Double.parseDouble(TFStart.getText()));

    }

    /**
     * Methode um zur Übersicht zurückzukehren.
     *
     * @see Methoden
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
    }

    /**
     * Methode zum Umrechnen.
     * <p></p>
     * Hier wird mit Methoden.CheckIfDez erst überprüft ob es sich bei der Eingabe um eine Dezimalzahl handelt.
     * Danach wird überprüft von welchem Typ umgerechnet wird und entsprechend von diesem in die übrigen Beidden
     * umgerechnet.
     *
     * @param eingabe Der vom Benutzer eingegebene Wert als Double.
     * @see Methoden
     */
    public void Umrechnen(double eingabe) {
        if (Methoden.checkIfDez(TFStart.getText()) == 2) {
            TFC.setText(Methoden.KeineDez);
            TFK.setText(Methoden.KeineDez);
            TFF.setText(Methoden.KeineDez);
        } else if (Methoden.checkIfDez(TFStart.getText()) == 1) {
            if (CBEingabe.getValue().equals(Celsius)) {
                TFC.setText(String.valueOf(eingabe));
                TFK.setText(String.valueOf(eingabe + 273.15));
                double fahrenheit = eingabe * 1.8 + 32;
                fahrenheit = Math.round(fahrenheit * 100) / 100;
                TFF.setText(String.valueOf(fahrenheit));
            } else if (CBEingabe.getValue().equals(Fahrenheit)) {
                TFF.setText(String.valueOf(eingabe));
                double celsius = (eingabe - 32) / 1.8;
                celsius = Math.round(celsius * 100) / 100;
                TFC.setText(String.valueOf(celsius));
                double kelvin = (eingabe - 32) / 1.8 + 273.15;
                kelvin = Math.round(kelvin * 100) / 100;
                TFK.setText(String.valueOf(kelvin));
            } else if (CBEingabe.getValue().equals(Kelvin)) {
                TFK.setText(String.valueOf(eingabe));
                TFC.setText(String.valueOf(eingabe - 273.15));
                double fahrenheit = (eingabe - 273.15) * 1.8 + 32;
                fahrenheit = Math.round(fahrenheit);
                TFF.setText(String.valueOf(fahrenheit));
            }
        }
    }

    /**
     * Methode des Interface Initializable um Methoden beim Aufruf einer FXML Datei auszuführen.
     * Gibt der Combobox ihre entsprechenden Werte und wählt den Ersten standardmäßig aus.
     *
     * @param location  Standard im Interface
     * @param resources Standard im Interface
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBEingabe.setItems(options);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
