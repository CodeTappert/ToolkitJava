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
 * Klasse für die Umrechnung verschiedener Zeiteinheiten.
 * <p></p>
 * Quelle fuer die Daten der Umrechnung: https://www.convertworld.com/de/zeit/
 * <p></p>
 * <p>
 * Erklärung der Abkürzungen: <br>
 * NSek = Nanosekunden, MiSek = Mikrosekunden, MilSek = Millisekunden, Sek = Sekunden, Min = Minuten, Hour = Stunden,
 * Day = Tage, Week = Wochen, Month = Monate, Year = Jahre, JZehnt = Jahrzehnt, JH = Jahrhundert
 *
 * @author Jannik Tappert
 * @version 1.0
 */

public class ZeitController implements Initializable {

    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    TextField TFStart;
    @FXML
    ComboBox CBEingabe;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    CheckBox CBNSek, CBMiSek, CBMilSek, CBSek, CBMin, CBHour, CBDay, CBWeek, CBMonth, CBYear, CBJZehnt, CBJH;
    @FXML
    HBox HNSek, HMiSek, HMilSek, HSek, HMin, HHour, HDay, HWeek, HMonth, HYear, HJZehnt, HJH;
    @FXML
    Label LNSek, LMiSek, LMilSek, LSek, LMin, LHour, LDay, LWeek, LMonth, LYear, LJZehnt, LJH;
    @FXML
    TextField TFNSek, TFMiSek, TFMilSek, TFSek, TFMin, TFHour, TFDay, TFWeek, TFMonth, TFYear, TFJZehnt, TFJH;
    @FXML
    RadioButton RadioExp;
    Object Nanosekunden = "Nanosekunden", Mikrosekunden = "Mikrosekunden", Millisekunden = "Millisekunden", Sekunden = "Sekunden", Minuten = "Minuten", Stunden = "Stunden", Tage = "Tage", Wochen = "Wochen", Monate = "Monate", Jahre = "Jahre", Jahrzehnte = "Jahrzehnte", Jahrhunderte = "Jahrhunderte";
    ObservableList<String> optionsTime = FXCollections.observableArrayList("Nanosekunden", "Mikrosekunden", "Millisekunden", "Sekunden", "Minuten", "Stunden", "Tage", "Wochen", "Monate", "Jahre", "Jahrzehnte", "Jahrhunderte");
    static int zahlercb = 0;
    int RadioSelected = 1;
    String ENSek, EMiSek, EMilSek, ESek, EMin, EHour, EDay, EWeek, EMonth, EYear, EJZehnt, EJH;

    /**
     * Methode welche sich um den Zähler kümmert der die Anzahl der angewählten Checkboxen beinhaltet.
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
     * Methode um alle Ausgabefelder einzublenden.
     * Entfernt aber zuerst alle durch den Aufruf von BtnVerstecken um eine Fehlermeldung zu vermeiden.
     */
    @FXML
    public void BtnAnzeigen() {
        BtnVerstecken();
        zahlercb = 12;
        CBNSek.setSelected(true);
        Methoden.CBChecked(CBNSek, HNSek, LNSek, TFNSek, VBoxErgebniss);
        CBMiSek.setSelected(true);
        Methoden.CBChecked(CBMiSek, HMiSek, LMiSek, TFMiSek, VBoxErgebniss);
        CBMilSek.setSelected(true);
        Methoden.CBChecked(CBMilSek, HMilSek, LMilSek, TFMilSek, VBoxErgebniss);
        CBSek.setSelected(true);
        Methoden.CBChecked(CBSek, HSek, LSek, TFSek, VBoxErgebniss);
        CBMin.setSelected(true);
        Methoden.CBChecked(CBMin, HMin, LMin, TFMin, VBoxErgebniss);
        CBHour.setSelected(true);
        Methoden.CBChecked(CBHour, HHour, LHour, TFHour, VBoxErgebniss);
        CBDay.setSelected(true);
        Methoden.CBChecked(CBDay, HDay, LDay, TFDay, VBoxErgebniss);
        CBWeek.setSelected(true);
        Methoden.CBChecked(CBWeek, HWeek, LWeek, TFWeek, VBoxErgebniss);
        CBMonth.setSelected(true);
        Methoden.CBChecked(CBMonth, HMonth, LMonth, TFMonth, VBoxErgebniss);
        CBYear.setSelected(true);
        Methoden.CBChecked(CBYear, HYear, LYear, TFYear, VBoxErgebniss);
        CBJZehnt.setSelected(true);
        Methoden.CBChecked(CBJZehnt, HJZehnt, LJZehnt, TFJZehnt, VBoxErgebniss);
        CBJH.setSelected(true);
        Methoden.CBChecked(CBJH, HJH, LJH, TFJH, VBoxErgebniss);
    }

