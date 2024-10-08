package domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta la scacchiera del gioco degli scacchi.
 * Implementa l'interfaccia Serializable.
 */

public class Scacchiera implements Serializable {
    public Casella[][] casella = new Casella[9][9];

    /**
     * Costruisce una nuova scacchiera e inizializza le caselle.
     */
    public Scacchiera(){
        this.casella = creazioneScacchiera();

    }

    /**
     * Crea e inizializza la configurazione standard della scacchiera.
     *
     * @return una matrice di caselle rappresentante la configurazione iniziale della scacchiera
     */
    public Casella[][] creazioneScacchiera() {
        casella[0][0] = new Casella(" ");
        casella[0][1] = new Casella("A  ");
        casella[0][2] = new Casella("B  ");
        casella[0][3] = new Casella("C  ");
        casella[0][4] = new Casella("D  ");
        casella[0][5] = new Casella("E  ");
        casella[0][6] = new Casella("F  ");
        casella[0][7] = new Casella("G  ");
        casella[0][8] = new Casella("H  ");

        for (int i = 1; i <= 8; i++) {
            casella[i][0] = new Casella("" + (i) + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("   ");
            }

        }

        casella[1][1] = new Casella("A1", new Torre("t1B","nero"),1,1,true);
        casella[1][2] = new Casella("B1", new Cavallo( "c1B","nero"),1,2,true);
        casella[1][3] = new Casella("C1", new Alfiere("a1B","nero"),1,3,true);
        casella[1][4] = new Casella("D1", new Regina("qnB","nero"),1,4,true);
        casella[1][5] = new Casella("E1", new Re("reB","nero"),1,5,true);
        casella[1][6] = new Casella("F1", new Alfiere("a2B","nero"),1,6,true);
        casella[1][7] = new Casella("G1", new Cavallo("c2B","nero"),1,7,true);
        casella[1][8] = new Casella("H1", new Torre("t2B","nero"),1,8,true);

        String[]  lettere = new String[]{"j", "A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 1; i <= 8; i++) {
            casella[2][i] = new Casella(lettere[i]+"2",new Pedone("p" + i+"B","nero"),2,i,true);
        }

        for (int i = 3; i <= 7; i++) {
            casella[i][0] = new Casella("" + (i) + "");
            for (int j = 1; j <= 8; j++) {
                casella[i][j] = new Casella("   ",lettere[j]+i, false);
            }
        }

        casella[7][0] = new Casella("7");
        for (int i = 1; i <= 8; i++) {
            casella[7][i] = new Casella(lettere[i]+"7",new Pedone("p"+i+"W","bianco"),7,i,true);
        }

        casella[8][0] = new Casella("8");
        casella[8][1] = new Casella("A8", new Torre("t1W","bianco"),8,1,true);
        casella[8][2] = new Casella("B8",new Cavallo("c1W","bianco"),8,2,true);
        casella[8][3] = new Casella ("C8",new Alfiere("a1W","bianco"),8,3,true);
        casella[8][4] = new Casella("D8",new Regina("qnW","bianco"),8,4,true);
        casella[8][5] = new Casella("E8",new Re("reW","bianco"),8,5,true);
        casella[8][6] = new Casella("F8",new Alfiere("a2W","bianco"),8,6,true);
        casella[8][7] = new Casella ("G8", new Cavallo("c2W","bianco"),8,7,true);
        casella[8][8] = new Casella ("H8",new Torre("t2W","bianco"),8,8,true);

        return casella;
    }

    /**
     * Visualizza la scacchiera.
     */
    public void viewScacchiera(){
        for(int k=0;k<9;k++){
            System.out.print(" "+this.casella[0][k].getNome()+"  ");
        }
        for (int i =1;i<9;i++){
            System.out.println();
            for(int j=0;j<9;j++){
                System.out.print(this.casella[i][j].getNome()+ " | ");
            }
        }
    }

    /**
     * Clona la scacchiera specificata.
     *
     * @param scacchiera la scacchiera da clonare
     * @return una copia della scacchiera specificata
     */
    public Scacchiera clone(Scacchiera scacchiera){
        Scacchiera clone = new Scacchiera();
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].isOccupata()) {
                    clone.casella[i][j] = new Casella(clone.casella[i][j].getPosizione(), scacchiera.casella[i][j].getPezzo(), i,j, true);
                }
                else {
                    clone.casella[i][j] = new Casella ("   ", clone.casella[i][j].getPosizione(), false);
                }
            }
        }
        return clone;
    }
    /**
     * Conta il numero totale di pezzi sulla scacchiera.
     *
     * @param s la scacchiera da esaminare
     * @return il numero totale di pezzi sulla scacchiera
     */
    public int contaPezzi(Scacchiera s){
        int count=0;
        for(int i = 0; i <9; i++){
            for(int j=0; j<9; j++){
                if(s.casella[i][j].isOccupata())
                    count+=1;
            }
        }
        return count;
    }

    /**
     * Calcola il valore totale dei pezzi sulla scacchiera.
     *
     * @param s la scacchiera da esaminare
     * @return il valore totale dei pezzi sulla scacchiera
     */

    public int contaValorePezzi(Scacchiera s){
        int valore=0;
        for(int i = 0; i <9; i++){
            for(int j=0; j<9; j++){
                if(s.casella[i][j].isOccupata())
                    valore+=s.casella[i][j].pezzo.getVALORE();
            }
        }
        return valore;
    }

}
