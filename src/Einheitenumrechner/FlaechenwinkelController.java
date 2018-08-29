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
 * Klasse welche die FXML Datei FlaechenwinkelRechner steuert.
 * <p></p>
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class FlaechenwinkelController implements Initializable {

    static int zahlercb = 0;
    @FXML
    Button BtnBack, BtnCalc, BtnAnzeigen, BtnVerstecken;
    @FXML
    Label LGrad, LGon, LRadiant, LStrich, LWinkelminute, LWinkelsekunde;
    @FXML
    TextField TFStart, TFGrad, TFGon, TFRadiant, TFStrich, TFWinkelminute, TFWinkelsekunde;
    @FXML
    HBox HGrad, HGon, HRadiant, HStrich, HWinkelminute, HWinkelsekunde;
    @FXML
    VBox VBoxErgebniss;
    @FXML
    ComboBox<String> CBEingabe;
    @FXML
    CheckBox CBGrad, CBGon, CBRadiant, CBStrich, CBWinkelminute, CBWinkelsekunde;
    @FXML
    RadioButton RadioExp;
    ObservableList<String> optionsWinkel = FXCollections.observableArrayList("Grad", "Gon", "Radiant", "Strich", "Winkelminute", "Winkelsekunde");
    Object Grad = "Grad", Gon = "Gon", Radiant = "Radiant", Strich = "Strich", Winkelminute = "Winkelminute", Winkelsekunde = "Winkelsekunde";
    double DEGrad, DEGon, DERadiant, DEStrich, DEWinkelminute, DEWinkelsekunde;
    int RadioSelected = 1;
    String EGrad, EGon, ERadiant, EStrich, EWinkelminute, EWinkelsekunde;

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
     * Bei Druck des Buttons Umrechnen wird die Methode umrechnen aufgerufen.
     */
    @FXML
    public void BtnCalc() {
        Umrechnen(TFStart.getText(), CBEingabe.getValue());
    }

    /**
     * Ruft zuerst BtnVerstecken auf, zwecks Fehlervemeidung, und lässt dann alle Ergebniss- Labels, TextFields und HBoxen anzeigen.
     */
    @FXML
    public void BtnAnzeigen() {
        BtnVerstecken();
        CBGrad.setSelected(true);
        Methoden.CBChecked(CBGrad, HGrad, LGrad, TFGrad, VBoxErgebniss);
        CBGon.setSelected(true);
        Methoden.CBChecked(CBGon, HGon, LGon, TFGon, VBoxErgebniss);
        CBRadiant.setSelected(true);
        Methoden.CBChecked(CBRadiant, HRadiant, LRadiant, TFRadiant, VBoxErgebniss);
        CBStrich.setSelected(true);
        Methoden.CBChecked(CBStrich, HStrich, LStrich, TFStrich, VBoxErgebniss);
        CBWinkelminute.setSelected(true);
        Methoden.CBChecked(CBWinkelminute, HWinkelminute, LWinkelminute, TFWinkelminute, VBoxErgebniss);
        CBWinkelsekunde.setSelected(true);
        Methoden.CBChecked(CBWinkelsekunde, HWinkelsekunde, LWinkelsekunde, TFWinkelsekunde, VBoxErgebniss);
        zahlercb = 6;
    }

    /**
     * Versteckt alle Ergebniss- Labels, TextFields und HBoxen sofern mindestens eine angezeigt wurde.
     */
    @FXML
    public void BtnVerstecken() {
        if (zahlercb > 0) {
            CBGrad.setSelected(false);
            Methoden.CBChecked(CBGrad, HGrad, LGrad, TFGrad, VBoxErgebniss);
            CBGon.setSelected(false);
            Methoden.CBChecked(CBGon, HGon, LGon, TFGon, VBoxErgebniss);
            CBRadiant.setSelected(false);
            Methoden.CBChecked(CBRadiant, HRadiant, LRadiant, TFRadiant, VBoxErgebniss);
            CBStrich.setSelected(false);
            Methoden.CBChecked(CBStrich, HStrich, LStrich, TFStrich, VBoxErgebniss);
            CBWinkelminute.setSelected(false);
            Methoden.CBChecked(CBWinkelminute, HWinkelminute, LWinkelminute, TFWinkelminute, VBoxErgebniss);
            CBWinkelsekunde.setSelected(false);
            Methoden.CBChecked(CBWinkelsekunde, HWinkelsekunde, LWinkelsekunde, TFWinkelsekunde, VBoxErgebniss);
            zahlercb = 0;
        }


    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBGrad() {
        Methoden.CBChecked(CBGrad, HGrad, LGrad, TFGrad, VBoxErgebniss);
        ZahlerCBHandle(CBGrad);
    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBGon() {
        Methoden.CBChecked(CBGon, HGon, LGon, TFGon, VBoxErgebniss);
        ZahlerCBHandle(CBGon);
    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBRadiant() {
        Methoden.CBChecked(CBRadiant, HRadiant, LRadiant, TFRadiant, VBoxErgebniss);
        ZahlerCBHandle(CBRadiant);
    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBStrich() {
        Methoden.CBChecked(CBStrich, HStrich, LStrich, TFStrich, VBoxErgebniss);
        ZahlerCBHandle(CBStrich);
    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBWinkelminute() {
        Methoden.CBChecked(CBWinkelminute, HWinkelminute, LWinkelminute, TFWinkelminute, VBoxErgebniss);
        ZahlerCBHandle(CBWinkelminute);
    }

    /**
     * Steuert was passiert wenn die Checkbox geklickt wird. Außerdem ändert es zahlercb entsprechend.
     *
     * @see Methoden
     */
    @FXML
    public void CBWinkelsekunde() {
        Methoden.CBChecked(CBWinkelsekunde, HWinkelsekunde, LWinkelsekunde, TFWinkelsekunde, VBoxErgebniss);
        ZahlerCBHandle(CBWinkelsekunde);
    }

    /**
     * Setzt in die Ergebnisstextfields die jeweiligen Ergebnisse die vorher von Umrechnen() errechnet wurden.
     */
    public void TextSetzen() {
        TFGrad.setText(EGrad);
        TFGon.setText(EGon);
        TFRadiant.setText(ERadiant);
        TFStrich.setText(EStrich);
        TFWinkelminute.setText(EWinkelminute);
        TFWinkelsekunde.setText(EWinkelsekunde);
    }

    /**
     * Setzt in das angegebene TextField den String Methoden.KeineDez.
     *
     * @param TF TextField in welches die Methode gesetzt werden soll.
     */
    public void FehlerSetzen(TextField TF) {
        TF.setText(Methoden.KeineDez);

    }

    /**
     * Überprüft zuerst ob der eigegebene String einer ganzahligen Dezimalzahl entspricht. Falls ja wird je nach ausgewählter Starteinheit
     * die Eingabe in alle Zieleinheiten umgerechnet und diese Ergebnisse den Ergebnissvariablen (als Double) DE**** (*** steht für die jeweilige Einheit) zugewiesen.
     * Sollte der Radio Button nicht ausgewählt sein wird Formatierung() aufgerufen.
     * Danach wird die Methode TextSetzen() aufgerufen.
     * Falls allerdings die Eingabe keine ganzahlige Dezimalzahl war wird FehlerSetzen() aufgerufen.
     *
     * @param Eingabe Vom Nutzer in TFStart eingegebener String.
     * @param Auswahl Vom Nutzer in CBEingabe ausgewählte Einheit.
     */
    public void Umrechnen(String Eingabe, Object Auswahl) {
        //Ueberpruefen ob Eingabe Dezimalzahl.
        int isDez = Methoden.checkIfDez(Eingabe);
        //Rechnen
        if (isDez == 1) {
            double EingabeD = Double.parseDouble(Eingabe);
            if (Auswahl.equals(Grad)) {
                DEGrad = EingabeD;
                DEGon = EingabeD * 1.1111;
                DERadiant = EingabeD / 57.2958;
                DEStrich = EingabeD * 17.4533;
                DEWinkelminute = EingabeD * 60;
                DEWinkelsekunde = 3600;
            } else if (Auswahl.equals(Gon)) {
                DEGrad = EingabeD / 1.1111;
                DEGon = EingabeD;
                DERadiant = EingabeD / 63.662;
                DEStrich = EingabeD * 15.708;
                DEWinkelminute = EingabeD * 54;
                DEWinkelsekunde = EingabeD * 3240;
            } else if (Auswahl.equals(Radiant)) {
                DEGrad = EingabeD * 57.2958;
                DEGon = EingabeD * 63.662;
                DERadiant = EingabeD;
                DEStrich = EingabeD * 1000;
                DEWinkelminute = EingabeD * 3437.75;
                DEWinkelsekunde = EingabeD * 206265;
            } else if (Auswahl.equals(Strich)) {
                DEGrad = EingabeD / 17.4533;
                DEGon = EingabeD / 15.708;
                DERadiant = EingabeD / 1000;
                DEStrich = EingabeD;
                DEWinkelminute = EingabeD * 3.43775;
                DEWinkelsekunde = EingabeD * 206.265;
            } else if (Auswahl.equals(Winkelminute)) {
                DEGrad = EingabeD / 60;
                DEGon = EingabeD / 54;
                DERadiant = EingabeD / 3437.75;
                DEStrich = EingabeD / 3.43775;
                DEWinkelminute = EingabeD;
                DEWinkelsekunde = EingabeD * 60;
            } else if (Auswahl.equals(Winkelsekunde)) {
                DEGrad = EingabeD / 3600;
                DEGon = EingabeD / 3240;
                DERadiant = EingabeD / 206265;
                DEStrich = EingabeD / 206.265;
                DEWinkelminute = EingabeD / 60;
                DEWinkelsekunde = EingabeD;
            }
            if (RadioSelected == 1) {
                Formatierung();
            } else {
                EGrad = Double.toString(DEGrad);
                EGon = Double.toString(DEGon);
                ERadiant = Double.toString(DERadiant);
                EStrich = Double.toString(DEStrich);
                EWinkelminute = Double.toString(DEWinkelminute);
                EWinkelsekunde = Double.toString(DEWinkelsekunde);
            }
            //Ergebnisse Setzen
            TextSetzen();
        } else {
            //Fehlermeldung setzen
            FehlerSetzen(TFGrad);
            FehlerSetzen(TFGon);
            FehlerSetzen(TFRadiant);
            FehlerSetzen(TFStrich);
            FehlerSetzen(TFWinkelminute);
            FehlerSetzen(TFWinkelsekunde);
        }
    }

    /**
     * Formatierungsmethode welche Dezimalzahlen  ohne Exponent darstellen lässt.
     */
    public void Formatierung() {
        DecimalFormat format = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setMaximumFractionDigits(20);
        EGrad = format.format(DEGrad);
        EGon = format.format(DEGon);
        ERadiant = format.format(DERadiant);
        EStrich = format.format(DEStrich);
        EWinkelminute = format.format(DEWinkelminute);
        EWinkelsekunde = format.format(DEWinkelsekunde);
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
        CBEingabe.setItems(optionsWinkel);
        CBEingabe.getSelectionModel().selectFirst();

    }
}
