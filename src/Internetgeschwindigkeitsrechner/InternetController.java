package Internetgeschwindigkeitsrechner;

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
 * Klasse für die Berechnung der Dauer von Internetübertragungen
 * <p></p>
 * Erklärung der Abkürzungen:
 * KB = Kilobyte, MB = Megabyte, GB = Gigabyte, TB = Terrabyte, Sek = Sekunde, Min = Minute, H = Stunden, Day = Tage.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class InternetController implements Initializable {

    @FXML
    Button BtnBack, BtnCalc;
    @FXML
    ComboBox CBEinheit;
    @FXML
    TextField Size, DLGes, ULGes, ULTime, DLTime;

    Object KB = "KB", MB = "MB", GB = "GB", TB = "TB", Empty = "Bitte Auswählen", Sek = "Sekunden", Min = "Minuten", H = "Stunden", Day = "Tage";

    ObservableList<String> optionsSize = FXCollections.observableArrayList("KB", "MB", "GB", "TB");
    ObservableList<String> optionsTime = FXCollections.observableArrayList("Sekunden", "Minuten", "Stunden", "Tage");

    /**
     * Methode um zum Hauptmenü zurückzukehren
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Hauptmenu/Hauptmenu.fxml");
    }

    /**
     * Methode welche die Umrechnung steuert. Zuerst die Eingabe überprüft und falls diese legal ist die Umrechnungsmethode aufruft.
     */
    @FXML
    public void BtnCalc() {
        if (getULGes() == -1 || getDLGes() == -1 || getGroesse() == -1) {
            DLTime.setText("FEHLER: Bitte Werte eingeben");
            ULTime.setText("FEHLER: Bitte Werte eingeben");
        } else if (getULGes() == -2 || getDLGes() == -2 || getGroesse() == -2) {
            DLTime.setText("Maximal 9 Zeichen eingeben");
            ULTime.setText("Maximal 9 Zeichen eingeben");
        } else {
            Umrechnen(getGroesse(), getDLGes(), getULGes(), getCBSize());
        }
    }

    /**
     * Methode um die Auswahl der Combobox CBSize, also die Einheit der Eingabegröße, zu bekommen.
     *
     * @return Object welches ausgewählt wurde.
     */
    public Object getCBSize() {
        return CBEinheit.getValue();
    }

    /**
     * Methode um den Wert des Feldes Dateigröße zu bekommen. Und falls diese größer 1000 ist automatisch in die nächsthöhere Einheit
     * umrechnet
     *
     * @return Die Eingabe als Double Wert umgerechnet in die entsprechende Einheit.
     */
    public double getGroesse() {
        if (Size.getText().isEmpty()) {

            return -1;
        } else if
                (Size.getText().length() > 9) {
            if (getCBSize().equals(KB)) {
                CBEinheit.getSelectionModel().select(MB);
                Size.setText(Double.toString(Double.parseDouble(Size.getText()) / 1000));
            } else if (getCBSize().equals(MB)) {
                CBEinheit.getSelectionModel().select(GB);
                Size.setText(Double.toString(Double.parseDouble(Size.getText()) / 1000));
            } else if (getCBSize().equals(GB)) {
                CBEinheit.getSelectionModel().select(TB);
                Size.setText(Double.toString(Double.parseDouble(Size.getText()) / 1000));
            }

        } else return Double.parseDouble(Size.getText());
        return Double.parseDouble(Size.getText());
    }

    /**
     * Methode welche die eingegeben Downloadgeschindigkeit (in kbit/s) überprüft und falls diese legal ist zurückgibt.
     *
     * @return Fehlercode: -1 falls leer, -2 falls die Eingabe länger 9 oder kürzer 0 ist, -3 falls die Eingabe
     * keine Dezimalzahl ist. Oder die Downloadgeschwindigkeit falls kein Fehlercode ausgegeben wird.
     */
    public int getDLGes() {
        if (DLGes.getText().isEmpty()) {

            return -1;
        } else if (DLGes.getText().length() > 9 || DLGes.getText().length() < 0) {
            return -2;
        } else {
            if (Methoden.checkIfDez(DLGes.getText()) == 1) {
                return Integer.parseInt(DLGes.getText());
            } else {
                return -3;
            }
        }
    }

    /**
     * Methode welche die eingegeben Uploadgeschindigkeit (in kbit/s) überprüft und falls diese legal ist zurückgibt.
     *
     * @return Fehlercode: -1 falls leer, -2 falls die Eingabe länger 9 oder kürzer 0 ist, -3 falls die Eingabe
     * keine Dezimalzahl ist. Oder die Uploadeschwindigkeit falls kein Fehlercode ausgegeben wird.
     */
    public int getULGes() {
        if (ULGes.getText().isEmpty()) {

            return -1;
        } else if
                (ULGes.getText().length() > 9 || ULGes.getText().length() < 0) {
            return -2;
        } else {
            if (Methoden.checkIfDez(ULGes.getText()) == 1) {
                return Integer.parseInt(ULGes.getText());
            } else {
                return -3;
            }
        }
    }

    /**
     * Methode welche die gesamte Umrechnung bearbeitet(und die entsprechende berechneDL/berechneUL Methode aufruft). Einerseits überprüft ob die Eingabe legal ist und falls ja die
     * Eingabeeinheit überprüft. Falls die Groesse größer 1000 ist ruft die Methode sich automatisch nochmals selber mit
     * der nächsthöheren Einheit auf.
     *
     * @param Groesse     Die vom Nutzer eingegebene Zahl der Dateigröße
     * @param DLGes       Die vom Nutzer angegebene Downloadgeschwindigkeit in kbit/s
     * @param ULGes       Die vom Nutzer angegebene Uploadgeschwindigkeit in kbit/s
     * @param AuswahlSize Die vom Nutzer, oder von der Methode getGroesse umgestellte, Auswahl in der ComboBox CBEinheit
     */
    public void Umrechnen(double Groesse, int DLGes, int ULGes, Object AuswahlSize) {
        int wert = 1;
        if (DLGes == -3 || ULGes == -3) {
            DLTime.setText("Bitte nur ganze Zahlen eingeben (0-9)");
            ULTime.setText("Bitte nur ganze Zahlen eingeben (0-9)");
        } else {
            if (DLGes == 0 || ULGes == 0) {
                DLTime.setText("Bitte nur Geschwindigkeiten größer 0 eingeben");
                ULTime.setText("Bitte nur Geschwindigkeiten größer 0 eingeben");
            } else if (DLGes != 0 && ULGes != 0) {

                if (AuswahlSize.equals(KB)) {
                    if (Groesse > 1000) {
                        double a = Groesse;
                        double c = a / 1000;
                        CBEinheit.getSelectionModel().select(MB);
                        Size.setText(Double.toString(c));
                        Umrechnen(c, DLGes, ULGes, getCBSize());
                    } else {
                        berechneDL(0, Groesse, DLGes);
                        berechneUL(0, Groesse, ULGes);


                    }
                } else if (AuswahlSize.equals(MB)) {
                    if (Groesse > 1000) {
                        double a = Groesse;
                        double c = a / 1000;
                        CBEinheit.getSelectionModel().select(GB);
                        Size.setText(Double.toString(c));
                        Umrechnen(c, DLGes, ULGes, getCBSize());
                    } else {
                        berechneDL(1, Groesse, DLGes);
                        berechneUL(1, Groesse, ULGes);

                    }
                } else if (AuswahlSize.equals(GB)) {
                    if (Groesse > 1000) {
                        double a = Groesse;
                        double c = a / 1000;
                        CBEinheit.getSelectionModel().select(TB);
                        Size.setText(Double.toString(c));
                        Umrechnen(c, DLGes, ULGes, getCBSize());
                    } else {
                        berechneDL(2, Groesse, DLGes);
                        berechneUL(2, Groesse, ULGes);
                    }
                } else if (AuswahlSize.equals(TB)) {
                    berechneDL(3, Groesse, DLGes);
                    berechneUL(3, Groesse, ULGes);
                }
            }
        }
    }


    /**
     * Methode die wirklich die Downloadzeit berechnet und dies schön formatiert ausgibt.
     *
     * @param i       Einheit: 0 = KB, 1=MB, 2=GB, 3=TB,
     * @param Groesse Dateigröße (Wert der vom Nutzer eingegeben wurde entsprechend umgerechnet)
     * @param DLGes   Die vom Nutzer eingegebene Downloadgeschwindigkeit.
     */
    public void berechneDL(double i, double Groesse, int DLGes) {
        double a = Groesse * 8 * Math.pow(1000, i);
        double zeit = a / DLGes;
        int sek1 = 1;
        int min = (60 * sek1);
        int std = (60 * min);
        int tag = (24 * std);
        int jah = (365 * tag);
        int jaherg = (int) zeit / (tag) / 365;
        int tagerg = (int) (zeit % jah) / (tag);
        int stderg = (int) (zeit % tag) / (std);
        int minerg = (int) (zeit % std) / (min);
        int sekerg = (int) (zeit % min * sek1);
        DLTime.setText(jaherg + " Jahre " + tagerg + " Tage " + stderg + " Stunden " + minerg + " Minuten " + sekerg + " Sekunden ");
    }

    /**
     * Methode die wirklich die Uploadzeit zu berechnet und dies schön formatiert ausgibt.
     *
     * @param i       Einheit: 0 = KB, 1=MB, 2=GB, 3=TB,
     * @param Groesse Dateigröße (Wert der vom Nutzer eingegeben wurde entsprechend umgerechnet)
     * @param ULGes   Die vom Nutzer eingegebene Uploadgeschwindigkeit.
     */
    public void berechneUL(double i, double Groesse, int ULGes) {

        double a = Groesse * 8 * Math.pow(1000, i);
        double zeit = a / ULGes;
        int sek1 = 1;
        int min = (60 * sek1);
        int std = (60 * min);
        int tag = (24 * std);
        int jah = (365 * tag);
        int jaherg = (int) zeit / (tag) / 365;
        int tagerg = (int) (zeit % jah) / (tag);
        int stderg = (int) (zeit % tag) / (std);
        int minerg = (int) (zeit % std) / (min);
        int sekerg = (int) (zeit % min * sek1);
        ULTime.setText(jaherg + " Jahre " + tagerg + " Tage " + stderg + " Stunden " + minerg + " Minuten " + sekerg + " Sekunden ");
    }

    /**
     * Methode des Interface Initializable um Methoden beim Aufruf einer FXML Datei auszuführen.
     * Befüllt die C0mbobox mit Werten und setzt den Standardwert.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBEinheit.setItems(optionsSize);
        CBEinheit.getSelectionModel().selectFirst();
    }
}
