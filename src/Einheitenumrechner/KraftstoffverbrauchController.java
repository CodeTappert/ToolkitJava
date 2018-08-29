package Einheitenumrechner;

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
 * Klasse welche die FXML Datei KrafstoffverbrauchRechner steuert.
 * <p></p>
 * Erklärung der Abkürzungen:
 * KML = Kilometer pro Liter, LPKM = Liter pro 100 Kilometer, MPG = Meilen pro Gallone.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class KraftstoffverbrauchController implements Initializable {
    @FXML
    ComboBox CBEingabe;
    @FXML
    TextField TFStart, TFKML, TFLPKM, TFMPG;
    @FXML
    Button BtnBack, BtnCalc;
    ObservableList<String> optionsFuel = FXCollections.observableArrayList("Kilometer pro Liter", "Liter pro 100 Kilometern", "Meilen pro Gallone");
    Object KML = "Kilometer pro Liter", LPKM = "Liter pro 100 Kilometern", MPG = "Meilen pro Gallone";
    double EKML = 0, ELPKM = 0, EMPG = 0;
    int weitermachen = 0;

    /**
     * Rückkehr zur Übersicht
     */
    @FXML
    public void BtnBack() {
        Methoden.FXMLLoad("../Einheitenumrechner/EinheitenumrechnerUebersicht.fxml");
    }

    /**
     * Überprüft beim Knopfdruck "Umrechnen" ob die Eingabe des Nutzers eine ganze Dezimalzahl ist und ruft dann die
     * Methode Umrechen auf. Dort werden die entsprechenen Werte den Variablen zugeordnet und von TextSetzen ausgegeben.
     */
    @FXML
    public void BtnCalc() {
        Umrechnen(TFStart.getText(), CBEingabe.getValue());
        if (weitermachen == 0) {
            TextSetzen();
        }
        weitermachen = 0;
    }

    /**
     * Rechnet die Nutzereingabe, nach einer Prüfung ob diese eine Dezimalzahl ist, in die anderen Einheiten um.
     *
     * @param Eingabe Der vom Nutzer eingebene Wert als String.
     * @param Auswahl Die vom Nutzer ausgewählte Einheit.
     */
    public void Umrechnen(String Eingabe, Object Auswahl) {
        double Wert = Double.parseDouble(Eingabe);
        int IsDez = Methoden.checkIfDez(Eingabe);
        if (IsDez == 1) {
            if (Auswahl.equals(KML)) {
                EKML = Wert;
                ELPKM = 100 / Wert;
                EMPG = Wert * 2.35215;
            } else if (Auswahl.equals(LPKM)) {
                EKML = 100 / Wert;
                ELPKM = Wert;
                EMPG = 235.215 / Wert;
            } else if (Auswahl.equals(MPG)) {
                EKML = Wert * 0.425144;
                ELPKM = 235.215 / Wert;
                EMPG = Wert;
            }
            weitermachen = 0;
        } else {
            weitermachen = 1;
            TFKML.setText(Methoden.KeineDez);
            TFLPKM.setText(Methoden.KeineDez);
            TFMPG.setText(Methoden.KeineDez);

        }
    }

    /**
     * Setzt die Textfelder auf die entsprechenden Werte.
     */
    public void TextSetzen() {
        TFKML.setText(Double.toString(EKML));
        TFLPKM.setText(Double.toString(ELPKM));
        TFMPG.setText(Double.toString(EMPG));
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
        CBEingabe.setItems(optionsFuel);
        CBEingabe.getSelectionModel().selectFirst();

    }
}
