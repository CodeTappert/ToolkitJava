package Zahlensystemumrechner;

import Einheitenumrechner.Methoden;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasse zur Steuerung der FXML Datei Zahlenumrechner
 * <p></p>
 * <p>
 * Der Programmteil mit welchem sich die verschiedenen Zahlensysteme ineinander Umrechnen lassen.
 * Für genauere Erklärungen vom Vorgehen einzelner Methoden bitte in den Quellcode schauen.
 *
 * @author Jannik Tappert
 * @version 2.0
 */
public class ZahlenumrechnerController implements Initializable {
    //Deklaration aller benötigten Variablen
    @FXML
    Button BtnBack, BtnCalc;
    @FXML
    TextField TFStart, TFDezimal, TFBinaer, TFHexadezimal, TFOktal;
    @FXML
    ComboBox CBEingabe;
    Object Zahlensystem, Dezimal = "Dezimal", Binaer = "Binär", Hexadezimal = "Hexadezimal", Oktal = "Oktal";
    String Nutzereingabe, ErgebnissDezimal, ErgebnissBinaer, ErgebnissHexadezimal, ErgebnissOktal,
            NichtLegal = "Ihre Eingabe ist nicht legal.";
    int IsLegal, Legal;
    ObservableList<String> Systeme = FXCollections.observableArrayList("Dezimal", "Binär", "Hexadezimal", "Oktal");

    /**
     * Handelt den Button "Umrechnen" und ist quasi auch die Ablaufsteuerung, da es die einzelnen Methoden nacheinander aufruft.
     */
    @FXML
    public void BtnCalc() {
        CheckZahlensystem();
        Nutzereingabe = TFStart.getText();
        UmrechenAuswahl();
        //Wurde Bereits in die Textfelder gesetzt, dass die Eingabe nicht legal ist? Falls 0 dann nein. Falls 1 dann ja.
        if (Legal == 0) {
            ErgebnisseSetzen();
        }
        Legal = 0;
    }

    /**
     * Zurück ins Hauptmenue
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Hauptmenu/Hauptmenu.fxml");
    }

    /**
     * Überprüft welches Zahlensystem vom Nutzer ausgewählt wurde und speichert den Wert in der Variable "Zahlensystem".
     */
    public void CheckZahlensystem() {
        Zahlensystem = CBEingabe.getValue();
    }

    /**
     * Wählt entsprechend des Werten in "Zahlensystem" aus welche Rechnungsfunktion aufgerufen werden soll.
     */
    public void UmrechenAuswahl() {
        if (Zahlensystem.equals(Dezimal)) {
            RechneDezimal();
        } else if (Zahlensystem.equals(Binaer)) {
            RechneBinaer();
        } else if (Zahlensystem.equals(Hexadezimal)) {
            RechneHexadezimal();
        } else if (Zahlensystem.equals(Oktal))
            RechneOktal();
    }

