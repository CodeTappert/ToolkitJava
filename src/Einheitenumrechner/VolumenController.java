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
 * Klasse für die Umrechnung verschiedener Volumeneinheiten.
 * <p></p>
 * Quelle für die Umrechnungsdaten ist der Google Einheitenumrechner: https://www.google.de/search?q=Einheitenumrechner
 * Erklärung der Abkürzungen:
 * ML = Milliliter,L = Liter, KCM = Kubikzentimeter, KM = Kubikmeter, EL = Esslöfel (britisch), TL = Esslöfel (britisch)
 * Cup = Cup (britisch)
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class VolumenController implements Initializable {


    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    TextField TFStart, TFML, TFL, TFKCM, TFKM, TFEL, TFTL, TFCup;
    @FXML
    CheckBox CBML, CBL, CBKCM, CBKM, CBEL, CBTL, CBCup;
    @FXML
    ComboBox CBEingabe;
    @FXML
    HBox HML, HL, HKCM, HKM, HEL, HTL, HCup;
    @FXML
    Label LML, LL, LKCM, LKM, LEL, LTL, LCup;
    @FXML
    VBox VBoxErgebniss;
    Object Milliliter = "Milliliter", Liter = "Liter", Kubikzentimeter = "Kubikzentimeter", Kubikmeter = "Kubikmeter", Teeloefel = "Teeloefel", Essloefel = "Essloefel", Cup = "Cup";
    ObservableList<String> optionsVolume = FXCollections.observableArrayList("Milliliter", "Liter", "Kubikzentimeter", "Kubikmeter", "Essloefel", "Teeloefel", "Cup");
    static int zahlercb = 0;

    /**
     * Methode welche die Anzahl der angewählten Checkboxen verwaltet.
     *
     * @param CB Die Checkbox welche die Methode aufruft.
     */
    public static void ZahlerCBHandle(CheckBox CB) {
        if (CB.isSelected()) {
            zahlercb++;
        } else {
            zahlercb--;
        }
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
     * Methode um alle Ausgabefelder einzublenden.
     * Setzt zahlercb auf 7.
     * Entfernt aber zuerst alle durch den Aufruf von BtnVerstecken um eine Fehlermeldung zu vermeiden.
     *
     * @see Methoden
     */
    @FXML
    public void BtnAnzeigen() {
        BtnVerstecken();
        zahlercb = 7;
        CBML.setSelected(true);
        Methoden.CBChecked(CBML, HML, LML, TFML, VBoxErgebniss);
        CBL.setSelected(true);
        Methoden.CBChecked(CBL, HL, LL, TFL, VBoxErgebniss);
        CBKCM.setSelected(true);
        Methoden.CBChecked(CBKCM, HKCM, LKCM, TFKCM, VBoxErgebniss);
        CBKM.setSelected(true);
        Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
        CBTL.setSelected(true);
        Methoden.CBChecked(CBTL, HTL, LTL, TFTL, VBoxErgebniss);
        CBEL.setSelected(true);
        Methoden.CBChecked(CBEL, HEL, LEL, TFEL, VBoxErgebniss);
        CBCup.setSelected(true);
        Methoden.CBChecked(CBCup, HCup, LCup, TFCup, VBoxErgebniss);
    }

    /**
     * Methode um alle Ausgabefelder auszublenden.
     * Setzt zahlercb auf 0
     *
     * @see Methoden
     */
    @FXML
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            zahlercb = 0;
            CBML.setSelected(false);
            Methoden.CBChecked(CBML, HML, LML, TFML, VBoxErgebniss);
            CBL.setSelected(false);
            Methoden.CBChecked(CBL, HL, LL, TFL, VBoxErgebniss);
            CBKCM.setSelected(false);
            Methoden.CBChecked(CBKCM, HKCM, LKCM, TFKCM, VBoxErgebniss);
            CBKM.setSelected(false);
            Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
            CBTL.setSelected(false);
            Methoden.CBChecked(CBTL, HTL, LTL, TFTL, VBoxErgebniss);
            CBEL.setSelected(false);
            Methoden.CBChecked(CBEL, HEL, LEL, TFEL, VBoxErgebniss);
            CBCup.setSelected(false);
            Methoden.CBChecked(CBCup, HCup, LCup, TFCup, VBoxErgebniss);
        }
    }

    /**
     * Methode welche den Umrechnungsknopf handelt.
     * Startet die Umrechnung durch den Funktionsaufruf.
     */
    @FXML
    public void BtnCalc() {
        if (zahlercb != 0) {
            Umrechnen();
        }

    }

    /**
     * Methode welche erst überprüft ob die Eingabe eine Dezimalzahl ist (mithilfe Methoden.CheckIfDez) und dann
     * die Umrechnungsfunktionen aufruft.
     * Methoden.KeineDez ist dabei die Standardausgabe falls Zeichen außer 0-9 genutzt werden.
     */
    public void Umrechnen() {
        int b = Methoden.checkIfDez(TFStart.getText());
        if (b == 2) {
            TFML.setText(Methoden.KeineDez);
            TFL.setText(Methoden.KeineDez);
            TFKCM.setText(Methoden.KeineDez);
            TFKM.setText(Methoden.KeineDez);
            TFEL.setText(Methoden.KeineDez);
            TFTL.setText(Methoden.KeineDez);
            TFCup.setText(Methoden.KeineDez);
        } else if (b == 1) FromLiter(ToLiter(Double.parseDouble(TFStart.getText()), CBEingabe.getValue()));
    }

    /**
     * Methode welche den eingegebenen Wert des Nutzers von der gewählten Einheit in Liter umwandelt.
     *
     * @param eingabe Der vom Nutzer eingegebene Wert als Double
     * @param Auswahl Die vom Nutzer getätigte Wahl der Eingabeeinheit.
     * @return Den vom Nutzer eingegebenen Wert in Litern.
     */
    public double ToLiter(Double eingabe, Object Auswahl) {
        if (Auswahl.equals(Milliliter)) {
            return eingabe / 1000;
        }
        if (Auswahl.equals(Liter)) {
            return eingabe;
        }
        if (Auswahl.equals(Kubikzentimeter)) {
            return eingabe / 1000;
        }
        if (Auswahl.equals(Kubikmeter)) {
            return eingabe * 1000;
        }
        if (Auswahl.equals(Teeloefel)) {
            return eingabe / 168.936;
        }
        if (Auswahl.equals(Essloefel)) {
            return eingabe / 56.3121;
        }
        if (Auswahl.equals(Cup)) {
            return eingabe / 3.51951;
        } else {
            return 0;
        }
    }

    /**
     * Methode welche die in Liter umgerechnete Einheit in allen Zieleinheiten, in den entsprechenden TextFeldern
     * ausgibt.
     *
     * @param eingabe Literwert als Double.
     */
    public void FromLiter(Double eingabe) {
        TFML.setText(String.valueOf((eingabe * 1000)));
        TFL.setText(String.valueOf(eingabe));
        TFKCM.setText(String.valueOf(eingabe * 1000));
        String EKM = String.valueOf(eingabe / 1000);
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EKM = format.format(Double.parseDouble(EKM));
        TFKM.setText(EKM);
        TFTL.setText(String.valueOf(eingabe * 168.936));
        TFEL.setText(String.valueOf(eingabe * 56.312));
        TFCup.setText(String.valueOf(eingabe * 3.51951));

    }

    /**
     * Methode die die Checkbox Milliliter handelt.
     */
    public void CBML() {
        Methoden.CBChecked(CBML, HML, LML, TFML, VBoxErgebniss);
        ZahlerCBHandle(CBML);

    }

    /**
     * Methode die die Checkbox Liter handelt.
     */
    public void CBL() {
        Methoden.CBChecked(CBL, HML, LML, TFML, VBoxErgebniss);
        ZahlerCBHandle(CBL);
    }

    /**
     * Methode die die Checkbox Kubikzentimeter handelt.
     */
    public void CBKCM() {
        Methoden.CBChecked(CBKCM, HKCM, LKCM, TFKCM, VBoxErgebniss);
        ZahlerCBHandle(CBKCM);
    }

    /**
     * Methode die die Checkbox Kubikmeter handelt.
     */
    public void CBKM() {
        Methoden.CBChecked(CBKM, HKM, LKM, TFKM, VBoxErgebniss);
        ZahlerCBHandle(CBKM);
    }

    /**
     * Methode die die Checkbox Teelöfel(britisch) handelt.
     */
    public void CBTL() {
        Methoden.CBChecked(CBTL, HTL, LTL, TFTL, VBoxErgebniss);
        ZahlerCBHandle(CBTL);
    }

    /**
     * Methode die die Checkbox Esslöfel(britisch) handelt.
     */
    public void CBEL() {
        Methoden.CBChecked(CBEL, HEL, LEL, TFEL, VBoxErgebniss);
        ZahlerCBHandle(CBEL);
    }

    /**
     * Methode die die Checkbox Cups(britisch) handelt.
     */
    public void CBCup() {
        Methoden.CBChecked(CBCup, HCup, LCup, TFCup, VBoxErgebniss);
        ZahlerCBHandle(CBCup);
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
        CBEingabe.setItems(optionsVolume);
        CBEingabe.getSelectionModel().selectFirst();
    }
}
