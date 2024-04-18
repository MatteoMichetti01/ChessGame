package domain;

import java.io.Serializable;

public class Torre extends Pezzo implements Serializable {
    public Torre(String nome, String colore) {
        super(nome, colore);
        valore = 5;
    }
}