    /**
     * Überprüft ob die Nutzereingabe für das entsprechende Zahlensystem legal ist. D.h. nur gültige Zeichen enthält.
     *
     * @param Auswahl Das Zahlensyste für welches die Eingabe überprüft werden soll.
     * @return 1 falls die Eingabe legal ist. 0 falls nicht.
     * @see Methoden
     */
    public int CheckIfEingabeIsLegal(Object Auswahl) {
        String Eingabe = TFStart.getText();
        if (TFStart.getText().isEmpty()) {
            return 2;
        } else {
            if (Auswahl.equals(Dezimal)) {
                return Methoden.checkIfDez(Eingabe);

            } else if (Auswahl.equals(Binaer)) {
                int IstBinaer = 1;
                for (int i = 0; i < Eingabe.length(); i++) {
                    //Binäres Zahlensystem erlaubt nur 0 und 1
                    if ((Character.getNumericValue(Eingabe.charAt(i)) != 0) && (Character.getNumericValue(Eingabe.charAt(i)) != 1)) {
                        IstBinaer = 0;
                    } else {
                    }
                }
                return IstBinaer;

            } else if (Auswahl.equals(Hexadezimal)) {
                int IstHexadezimal = 1;
                for (int i = 0; i < Eingabe.length(); i++) {
                    //Hexadezimales Zahlensystemerlaubt Zeichen von 0-9 sowie A-F
                    if (Eingabe.charAt(i) != 'A' && Eingabe.charAt(i) != 'B' && Eingabe.charAt(i) != 'C' && Eingabe.charAt(i) != 'D'
                            && Eingabe.charAt(i) != 'E' && Eingabe.charAt(i) != 'F' && Character.getNumericValue(Eingabe.charAt(i)) != 1
                            && Character.getNumericValue(Eingabe.charAt(i)) != 1 && Character.getNumericValue(Eingabe.charAt(i)) != 2
                            && Character.getNumericValue(Eingabe.charAt(i)) != 3 && Character.getNumericValue(Eingabe.charAt(i)) != 4
                            && Character.getNumericValue(Eingabe.charAt(i)) != 5 && Character.getNumericValue(Eingabe.charAt(i)) != 6
                            && Character.getNumericValue(Eingabe.charAt(i)) != 7 && Character.getNumericValue(Eingabe.charAt(i)) != 8
                            && Character.getNumericValue(Eingabe.charAt(i)) != 9 && Character.getNumericValue(Eingabe.charAt(i)) != 0) {
                        IstHexadezimal = 0;
                    } else {
                    }
                }
                return IstHexadezimal;

            } else if (Auswahl.equals(Oktal)) {
                int IstOktal = 1;
                //Oktales Zahlensystem erlaubt Zeichen von 0-7
                for (int i = 0; i < Eingabe.length(); i++) {
                    if (Character.getNumericValue(Eingabe.charAt(i)) != 0 && Character.getNumericValue(Eingabe.charAt(i)) != 1 && Character.getNumericValue(Eingabe.charAt(i)) != 2 &&
                            Character.getNumericValue(Eingabe.charAt(i)) != 3 && Character.getNumericValue(Eingabe.charAt(i)) != 4 && Character.getNumericValue(Eingabe.charAt(i)) != 5 &&
                            Character.getNumericValue(Eingabe.charAt(i)) != 6 && Character.getNumericValue(Eingabe.charAt(i)) != 7) {
                        IstOktal = 0;
                    }
                }
                return IstOktal;
            } else return 0;

        }
    }

    /**
     * Setzt den Inhalt des String "NichtLegal" in die ErgebnissTextfelder.
     */
    public void EingabeNichtLegal() {
        TFDezimal.setText(NichtLegal);
        TFBinaer.setText(NichtLegal);
        TFHexadezimal.setText(NichtLegal);
        TFOktal.setText(NichtLegal);
        Legal = 1;
    }

    /**
     * Pruft zuerst ob die Eingabe für das Zahlensystem legal ist.
     * Rechnet die Eingabe mithilfe der entsprechenden Methoden aus Umrechner aus und setzt die Ergebnisse in die Ergebnissvariablen.
     *
     * @see Umrechner
     */
    public void RechneDezimal() {
        IsLegal = CheckIfEingabeIsLegal(Dezimal);
        if (IsLegal == 1) {
            ErgebnissDezimal = Nutzereingabe;
            ErgebnissBinaer = Umrechner.DezInBin(Nutzereingabe);
            ErgebnissHexadezimal = Umrechner.DezInHex(Nutzereingabe);
            ErgebnissOktal = Umrechner.DezInOkt(Nutzereingabe);
        } else if (IsLegal == 0) {
            EingabeNichtLegal();
        } else if (IsLegal == 2) {
            IstLeer();
        }
        IsLegal = 0;
    }

