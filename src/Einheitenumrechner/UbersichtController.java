package Einheitenumrechner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Klasse welche die Steuerung der Einheitenumrechnerübersicht enthält. Sie ruft die einzelnen FXML Dateien
 * der Größen bzw. wieder das Hauptmenü auf.
 * <p></p>
 * Erklärung der Buttonnamen:
 * Back = Zurück zum Hauptmenü, Time = Zeit, Lenght = Länge, Size = Größe, Rate = Datenrate, Pressure = Druck,
 * Energie = Energie, Area = Fläche, Angle = Flächenwinkel, Frequency = Frequenz, Speed = Geschwindigkeit,
 * Fuel = Kraftstoffverbrauch, Mass = Masse, Temperature = Temperatur, Volume = Volumen.
 *
 * @author Jannik Tappert
 * @version 1.0
 */
public class UbersichtController {

    @FXML
    Button BtnBack, BtnTime, BtnLength, BtnSize, BtnRate, BtnPressure, BtnEnergie, BtnArea, BtnAngle, BtnFrequency, BtnSpeed, BtnFuel, BtnMass, BtnTemperature, BtnVolume;

    /**
     * Methode um zum Hauptmenü zurückzukehren.
     */
    public void BtnBack() {
        Methoden.FXMLLoad("../Hauptmenu/Hauptmenu.fxml");
    }

    /**
     * Methode um den Zeitumrechner aufzurufen.
     */
    public void BtnTime() {
        Methoden.FXMLLoad("../Einheitenumrechner/ZeitRechner.fxml");
    }

    /**
     * Methode um den Längenumrechner aufzurufen.
     */
    public void BtnLength() {
        Methoden.FXMLLoad("../Einheitenumrechner/LaengeRechner.fxml");
    }

    /**
     * Methode um den Größenumrechner aufzurufen.
     */
    public void BtnSize() {

    }

    /**
     * Methode um den Datenratenumrechner aufzurufen.
     */
    public void BtnRate() {
        Methoden.FXMLLoad("../Einheitenumrechner/DatenuebertragungsRechner.fxml");
    }

    /**
     * Methode um den Druckumrechner aufzurufen.
     */
    public void BtnPressure() {
        Methoden.FXMLLoad("../Einheitenumrechner/DruckRechner.fxml");
    }

    /**
     * Methode um den Energieumrechner aufzurufen.
     */
    public void BtnEnergie() {
        Methoden.FXMLLoad("../Einheitenumrechner/EnergieRechner.fxml");
    }

    /**
     * Methode um den Flächenumrechner aufzurufen.
     */
    public void BtnArea() {
        Methoden.FXMLLoad("../Einheitenumrechner/FlaecheRechner.fxml");
    }

    /**
     * Methode um den Flächenwinkelumrechner aufzurufen.
     */
    public void BtnAngle() {
        Methoden.FXMLLoad("../Einheitenumrechner/FlaechenwinkelRechner.fxml");
    }

    /**
     * Methode um den Frequenzumrechner aufzurufen.
     */
    public void BtnFrequency() {
        Methoden.FXMLLoad("../Einheitenumrechner/FrequenzRechner.fxml");

    }

    /**
     * Methode um den Geschwindigkeitsrechner aufzurufen.
     */
    public void BtnSpeed() {
        Methoden.FXMLLoad("../Einheitenumrechner/GeschwindigkeitsRechner.fxml");

    }

    /**
     * Methode um den Treibstoffverbrauchsumrechner aufzurufen.
     */
    public void BtnFuel() {
        Methoden.FXMLLoad("../Einheitenumrechner/KraftstoffverbrauchRechner.fxml");
    }

    /**
     * Methode um den Massenumrechner aufzurufen.
     */
    public void BtnMass() {
        Methoden.FXMLLoad("../Einheitenumrechner/MasseRechner.fxml");
    }

    /**
     * Methode um den Temperaturumrechner aufzurufen.
     */
    public void BtnTemperature() {
        Methoden.FXMLLoad("../Einheitenumrechner/TemperaturRechner.fxml");
    }

    /**
     * Methode um den Volumenumrechner aufzurufen.
     */
    public void BtnVolume() {
        Methoden.FXMLLoad("../Einheitenumrechner/VolumenRechner.fxml");

    }
}



