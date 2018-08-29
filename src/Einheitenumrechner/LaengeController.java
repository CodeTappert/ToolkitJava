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
 * Klasse welche die FXML Datei zum Längenumrechner steuert.
 * <p></p>
 * Erklärung der Ablürzungen:
 * MM = Millimeter, CM = Zentimeter, M = Meter, KM = Kilometer, Meile = Meile, Y = Yard, F = Fuß, Z = Zoll, SM = Seemeile.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class LaengeController implements Initializable {
    @FXML
    CheckBox CBMM, CBCM, CBM, CBKM, CBMeile, CBY, CBF, CBZ, CBSM;
    @FXML
    TextField TFMM, TFCM, TFM, TFKM, TFMeile, TFY, TFF, TFZ, TFSM, TFStart;
    @FXML
    HBox HMM, HCM, HM, HKM, HMeile, HY, HF, HZ, HSM;
    @FXML
    Label LMM, LCM, LM, LKM, LMeile, LY, LF, LZ, LSM;
    @FXML
    ComboBox CBEingabe;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    RadioButton RadioExp;
    static int zahlercb;
    int RadioSelected = 1;
    ObservableList<String> optionsLange = FXCollections.observableArrayList("Millimeter", "Zentimeter", "Meter", "Kilometer", "Meile", "Yard", "Fuß", "Zoll", "Seemeile");
    Object Millimeter = "Millimeter", Zentimeter = "Zentimeter", Meter = "Meter", Kilometer = "Kilometer", Meile = "Meile", Yard = "Yard", Fuss = "Fuß", Zoll = "Zoll", Seemeile = "Seemeile";
    String EMM, ECM, EM, EKM, EMeile, EY, EF, EZ, ESM;

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
     * Handelt die Checkbox Millimeter.
     */
    @FXML
    public void CBMM() {
        Methoden.CBChecked(CBMM, HMM, LMM, TFMM, VBoxErgebniss);
        ZahlerCBHandle(CBMM);
    }

    /**
     * Handelt die Checkbox Zentimeter.
     */
    @FXML
    public void CBCM() {
        Methoden.CBChecked(CBCM, HCM, LCM, TFCM, VBoxErgebniss);
        ZahlerCBHandle(CBCM);
    }

    /**
     * Handelt die Checkbox Meter.
     */
    @FXML
    public void CBM() {
        Methoden.CBChecked(CBM, HM, LM, TFM, VBoxErgebniss);
        ZahlerCBHandle(CBM);
    }

    /**
     * Handelt die Checkbox Kilometer.
     */
    @FXML
    public void CBKM() {
        Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
        ZahlerCBHandle(CBKM);
    }

    /**
     * Handelt die Checkbox Meile.
     */
    @FXML
    public void CBMeile() {
        Methoden.CBChecked(CBMeile, HMeile, LMeile, TFMeile, VBoxErgebniss);
        ZahlerCBHandle(CBMeile);
    }

    /**
     * Handelt die Checkbox Yard.
     */
    @FXML
    public void CBY() {
        Methoden.CBChecked(CBY, HY, LY, TFY, VBoxErgebniss);
        ZahlerCBHandle(CBY);
    }

    /**
     * Handelt die Checkbox Fuß.
     */
    @FXML
    public void CBF() {
        Methoden.CBChecked(CBF, HF, LF, TFF, VBoxErgebniss);
        ZahlerCBHandle(CBF);
    }

    /**
     * Handelt die Checkbox Zoll.
     */
    @FXML
    public void CBZ() {
        Methoden.CBChecked(CBZ, HZ, LZ, TFZ, VBoxErgebniss);
        ZahlerCBHandle(CBZ);
    }

    /**
     * Handelt die Checkbox Seemeile.
     */
    @FXML
    public void CBSM() {
        Methoden.CBChecked(CBSM, HSM, LSM, TFSM, VBoxErgebniss);
        ZahlerCBHandle(CBSM);
    }

    /**
     * Steuerung des Buttons "Umrechnen".
     * Überprüft zuerst mithilfe von Methoden.CheckIfDez ob die Eingabe eine Dezimalzahl ist und ruft falls ja die Methode
     * FromMeter auf um die Umrechnung zu starten.
     *
     * @see Methoden
     */
    @FXML
    public void BtnCalc() {
        int b = Methoden.checkIfDez(TFStart.getText());
        if (b == 2) {
            TFMM.setText(Methoden.KeineDez);
            TFCM.setText(Methoden.KeineDez);
            TFM.setText(Methoden.KeineDez);
            TFKM.setText(Methoden.KeineDez);
            TFMeile.setText(Methoden.KeineDez);
            TFY.setText(Methoden.KeineDez);
            TFF.setText(Methoden.KeineDez);
            TFZ.setText(Methoden.KeineDez);
            TFSM.setText(Methoden.KeineDez);
        } else if (b == 1) {
            FromMeter(ToMeter(Double.parseDouble(TFStart.getText()), CBEingabe.getValue()));
        }
    }

    /**
     * Rechnet die Eingabe des Nutzers von der gewählten Einheit in Meter um und gibt dem Wert zurück.
     *
     * @param Input   Der vom Nutzer eingebene Wert als Double.
     * @param Auswahl Die vom Nutzer gewählte Einheit.
     * @return Umrechnung in Meter.
     */
    public double ToMeter(double Input, Object Auswahl) {
        if (Auswahl.equals(Millimeter)) {
            return Input / 1000;
        } else if (Auswahl.equals(Zentimeter)) {
            return Input / 100;
        } else if (Auswahl.equals(Meter)) {
            return Input;
        } else if (Auswahl.equals(Kilometer)) {
            return Input * 1000;
        } else if (Auswahl.equals(Meile)) {
            return Input * 1609.34;
        } else if (Auswahl.equals(Yard)) {
            return Input * 0.9144;
        } else if (Auswahl.equals(Fuss)) {
            return Input * 0.3048;
        } else if (Auswahl.equals(Zoll)) {
            return Input * 0.0254;
        } else if (Auswahl.equals(Seemeile)) {
            return Input * 1852;
        } else return 0;
    }

    /**
     * Methode die die Werte von Sekunden in die einzelnen Einheiten umrechnet und in die Ergebnissvariablen gesetzt.
     * Danach wird die Formatierungsmethode aufgerufen (falls die Radio Box nicht angewählt wurde) und dananch die Werte der Ergebnissvariablen in die entsprechenden Textfelder gesetzt.
     *
     * @param meter Der in Sekunden umgerechnete Eingabewert.
     */
    public void FromMeter(Double meter) {
        EMM = Double.toString(meter * 1000);
        ECM = Double.toString(meter * 100);
        EM = Double.toString(meter);
        EKM = Double.toString(meter / 1000);
        EMeile = Double.toString(meter / 1609.34);
        EY = Double.toString(meter / 0.9144);
        EF = Double.toString(meter / 0.3048);
        EZ = Double.toString(meter / 0.0254);
        ESM = Double.toString(meter / 1852);
        if (RadioSelected == 1) {
            Formatierung();
        }
        ErgebnissSetzen();
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EMM = format.format(Double.parseDouble(EMM));
        ECM = format.format(Double.parseDouble(ECM));
        EM = format.format(Double.parseDouble(EM));
        EKM = format.format(Double.parseDouble(EKM));
        EMeile = format.format(Double.parseDouble(EMeile));
        EY = format.format(Double.parseDouble(EY));
        EF = format.format(Double.parseDouble(EF));
        EZ = format.format(Double.parseDouble(EZ));
        ESM = format.format(Double.parseDouble(ESM));
    }

    /**
     * Setzt in die Ergebnisstextfields die jeweiligen Ergebnisse die vorher von Umrechnen() errechnet wurden.
     */
    public void ErgebnissSetzen() {
        TFMM.setText(EMM);
        TFCM.setText(ECM);
        TFM.setText(EM);
        TFKM.setText(EKM);
        TFMeile.setText(EMeile);
        TFY.setText(EY);
        TFF.setText(EF);
        TFZ.setText(EZ);
        TFSM.setText(ESM);

    }

    /**
     * Bringt den Nutzer zurück zur Übersicht.
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
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
        zahlercb = 9;
        CBMM.setSelected(true);
        Methoden.CBChecked(CBMM, HMM, LMM, TFMM, VBoxErgebniss);
        CBCM.setSelected(true);
        Methoden.CBChecked(CBCM, HCM, LCM, TFCM, VBoxErgebniss);
        CBM.setSelected(true);
        Methoden.CBChecked(CBM, HM, LM, TFM, VBoxErgebniss);
        CBKM.setSelected(true);
        Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
        CBMeile.setSelected(true);
        Methoden.CBChecked(CBMeile, HMeile, LMeile, TFMeile, VBoxErgebniss);
        CBY.setSelected(true);
        Methoden.CBChecked(CBY, HY, LY, TFY, VBoxErgebniss);
        CBF.setSelected(true);
        Methoden.CBChecked(CBF, HF, LF, TFF, VBoxErgebniss);
        CBZ.setSelected(true);
        Methoden.CBChecked(CBZ, HZ, LZ, TFZ, VBoxErgebniss);
        CBSM.setSelected(true);
        Methoden.CBChecked(CBSM, HSM, LSM, TFSM, VBoxErgebniss);


    }

    /**
     * Versteckt alle HBoxen, Labels und TextFields der Ergebnisseite (und setzt den Zähler auf 0)
     *
     * @see Methoden
     */
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            zahlercb = 0;
            CBMM.setSelected(false);
            Methoden.CBChecked(CBMM, HMM, LMM, TFMM, VBoxErgebniss);
            CBCM.setSelected(false);
            Methoden.CBChecked(CBCM, HCM, LCM, TFCM, VBoxErgebniss);
            CBM.setSelected(false);
            Methoden.CBChecked(CBM, HM, LM, TFM, VBoxErgebniss);
            CBKM.setSelected(false);
            Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
            CBMeile.setSelected(false);
            Methoden.CBChecked(CBMeile, HMeile, LMeile, TFMeile, VBoxErgebniss);
            CBY.setSelected(false);
            Methoden.CBChecked(CBY, HY, LY, TFY, VBoxErgebniss);
            CBF.setSelected(false);
            Methoden.CBChecked(CBF, HF, LF, TFF, VBoxErgebniss);
            CBZ.setSelected(false);
            Methoden.CBChecked(CBZ, HZ, LZ, TFZ, VBoxErgebniss);
            CBSM.setSelected(false);
            Methoden.CBChecked(CBSM, HSM, LSM, TFSM, VBoxErgebniss);
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
        CBEingabe.setItems(optionsLange);
        CBEingabe.getSelectionModel().select(Meter);
        zahlercb = 1;
        BtnVerstecken();
    }
}
