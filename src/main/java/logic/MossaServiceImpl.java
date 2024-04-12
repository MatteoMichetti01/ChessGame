package logic;
import domain.*;

public class MossaServiceImpl implements Mossa {
    public boolean sottoScacco = false;
    public boolean scaccoMatto = false;

    Scacchiera scacchiera;


    public MossaServiceImpl(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }

    // questo metodo viene chiamato da controlloScacco e controlla se il pezzo che stiamo controllando può arrivare alla casella del re. Se può farlo restituisce true, quindi è sotto scacco, se non può farlo va in eccezione e quindi va avanti
    public static void controlloSePezzoArrivaRe(int PosXRe, int PosYRe, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (PosXRe == 0 || PosYRe == 0) throw new MossaNonValida("Mossa non valida, fuori scacchiera");
        if (scacchiera.casella[PosXRe][PosYRe].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[PosXRe][PosYRe].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");
        PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getClass());
        service.controlloMossa(PosXRe,PosYRe,vecchiaPosX,vecchiaPosY,scacchiera);
    }

    /*public static boolean pezzoInchiodato (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        int posxRE = 0, posyRE = 0;
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
        Casella temp = new Casella("   ", scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(),false);
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            temp = scacchiera.casella[nuovaPosX][nuovaPosY];
        }
        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX, nuovaPosY, true);
        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("   ", scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), false);
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].isOccupata() && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                    try {
                        controlloSePezzoArrivaRe(posxRE, posyRE, i, j, scacchiera);
                        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                        if (temp.isOccupata()) {
                            scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
                        } else {
                            scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
                        }
                        return true;
                    } catch (MossaNonValida m) {
                    }
                }
            }
        }

        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
        if (temp.isOccupata()) {
            scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
        } else {
            scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
        }
        return false;
    }

     */


    public Scacchiera move(String nomePezzo, String new_Posizione, String colore) throws MossaNonValida {
        int vecchiaPosX = 0, vecchiaPosY = 0;
        int nuovaPosX = 0, nuovaPosY = 0;
        String vecchiapos = " ";


        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                if (scacchiera.casella[x][y].getNome().equals(nomePezzo) && scacchiera.casella[x][y].getPezzo().getColore().equals(colore)) {
                    vecchiaPosX = x;
                    vecchiaPosY = y;
                    vecchiapos = scacchiera.casella[x][y].getPosizione();
                }

                if (scacchiera.casella[x][y].getPosizione().equals(new_Posizione)) {
                    nuovaPosX = x;
                    nuovaPosY = y;
                }
            }
        }
        if (vecchiaPosX == 0 || vecchiaPosY == 0) throw new MossaNonValida("Mossa non valida, il pezzo non esiste");
        if (nuovaPosX == 0 || nuovaPosY == 0) throw new MossaNonValida("Mossa non valida, fuori scacchiera1");
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");

        PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getClass());
        service.controlloMossa(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);

        //punteggio


       if(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo()!=null) {
           //CONTROLLO SE IL PEZZO HA UN COLORE DIVERSO
           if (!(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))) {
               //verifico il colore
               if (scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("bianco")) {
                   //assegno il valore del pezzo mangiato
                   int pb = GameSession.getGiocatore1().getPunteggio();
                   pb+=scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getValore();
                   GameSession.getGiocatore1().setPunteggio(pb);
                   System.out.println("punteggio bianco : " + GameSession.getGiocatore1().getPunteggio());
               } else {
                   int pn = GameSession.getGiocatore2().getPunteggio();
                   pn+=scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getValore();
                   GameSession.getGiocatore2().setPunteggio(pn);
                   System.out.println("punteggio nero : " + GameSession.getGiocatore2().getPunteggio());
               }
           }
       }

        Casella temp = new Casella("  ", new_Posizione,false);
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            temp = scacchiera.casella[nuovaPosX][nuovaPosY];
        }
        // effettua la mossa: mette nella nuova posizione il pezzo, e inserisce la casella vuota nella vecchia posizione
        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(new_Posizione, scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX, nuovaPosY, true);
        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("   ", vecchiapos, false);

        if (sottoScacco) {
            sottoScacco = Scacco.uscitaScacco(scacchiera, nuovaPosX, nuovaPosY);
            if (sottoScacco) {
               // System.out.println("il re è ancora sotto scacco");
                scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(vecchiapos, scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                if (temp.isOccupata()) {
                    scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
                } else {
                    scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
                }
                throw new MossaNonValida("sei ancora in scacco, riprova con un'altra mossa ");
            }
        }
        sottoScacco = Scacco.controlloScacco(scacchiera, nuovaPosX, nuovaPosY);

        if (sottoScacco) {
            scaccoMatto = Scacco.controlloScaccoMatto(scacchiera, nuovaPosX, nuovaPosY);
            if (scaccoMatto){
                scacchiera.viewscacchiera();
                System.out.println();
                System.out.println("SCACCO MATTO");
                System.out.println("Il "+ scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore() +" ha vinto!");
                GameSession.setScaccoMatto1(true);
            }
            else {
                if (colore.equals("bianco"))
                    System.out.println("Il re nero è sotto scacco!");
                else
                    System.out.println("Il re bianco è sotto scacco!");
            }
        }

        return scacchiera;
    }
}