    /**
     * Methode um alle Ausgabefelder auszublenden.
     */
    @FXML
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            zahlercb = 0;
            CBNSek.setSelected(false);
            Methoden.CBChecked(CBNSek, HNSek, LNSek, TFNSek, VBoxErgebniss);
            CBMiSek.setSelected(false);
            Methoden.CBChecked(CBMiSek, HMiSek, LMiSek, TFMiSek, VBoxErgebniss);
            CBMilSek.setSelected(false);
            Methoden.CBChecked(CBMilSek, HMilSek, LMilSek, TFMilSek, VBoxErgebniss);
            CBSek.setSelected(false);
            Methoden.CBChecked(CBSek, HSek, LSek, TFSek, VBoxErgebniss);
            CBMin.setSelected(false);
            Methoden.CBChecked(CBMin, HMin, LMin, TFMin, VBoxErgebniss);
            CBHour.setSelected(false);
            Methoden.CBChecked(CBHour, HHour, LHour, TFHour, VBoxErgebniss);
            CBDay.setSelected(false);
            Methoden.CBChecked(CBDay, HDay, LDay, TFDay, VBoxErgebniss);
            CBWeek.setSelected(false);
            Methoden.CBChecked(CBWeek, HWeek, LWeek, TFWeek, VBoxErgebniss);
            CBMonth.setSelected(false);
            Methoden.CBChecked(CBMonth, HMonth, LMonth, TFMonth, VBoxErgebniss);
            CBYear.setSelected(false);
            Methoden.CBChecked(CBYear, HYear, LYear, TFYear, VBoxErgebniss);
            CBJZehnt.setSelected(false);
            Methoden.CBChecked(CBJZehnt, HJZehnt, LJZehnt, TFJZehnt, VBoxErgebniss);
            CBJH.setSelected(false);
            Methoden.CBChecked(CBJH, HJH, LJH, TFJH, VBoxErgebniss);
        }
    }

    /**
     * Methode um zur Übersicht zurückzukehren.
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
     * Methode welche erst überprüft ob die Eingabe eine ganze Dezimalzahl ist (mithilfe Methoden.CheckifDez() und dann
     * die Umrechnung vornimmt.
     * Methoden.KeineDez ist dabei die Standardausabe wenn die Eingabe keine Dezimalzahl ist bzw. Zeichen außer 0-9
     * enthalten sind.
     */
    public void Umrechnen() {
        int b = Methoden.checkIfDez(TFStart.getText());
        if (b == 2) {
            TFNSek.setText(Methoden.KeineDez);
            TFMiSek.setText(Methoden.KeineDez);
            TFMilSek.setText(Methoden.KeineDez);
            TFSek.setText(Methoden.KeineDez);
            TFMin.setText(Methoden.KeineDez);
            TFHour.setText(Methoden.KeineDez);
            TFDay.setText(Methoden.KeineDez);
            TFWeek.setText(Methoden.KeineDez);
            TFMonth.setText(Methoden.KeineDez);
            TFYear.setText(Methoden.KeineDez);
            TFJZehnt.setText(Methoden.KeineDez);
            TFJH.setText(Methoden.KeineDez);
        } else if (b == 1) {
            FromSek(ToSek(Double.parseDouble(TFStart.getText()), CBEingabe.getValue()));
        }
    }

    /**
     * Methode die die Werte von Sekunden in die einzelnen Einheiten umrechnet und in die Ergebnissvariablen gesetzt.
     * Danach wird die Formatierungsmethode aufgerufen (falls die Radio Box nicht angewählt wurde) und dananch die Werte der Ergebnissvariablen in die entsprechenden Textfelder gesetzt.
     *
     * @param input Der in Sekunden umgerechnete Eingabewert.
     */
    public void FromSek(double input) {
        ENSek = Double.toString(input * 1000000000);
        EMiSek = Double.toString(input * 1000000);
        EMilSek = Double.toString(input * 1000);
        ESek = Double.toString(input);
        EMin = Double.toString(input / 60);
        EHour = Double.toString(input / 3600);
        EDay = Double.toString(input / 86400);
        EWeek = Double.toString(input / 86400 / 7);
        EMonth = Double.toString(input / 86400 / 30.4375);
        EYear = Double.toString(input / 86400 / 365.25);
        EJZehnt = Double.toString(input / 86400 / 365.25 / 10);
        EJH = Double.toString(input / 86400 / 365.25 / 100);
        System.out.println(RadioSelected);
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
        ENSek = format.format(Double.parseDouble(ENSek));
        EMiSek = format.format(Double.parseDouble(EMiSek));
        EMilSek = format.format(Double.parseDouble(EMilSek));
        ESek = format.format(Double.parseDouble(ESek));
        EMin = format.format(Double.parseDouble(EMin));
        EHour = format.format(Double.parseDouble(EHour));
        EDay = format.format(Double.parseDouble(EDay));
        EWeek = format.format(Double.parseDouble(EWeek));
        EMonth = format.format(Double.parseDouble(EMonth));
        EYear = format.format(Double.parseDouble(EYear));
        EJZehnt = format.format(Double.parseDouble(EJZehnt));
        EJH = format.format(Double.parseDouble(EJH));
    }

    /**
     * Setzt in die Ergebnisstextfields die jeweiligen Ergebnisse die vorher von Umrechnen() errechnet wurden.
     */
    public void ErgebnisseSetzen() {
        TFNSek.setText(ENSek);
        TFMiSek.setText(EMiSek);
        TFMilSek.setText(EMilSek);
        TFSek.setText(ESek);
        TFMin.setText(EMin);
        TFHour.setText(EHour);
        TFDay.setText(EDay);
        TFWeek.setText(EWeek);
        TFMonth.setText(EMonth);
        TFYear.setText(EYear);
        TFJZehnt.setText(EJZehnt);
        TFJH.setText(EJH);
    }

    /**
     * Methode um den eingegeben Wert in Sekunden umzurechnen.
     *
     * @param input   Der vom Nutzer eingegebe Wert als Double.
     * @param Auswahl Ausgewaehler Wert in der Combobox
     * @return Eingabewert in Sekunden
     */
    public double ToSek(double input, Object Auswahl) {
        if (Auswahl.equals(Nanosekunden)) {
            return input / 1000000000;
        }
        if (Auswahl.equals(Mikrosekunden)) {
            return input / 1000000;
        }
        if (Auswahl.equals(Millisekunden)) {
            return input / 1000;
        }
        if (Auswahl.equals(Sekunden)) {
            return input;
        }
        if (Auswahl.equals(Minuten)) {
            return input * 60;
        }
        if (Auswahl.equals(Stunden)) {
            return input * 3600;
        }
        if (Auswahl.equals(Tage)) {
            return input * 86400;
        }
        if (Auswahl.equals(Wochen)) {
            return input * 86400 * 7;
        }
        if (Auswahl.equals(Monate)) {
            return input * 86400 * 30.4375;
        }
        if (Auswahl.equals(Jahre)) {
            return input * 86400 * 365.25;
        }
        if (Auswahl.equals(Jahrzehnte)) {
            return input * 86400 * 365.25 * 10;
        }
        if (Auswahl.equals(Jahrhunderte)) {
            return input * 86400 * 365.25 * 100;
        } else {
            return 0;
        }
    }

    /**
     * Methode die die Checkbox Nanosekunden handelt.
     */
    public void CBNSek() {
        Methoden.CBChecked(CBNSek, HNSek, LNSek, TFNSek, VBoxErgebniss);
        ZahlerCBHandle(CBNSek);
    }

    /**
     * Methode die die Checkbox Mikrosekunden handelt.
     */
    public void CBMiSek() {
        Methoden.CBChecked(CBMiSek, HMiSek, LMiSek, TFMiSek, VBoxErgebniss);
        ZahlerCBHandle(CBMiSek);
    }

    /**
     * Methode die die Checkbox Millisekunden handelt.
     */
    public void CBMilSek() {
        Methoden.CBChecked(CBMilSek, HMilSek, LMilSek, TFMiSek, VBoxErgebniss);
        ZahlerCBHandle(CBMilSek);
    }

    /**
     * Methode die die Checkbox Sekunden handelt.
     */
    public void CBSek() {
        Methoden.CBChecked(CBSek, HSek, LSek, TFSek, VBoxErgebniss);
        ZahlerCBHandle(CBSek);
    }

    /**
     * Methode die die Checkbox Minuten handelt.
     */
    public void CBMin() {
        Methoden.CBChecked(CBMin, HMin, LMin, TFMin, VBoxErgebniss);
        ZahlerCBHandle(CBMin);
    }

    /**
     * Methode die die Checkbox Stunden handelt.
     */
    public void CBHour() {
        Methoden.CBChecked(CBHour, HHour, LHour, TFHour, VBoxErgebniss);
        ZahlerCBHandle(CBHour);
    }

    /**
     * Methode die die Checkbox Tage handelt.
     */
    public void CBDay() {
        Methoden.CBChecked(CBDay, HDay, LDay, TFDay, VBoxErgebniss);
        ZahlerCBHandle(CBDay);
    }

    /**
     * Methode die die Checkbox Wochen handelt.
     */
    public void CBWeek() {
        Methoden.CBChecked(CBWeek, HWeek, LWeek, TFWeek, VBoxErgebniss);
        ZahlerCBHandle(CBWeek);
    }

    /**
     * Methode die die Checkbox Monate handelt.
     */
    public void CBMonth() {
        Methoden.CBChecked(CBMonth, HMonth, LMonth, TFMonth, VBoxErgebniss);
        ZahlerCBHandle(CBMonth);
    }

    /**
     * Methode die die Checkbox Jahre handelt.
     */
    public void CBYear() {
        Methoden.CBChecked(CBYear, HYear, LYear, TFYear, VBoxErgebniss);
        ZahlerCBHandle(CBYear);
    }

    /**
     * Methode die die Checkbox Jahrzehnte handelt.
     */
    public void CBJZehnt() {
        Methoden.CBChecked(CBJZehnt, HJZehnt, LJZehnt, TFJZehnt, VBoxErgebniss);
        ZahlerCBHandle(CBJZehnt);
    }

    /**
     * Methode die die Checkbox Jahrhunderte handelt.
     */
    public void CBJH() {
        Methoden.CBChecked(CBJH, HJH, LJH, TFJH, VBoxErgebniss);
        ZahlerCBHandle(CBJH);
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
        CBEingabe.setItems(optionsTime);
        CBEingabe.getSelectionModel().selectFirst();

    }
}