    /**
     * Pruft zuerst ob die Eingabe für das Zahlensystem legal ist.
     * Rechnet die Eingabe mithilfe der entsprechenden Methoden aus Umrechner aus und setzt die Ergebnisse in die Ergebnissvariablen.
     *
     * @see Umrechner
     */
    public void RechneBinaer() {
        IsLegal = CheckIfEingabeIsLegal(Binaer);
        if (IsLegal == 1) {
            ErgebnissDezimal = Umrechner.BinInDez(Nutzereingabe);
            ErgebnissBinaer = Nutzereingabe;
            ErgebnissHexadezimal = Umrechner.BinInHex(Nutzereingabe);
            ErgebnissOktal = Umrechner.BinInOkt(Nutzereingabe);
        } else if (IsLegal == 0) {
            EingabeNichtLegal();
        } else if (IsLegal == 2) {
            IstLeer();
        }
        IsLegal = 0;
    }

    /**
     * Pruft zuerst ob die Eingabe für das Zahlensystem legal ist.
     * Rechnet die Eingabe mithilfe der entsprechenden Methoden aus Umrechner aus und setzt die Ergebnisse in die Ergebnissvariablen.
     *
     * @see Umrechner
     */
    public void RechneHexadezimal() {
        Nutzereingabe = Nutzereingabe.toUpperCase();
        TFStart.setText(Nutzereingabe);
        IsLegal = CheckIfEingabeIsLegal(Hexadezimal);
        if (IsLegal == 1) {
            ErgebnissDezimal = Umrechner.HexInDez(Nutzereingabe);
            ErgebnissBinaer = Umrechner.HexInBin(Nutzereingabe);
            ErgebnissHexadezimal = Nutzereingabe;
            ErgebnissOktal = Umrechner.HexInOkt(Nutzereingabe);
        } else if (IsLegal == 0) {
            EingabeNichtLegal();
        } else if (IsLegal == 2) {
            IstLeer();
        }
        IsLegal = 0;
    }

    /**
     * Pruft zuerst ob die Eingabe für das Zahlensystem legal ist.
     * Rechnet die Eingabe mithilfe der entsprechenden Methoden aus Umrechner aus und setzt die Ergebnisse in die Ergebnissvariablen.
     *
     * @see Umrechner
     */
    public void RechneOktal() {
        IsLegal = CheckIfEingabeIsLegal(Oktal);
        if (IsLegal == 1) {
            ErgebnissDezimal = Umrechner.OktInDez(Nutzereingabe);
            ErgebnissBinaer = Umrechner.OktInBin(Nutzereingabe);
            ErgebnissHexadezimal = Umrechner.OktInHex(Nutzereingabe);
            ErgebnissOktal = Nutzereingabe;
        } else if (IsLegal == 0) {
            EingabeNichtLegal();
        } else if (IsLegal == 2) {
            IstLeer();
        }
        IsLegal = 0;
    }

    /**
     * Setzt die Werte aus den Ergebnissvariablen in die Ergebnisstextfelder
     */
    public void ErgebnisseSetzen() {
        TFDezimal.setText(ErgebnissDezimal);
        TFBinaer.setText(ErgebnissBinaer);
        TFHexadezimal.setText(ErgebnissHexadezimal);
        TFOktal.setText(ErgebnissOktal);
    }

    /**
     * Wird aufgerufen wenn vom Nutzer nichts eingegeben wurde.
     * Setzt die Ergebnissvariablen auf Umrechner.NichtsEingegeben und lässt dann die "Ergebnisse" in die entsprechenden
     * Textfelder setzen.
     *
     * @see Umrechner
     */
    public void IstLeer() {
        ErgebnissDezimal = Umrechner.NichtsEingegeben;
        ErgebnissBinaer = Umrechner.NichtsEingegeben;
        ErgebnissHexadezimal = Umrechner.NichtsEingegeben;
        ErgebnissOktal = Umrechner.NichtsEingegeben;
        ErgebnisseSetzen();
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
        CBEingabe.setItems(Systeme);
        CBEingabe.getSelectionModel().selectFirst();
    }
}


