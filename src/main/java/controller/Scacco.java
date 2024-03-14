package controller;

import domain.Casella;
import domain.Scacchiera;
import logic.MossaNonValida;

import java.util.*;
public class Scacco {

    public ArrayList<Casella> casellePossibiliReNero(Scacchiera scacchiera) {
        ArrayList<Casella> caselleReNero = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo().getNome().equals("re") && scacchiera.casella[i][j].getPezzo().getColore().equals("nero")) {
                    //dx
                    caselleReNero.add(scacchiera.casella[i + 1][j - 1]);
                    caselleReNero.add(scacchiera.casella[i + 1][j]);
                    caselleReNero.add(scacchiera.casella[i + 1][j + 1]);

                    caselleReNero.add(scacchiera.casella[i][j - 1]);
                    caselleReNero.add(scacchiera.casella[i][j + 1]);
                    //sx
                    caselleReNero.add(scacchiera.casella[i - 1][j - 1]);
                    caselleReNero.add(scacchiera.casella[i - 1][j]);
                    caselleReNero.add(scacchiera.casella[i - 1][j + 1]);


                }
            }
        }
        //avevo fatto un for each sostituito poi con questo removeIf
        caselleReNero.removeIf(Casella::isOccupata);
        return caselleReNero;
    }

    public static String PosReNero(Scacchiera scacchiera) {
        String posReN = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getNome().equals("re") && scacchiera.casella[i][j].getPezzo().getColore().equals("nero")) {
                        posReN = scacchiera.casella[i][j].getPosizione();
                    }

                }
            }
        }
        return posReN;
    }

    public static boolean uscitaScacco(Scacchiera scacchiera, int posPezzoX,int posPezzoY ){
        int posxRE= 0 , posyRE = 0;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getNome().equals("re") && (scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                        posxRE = i;
                        posyRE = j;
                    }

                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (!(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                        PezzoService p1 = new PezzoService(scacchiera);
                        try {
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE,posyRE,i,j, scacchiera);
                            System.out.println("il re "+scacchiera.casella[posxRE][posyRE].getPezzo().getColore()+" è ancora sotto scacco");
                            return true;
                        } catch (MossaNonValida m){ }
                    }
                }
            }
        }
        return false;
    }



    //questo metodo esegue una mossa con ogni pezzo bianco che lo va a posizionare sulla casella del re nero
    //in teoria se non viene sollevata l'eccezione "pezzi in mezzo" vuol dire che il re è sotto scacco da parte di qualche pezzo
    public static boolean controlloScacco(Scacchiera scacchiera, int posPezzoX, int posPezzoY) throws MossaNonValida {
        int posxRE = 0, posyRE = 0;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        if (colore.equals("bianco")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reB") && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                            posxRE = i;
                            posyRE = j;
                        }

                    }
                }
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reW") && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                            posxRE = i;
                            posyRE = j;
                        }

                    }
                }
            }
        }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                            PezzoService p1 = new PezzoService(scacchiera);
                            try {
                                p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE, posyRE, i, j, scacchiera);
                                System.out.println("il re " + scacchiera.casella[posxRE][posyRE].getPezzo().getColore() + " è sotto scacco");
                                return true;
                            } catch (MossaNonValida m) {
                            }
                        }
                    }
                }
            }
            return false;
    }

    public static boolean controlloScaccoMatto(Scacchiera scacchiera, int posPezzoX, int posPezzoY)throws MossaNonValida {
        Boolean intornoRe = false;
        int posxRE = 0, posyRE = 0;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        if (colore.equals("bianco")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reB") && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                            posxRE = i;
                            posyRE = j;
                        }

                    }
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reW") && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                            posxRE = i;
                            posyRE = j;
                        }

                    }
                }
            }
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(scacchiera.casella[i][j].getPezzo()!= null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)){
                    PezzoService p1= new PezzoService(scacchiera);
                    if (p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE - 1, posyRE, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE - 1, posyRE + 1, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE - 1, posyRE - 1, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE, posyRE + 1, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE, posyRE - 1, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE + 1, posyRE, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE + 1, posyRE - 1, i, j, scacchiera) &&
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE + 1, posyRE + 1, i, j, scacchiera)
                    ) {intornoRe = true;}
                }
            }
        }
        return intornoRe;
    }
}
