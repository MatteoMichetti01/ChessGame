package domain;

import Controller.MossaTest;

import java.util.Arrays;

public class Scacchiera {
    public Pezzo[][] p;
    public int posPezzoX,posPezzoY;
    public Scacchiera(Pezzo[][] p){
        this.p = this.creazioneScacchiera(p);
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                p[i][j].setPosX(i);
                p[i][j].setPosY(j);
            }
        }

    }
    public Pezzo[][] creazioneScacchiera(Pezzo[][] c) {
        c[0][0] = new CasellaVuota("   ", "   ",0,0);
        c[0][1] = new CasellaVuota(" A   ", "   ",0,1);
        c[0][2] = new CasellaVuota("  B   ", "   ",0,2);
        c[0][3] = new CasellaVuota("  C   ", "   ",0,3);
        c[0][4] = new CasellaVuota("  D   ", "   ",0,4);
        c[0][5] = new CasellaVuota("  E   ", "   ",0,5);
        c[0][6] = new CasellaVuota("  F   ", "   ",0,6);
        c[0][7] = new CasellaVuota("  G   ", "   ",0,7);
        c[0][8] = new CasellaVuota("  H", "   ",0,8);

        for (int i = 1; i <= 8; i++) {
            c[i][0] = new CasellaVuota("" + (9 - i) + "", "   ",i,0);
            for (int j = 1; j <= 8; j++) {
                c[i][j] = new CasellaVuota("|    |", "   ",i,j);
            }
        }

        c[1][1] = new Torre("| t1 |", "nero",1,1);
        c[1][2] = new Cavallo("| c1 |", "nero",1,2);
        c[1][3] = new Alfiere("| a1 |", "nero",1,3);
        c[1][4] = new Regina("| q  |", "nero",1,4);
        c[1][5] = new Re("| re |", "nero",1,5);
        c[1][6] = new Alfiere("| a2 |", "nero",1,6);
        c[1][7] = new Cavallo("| c2 |", "nero",1,7);
        c[1][8] = new Torre("| t2 |", "nero",1,8);

        for (int i = 1; i <= 8; i++) {
            c[2][i] = new Pedone("| p" + i + " |", "nero",2,i);
        }

        for (int i = 3; i <= 7; i++) {
            if (c[i][0] == null) c[i][0] = new CasellaVuota("" + i + "", null,i,0);
            for (int j = 1; j <= 8; j++) {
                if (c[i][j] == null) c[i][j] = new CasellaVuota("|    |", null,i,j);
            }
        }

        c[7][0] = new CasellaVuota("2", null,7,0);
        for (int i = 1; i <= 8; i++) {
            c[7][i] = new Pedone("| p" + i + " |", "bianco",7,i);
        }

        c[8][0] = new CasellaVuota("1", null,8,0);
        c[8][1] = new Torre("| t1 |", "bianco",8,1);
        c[8][2] = new Cavallo("| c1 |", "bianco",8,2);
        c[8][3] = new Alfiere("| a1 |", "bianco",8,3);
        c[8][4] = new Regina("| q  |", "bianco",8,4);
        c[8][5] = new Re("| re |", "bianco",8,5);
        c[8][6] = new Alfiere("| a2 |", "bianco",8,6);
        c[8][7] = new Cavallo("| c2 |", "bianco",8,7);
        c[8][8] = new Torre("| t2 |", "bianco",8,8);

        return c;
    }





    public MossaTest getPoszione(String nome, String colore){
        MossaTest posAttuale = new MossaTest();
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
