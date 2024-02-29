package domain;

import Controller.Mossa;

import java.util.Arrays;

public class Scacchiera {
    public Pezzo[][] p;
    public Scacchiera(Pezzo[][] p){
        this.p = this.creazioneScacchiera(p);


    }
    public Pezzo[][] creazioneScacchiera(Pezzo[][] c){

                c[0][0] = new Torre("t1","nero");
                c[0][1] = new Cavallo("c1","nero");
                c[0][2] = new Alfiere("a1","nero");
                c[0][3] =new Regina("queen","nero");
                c[0][4] = new Re("re","nero");
                c[0][5] = new Alfiere("a2","nero");
                c[0][6] = new Cavallo("c2","nero");
                c[0][7] = new Torre("t2","nero");
                for (int i = 0; i < 8; i++) {
                        c[1][i] = new Pedone("p"+i,"nero");
                    }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (c[i][j] == null) c[i][j] = new CasellaVuota(" ", null);
                    }
                }
                c[7][0] = new Torre("t1","bianco");
                c[7][1] = new Cavallo("c1","bianco");
                c[7][2] = new Alfiere("a1","bianco");
                c[7][3] =new Regina("queen","bianco");
                c[7][4] = new Re("re","bianco");
                c[7][5] = new Alfiere("a2","bianco");
                c[7][6] = new Cavallo("c2","bianco");
                c[7][7] = new Torre("t2","bianco");
                for (int i = 0; i < 8; i++) {
                    c[6][i] = new Pedone("p"+i,"bianco");
                }


        return c;
    }
    public Mossa getPoszione(String nome, String colore){
        Mossa posAttuale = new Mossa();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(p[i][j].getNome().equals(nome) && p[i][j].getColore().equals(colore)) {
                    posAttuale.setPosx(i);
                    posAttuale.setPosy(j);
                }
            }

        }
        return posAttuale;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scacchiera that = (Scacchiera) o;
        return Arrays.equals(p, that.p);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(p);
    }
}
