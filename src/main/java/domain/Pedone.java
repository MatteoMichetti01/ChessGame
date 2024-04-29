package domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta un pedone nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */

public class Pedone extends Pezzo implements Serializable {

    /**
     * Costruisce un nuovo pedone con il nome e il colore specificati.
     * Il valore del pedone viene impostato a 1.
     *
     * @param nome    il nome del pedone
     * @param colore  il colore del pedone
     */

    public Pedone(String nome, String colore) {

        super(nome, colore);
        VALORE = 1;
    }

}
