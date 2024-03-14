package controller;
import domain.*;
import logic.MossaNonValida;

public class PezzoService implements Mossa {
    public boolean sottoScacco = false;
    Scacchiera scacchiera;

    public PezzoService(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }



    public boolean controlloScacco(String nomePezzo, int nuovaPosX,int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera ) throws MossaNonValida {
        if (nuovaPosX == 0 || nuovaPosY == 0) throw new MossaNonValida("Mossa non valida, fuori scacchiera");
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");
        //switch-case
        ControlloMosse.controlloMossa(nomePezzo,nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
        return true;
    }


    public Scacchiera move (String nomePezzo, String new_Posizione, String colore) throws MossaNonValida {
        int vecchiaPosX  = 0 , vecchiaPosY = 0 ;
        int nuovaPosX  = 0 , nuovaPosY = 0;
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
        if (nuovaPosX == 0 || nuovaPosY == 0) throw new MossaNonValida("Mossa non valida, fuori scacchiera");
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");

        ControlloMosse.controlloMossa(nomePezzo,nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);

        // effettua la mossa: mette nella nuova posizione il pezzo, e inserisce la casella vuota nella vecchia posizione
        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(new_Posizione,scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX,nuovaPosY, true);
        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("   ", vecchiapos, false);

        if(sottoScacco){
            sottoScacco = Scacco.uscitaScacco(scacchiera , nuovaPosX, nuovaPosY);
            if (sottoScacco) {
                scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(new_Posizione,scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX,vecchiaPosY, true);
                scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", vecchiapos, false);
                throw new MossaNonValida("sei ancora in scacco, riprova un'altra mossa ");
            }
        }
        sottoScacco = Scacco.controlloScacco(scacchiera,nuovaPosX,nuovaPosY);

        return scacchiera;
    }
}

/*System.out.println("vecchia posx: "+vecchiaPosX);
                System.out.println("vecchia posy: "+vecchiaPosY);
                System.out.println("nuova posx: "+nuovaPosX);
                System.out.println("nuova posy: "+nuovaPosY);*/
