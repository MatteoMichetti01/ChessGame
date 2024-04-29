package domain;

import java.io.Serializable;
/**
 * Questa classe rappresenta un alfiere nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */
public class Alfiere extends Pezzo implements Serializable {
    /**
     * Costruisce un nuovo alfiere con il nome e il colore specificati.
     * Il valore dell'alfiere viene impostato a 3.
     *
     * @param nome    il nome dell'alfiere
     * @param colore  il colore dell'alfiere
     */

    public Alfiere(String nome, String colore) {
        super(nome, colore);
        VALORE = 3;
    }

}
