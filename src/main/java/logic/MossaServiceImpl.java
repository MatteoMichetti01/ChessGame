package logic;
import domain.*;

public class MossaServiceImpl implements Mossa {
    public boolean sottoScacco = false;
    public boolean scaccoMatto = false;

    public boolean pezzoInchiodato = false;
    public Pezzo pezzoMangiato;

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



    public Scacchiera move(String nomePezzo, String new_Posizione, String colore) throws MossaNonValida {
        int vecchiaPosX = 0, vecchiaPosY = 0;
        int nuovaPosX = 0, nuovaPosY = 0;
        String vecchiapos = " ";
        //queste variabili servono per pezzo inchiodato
        int tempPosX=0, tempPosY=0;
        String coloreOpposto;


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


        SessioneGioco m1 = SessioneGioco.getInstanza();
        //PROMOZIONE
        if (scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("bianco") &&  nuovaPosX == 1 && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getClass().equals(Pedone.class) ) {
            if(scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && !scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getNome().equals("reB")) {
                Promozione.promozione(m1.giocatore1, scacchiera,vecchiaPosX, vecchiaPosY);
            }
            else if(!scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata()){
                Promozione.promozione(m1.giocatore1, scacchiera,vecchiaPosX, vecchiaPosY);
            }
        }
        if(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("nero") && nuovaPosX == 8 && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getClass().equals(Pedone.class)){
            if(scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && !scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getNome().equals("reW")) {
                Promozione.promozione(m1.giocatore2, scacchiera, vecchiaPosX, vecchiaPosY);
            }
            else if(!scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata()){
                Promozione.promozione(m1.giocatore2, scacchiera, vecchiaPosX, vecchiaPosY);
            }

        }


        //punteggio


       if(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo()!=null) {
           //CONTROLLO SE IL PEZZO HA UN COLORE DIVERSO
           if (!(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))) {
               //verifico il colore
             pezzoMangiato=scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo();
           }
       }

        Casella temp = new Casella("   ", new_Posizione,false);
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            temp = scacchiera.casella[nuovaPosX][nuovaPosY];
        }
        // effettua la mossa: mette nella nuova posizione il pezzo, e inserisce la casella vuota nella vecchia posizione
        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(new_Posizione, scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX, nuovaPosY, true);
        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("   ", vecchiapos, false);

        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals("bianco")) {
            coloreOpposto = "nero";
        }
        else {
            coloreOpposto = "bianco";
        }
        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++) {
                if(scacchiera.casella[i][j].isOccupata() && scacchiera.casella[i][j].getPezzo().getColore().equals(coloreOpposto)) {
                    tempPosX=i;
                    tempPosY=j;
                    i=9;
                    j=9;
                }
            }
        }
        pezzoInchiodato = Scacco.controlloScacco(scacchiera,tempPosX,tempPosY);
        if(pezzoInchiodato && !sottoScacco) {
            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(vecchiapos, scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
            scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
            throw new MossaNonValida("il pezzo è inchiodato, non puoi metterti in scacco da solo");
        }

        if (sottoScacco) {
            sottoScacco = Scacco.uscitaScacco(scacchiera, nuovaPosX, nuovaPosY);
            if (sottoScacco) {
                scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(vecchiapos, scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                scacchiera.casella[nuovaPosX][nuovaPosY] = temp;
                throw new MossaNonValida("sei ancora in scacco, riprova con un'altra mossa ");
            }
        }
        sottoScacco = Scacco.controlloScacco(scacchiera, nuovaPosX, nuovaPosY);

        if (sottoScacco) {
            scaccoMatto = Scacco.controlloScaccoMatto(scacchiera, nuovaPosX, nuovaPosY);
            if (scaccoMatto){
                scacchiera.viewScacchiera();
                System.out.println();
                System.out.println("SCACCO MATTO");
                System.out.println("Il "+ scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore() +" ha vinto!");
                SessioneGioco.setScaccoMatto1(true);
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
