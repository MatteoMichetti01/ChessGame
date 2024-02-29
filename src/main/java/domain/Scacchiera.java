package domain;

import Controller.Mossa;

import java.util.Arrays;

public class Scacchiera {
    public Pezzo[][] p;
    public Scacchiera(Pezzo[][] p){
        this.p = this.creazioneScacchiera(p);


    }
    public Pezzo[][] creazioneScacchiera(Pezzo[][] c) {
        c[0][0] = new CasellaVuota("   ", "   ");
        c[0][1] = new CasellaVuota(" A   ", "   ");
        c[0][2] = new CasellaVuota("  B   ", "   ");
        c[0][3] = new CasellaVuota("  C   ", "   ");
        c[0][4] = new CasellaVuota("  D   ", "   ");
        c[0][5] = new CasellaVuota("  E   ", "   ");
        c[0][6] = new CasellaVuota("  F   ", "   ");
        c[0][7] = new CasellaVuota("  G   ", "   ");
        c[0][8] = new CasellaVuota("  H", "   ");

        for (int i = 1; i <= 8; i++) {
            c[i][0] = new CasellaVuota("" + (9 - i) + "", "   ");
            for (int j = 1; j <= 8; j++) {
                c[i][j] = new CasellaVuota("|    |", "   ");
            }
        }

        c[1][1] = new Torre("| t1 |", "nero");
        c[1][2] = new Cavallo("| c1 |", "nero");
        c[1][3] = new Alfiere("| a1 |", "nero");
        c[1][4] = new Regina("| q  |", "nero");
        c[1][5] = new Re("| re |", "nero");
        c[1][6] = new Alfiere("| a2 |", "nero");
        c[1][7] = new Cavallo("| c2 |", "nero");
        c[1][8] = new Torre("| t2 |", "nero");

        for (int i = 1; i <= 8; i++) {
            c[2][i] = new Pedone("| p" + i + " |", "nero");
        }

        for (int i = 3; i <= 7; i++) {
            if (c[i][0] == null) c[i][0] = new CasellaVuota("" + i + "", null);
            for (int j = 1; j <= 8; j++) {
                if (c[i][j] == null) c[i][j] = new CasellaVuota("|    |", null);
            }
        }

        c[7][0] = new CasellaVuota("2", null);
        for (int i = 1; i <= 8; i++) {
            c[7][i] = new Pedone("| p" + i + " |", "bianco");
        }

        c[8][0] = new CasellaVuota("1", null);
        c[8][1] = new Torre("| t1 |", "bianco");
        c[8][2] = new Cavallo("| c1 |", "bianco");
        c[8][3] = new Alfiere("| a1 |", "bianco");
        c[8][4] = new Regina("| q  |", "bianco");
        c[8][5] = new Re("| re |", "bianco");
        c[8][6] = new Alfiere("| a2 |", "bianco");
        c[8][7] = new Cavallo("| c2 |", "bianco");
        c[8][8] = new Torre("| t2 |", "bianco");

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
