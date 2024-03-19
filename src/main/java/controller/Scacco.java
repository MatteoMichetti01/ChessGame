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

    public static boolean uscitaScacco(Scacchiera scacchiera, int posPezzoX,int posPezzoY){
        int posxRE = 0, posyRE = 0;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        if (colore.equals("nero")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reB") && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
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
                        if (scacchiera.casella[i][j].getPezzo().getNome().equals("reW") && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
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
                    if (!(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                        PezzoService p1 = new PezzoService(scacchiera);
                        try {
                            p1.controlloSePezzoArrivaRe(scacchiera.casella[i][j].getPezzo().getNome(), posxRE,posyRE,i,j, scacchiera);
                            //System.out.println("IL RE "+scacchiera.casella[posxRE][posyRE].getPezzo().getColore().toUpperCase()+" E' ANCORA SOTTO SCACCO!");
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
        //controlloScaccoMatto(scacchiera,posPezzoX,posPezzoY,colore,posxRE,posyRE);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                        PezzoService p1 = new PezzoService(scacchiera);
                        try {
                            p1.controlloSePezzoArrivaRe(scacchiera.casella[i][j].getPezzo().getNome(), posxRE, posyRE, i, j, scacchiera);
                            System.out.println("IL RE " + scacchiera.casella[posxRE][posyRE].getPezzo().getColore().toUpperCase() + "E' SOTTO SCACCO!");
                            return true;
                        } catch (MossaNonValida m) {}
                    }
                }
            }
            return false;
    }

    public static boolean controlloScaccoMatto(Scacchiera scacchiera, int posPezzoX, int posPezzoY)throws MossaNonValida {
        boolean mossasi = true;
        boolean sScacco = true;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                    for (int k=1; k<9; k++) {
                        for (int z=1; z<9; z++) {
                            mossasi = true;
                            if (scacchiera.casella[k][z].isOccupata() && scacchiera.casella[i][j].getPezzo().getColore().equals(scacchiera.casella[k][z].getPezzo().getColore())) {
                                mossasi = false;
                            }
                            else {
                                try {
                                    ControlloMosse.controlloMossa(scacchiera.casella[i][j].getPezzo().getNome(), k, z, i, j, scacchiera);
                                } catch (MossaNonValida m) {
                                    mossasi = false;
                                }
                            }
                            if (mossasi) {
                                scacchiera.casella[k][z] = new Casella(scacchiera.casella[i][j].getNome(), scacchiera.casella[i][j].getPezzo(), k, z, true);
                                scacchiera.casella[i][j] = new Casella("   ", scacchiera.casella[i][j].getPosizione(), false);
                                sScacco = Scacco.uscitaScacco(scacchiera, k, z);
                                scacchiera.casella[i][j] = new Casella(scacchiera.casella[k][z].getNome(), scacchiera.casella[k][z].getPezzo(), i, j, true);
                                scacchiera.casella[k][z] = new Casella("   ", scacchiera.casella[k][z].getPosizione(), false);
                                if (!(sScacco)) {
                                    return false;
                                }
                            }

                        }
                    }
                }
            }
        }
        return true;
    }
}
