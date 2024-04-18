package domain;

import java.io.Serializable;

public class Regina extends Pezzo implements Serializable {
    public Regina(String nome, String colore) {
        super(nome, colore);
        valore = 9;
    }


}
