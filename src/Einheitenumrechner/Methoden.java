package Einheitenumrechner;

import Hauptmenu.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * Klasse welche die generischen Methoden für die Einheitenumrechner enthält.
 */
public class Methoden {
    /**
     * Standardausgabe falls die Eingabe keine Dezimalzahl ist.
     */
    public static String KeineDez = "Bitte nur ganze Zahlen eingeben. (0-9)";

    /**
     * Methode um zu überprüfen ob eine Checkbox aktiviert ist und falls dies der Fall ist die entsprechenden
     * HBoxen, Label und Textfield hinzuzufügen. Nötig für die Umrechner mit variabler Anzeige
     *
     * @param CB Die Checkbox die überprüft wird.
     * @param H  Die HBox der entsprechenden Einheit.
     * @param L  Das Label dass den Namen der entsprechenden Einheit beinhaltet.
     * @param TF Das Textfeld, welches die entsprechende Ausgabe ausgibt.
     * @param V  Die Vbox in welche die Sachen eingefügt werden. (Eigentlich immer VBoxErgebniss aber um die Methode
     *           so generisch wie möglich zu halten.)
     */
    public static void CBChecked(CheckBox CB, HBox H, Label L, TextField TF, VBox V) {
        if (CB.isSelected()) {
            V.getChildren().add(H);
            H.getChildren().add(L);
            H.getChildren().add(TF);

        } else {
            V.getChildren().remove(H);
            H.getChildren().remove(L);
            H.getChildren().remove(TF);
        }
    }

    /**
     * Methode um zu überprüfen ob ein eingegebener String einer ganzen Dezimalzahl entspricht
     *
     * @param Eingabe Der vom Nutzer eingegebene Wert als String
     * @return Wert zur Differenzierung des Ergebnisses. 1 für "Ist Dezimalzahl", 2 für "Ist keine Dezimalzahl".
     */
    public static int checkIfDez(String Eingabe) {
        int wert = 0;
        for (int i = 0; i < Eingabe.length(); i++) {
            if ((Character.getNumericValue(Eingabe.charAt(i)) != 0 && Character.getNumericValue(Eingabe.charAt(i)) != 1 && Character.getNumericValue(Eingabe.charAt(i)) != 2 &&
                    Character.getNumericValue(Eingabe.charAt(i)) != 3 && Character.getNumericValue(Eingabe.charAt(i)) != 4 && Character.getNumericValue(Eingabe.charAt(i)) != 5 &&
                    Character.getNumericValue(Eingabe.charAt(i)) != 6 && Character.getNumericValue(Eingabe.charAt(i)) != 7 && Character.getNumericValue(Eingabe.charAt(i)) != 8 &&
                    Character.getNumericValue(Eingabe.charAt(i)) != 9)) {
                wert = 2;
            } else {
                wert = 1;
            }
        }
        return wert;
    }

    /**
     * Methode um FXML Dateien zu laden, also das angezeigte Fenster zu ändern in Verbindung mit Start.openLayer()
     * @see Start
     * @param FXMLDatei Der Pfad der zu öffnenden FXML Datei.
     */
    public static void FXMLLoad(String FXMLDatei) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Start.class.getResource(FXMLDatei));
        AnchorPane root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Start.openLayer(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
