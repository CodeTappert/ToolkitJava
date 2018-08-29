package Einheitenumrechner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Klasse welche die FXML Datei FrequenzRechner steuert.
 * <p></p>
 * <p>
 * Erklärung der Abkürzungen:
 * H = Hertz, KH = Kilohertz, MH = Megahertz, GH = Gigahertz.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class FrequenzController implements Initializable {
    @FXML
    Button BtnCalc, BtnBack;
    @FXML
    TextField TFStart, TFH, TFKH, TFMH, TFGH;
    @FXML
    ComboBox CBEingabe;
    @FXML
    RadioButton RadioExp;
    ObservableList<String> optionsFrequency = FXCollections.observableArrayList("Hertz", "Kilohertz", "Megahertz", "Gigahertz");
    Object Hertz = "Hertz", Kilohertz = "Kilohertz", Megahertz = "Megahertz", Gigahertz = "Gigahertz";
    //Double Ergebnis
    double DEH, DEKH, DEMH, DEGH;
    //String Ergebniss
    String EH, EKH, EMH, EGH;
    int weitermachen = 0, RadioSelected = 1;

    /**
     * Zurück zur Übersicht.
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
    }

    /**
     * Steuert den Button Umrechnung. Ruft die Methode umrechnen auf. Und dann sofern weitermachen 0 ist ruft es auch TextSetzen auf. Falls es 1 ist ruft es FehlerSetzen auf.
     */
    @FXML
    public void BtnCalc() {
        Umrechnen(TFStart.getText(), CBEingabe.getValue());
        if (weitermachen == 0) {
            TextSetzen();
        } else {
            FehlerSetzen(Methoden.KeineDez);
        }
        weitermachen = 0;
    }

    /**
     * Überprüft ob die RadioBox angewählt ist und setzt einen entsprechenden Integer Wert.
     * 0 = angewählt, 1 = nicht angewählt.
     */
    public void RadioExp() {
        if (RadioExp.isSelected()) {
            RadioSelected = 0;
        } else {
            RadioSelected = 1;
        }

    }

    /**
     * Ruft checkIfDez auf. Falls diese 1 zurück gibt danach entsprechend der vom Nutzer ausgewählten Ausgangseinheit die Eingabe des Nutzers umrechnet und
     * die Werte in den Ergebnisvariablen speichert. Bzw. Formatiert falls die Radio Box NICHT ausgewählt ist.
     *
     * @param Eingabe Die Eingabe vom Nutzer.
     * @param Auswahl Die vom Nutzer ausgewählte Einheit.
     */
    public void Umrechnen(String Eingabe, Object Auswahl) {
        int dez = Methoden.checkIfDez(Eingabe);
        if (dez == 1) {
            double EingabeD = Double.parseDouble(Eingabe);
            if (Auswahl.equals(Hertz)) {
                DEH = EingabeD;
                DEKH = EingabeD / 1000;
                DEMH = EingabeD / 1000 / 1000;
                DEGH = EingabeD / 1000 / 1000 / 1000;
            } else if (Auswahl.equals(Kilohertz)) {
                DEH = EingabeD * 1000;
                DEKH = EingabeD;
                DEMH = EingabeD / 1000;
                DEGH = EingabeD / 1000 / 1000;
            } else if (Auswahl.equals(Megahertz)) {
                DEH = EingabeD * 1000 * 1000;
                DEKH = EingabeD * 1000;
                DEMH = EingabeD;
                DEGH = EingabeD / 1000;
            } else if (Auswahl.equals(Gigahertz)) {
                DEH = EingabeD * 1000 * 1000 * 1000;
                DEKH = EingabeD * 1000 * 1000;
                DEMH = EingabeD * 1000;
                DEGH = EingabeD;
            }
        } else {
            weitermachen = 1;
        }
        if (RadioSelected == 1) {
            Formatierung();
        } else {
            EH = Double.toString(DEH);
            EKH = Double.toString(DEKH);
            EMH = Double.toString(DEMH);
            EGH = Double.toString(DEGH);
        }
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EH = format.format(DEH);
        EKH = format.format(DEKH);
        EMH = format.format(DEMH);
        EGH = format.format(DEGH);
    }

    /**
     * Setzt in die entsprechenden Textfelder die Werte aus den Ergebnisvariablen.
     */
    public void TextSetzen() {
        TFH.setText(EH);
        TFKH.setText(EKH);
        TFMH.setText(EMH);
        TFGH.setText(EGH);
    }

    /**
     * Setzt in alle Ergebnistextfelder die Meldung von Methoden.KeineDez
     */
    public void FehlerSetzen(String A) {
        TFH.setText(A);
        TFKH.setText(A);
        TFMH.setText(A);
        TFGH.setText(A);
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
        CBEingabe.setItems(optionsFrequency);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
