package domain;

public class Scacchiera {
    public Casella[][] casella;
    public Pezzo pezzo;
    public Scacchiera(Casella[][] casella){
        this.casella= this.creazioneScacchiera(casella);

    }
    public Casella[][] creazioneScacchiera(Casella[][] casella) {
        casella[0][0] = new Casella(" ");
        casella[0][1] = new Casella("A ");
        casella[0][2] = new Casella("B ");
        casella[0][3] = new Casella("C ");
        casella[0][4] = new Casella("D ");
        casella[0][5] = new Casella("E ");
        casella[0][6] = new Casella("F ");
        casella[0][7] = new Casella("G ");
        casella[0][8] = new Casella("H ");

        for (int i = 1; i <= 8; i++) {
            casella[i][0] = new Casella("" + (i) + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("   ");
            }

        }


        casella[1][1] = new Casella("A1", new Torre("t1","nero"),1,1,true);
        casella[1][2] = new Casella("B1", new Cavallo( "c1","nero"),1,2,true);
        casella[1][3] = new Casella("C1", new Alfiere("a1","nero"),1,3,true);
        casella[1][4] = new Casella("D1", new Regina("qn","nero"),1,4,true);
        casella[1][5] = new Casella("E1", new Re("re","nero"),1,5,true);
        casella[1][6] = new Casella("F1", new Alfiere("a2","nero"),1,6,true);
        casella[1][7] = new Casella("G1", new Cavallo("c2","nero"),1,7,true);
        casella[1][8] = new Casella("H1", new Torre("t2","nero"),1,8,true);

        String[]  lettere = new String[]{"j", "A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 1; i <= 8; i++) {
            casella[2][i] = new Casella(lettere[i]+"2",new Pedone("p" + i,"nero"),2,i,true);
        }

        for (int i = 3; i <= 7; i++) {
            casella[i][0] = new Casella("" + (i) + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("  ",lettere[j]+i, false);
            }
        }

        /*for (int i = 1; i <= 8; i++) {
            casella[2][i] = new Casella(lettere[i]+"2",new Pedone("| p" + i + " |"),2,i,true);
        }*/

        casella[7][0] = new Casella("7");
        for (int i = 1; i <= 8; i++) {
            casella[7][i] = new Casella(lettere[i]+"7",new Pedone("p"+i,"bianco"),7,i,true);
        }

        casella[8][0] = new Casella("8");
        casella[8][1] = new Casella("A8", new Torre("t1","bianco"),8,1,true);
        casella[8][2] = new Casella("B8",new Cavallo("c1","bianco"),8,2,true);
        casella[8][3] = new Casella ("C8",new Alfiere("a1","bianco"),8,3,true);
        casella[8][4] = new Casella("D8",new Regina("qn","bianco"),8,4,true);
        casella[8][5] = new Casella("E8",new Re("re","bianco"),8,5,true);
        casella[8][6] = new Casella("F8",new Alfiere("a2","bianco"),8,6,true);
        casella[8][7] = new Casella ("G8", new Cavallo("c2","bianco"),8,7,true);
        casella[8][8] = new Casella ("H8",new Torre("t2","bianco"),8,8,true);




        //inserimento nome per attributo di casella con coordinate giuste di scachiera


       // for(int i=2; i<8; i++) {
         //   for (int j= 0; j < 8; j++) casella[i][j].setNome(lettere[i] + j);
       // }


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
