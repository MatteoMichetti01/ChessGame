package domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta una torre nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */

public class Torre extends Pezzo implements Serializable {

    /**
     * Costruisce una nuova torre con il nome e il colore specificati.
     * Il valore della torre viene impostato a 5.
     *
     * @param nome    il nome della torre
     * @param colore  il colore della torre
     */
    public Torre(String nome, String colore) {
        super(nome, colore);
        VALORE = 5;
    }
}
