package domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta un cavallo nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */

public class Cavallo  extends Pezzo implements Serializable {


    /**
     * Costruisce un nuovo cavallo con il nome e il colore specificati.
     * Il valore del cavallo viene impostato a 3.
     *
     * @param nome    il nome del cavallo
     * @param colore  il colore del cavallo
     */

    public Cavallo(String nome, String colore) {

        super(nome, colore);
        VALORE = 3;
    }
}
