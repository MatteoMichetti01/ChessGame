package domain;

import java.io.Serializable;
/**
 * Questa classe rappresenta una regina nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */

public class Regina extends Pezzo implements Serializable {

    /**
     * Costruisce una nuova regina con il nome e il colore specificati.
     * Il valore della regina viene impostato a 9.
     *
     * @param nome    il nome della regina
     * @param colore  il colore della regina
     */
    public Regina(String nome, String colore) {
        super(nome, colore);
        VALORE = 9;
    }


}
