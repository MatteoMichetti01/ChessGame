package controller;

import domain.Casella;
import domain.Scacchiera;
import logic.Giocatore;
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


    //questo metodo esegue una mossa con ogni pezzo bianco che lo va a posizionare sulla casella del re nero
    //in teoria se non viene sollevata l'eccezione "pezzi in mezzo" vuol dire che il re è sotto scacco da parte di qualche pezzo
    public static boolean controlloScaccoReNero(Scacchiera scacchiera) throws MossaNonValida {
        int posxRE= 0 , posyRE = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getNome().equals("re") && scacchiera.casella[i][j].getPezzo().getColore().equals("nero")) {
                        posxRE = i;
                        posyRE = j;
                    }

                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getColore().equals("bianco")) {
                        PezzoService p1 = new PezzoService(scacchiera);
                        String PosReNero = PosReNero(scacchiera);
                        try {
                            p1.controlloScacco(scacchiera.casella[i][j].getPezzo().getNome(), posxRE,posyRE,i,j, scacchiera);
                            System.out.println("il re è sotto scacco");
                        } catch (MossaNonValida m) {
                            return false;
                        }
                    }

                }

            }


        }
        return true;
    }
}
