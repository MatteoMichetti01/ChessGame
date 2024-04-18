package domain;

import java.io.Serializable;

public class Pedone extends Pezzo implements Serializable {

    public Pedone(String nome, String colore) {

        super(nome, colore);
        valore = 1;
    }

}
