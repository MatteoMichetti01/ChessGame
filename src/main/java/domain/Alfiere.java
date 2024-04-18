package domain;

import java.io.Serializable;

public class Alfiere extends Pezzo implements Serializable {

    public Alfiere(String nome, String colore) {
        super(nome, colore);
        valore = 3;
    }

}
