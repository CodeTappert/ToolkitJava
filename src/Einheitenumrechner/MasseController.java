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
 * Klasse welche die FXML Datei zum Masse Umrechner steuert.
 * <p></p>
 * Erklärung der Abkürzungen:
 * Mik = Mikrogramm, Mil = Milligramm, G = Gramm, KG = Kilogramm, T = Tonne, U = Unze, P = Pfund.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class MasseController implements Initializable {
    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    TextField TFMik, TFStart, TFMil, TFG, TFKG, TFT, TFU, TFP;
    @FXML
    ComboBox CBEingabe;
    @FXML
    CheckBox CBMik, CBMil, CBG, CBKG, CBT, CBU, CBP;
    @FXML
    Label LMik, LMil, LG, LKG, LT, LU, LP;
    @FXML
    HBox HMik, HMil, HG, HKG, HT, HU, HP;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    RadioButton RadioExp;
    Object Mikrogramm = "Mikrogramm", Milligramm = "Milligramm", Gramm = "Gramm", Kilogramm = "Kilogramm", Tonne = "Tonne", Unze = "Unze", Pfund = "Pfund";
    ObservableList<String> optionsMass = FXCollections.observableArrayList("Mikrogramm", "Milligramm", "Gramm", "Kilogramm", "Tonne", "Unze", "Pfund");
    static int zahlercb;
    int RadioSelected = 1;
    String EMik, EMil, EG, EKG, ET, EU, EP;

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
     * Methode um zurück zur Übersicht zu gelangen.
     *
     * @see Methoden
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
    }

    /**
     * Methode zum Handeln des "Umrechnen"-Buttons.
     */
    @FXML
    public void BtnCalc() {
        Umrechnen();
    }

    /**
     * Methode die zuerst mithilfe von Methoden.CheckIfDez überprüft ob der Nutzer eine ganzzahlige Dezimalzahl eingegeben hat.
     * Falls nein wird die dies Ausgegeben und falls ja die Methode FromGramm aufgerufen.
     *
     * @see Methoden
     */
    public void Umrechnen() {
        if (Methoden.checkIfDez(TFStart.getText()) == 2) {
            TFMil.setText(Methoden.KeineDez);
            TFMik.setText(Methoden.KeineDez);
            TFKG.setText(Methoden.KeineDez);
            TFP.setText(Methoden.KeineDez);
            TFT.setText(Methoden.KeineDez);
            TFU.setText(Methoden.KeineDez);
            TFG.setText(Methoden.KeineDez);
        } else if (Methoden.checkIfDez(TFStart.getText()) == 1)
            FromGramm(ToGramm(Double.parseDouble(TFStart.getText()), CBEingabe.getValue()));
    }

    /**
     * Methode die die Werte von Sekunden in die einzelnen Einheiten umrechnet und in die Ergebnissvariablen gesetzt.
     * Danach wird die Formatierungsmethode aufgerufen (falls die Radio Box nicht angewählt wurde) und dananch die Werte der Ergebnissvariablen in die entsprechenden Textfelder gesetzt.
     *
     * @param Input Der in Sekunden umgerechnete Eingabewert.
     */
    public void FromGramm(Double Input) {
        EMik = String.valueOf(Input * 1000000);
        EMil = String.valueOf(Input * 1000);
        EG = String.valueOf(Input);
        EKG = String.valueOf(Input / 1000);
        ET = String.valueOf(Input / 1000000);
        EU = String.valueOf(Input / 28.3495);
        EP = String.valueOf(Input / 500);
        if (RadioSelected == 1) {
            Formatierung();
        }
        ErgebnisseSetzen();
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EMik = format.format(Double.parseDouble(EMik));
        EMil = format.format(Double.parseDouble(EMil));
        EG = format.format(Double.parseDouble(EG));
        EKG = format.format(Double.parseDouble(EKG));
        ET = format.format(Double.parseDouble(ET));
        EU = format.format(Double.parseDouble(EU));
        EP = format.format(Double.parseDouble(EP));
    }

    /**
     * Setzt in die Ergebnisstextfields die jeweiligen Ergebnisse die vorher von Umrechnen() errechnet wurden.
     */
    public void ErgebnisseSetzen() {
        TFMik.setText(EMik);
        TFMil.setText(EMil);
        TFG.setText(EG);
        TFKG.setText(EKG);
        TFT.setText(ET);
        TFU.setText(EU);
        TFP.setText(EP);
    }

    /**
     * Methode welche die Nutzereingabe von der gewählten Einheit in Gramm umwandelt.
     *
     * @param eingabe Nutuereingabe als Double
     * @param Auswahl Die in der Combobox gewählte Einheit.
     * @return Nutzereingabe als Double in Gramm umgerechnet.
     */
    public double ToGramm(Double eingabe, Object Auswahl) {
        if (Auswahl.equals(Mikrogramm)) {
            return eingabe / 1000000;
        } else if (Auswahl.equals(Milligramm)) {
            return eingabe / 1000;
        } else if (Auswahl.equals(Gramm)) {
            return eingabe;
        } else if (Auswahl.equals(Kilogramm)) {
            return eingabe * 1000;
        } else if (Auswahl.equals(Tonne)) {
            return eingabe * 1000000;
        } else if (Auswahl.equals(Unze)) {
            return eingabe * 28.3495;
        } else if (Auswahl.equals(Pfund)) {
            return eingabe * 500;
        } else {
            //KOmmt niemals vor muss aber da sein, damit kein Error ausgegeben wird.
            return 0;
        }
    }

    /**
     * Methode welche die Checkbox Mikrogramm handelt.
     */
    @FXML
    public void CBMik() {
        Methoden.CBChecked(CBMik, HMik, LMik, TFMik, VBoxErgebniss);
        ZahlerCBHandle(CBMik);
    }

    /**
     * Methode welche die Checkbox Milligramm handelt.
     */
    @FXML
    public void CBMil() {
        Methoden.CBChecked(CBMil, HMil, LMil, TFMil, VBoxErgebniss);
        ZahlerCBHandle(CBMil);
    }

    /**
     * Methode welche die Checkbox Gramm handelt.
     */
    @FXML
    public void CBG() {
        Methoden.CBChecked(CBG, HG, LG, TFG, VBoxErgebniss);
        ZahlerCBHandle(CBG);
    }

    /**
     * Methode welche die Checkbox Kilogramm handelt.
     */
    @FXML
    public void CBKG() {
        Methoden.CBChecked(CBKG, HKG, LKG, TFKG, VBoxErgebniss);
        ZahlerCBHandle(CBKG);
    }

    /**
     * Methode welche die Checkbox Tonne handelt.
     */
    @FXML
    public void CBT() {
        Methoden.CBChecked(CBT, HT, LT, TFT, VBoxErgebniss);
        ZahlerCBHandle(CBT);
    }

    /**
     * Methode welche die Checkbox Unze handelt.
     */
    @FXML
    public void CBU() {
        Methoden.CBChecked(CBU, HU, LU, TFU, VBoxErgebniss);
        ZahlerCBHandle(CBU);
    }

    /**
     * Methode welche die Checkbox Pfund handelt.
     */
    @FXML
    public void CBP() {
        Methoden.CBChecked(CBP, HP, LP, TFP, VBoxErgebniss);
        ZahlerCBHandle(CBP);
    }

    /**
     * Ruft zuerst die Methode BtnVerstecken auf um alles zu verstecken und lässt danach alle HBoxen, Labels und TextFields
     * erscheinen. Wählt auch alle Checkboxen an. Das Verstecken ist nötig um Fehler zu vermeiden.
     *
     * @see Methoden
     */
    @FXML
    public void BtnAnzeigen() {
        BtnVerstecken();
        zahlercb = 7;
        CBMik.setSelected(true);
        Methoden.CBChecked(CBMik, HMik, LMik, TFMik, VBoxErgebniss);
        CBMil.setSelected(true);
        Methoden.CBChecked(CBMil, HMil, LMil, TFMil, VBoxErgebniss);
        CBG.setSelected(true);
        Methoden.CBChecked(CBG, HG, LG, TFG, VBoxErgebniss);
        CBKG.setSelected(true);
        Methoden.CBChecked(CBKG, HKG, LKG, TFKG, VBoxErgebniss);
        CBT.setSelected(true);
        Methoden.CBChecked(CBT, HT, LT, TFT, VBoxErgebniss);
        CBU.setSelected(true);
        Methoden.CBChecked(CBU, HU, LU, TFU, VBoxErgebniss);
        CBP.setSelected(true);
        Methoden.CBChecked(CBP, HP, LP, TFP, VBoxErgebniss);
    }

    /**
     * Versteckt alle Ergebniss- HBoxen,Labels und Textfields. Entwählt auch die Checkboxen.
     *
     * @see Methoden
     */
    @FXML
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            zahlercb = 0;
            CBMik.setSelected(false);
            Methoden.CBChecked(CBMik, HMik, LMik, TFMik, VBoxErgebniss);
            CBMil.setSelected(false);
            Methoden.CBChecked(CBMil, HMil, LMil, TFMil, VBoxErgebniss);
            CBG.setSelected(false);
            Methoden.CBChecked(CBG, HG, LG, TFG, VBoxErgebniss);
            CBKG.setSelected(false);
            Methoden.CBChecked(CBKG, HKG, LKG, TFKG, VBoxErgebniss);
            CBT.setSelected(false);
            Methoden.CBChecked(CBT, HT, LT, TFT, VBoxErgebniss);
            CBU.setSelected(false);
            Methoden.CBChecked(CBU, HU, LU, TFU, VBoxErgebniss);
            CBP.setSelected(false);
            Methoden.CBChecked(CBP, HP, LP, TFP, VBoxErgebniss);

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
        CBEingabe.setItems(optionsMass);
        CBEingabe.getSelectionModel().select(Gramm);
    }
}
