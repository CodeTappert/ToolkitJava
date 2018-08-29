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
 * Klasse welche die FXML Datei DruckRechner steuert.
 * <p></p>
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class DruckController implements Initializable {
    @FXML
    Button BtnCalc, BtnBack;
    @FXML
    TextField TFStart, TFBar, TFPascal, TFPhysikalische_Atmosphaere, TFpound_per_square_inch, TFTorr;
    @FXML
    ComboBox CBEingabe;
    @FXML
    RadioButton RadioExp;
    Object Starteinheit, Pascal = "Pascal", Bar = "Bar", Physikalische_Atmosphaere = "Physikalische Atmosphäre", pound_force_per_square_inch = "pound for per square inch", Torr = "Torr";
    ObservableList<String> optionsPressure = FXCollections.observableArrayList("Pascal", "Bar", "Physikalische Atmosphäre", "pound for per square inch", "Torr");
    String Nutzereingabe, EPascal, EBar, EPhysikalische_Atmosphaere, Epound_force_per_square_inch, ETorr, LeereEingabe = "Es wurde nichts eingegeben.";
    double EingabeInBar;
    int RadioSelected = 1;

    /**
     * Zurück zur Übersicht.
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
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
     * Knopfdruck auf Umrechnen. Holt sich zuerst die ausgewählte Einheit, dann die Nutzereingabe, überprüft diese ob diese eine ganzahlige Dezimalzahl ist.
     * Falls ja wird diese Eingabe in Quadratmeter umgerechnet und dann von der Einheit in alle anderen Einheiten.
     * Falls der Radio Button nicht ausgeählt ist, wird die Eingabe so formatiert, dass keine Exponenten angezeigt werden.
     * Danach werden die Ergebnisse in die entsprechenden Textfelder gesetzt.
     * Falls nein wird Methode.KeineDez in die Textfelder gesetzt.
     * Falls nichts eingeben wurde wird der String LeereEingabe in die Textfelder gesetzt.
     *
     * @see Methoden
     */
    @FXML
    public void BtnCalc() {
        SetStarteinheit();
        SetNutzereingabe();
        int IsDez = Methoden.checkIfDez(Nutzereingabe);
        if (!Nutzereingabe.isEmpty()) {
            if (IsDez == 1) {
                InBar();
                VonBar();
                if (RadioSelected == 1) {
                    Formatierung();
                }
                ErgebnisseSetzen();
            } else {
                FehlerSetzen(Methoden.KeineDez);
            }
        } else {
            FehlerSetzen(LeereEingabe);
        }
    }

    /**
     * Setzt den Wert von Starteinheit auf die in der ComboBox ausgewählte Einheit.
     */
    public void SetStarteinheit() {
        Starteinheit = CBEingabe.getValue();
    }

    /**
     * Setzt den Wert von Nutzereingabe auf den in der Textbox TFStart eingegebenen String.
     */
    public void SetNutzereingabe() {
        Nutzereingabe = TFStart.getText();
    }

    /**
     * Wandelt die Nutzereingabe in Bar um und speichert den in der Variable EingabeInBar.
     */
    public void InBar() {
        double Eingabe = Double.parseDouble(Nutzereingabe);
        Object Einheit = Starteinheit;
        if (Einheit.equals(Bar)) {
            EingabeInBar = Eingabe;
        } else if (Einheit.equals(Pascal)) {
            EingabeInBar = Eingabe * 0.00001;
        } else if (Einheit.equals(Physikalische_Atmosphaere)) {
            EingabeInBar = Eingabe * 1.01325;
        } else if (Einheit.equals(pound_force_per_square_inch)) {
            EingabeInBar = Eingabe * 0.0689476;
        } else if (Einheit.equals(Torr)) {
            EingabeInBar = Eingabe * 0.00133322;
        }
    }

    /**
     * Rechnet EingabeInBar in alle Einheiten um und speichert die in String umgewandelte Ergebnisse in den ErgebnissStrings.
     */
    public void VonBar() {
        EBar = Double.toString(EingabeInBar);
        EPascal = Double.toString(EingabeInBar / 0.00001);
        EPhysikalische_Atmosphaere = Double.toString(EingabeInBar / 1.01325);
        Epound_force_per_square_inch = Double.toString(EingabeInBar / 0.0689476);
        ETorr = Double.toString(EingabeInBar / 0.00133322);
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EBar = format.format(Double.parseDouble(EBar));
        EPascal = format.format(Double.parseDouble(EPascal));
        EPhysikalische_Atmosphaere = format.format(Double.parseDouble(EPhysikalische_Atmosphaere));
        Epound_force_per_square_inch = format.format(Double.parseDouble(Epound_force_per_square_inch));
        ETorr = format.format(Double.parseDouble(ETorr));
    }

    /**
     * Setzt die entsprechenden Ergebnisse in die jeweiligen TextFelder.
     */
    public void ErgebnisseSetzen() {
        TFBar.setText(EBar);
        TFPascal.setText(EPascal);
        TFPhysikalische_Atmosphaere.setText(EPhysikalische_Atmosphaere);
        TFpound_per_square_inch.setText(Epound_force_per_square_inch);
        TFTorr.setText(ETorr);
    }

    /**
     * Setzt die Fehlermeldung in alle Ergebnisstextfelder.
     *
     * @param Fehlermeldung Die Fehlermeldung die gesetzt werden soll.
     */
    public void FehlerSetzen(String Fehlermeldung) {
        TFBar.setText(Fehlermeldung);
        TFPascal.setText(Fehlermeldung);
        TFPhysikalische_Atmosphaere.setText(Fehlermeldung);
        TFpound_per_square_inch.setText(Fehlermeldung);
        TFTorr.setText(Fehlermeldung);
    }

    /**
     * Methode des Interface Initializable um Methoden beim Aufruf einer FXML Datei auszuführen.
     * Setzt alle Ausgabeboxen auf nicht sichtbar. (müssen im Scenebuilder standardmäßig sichtbar sein um
     * richtig geladen werden zu können.)
     * Und gibt der Combobox ihre entsprechenden Werte und wählt den Ersten standardmäßig aus.
     *
     * @param location  Standard im Interface
     * @param resources Standard im Interface
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBEingabe.setItems(optionsPressure);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
