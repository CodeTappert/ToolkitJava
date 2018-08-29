package Einheitenumrechner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasse welche die FXML Datei GeschwindigkeitsCoontroller steuert.
 * <p></p>
 * <p>
 * Erklärung der Abkürzungen:
 * Meter = Meter pro Sekunde, Fuss = Fuß pro Sekunde, Kilometer = Kilometer pro Stunde, Meilen = Meilen pro Stunde, Knoten = Knoten.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class GeschwindigkeitController implements Initializable {
    @FXML
    Button BtnBack, BtnCalc;
    @FXML
    TextField TFStart, TFMeter, TFMeilen, TFFuss, TFKilometer, TFKnoten;
    @FXML
    ComboBox CBEingabe;
    ObservableList<String> optionsSpeed = FXCollections.observableArrayList("Meter pro Sekunde", "Fuß pro Sekunde", "Kilometer pro Stunde", "Meilen pro Stunde", "Knoten");
    Object Meter = "Meter pro Sekunde", Fuss = "Fuß pro Sekunde", Kilometer = "Kilometer pro Stunde", Meilen = "Meilen pro Stunde", Knoten = "Knoten";
    double EMeter = 0, EKilometer = 0, EFuss = 0, EMeilen = 0, EKnoten = 0;
    int weitermachen = 0;

    /**
     * Zurück zur Übersicht.
     */
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
    }

    /**
     * Steuert den Button Umrechnung. Ruft die Methode umrechnen auf. Und dann sofern weitermachen 0 ist ruft es auch TextSetzen auf.
     */
    public void BtnCalc() {
        Umrechnen(TFStart.getText(), CBEingabe.getValue());
        if (weitermachen == 0) {
            TextSetzen();
        }
        weitermachen = 0;
    }

    /**
     * Ruft checkIfDez auf. Falls diese 1 zurück gibt danach entsprechend der vom Nutzer ausgewählten Ausgangseinheit die Eingabe des Nutzers umrechnet und
     * die Werte in den Ergebnisvariablen speichert.
     *
     * @param Eingabe Die Eingabe vom Nutzer.
     * @param Auswahl Die vom Nutzer ausgewählte Einheit.
     */
    public void Umrechnen(String Eingabe, Object Auswahl) {
        int IsDez = Methoden.checkIfDez(Eingabe);

        if (IsDez == 1) {
            double EingabeD = Double.parseDouble(Eingabe);

            if (Auswahl.equals(Meter)) {
                EMeter = EingabeD;
                EFuss = EingabeD * 3.28084;
                EKilometer = EingabeD * 3.6;
                EMeilen = EingabeD * 2.23694;
                EKnoten = EingabeD * 1.94384;
            } else if (Auswahl.equals(Fuss)) {
                EMeter = EingabeD / 3.28084;
                EFuss = EingabeD;
                EKilometer = EingabeD * 1.09728;
                EMeilen = EingabeD / 1.46667;
                EKnoten = EingabeD / 1.68781;
            } else if (Auswahl.equals(Kilometer)) {
                EMeter = EingabeD / 3.6;
                EFuss = EingabeD / 1.09728;
                EKilometer = EingabeD;
                EMeilen = EingabeD / 1.60934;
                EKnoten = EingabeD / 1.852;
            } else if (Auswahl.equals(Meilen)) {
                EMeter = EingabeD / 2.23694;
                EFuss = EingabeD * 1.46667;
                EKilometer = EingabeD * 1.60934;
                EMeilen = EingabeD;
                EKnoten = EingabeD / 1.15078;
            } else if (Auswahl.equals(Knoten)) {
                EMeter = EingabeD / 1.94384;
                EFuss = EingabeD * 1.68781;
                EKilometer = EingabeD * 1.852;
                EMeilen = 1.15078;
                EKnoten = EingabeD;
            }
        } else {
            FehlerAusgeben();
            weitermachen = 1;
        }
    }

    /**
     * Setzt in die entsprechenden Textfelder die Werte aus den Ergebnisvariablen.
     */
    public void TextSetzen() {
        TFMeter.setText(Double.toString(EMeter));
        TFFuss.setText(Double.toString(EFuss));
        TFKilometer.setText(Double.toString(EKilometer));
        TFMeilen.setText(Double.toString(EMeilen));
        TFKnoten.setText(Double.toString(EKnoten));
    }

    /**
     * Setzt in alle Ergebnistextfelder die Meldung von Methoden.KeineDez
     */
    public void FehlerAusgeben() {
        TFMeter.setText(Methoden.KeineDez);
        TFFuss.setText(Methoden.KeineDez);
        TFKilometer.setText(Methoden.KeineDez);
        TFMeilen.setText(Methoden.KeineDez);
        TFKnoten.setText(Methoden.KeineDez);
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
        CBEingabe.setItems(optionsSpeed);
        CBEingabe.getSelectionModel().selectFirst();
    }
}