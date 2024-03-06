package domain;

import Controller.MossaTest;

import java.util.Arrays;

public class Scacchiera {
    public Casella[][] casella;
    public Pezzo pezzo;
    public Scacchiera(Casella[][] casella){
        this.casella= this.creazioneScacchiera(casella);

    }
    public Casella[][] creazioneScacchiera(Casella[][] casella) {
        casella[0][0] = new Casella("  ");
        casella[0][1] = new Casella(" A   ");
        casella[0][2] = new Casella("  B   ");
        casella[0][3] = new Casella("  C   ");
        casella[0][4] = new Casella("  D   ");
        casella[0][5] = new Casella("  E   ");
        casella[0][6] = new Casella("  F   ");
        casella[0][7] = new Casella("  G   ");
        casella[0][8] = new Casella("  H");

        for (int i = 1; i <= 8; i++) {
            casella[i][0] = new Casella("" + (9 - i) + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("|    |");
            }
        }

        casella[1][1] = new Casella("| t1 |", new Torre("|"+" t1 "+"|"),1,1,true);
        casella[1][2] = new Casella("| c1 |", new Cavallo("|"+" c1 "+"|"),1,2,true);
        casella[1][3] = new Casella("| a1 |", new Alfiere("|"+" a1 "+"|"),1,3,true);
        casella[1][4] = new Casella("| q  |", new Regina("|"+" q  "+"|"),1,4,true);
        casella[1][5] = new Casella("| re |", new Re("|"+" re "+"|"),1,5,true);
        casella[1][6] = new Casella("| a2 |", new Alfiere("|"+" a2 "+"|"),1,6,true);
        casella[1][7] = new Casella("| c2 |", new Cavallo("|"+" c2 "+"|"),1,7,true);
        casella[1][8] = new Casella("| t2 |", new Torre("|"+" t2 "+"|"),1,8,true);

        for (int i = 1; i <= 8; i++) {
            casella[2][i] = new Casella("A"+(8-i),new Pedone("| p" + i + " |"),2,i,true);
        }

        for (int i = 3; i <= 7; i++) {
            casella[i][0] = new Casella("" + i + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("|    |");
            }
        }

        casella[7][0] = new Casella("2");
        for (int i = 1; i <= 8; i++) {
            casella[7][i] = new Casella(" ",new Pedone("| p" + i + " |"),7,i,true);
        }

        casella[8][0] = new Casella("1");
        casella[8][1] = new Casella(" ", new Torre("| t1 |"),8,1,true);
        casella[8][2] = new Casella(" ",new Cavallo("| c1 |"),8,2,true);
        casella[8][3] = new Casella (" ",new Alfiere("| a1 |"),8,3,true);
        casella[8][4] = new Casella(" ",new Regina("| q  |"),8,4,true);
        casella[8][5] = new Casella(" ",new Re("| re |"),8,5,true);
        casella[8][6] = new Casella(" ",new Alfiere("| a2 |"),8,6,true);
        casella[8][7] = new Casella (" ", new Cavallo("| c2 |"),8,7,true);
        casella[8][8] = new Casella (" ",new Torre("| t2 |"),8,8,true);

        return casella;
    }





   /* public MossaTest getPoszione(String nome, String colore){
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
        return Arrays.equals(o.pezzo, that.pezzo);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pezzo);
    }*/
}
