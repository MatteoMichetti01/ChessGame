package domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta il re nel gioco degli scacchi.
 * Estende la classe Pezzo e implementa l'interfaccia Serializable.
 */

public class Re extends Pezzo implements Serializable {

    /**
     * Costruisce un nuovo re con il nome e il colore specificati.
     *
     * @param nome    il nome del re
     * @param colore  il colore del re
     */

    public Re(String nome, String colore) {
        super(nome, colore);
    }

}
