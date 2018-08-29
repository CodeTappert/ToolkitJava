package Einheitenumrechner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Klasse welche die FXML Datei FlacheRechner steuert.
 * <p></p>
 * Das Q steht für Quadrat.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class FlaecheController implements Initializable {
    static int zahlercb;
    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    TextField TFStart, TFQMeter, TFQKilometer, TFQYard, TFQFuss, TFQMeile, TFQZoll, TFHektar, TFAcre;
    @FXML
    Label LQMeter, LQKilometer, LQYard, LQFuss, LQMeile, LQZoll, LHektar, LAcre;
    @FXML
    HBox HQMeter, HQKilometer, HQYard, HQFuss, HQMeile, HQZoll, HHektar, HAcre;
    @FXML
    CheckBox CBQMeter, CBQKilometer, CBQYard, CBQFuss, CBQMeile, CBQZoll, CBHektar, CBAcre;
    @FXML
    ComboBox CBEingabe;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    RadioButton RadioExp;
    ObservableList<String> optionsArea = FXCollections.observableArrayList("Quadratmeter", "Quadratkilometer", "Quadratyard", "Quadratfuß", "Quadratmeile", "Qudaratzoll", "Hektar", "Acre");
    Object QMeter = "Quadratmeter", QKilometer = "Quadratkilometer", QYard = "Quadratyard", QFuss = "Quadratfuß", QMeile = "Quadratmeile", QZoll = "Quadratzoll", Hektar = "Hektar", Acre = "Acre";
    Object Starteinheit;
    String Nutzereingabe, EQMeter, EQKilometer, EQYard, EQFuss, EQMeile, EQZoll, EHektar, EAcre, LeereEingabe = "Es wurde kein Wert eingegeben.";
    double EingabeInQMetern;
    int RadioSelected = 1;

    /**
     * Überprüft und ändert die Anzahl der angewählten Checkboxen.
     *
     * @param CB Checkbox die überprüft werden soll.
     */
    public static void ZahlerCBHandle(CheckBox CB) {
        if (CB.isSelected()) {
            zahlercb++;
        } else {
            zahlercb--;
        }
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
     * Zurück zur Übersicht.
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
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
                InQuadratmeter();
                VonQuadratmeter();
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
     * Rechnet die Eingabe des Nutzers in Quadratmeter um und speichert dies in der Variable EingabeInQMetern;
     */
    public void InQuadratmeter() {
        Object Einheit = Starteinheit;
        double Eingabe = Double.parseDouble(Nutzereingabe);

        if (Einheit.equals(QMeter)) {
            EingabeInQMetern = Eingabe;
        } else if (Einheit.equals(QKilometer)) {
            EingabeInQMetern = Eingabe * 1000000;
        } else if (Einheit.equals(QYard)) {
            EingabeInQMetern = Eingabe / 0.836127;
        } else if (Einheit.equals(QFuss)) {
            EingabeInQMetern = Eingabe / 0.092903;
        } else if (Einheit.equals(QMeile)) {
            EingabeInQMetern = Eingabe * 2590000;
        } else if (Einheit.equals(QZoll)) {
            EingabeInQMetern = Eingabe / 0.00064516;
        } else if (Einheit.equals(Hektar)) {
            EingabeInQMetern = Eingabe * 10000;
        } else if (Einheit.equals(Acre)) {
            EingabeInQMetern = Eingabe * 4046.86;
        }
    }

    /**
     * Rechnet EingabeInQMetern in alle Einheiten um und speichert die in String umgewandelte Ergebnisse in den ErgebnissStrings.
     */
    public void VonQuadratmeter() {
        EQMeter = Double.toString(EingabeInQMetern);
        EQKilometer = Double.toString(EingabeInQMetern / 1000000);
        EQYard = Double.toString(EingabeInQMetern / 0.836127);
        EQFuss = Double.toString(EingabeInQMetern / 0.092903);
        EQMeile = Double.toString(EingabeInQMetern / 2590000);
        EQZoll = Double.toString(EingabeInQMetern / 0.00064516);
        EHektar = Double.toString(EingabeInQMetern / 1000);
        EAcre = Double.toString(EingabeInQMetern / 4046.86);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQMeter() {
        Methoden.CBChecked(CBQMeter, HQMeter, LQMeter, TFQMeter, VBoxErgebniss);
        ZahlerCBHandle(CBQMeter);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQKilometer() {
        Methoden.CBChecked(CBQKilometer, HQKilometer, LQKilometer, TFQKilometer, VBoxErgebniss);
        ZahlerCBHandle(CBQKilometer);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQYard() {
        Methoden.CBChecked(CBQYard, HQYard, LQYard, TFQYard, VBoxErgebniss);
        ZahlerCBHandle(CBQYard);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQFuss() {
        Methoden.CBChecked(CBQFuss, HQFuss, LQFuss, TFQFuss, VBoxErgebniss);
        ZahlerCBHandle(CBQFuss);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQMeile() {
        Methoden.CBChecked(CBQMeile, HQMeile, LQMeile, TFQMeile, VBoxErgebniss);
        ZahlerCBHandle(CBQMeile);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBQZoll() {
        Methoden.CBChecked(CBQZoll, HQZoll, LQZoll, TFQZoll, VBoxErgebniss);
        ZahlerCBHandle(CBQZoll);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBHektar() {
        Methoden.CBChecked(CBHektar, HHektar, LHektar, TFHektar, VBoxErgebniss);
        ZahlerCBHandle(CBHektar);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    @FXML
    public void CBAcre() {
        Methoden.CBChecked(CBAcre, HAcre, LAcre, TFAcre, VBoxErgebniss);
        ZahlerCBHandle(CBAcre);
    }

    /**
     * Ruft zuerst die Methode BtnVerstecken auf und danach zeigt es alle HBoxen, Labels und TextFields der Ergebnisseseite an und setzt den Zähler auf 9.
     * (Der Aufruf von BtnVerstecken ist zur Vermeidung eines Fehlers.)
     *
     * @see Methoden
     */
    @FXML
    public void BtnAnzeigen() {
        BtnVerstecken();
        CBQMeter.setSelected(true);
        Methoden.CBChecked(CBQMeter, HQMeter, LQMeter, TFQMeter, VBoxErgebniss);
        CBQKilometer.setSelected(true);
        Methoden.CBChecked(CBQKilometer, HQKilometer, LQKilometer, TFQKilometer, VBoxErgebniss);
        CBQYard.setSelected(true);
        Methoden.CBChecked(CBQYard, HQYard, LQYard, TFQYard, VBoxErgebniss);
        CBQFuss.setSelected(true);
        Methoden.CBChecked(CBQFuss, HQFuss, LQFuss, TFQFuss, VBoxErgebniss);
        CBQMeile.setSelected(true);
        Methoden.CBChecked(CBQMeile, HQMeile, LQMeile, TFQMeile, VBoxErgebniss);
        CBQZoll.setSelected(true);
        Methoden.CBChecked(CBQZoll, HQZoll, LQZoll, TFQZoll, VBoxErgebniss);
        CBHektar.setSelected(true);
        Methoden.CBChecked(CBHektar, HHektar, LHektar, TFHektar, VBoxErgebniss);
        CBAcre.setSelected(true);
        Methoden.CBChecked(CBAcre, HAcre, LAcre, TFAcre, VBoxErgebniss);
        zahlercb = 8;

    }

    /**
     * Versteckt alle HBoxen, Labels und TextFields der Ergebnisseite (und setzt den Zähler auf 0)
     *
     * @see Methoden
     */
    @FXML
    public void BtnVerstecken() {
        if (zahlercb >= 1) {
            CBQMeter.setSelected(false);
            Methoden.CBChecked(CBQMeter, HQMeter, LQMeter, TFQMeter, VBoxErgebniss);
            CBQKilometer.setSelected(false);
            Methoden.CBChecked(CBQKilometer, HQKilometer, LQKilometer, TFQKilometer, VBoxErgebniss);
            CBQYard.setSelected(false);
            Methoden.CBChecked(CBQYard, HQYard, LQYard, TFQYard, VBoxErgebniss);
            CBQFuss.setSelected(false);
            Methoden.CBChecked(CBQFuss, HQFuss, LQFuss, TFQFuss, VBoxErgebniss);
            CBQMeile.setSelected(false);
            Methoden.CBChecked(CBQMeile, HQMeile, LQMeile, TFQMeile, VBoxErgebniss);
            CBQZoll.setSelected(false);
            Methoden.CBChecked(CBQZoll, HQZoll, LQZoll, TFQZoll, VBoxErgebniss);
            CBHektar.setSelected(false);
            Methoden.CBChecked(CBHektar, HHektar, LHektar, TFHektar, VBoxErgebniss);
            CBAcre.setSelected(false);
            Methoden.CBChecked(CBAcre, HAcre, LAcre, TFAcre, VBoxErgebniss);
            zahlercb = 0;
        }
    }

    /**
     * Setzt die entsprechenden Ergebnisse in die jeweiligen TextFelder.
     */
    public void ErgebnisseSetzen() {
        TFQMeter.setText(EQMeter);
        TFQKilometer.setText(EQKilometer);
        TFQYard.setText(EQYard);
        TFQFuss.setText(EQFuss);
        TFQMeile.setText(EQMeile);
        TFQZoll.setText(EQZoll);
        TFHektar.setText(EHektar);
        TFAcre.setText(EAcre);
    }

    /**
     * Setzt die Fehlermeldung in alle Ergebnisstextfelder.
     *
     * @param Fehlermeldung Die Fehlermeldung die gesetzt werden soll.
     */
    public void FehlerSetzen(String Fehlermeldung) {
        TFQMeter.setText(Fehlermeldung);
        TFQKilometer.setText(Fehlermeldung);
        TFQYard.setText(Fehlermeldung);
        TFQFuss.setText(Fehlermeldung);
        TFQMeile.setText(Fehlermeldung);
        TFQZoll.setText(Fehlermeldung);
        TFHektar.setText(Fehlermeldung);
        TFAcre.setText(Fehlermeldung);
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EQMeter = format.format(Double.parseDouble(EQMeter));
        EQKilometer = format.format(Double.parseDouble(EQKilometer));
        EQYard = format.format(Double.parseDouble(EQYard));
        EQFuss = format.format(Double.parseDouble(EQFuss));
        EQMeile = format.format(Double.parseDouble(EQMeile));
        EQZoll = format.format(Double.parseDouble(EQZoll));
        EHektar = format.format(Double.parseDouble(EHektar));
        EAcre = format.format(Double.parseDouble(EAcre));
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
        zahlercb = 1;
        BtnVerstecken();
        CBEingabe.setItems(optionsArea);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
