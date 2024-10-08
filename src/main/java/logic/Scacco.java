package logic;

import domain.Casella;
import domain.Pezzo;
import domain.Scacchiera;
import logic.impl.MossaServiceImpl;

/**
 * La classe Scacco fornisce metodi per gestire lo stato di scacco degli scacchi.
 */
public class Scacco {

    /**
     * Controlla se il re è ancora sotto scacco dopo una mossa.
     *
     * @param scacchiera La scacchiera corrente.
     * @param posPezzoX La coordinata X del pezzo mosso.
     * @param posPezzoY La coordinata Y del pezzo mosso.
     * @return true se il re è ancora sotto scacco dopo la mossa, false altrimenti.
     */
    public static boolean uscitaScacco(Scacchiera scacchiera, int posPezzoX,int posPezzoY){
        int posxRE = 0, posyRE = 0;
        // mi salvo in una variabile il colore del re che dovò controllare
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        // se il colore è nero mi salvo le coordinate del re nero
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
        // altrimenti se colore è bianco allora mi salvo le coordinate del re bianco
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

        // per ogni pezzo del colore opposto del re, controlla se può arrivare alla casella del re tramite il metodo
        // controlloSePezzoArrivaRe nella classe pezzo service. Se viene restituito true vuol dire che il re è ancora sotto scacco
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (!(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                        try {
                            MossaServiceImpl.controlloSePezzoArrivaRe(posxRE,posyRE,i,j, scacchiera);
                            return true;
                        } catch (MossaNonValida m){ }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Controlla se il re è sotto scacco.
     *
     * @param scacchiera La scacchiera corrente.
     * @param posPezzoX La coordinata X del pezzo mosso.
     * @param posPezzoY La coordinata Y del pezzo mosso.
     * @return true se il re è sotto scacco, false altrimenti.
     */
    public static boolean controlloScacco(Scacchiera scacchiera, int posPezzoX, int posPezzoY)  {
        int posxRE = 0, posyRE = 0;
        // mi salvo in una variabile il colore dei pezzi che dovò controllare
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        // se dovrò controllare i pezzi bianchi allora prendo le coordinate del re nero
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
        // altrimenti se devo controllare i pezzi neri allora prendo le coordinate del re bianco
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
        // per ogni pezzo del colore salvato inizialmente, controlla se può arrivare alla casella del re del colore opposto tramite il metodo
        // controlloSePezzoArrivaRe nella classe pezzo service. Se viene restituito true vuol dire che il re è sotto scacco
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                    try {
                        MossaServiceImpl.controlloSePezzoArrivaRe(posxRE, posyRE, i, j, scacchiera);
                        return true;
                    } catch (MossaNonValida m) { }
                }
            }
        }
        return false;
    }

    /**
     * Controlla se il re è in scacco matto.
     *
     * @param scacchiera La scacchiera corrente.
     * @param posPezzoX La coordinata X del pezzo mosso.
     * @param posPezzoY La coordinata Y del pezzo mosso.
     * @return true se il re è in scacco matto, false altrimenti.
     */
    public static boolean controlloScaccoMatto(Scacchiera scacchiera, int posPezzoX, int posPezzoY) {
        boolean mossasi;
        boolean sScacco;
        String colore = scacchiera.casella[posPezzoX][posPezzoY].getPezzo().getColore();
        // questi due for servono per prendere tutti i pezzi del colore che vogliamo controllare
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                    // questi due for servono per provare per ogni pezzo qualsiasi mossa all'interno della scacchiera
                    for (int k=1; k<9; k++) {
                        for (int z=1; z<9; z++) {
                            mossasi = true;
                            //Questo if-else serve per controllare se la mossa può essere effettuata. Se non può essere effettuata la variabile booleana
                            //mossasi viene impostata a false e passa alla mossa successiva
                            if (scacchiera.casella[k][z].isOccupata() && scacchiera.casella[i][j].getPezzo().getColore().equals(scacchiera.casella[k][z].getPezzo().getColore())) {
                                mossasi = false;
                            }
                            else {
                                try {
                                    PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[i][j].getPezzo().getClass());
                                    service.controlloMossa(k,z,i,j,scacchiera);
                                } catch (MossaNonValida m) {
                                    mossasi = false;
                                }
                            }
                            // Se la mossa è valida provo a simulare la mossa. Successivamente chiamo il metodo uscitaScacco che mi controlla
                            // se con quella determinata mossa sono uscito dallo scacco. Se avviene ciò allora annullo la mossa e restituisco
                            // false perchè vuol dire che c'è almeno una mossa che mi fa uscire dallo scacco e quindi automaticamente non è scacco
                            // matto. Se non trova nessuna mossa per nessun pezzo per uscire dallo scacco allora arriva alla fine del metodo e
                            // restituisce true perchè è scacco matto.
                            Casella temp = new Casella("  ", scacchiera.casella[k][z].getPosizione(), false);
                            if (mossasi) {
                                if(scacchiera.casella[k][z].isOccupata()){
                                    temp = scacchiera.casella[k][z];
                                }

                                    scacchiera.casella[k][z] = new Casella(scacchiera.casella[k][z].getPosizione(), scacchiera.casella[i][j].getPezzo(), k, z, true);
                                    scacchiera.casella[i][j] = new Casella("   ", scacchiera.casella[i][j].getPosizione(), false);
                                    sScacco = Scacco.uscitaScacco(scacchiera, k, z);
                                    scacchiera.casella[i][j] = new Casella(scacchiera.casella[i][j].getPosizione(), scacchiera.casella[k][z].getPezzo(), i, j, true);
                                    if (temp.isOccupata()) {
                                        scacchiera.casella[k][z] = temp;
                                    } else {
                                        scacchiera.casella[k][z] = new Casella("   ", scacchiera.casella[k][z].getPosizione(), false);
                                    }

                                if (!(sScacco)) {
                                    System.out.println("possibile mossa per uscire dallo scacco: " + scacchiera.casella[i][j].getPezzo().getNome() + " in " + scacchiera.casella[k][z].getPosizione());
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
