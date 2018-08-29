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
 * Klasse welche die FXML Datei EnergieRechner steuert.
 * <p></p>
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class EnergieController implements Initializable {
    @FXML
    Button BtnCalc, BtnBack, BtnAnzeigen, BtnVerstecken;
    static int zahlercb;
    @FXML
    Label LJoule, LKilojoule, LKalorie, LKilokalorie, LWattstunde, LKilowattstunde, LElektronenvolt;
    @FXML
    TextField TFStart, TFJoule, TFKilojoule, TFKalorie, TFKilokalorie, TFWattstunde, TFKilowattstunde, TFElektronenvolt;
    @FXML
    HBox HJoule, HKilojoule, HKalorie, HKilokalorie, HWattstunde, HKilowattstunde, HElektronenvolt;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    ComboBox CBEingabe;
    @FXML
    CheckBox CBJoule, CBKilojoule, CBKalorie, CBKilokalorie, CBWattstunde, CBKilowattstunde, CBElektronenvolt;
    @FXML
    RadioButton RadioExp;
    Object Starteinheit, Joule = "Joule", Kilojoule = "Kilojoule", Kalorie = "Kalorie", Kilokalorie = "Kilokalorie", Wattstunde = "Wattstunde", Kilowattstunde = "Kilowattstunde", Elektronenvolt = "Elektronenvolt";
    ObservableList<String> optionsEnergy = FXCollections.observableArrayList("Joule", "Kilojoule", "Kalorie", "Kilokalorie", "Wattstunde", "Kilowattstunde", "Elektronenvolt");
    String Nutzereingabe, EJoule, EKilojoule, EKalorie, EKilokalorie, EWattstunde, EKilowattstunde, EElektronenvolt, LeereEingabe = "Es wurde nichts eingegeben.";
    double EingabeInJoule;
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
                InJoule();
                VonJoule();
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
     * Wandelt die Nutzereingabe in Joule um und speichert den in der Variable EingabeInJoule.
     */
    public void InJoule() {
        Double Eingabe = Double.parseDouble(Nutzereingabe);
        Object Einheit = Starteinheit;
        if (Einheit.equals(Joule)) {
            EingabeInJoule = Eingabe;
        } else if (Einheit.equals(Kilojoule)) {
            EingabeInJoule = Eingabe * 1000;
        } else if (Einheit.equals(Kalorie)) {
            EingabeInJoule = Eingabe * 4.184;
        } else if (Einheit.equals(Kilokalorie)) {
            EingabeInJoule = Eingabe * 4184;
        } else if (Einheit.equals(Wattstunde)) {
            EingabeInJoule = Eingabe * 3600;
        } else if (Einheit.equals(Kilowattstunde)) {
            EingabeInJoule = Eingabe * 3600000;
        } else if (Einheit.equals(Elektronenvolt)) {
            EingabeInJoule = Eingabe * 1.6022e-19;
        }
    }

    /**
     * Rechnet EingabeInJoule in alle Einheiten um und speichert die in String umgewandelte Ergebnisse in den ErgebnissStrings.
     */
    public void VonJoule() {
        EJoule = Double.toString(EingabeInJoule);
        EKilojoule = Double.toString(EingabeInJoule / 1000);
        EKalorie = Double.toString(EingabeInJoule / 4.184);
        EKilokalorie = Double.toString(EingabeInJoule / 4184);
        EWattstunde = Double.toString(EingabeInJoule / 3600);
        EKilowattstunde = Double.toString(EingabeInJoule / 3600000);
        EElektronenvolt = Double.toString(EingabeInJoule / 1.6022e-19);
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(30);
        EJoule = format.format(Double.parseDouble(EJoule));
        EKilojoule = format.format(Double.parseDouble(EKilojoule));
        EKalorie = format.format(Double.parseDouble(EKalorie));
        EKilokalorie = format.format(Double.parseDouble(EKilokalorie));
        EWattstunde = format.format(Double.parseDouble(EWattstunde));
        EKilowattstunde = format.format(Double.parseDouble(EKilowattstunde));
        EElektronenvolt = format.format(Double.parseDouble(EElektronenvolt));
    }

    /**
     * Setzt die entsprechenden Ergebnisse in die jeweiligen TextFelder.
     */
    public void ErgebnisseSetzen() {
        TFJoule.setText(EJoule);
        TFKilojoule.setText(EKilojoule);
        TFKalorie.setText(EKalorie);
        TFKilokalorie.setText(EKilokalorie);
        TFWattstunde.setText(EWattstunde);
        TFKilowattstunde.setText(EKilowattstunde);
        TFElektronenvolt.setText(EElektronenvolt);
    }

    /**
     * Setzt die Fehlermeldung in alle Ergebnisstextfelder.
     *
     * @param Fehlermeldung Die Fehlermeldung die gesetzt werden soll.
     */
    public void FehlerSetzen(String Fehlermeldung) {
        TFJoule.setText(Fehlermeldung);
        TFKilojoule.setText(Fehlermeldung);
        TFKalorie.setText(Fehlermeldung);
        TFKilokalorie.setText(Fehlermeldung);
        TFWattstunde.setText(Fehlermeldung);
        TFKilojoule.setText(Fehlermeldung);
        TFElektronenvolt.setText(Fehlermeldung);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBJoule() {
        Methoden.CBChecked(CBJoule, HJoule, LJoule, TFJoule, VBoxErgebniss);
        ZahlerCBHandle(CBJoule);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKilojoule() {
        Methoden.CBChecked(CBKilojoule, HKilojoule, LKilojoule, TFKilojoule, VBoxErgebniss);
        ZahlerCBHandle(CBKilojoule);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKalorie() {
        Methoden.CBChecked(CBKalorie, HKalorie, LKalorie, TFKalorie, VBoxErgebniss);
        ZahlerCBHandle(CBKalorie);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKilokalorie() {
        Methoden.CBChecked(CBKilokalorie, HKilokalorie, LKilokalorie, TFKilokalorie, VBoxErgebniss);
        ZahlerCBHandle(CBKilokalorie);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBWattstunde() {
        Methoden.CBChecked(CBWattstunde, HWattstunde, LWattstunde, TFWattstunde, VBoxErgebniss);
        ZahlerCBHandle(CBWattstunde);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKilowattstunde() {
        Methoden.CBChecked(CBKilowattstunde, HKilowattstunde, LKilowattstunde, TFKilowattstunde, VBoxErgebniss);
        ZahlerCBHandle(CBKilowattstunde);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBElektronenvolt() {
        Methoden.CBChecked(CBElektronenvolt, HElektronenvolt, LElektronenvolt, TFElektronenvolt, VBoxErgebniss);
        ZahlerCBHandle(CBElektronenvolt);
    }

    /**
     * Ruft zuerst die Methode BtnVerstecken auf und danach zeigt es alle HBoxen, Labels und TextFields der Ergebnisseseite an und setzt den Zähler auf 9.
     * (Der Aufruf von BtnVerstecken ist zur Vermeidung eines Fehlers.)
     *
     * @see Methoden
     */
    public void BtnAnzeigen() {
        BtnVerstecken();
        CBJoule.setSelected(true);
        Methoden.CBChecked(CBJoule, HJoule, LJoule, TFJoule, VBoxErgebniss);
        CBKilojoule.setSelected(true);
        Methoden.CBChecked(CBKilojoule, HKilojoule, LKilojoule, TFKilojoule, VBoxErgebniss);
        CBKalorie.setSelected(true);
        Methoden.CBChecked(CBKalorie, HKalorie, LKalorie, TFKalorie, VBoxErgebniss);
        CBKilokalorie.setSelected(true);
        Methoden.CBChecked(CBKilokalorie, HKilokalorie, LKilokalorie, TFKilokalorie, VBoxErgebniss);
        CBWattstunde.setSelected(true);
        Methoden.CBChecked(CBWattstunde, HWattstunde, LWattstunde, TFWattstunde, VBoxErgebniss);
        CBKilowattstunde.setSelected(true);
        Methoden.CBChecked(CBKilowattstunde, HKilowattstunde, LKilowattstunde, TFKilowattstunde, VBoxErgebniss);
        CBElektronenvolt.setSelected(true);
        Methoden.CBChecked(CBElektronenvolt, HElektronenvolt, LElektronenvolt, TFElektronenvolt, VBoxErgebniss);
        zahlercb = 7;
    }

    /**
     * Versteckt alle HBoxen, Labels und TextFields der Ergebnisseite (und setzt den Zähler auf 0)
     *
     * @see Methoden
     */
    public void BtnVerstecken() {
        if (zahlercb >= 1) {
            CBJoule.setSelected(false);
            Methoden.CBChecked(CBJoule, HJoule, LJoule, TFJoule, VBoxErgebniss);
            CBKilojoule.setSelected(false);
            Methoden.CBChecked(CBKilojoule, HKilojoule, LKilojoule, TFKilojoule, VBoxErgebniss);
            CBKalorie.setSelected(false);
            Methoden.CBChecked(CBKalorie, HKalorie, LKalorie, TFKalorie, VBoxErgebniss);
            CBKilokalorie.setSelected(false);
            Methoden.CBChecked(CBKilokalorie, HKilokalorie, LKilokalorie, TFKilokalorie, VBoxErgebniss);
            CBWattstunde.setSelected(false);
            Methoden.CBChecked(CBWattstunde, HWattstunde, LWattstunde, TFWattstunde, VBoxErgebniss);
            CBKilowattstunde.setSelected(false);
            Methoden.CBChecked(CBKilowattstunde, HKilowattstunde, LKilowattstunde, TFKilowattstunde, VBoxErgebniss);
            CBElektronenvolt.setSelected(false);
            Methoden.CBChecked(CBElektronenvolt, HElektronenvolt, LElektronenvolt, TFElektronenvolt, VBoxErgebniss);
            zahlercb = 0;
        }
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
        CBEingabe.setItems(optionsEnergy);
        CBEingabe.getSelectionModel().selectFirst();
    }
}

