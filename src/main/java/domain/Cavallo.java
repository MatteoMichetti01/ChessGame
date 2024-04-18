package domain;

import java.io.Serializable;

public class Cavallo  extends Pezzo implements Serializable {

    public Cavallo(String nome, String colore) {

        super(nome, colore);
        valore = 3;
    }
}
