package Zahlensystemumrechner;

/**
 * Backend Klasse für die Umrechnung der Zahlensysteme
 * <p></p>
 * <p>
 * Wurde mehrfach überarbeitet. Einmal um sie FXML fähig zu machen (War eine reine Console-Anwendung), und ein 2. Mal um RAM sparenden zu arbeiten
 * (War unter 8GB Ram sonst nicht möglich) und um keine externen Bibliotheken zu nutzen.
 *
 * @author Jannik Tappert
 * @version 3.0
 */
public class Umrechner {
    //Begrenzung auf 18 Zeichen durch den Datentyp long. Sollte mehr als ausreichend sein.
    static String FehlerEingabeZuLang = "Bitte maximal 18 Zeichen eingbeen";
    //Muss abgefangen werden bzw. Irgendwas muss zurückgegeben werden. Ist so ziemlich egal Was, da eh die Eingabe als "nicht legal" gewertet wird.
    static String NichtsEingegeben = "Sie haben Nichts eingegeben";

    /**
     * Dezimal in Binär
     *
     * @param Eingabe Dezimalwert als String
     * @return Binärwert als String
     */
    public static String DezInBin(String Eingabe) {
        if ((Eingabe.length() <= 18) && Eingabe.length() > 0) {
            Long binaer = Long.parseLong(Eingabe);
            return Long.toBinaryString(binaer);
        } else if (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Binär in Dezimal
     *
     * @param Eingabe Binärwert als String
     * @return Dezimalwert als String
     */
    public static String BinInDez(String Eingabe) {
        if ((Eingabe.length() <= 18) && (Eingabe.length() > 0)) {
            long dezimal = Long.parseLong(Eingabe, 2);
            return Long.toString(dezimal);
        } else if
                (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Dezimal in Hexadezimal
     *
     * @param Eingabe Dezimalwert als String
     * @return Hexadezimalwert als String
     */
    public static String DezInHex(String Eingabe) {
        if ((Eingabe.length() <= 18) && (Eingabe.length() > 0)) {
            Long hexadezimal = Long.parseLong(Eingabe);
            return Long.toHexString(hexadezimal).toUpperCase();
        } else if (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Hexadezimal in Dezimal
     *
     * @param Eingabe Hexadezimal als String
     * @return Dezimal als String
     */
    public static String HexInDez(String Eingabe) {
        if ((Eingabe.length() <= 18) && (Eingabe.length() > 0)) {
            Long dezimal = Long.parseLong(Eingabe, 16);
            return Long.toString(dezimal);
        } else if (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Dezimal in Oktal
     *
     * @param Eingabe Dezimal als String
     * @return Oktal als String
     */
    public static String DezInOkt(String Eingabe) {
        if ((Eingabe.length() <= 18) && (Eingabe.length() > 0)) {
            Long oktal = Long.parseLong(Eingabe);
            return Long.toOctalString(oktal);
        } else if (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Oktal in Dezimal
     *
     * @param Eingabe Oktal als String
     * @return Dezimal als String
     */
    public static String OktInDez(String Eingabe) {
        if ((Eingabe.length() <= 18) && (Eingabe.length() > 0)) {
            Long dezimal = Long.parseLong(Eingabe, 8);
            return Long.toString(dezimal);
        } else if (Eingabe.isEmpty()) {
            return NichtsEingegeben;
        } else {
            return FehlerEingabeZuLang;
        }
    }

    /**
     * Binär in Oktal mit Hilfe von BinInDez und DezInOkt
     *
     * @param Eingabe Binär als String
     * @return Oktal als String
     */
    public static String BinInOkt(String Eingabe) {
        String Zwischenergebniss = BinInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) || (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInOkt(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }

    /**
     * Oktal in Binär mit Hilfe von OktInDez und DezInBin
     *
     * @param Eingabe Oktal als String
     * @return Binär als String
     */
    public static String OktInBin(String Eingabe) {
        String Zwischenergebniss = OktInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) || (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInBin(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }

    /**
     * Binär in Hexadezimal mit Hilfe von BinInDez und DezinHex
     *
     * @param Eingabe Binär als String
     * @return Hexadezimal als String
     */
    public static String BinInHex(String Eingabe) {
        String Zwischenergebniss = BinInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) | (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInHex(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }

    /**
     * Hexadezimal in Binär mit Hilfe von HexInDez und DezInBin
     *
     * @param Eingabe Hexadezimal als String
     * @return Binär als String
     */
    public static String HexInBin(String Eingabe) {
        String Zwischenergebniss = HexInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) || (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInBin(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }

    /**
     * Hexadezimal in Oktal mit Hilfe von HexInDez und DezInOkt
     *
     * @param Eingabe Hexadezimal als String
     * @return Oktal als String
     */
    public static String HexInOkt(String Eingabe) {
        String Zwischenergebniss = HexInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) || (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInOkt(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }

    /**
     * Oktal in Hexadezimal mit Hilfe von OktInDez und DezInHex
     *
     * @param Eingabe Oktal als String
     * @return Hexadezimal als String
     */
    public static String OktInHex(String Eingabe) {
        String Zwischenergebniss = OktInDez(Eingabe);
        if ((!Zwischenergebniss.equals(FehlerEingabeZuLang)) || (!Zwischenergebniss.equals(NichtsEingegeben))) {
            return DezInHex(Zwischenergebniss);
        } else if (Zwischenergebniss.equals(FehlerEingabeZuLang)) {
            return FehlerEingabeZuLang;
        } else if (Zwischenergebniss.equals(NichtsEingegeben)) {
            return NichtsEingegeben;
        } else return "";

    }
}

