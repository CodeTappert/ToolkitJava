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
 * Klasse welche die FXML Datei DatenuebertragungsRechner steuert.
 * <p></p>
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class DatenuebertragungsController implements Initializable {
    static int zahlercb;
    @FXML
    RadioButton RadioExp;
    @FXML
    HBox HBit, HKilobit, HKilobyte, HKibibit, HMegabit, HMegabyte, HMebibit, HGigabit, HGigabyte, HGibibit, HTerabit, HTerabyte, HTebibit;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    Label LBit, LKilobit, LKilobyte, LKibibit, LMegabit, LMegabyte, LMebibit, LGigabit, LGigabyte, LGibibit, LTerabit, LTerabyte, LTebibit;
    @FXML
    TextField TFStart, TFBit, TFKilobit, TFKilobyte, TFKibibit, TFMegabit, TFMegabyte, TFMebibit, TFGigabit, TFGigabyte, TFGibibit, TFTerabit, TFTerabyte, TFTebibit;
    @FXML
    CheckBox CBBit, CBKilobit, CBKilobyte, CBKibibit, CBMegabit, CBMegabyte, CBMebibit, CBGigabit, CBGigabyte, CBGibibit, CBTerabit, CBTerabyte, CBTebibit;
    @FXML
    ComboBox<String> CBEingabe;
    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    Object Starteinheit, Bit = "Bit", Kilobit = "Kilobit", Kilobyte = "Kilobyte", Kibibit = "Kibibit", Megabit = "Megabit", Megabyte = "Megabyte", Mebibit = "Mebibit", Gigabit = "Gigabit", Gigabyte = "Gigabyte", Gibibit = "Gibibit", Terabit = "Terabit", Terabyte = "Terabyte", Tebibit = "Tebibit";
    ObservableList<String> optionsDateSize = FXCollections.observableArrayList("Bit", "Kilobit", "Kilobyte", "Kibibit", "Megabit", "Megabyte", "Mebibit", "Gigabit", "Gigabyte", "Gibibit", "Terabit", "Terabyte", "Tebibit");
    String EBit, EKilobit, EKilobyte, EKibibit, EMegabit, EMegabyte, EMebibit, EGigabit, EGigabyte, EGibibit, ETerabit, ETerabyte, ETebibit, Nutzereingabe, LeereEingabe = "Es wurde nichts eingegeben.";
    int RadioSelected = 1;
    double EingabeInMegabyte;

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
                InMegabyte();
                VonMegabyte();
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
     * Wandelt die Nutzereingabe in Megabyte pro Sekunde um und speichert den in der Variable EingabeInMegabyte.
     */
    public void InMegabyte() {
        Object Einheit = Starteinheit;
        double Eingabe = Double.parseDouble(Nutzereingabe);
        if (Einheit.equals(Bit)) {
            EingabeInMegabyte = Eingabe * 1.25e-7;
        } else if (Einheit.equals(Kilobit)) {
            EingabeInMegabyte = Eingabe * 0.000125;
        } else if (Einheit.equals(Kilobyte)) {
            EingabeInMegabyte = Eingabe * 0.001;
        } else if (Einheit.equals(Kibibit)) {
            EingabeInMegabyte = Eingabe * 0.000128;
        } else if (Einheit.equals(Megabit)) {
            EingabeInMegabyte = Eingabe * 0.125;
        } else if (Einheit.equals(Megabyte)) {
            EingabeInMegabyte = Eingabe;
        } else if (Einheit.equals(Mebibit)) {
            EingabeInMegabyte = 0.0131072;
        } else if (Einheit.equals(Gigabit)) {
            EingabeInMegabyte = Eingabe * 125;
        } else if (Einheit.equals(Gigabyte)) {
            EingabeInMegabyte = Eingabe * 1000;
        } else if (Einheit.equals(Gibibit)) {
            EingabeInMegabyte = Eingabe * 134.218;
        } else if (Einheit.equals(Terabit)) {
            EingabeInMegabyte = Eingabe * 125000;
        } else if (Einheit.equals(Terabyte)) {
            EingabeInMegabyte = Eingabe * 1000000;
        } else if (Einheit.equals(Tebibit)) {
            EingabeInMegabyte = Eingabe * 137439;
        }
    }

    /**
     * Rechnet EingabeMegabyte in alle Einheiten um und speichert die in String umgewandelte Ergebnisse in den ErgebnissStrings.
     */
    public void VonMegabyte() {
        EBit = Double.toString(EingabeInMegabyte / 1.25e-7);
        EKilobit = Double.toString(EingabeInMegabyte / 0.000125);
        EKilobyte = Double.toString(EingabeInMegabyte / 0.001);
        EKibibit = Double.toString(EingabeInMegabyte / 0.000128);
        EMegabit = Double.toString(EingabeInMegabyte / 0.125);
        EMegabyte = Double.toString(EingabeInMegabyte);
        EMebibit = Double.toString(EingabeInMegabyte / 0.0131072);
        EGigabit = Double.toString(EingabeInMegabyte / 125);
        EGigabyte = Double.toString(EingabeInMegabyte / 1000);
        EGibibit = Double.toString(EingabeInMegabyte / 134.218);
        ETerabit = Double.toString(EingabeInMegabyte / 125000);
        ETerabyte = Double.toString(EingabeInMegabyte / 1000000);
        ETebibit = Double.toString(EingabeInMegabyte / 137439);
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EBit = format.format(Double.parseDouble(EBit));
        EKilobit = format.format(Double.parseDouble(EKilobit));
        EKilobyte = format.format(Double.parseDouble(EKilobyte));
        EKibibit = format.format(Double.parseDouble(EKibibit));
        EMegabit = format.format(Double.parseDouble(EMegabit));
        EMegabyte = format.format(Double.parseDouble(EMegabyte));
        EMebibit = format.format(Double.parseDouble(EMebibit));
        EGigabit = format.format(Double.parseDouble(EGibibit));
        EGigabyte = format.format(Double.parseDouble(EGigabyte));
        EGibibit = format.format(Double.parseDouble(EGigabit));
        ETerabit = format.format(Double.parseDouble(ETerabit));
        ETerabyte = format.format(Double.parseDouble(ETerabyte));
        ETebibit = format.format(Double.parseDouble(ETebibit));
    }

    /**
     * Setzt die Fehlermeldung in alle Ergebnisstextfelder.
     *
     * @param Fehlermeldung Die Fehlermeldung die gesetzt werden soll.
     */
    public void FehlerSetzen(String Fehlermeldung) {
        TFBit.setText(Fehlermeldung);
        TFKilobit.setText(Fehlermeldung);
        TFKilobyte.setText(Fehlermeldung);
        TFKibibit.setText(Fehlermeldung);
        TFMegabit.setText(Fehlermeldung);
        TFMegabyte.setText(Fehlermeldung);
        TFMebibit.setText(Fehlermeldung);
        TFGigabit.setText(Fehlermeldung);
        TFGigabyte.setText(Fehlermeldung);
        TFGibibit.setText(Fehlermeldung);
        TFTerabit.setText(Fehlermeldung);
        TFTerabyte.setText(Fehlermeldung);
        TFTebibit.setText(Fehlermeldung);
    }

    /**
     * Setzt die entsprechenden Ergebnisse in die jeweiligen TextFelder.
     */
    public void ErgebnisseSetzen() {
        TFBit.setText(EBit);
        TFKilobit.setText(EKilobit);
        TFKilobyte.setText(EKilobyte);
        TFKibibit.setText(EKibibit);
        TFMegabit.setText(EMegabit);
        TFMegabyte.setText(EMegabyte);
        TFMebibit.setText(EMebibit);
        TFGigabit.setText(EGigabit);
        TFGigabyte.setText(EGigabyte);
        TFGibibit.setText(EGibibit);
        TFTerabit.setText(ETerabit);
        TFTerabyte.setText(ETerabyte);
        TFTebibit.setText(ETebibit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBBit() {
        Methoden.CBChecked(CBBit, HBit, LBit, TFBit, VBoxErgebniss);
        ZahlerCBHandle(CBBit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKilobit() {
        Methoden.CBChecked(CBKilobit, HKilobit, LKilobit, TFKilobit, VBoxErgebniss);
        ZahlerCBHandle(CBKilobit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKilobyte() {
        Methoden.CBChecked(CBKilobyte, HKilobyte, LKilobyte, TFKilobyte, VBoxErgebniss);
        ZahlerCBHandle(CBKilobyte);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBKibibit() {
        Methoden.CBChecked(CBKibibit, HKibibit, LKibibit, TFKibibit, VBoxErgebniss);
        ZahlerCBHandle(CBKibibit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBMegabit() {
        Methoden.CBChecked(CBMegabit, HMegabit, LMegabit, TFMegabit, VBoxErgebniss);
        ZahlerCBHandle(CBMegabit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBMegabyte() {
        Methoden.CBChecked(CBMegabyte, HMegabyte, LMegabyte, TFMegabyte, VBoxErgebniss);
        ZahlerCBHandle(CBMegabyte);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBMebibit() {
        Methoden.CBChecked(CBMebibit, HMebibit, LMebibit, TFMebibit, VBoxErgebniss);
        ZahlerCBHandle(CBMebibit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBGigabit() {
        Methoden.CBChecked(CBGigabit, HGigabit, LGigabit, TFGigabit, VBoxErgebniss);
        ZahlerCBHandle(CBGigabit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBGigabyte() {
        Methoden.CBChecked(CBGigabyte, HGigabyte, LGigabyte, TFGigabyte, VBoxErgebniss);
        ZahlerCBHandle(CBGigabyte);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBGibibit() {
        Methoden.CBChecked(CBGibibit, HGibibit, LGibibit, TFGibibit, VBoxErgebniss);
        ZahlerCBHandle(CBGibibit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBTerabit() {
        Methoden.CBChecked(CBTerabit, HTerabit, LTerabit, TFTerabit, VBoxErgebniss);
        ZahlerCBHandle(CBTerabit);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBTerabyte() {
        Methoden.CBChecked(CBTerabyte, HTerabyte, LTerabyte, TFTerabyte, VBoxErgebniss);
        ZahlerCBHandle(CBTerabyte);
    }

    /**
     * Handelt die Checkbox, setzt entsprechend die passende ComboBox, blendet das ensprechende Label, Textfield und Hbox ein und aus.
     * Und setzt den Integer zahlercb entsprechend.
     */
    public void CBTebibit() {
        Methoden.CBChecked(CBTebibit, HTebibit, LTebibit, TFTebibit, VBoxErgebniss);
        ZahlerCBHandle(CBTebibit);
    }

    /**
     * Ruft zuerst die Methode BtnVerstecken auf und danach zeigt es alle HBoxen, Labels und TextFields der Ergebnisseseite an und setzt den Zähler auf 9.
     * (Der Aufruf von BtnVerstecken ist zur Vermeidung eines Fehlers.)
     *
     * @see Methoden
     */
    public void BtnAnzeigen() {
        BtnVerstecken();
        CBBit.setSelected(true);
        Methoden.CBChecked(CBBit, HBit, LBit, TFBit, VBoxErgebniss);
        CBKilobit.setSelected(true);
        Methoden.CBChecked(CBKilobit, HKilobit, LKilobit, TFKilobit, VBoxErgebniss);
        CBKilobyte.setSelected(true);
        Methoden.CBChecked(CBKilobyte, HKilobyte, LKilobyte, TFKilobyte, VBoxErgebniss);
        CBKibibit.setSelected(true);
        Methoden.CBChecked(CBKibibit, HKibibit, LKibibit, TFKibibit, VBoxErgebniss);
        CBMegabit.setSelected(true);
        Methoden.CBChecked(CBMegabit, HMegabit, LMegabit, TFMegabit, VBoxErgebniss);
        CBMegabyte.setSelected(true);
        Methoden.CBChecked(CBMegabyte, HMegabyte, LMegabyte, TFMegabyte, VBoxErgebniss);
        CBMebibit.setSelected(true);
        Methoden.CBChecked(CBMebibit, HMebibit, LMebibit, TFMebibit, VBoxErgebniss);
        CBGigabit.setSelected(true);
        Methoden.CBChecked(CBGigabit, HGigabit, LGigabit, TFGigabit, VBoxErgebniss);
        CBGigabyte.setSelected(true);
        Methoden.CBChecked(CBGigabyte, HGigabyte, LGigabyte, TFGigabyte, VBoxErgebniss);
        CBGibibit.setSelected(true);
        Methoden.CBChecked(CBGibibit, HGibibit, LGibibit, TFGibibit, VBoxErgebniss);
        CBTerabit.setSelected(true);
        Methoden.CBChecked(CBTerabit, HTerabit, LTerabit, TFTerabit, VBoxErgebniss);
        CBTerabyte.setSelected(true);
        Methoden.CBChecked(CBTerabyte, HTerabyte, LTerabyte, TFTerabyte, VBoxErgebniss);
        CBTebibit.setSelected(true);
        Methoden.CBChecked(CBTebibit, HTebibit, LTebibit, TFTebibit, VBoxErgebniss);
        zahlercb = 13;
    }

    /**
     * Versteckt alle HBoxen, Labels und TextFields der Ergebnisseite (und setzt den Zähler auf 0)
     *
     * @see Methoden
     */
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            CBBit.setSelected(false);
            Methoden.CBChecked(CBBit, HBit, LBit, TFBit, VBoxErgebniss);
            CBKilobit.setSelected(false);
            Methoden.CBChecked(CBKilobit, HKilobit, LKilobit, TFKilobit, VBoxErgebniss);
            CBKilobyte.setSelected(false);
            Methoden.CBChecked(CBKilobyte, HKilobyte, LKilobyte, TFKilobyte, VBoxErgebniss);
            CBKibibit.setSelected(false);
            Methoden.CBChecked(CBKibibit, HKibibit, LKibibit, TFKibibit, VBoxErgebniss);
            CBMegabit.setSelected(false);
            Methoden.CBChecked(CBMegabit, HMegabit, LMegabit, TFMegabit, VBoxErgebniss);
            CBMegabyte.setSelected(false);
            Methoden.CBChecked(CBMegabyte, HMegabyte, LMegabyte, TFMegabyte, VBoxErgebniss);
            CBMebibit.setSelected(false);
            Methoden.CBChecked(CBMebibit, HMebibit, LMebibit, TFMebibit, VBoxErgebniss);
            CBGigabit.setSelected(false);
            Methoden.CBChecked(CBGigabit, HGigabit, LGigabit, TFGigabit, VBoxErgebniss);
            CBGigabyte.setSelected(false);
            Methoden.CBChecked(CBGigabyte, HGigabyte, LGigabyte, TFGigabyte, VBoxErgebniss);
            CBGibibit.setSelected(false);
            Methoden.CBChecked(CBGibibit, HGibibit, LGibibit, TFGibibit, VBoxErgebniss);
            CBTerabit.setSelected(false);
            Methoden.CBChecked(CBTerabit, HTerabit, LTerabit, TFTerabit, VBoxErgebniss);
            CBTerabyte.setSelected(false);
            Methoden.CBChecked(CBTerabyte, HTerabyte, LTerabyte, TFTerabyte, VBoxErgebniss);
            CBTebibit.setSelected(false);
            Methoden.CBChecked(CBTebibit, HTebibit, LTebibit, TFTebibit, VBoxErgebniss);
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
        CBEingabe.setItems(optionsDateSize);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
