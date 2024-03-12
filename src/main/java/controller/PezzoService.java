package controller;
import domain.*;
import logic.MossaNonValida;

public class PezzoService implements Mossa {

    Scacchiera scacchiera;

    public PezzoService(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
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
        //questo if qua sopra per ora va bene ma quando implementeremo "mangiare" bisogna gestirlo
        switch (nomePezzo.charAt(0)) {

            case 'p':
                PedoneService.controlloPedone(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
                break;

            case 'a':
                AlfiereService.controlloAlfiere(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
                break;

            case 't':
                TorreService.controlloTorre(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
                break;

            case 'c':
               CavalloService.controlloCavallo(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
               break;

            case 'q':
                ReginaService.controlloRegina(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
                break;
                
            case 'r':
                ReService.controlloRe(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera);
                break;
        }

        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(new_Posizione,scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX,nuovaPosY, true);
        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("  ", vecchiapos, false);
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if(scacchiera.casella[i][j].getNome() != "  ") {
                    if (scacchiera.casella[i][j].getPezzo().getNome().equals("re") && scacchiera.casella[i][j].getPezzo().getColore().equals("nero")) {
                        Re re = new Re();
                        re = (Re) scacchiera.casella[i][j].getPezzo();
                        re.setPosX(i);
                        re.setPosY(j);
                        re.controlloScacco(re,scacchiera);
                    }
                }
            }
        }

        return scacchiera;
    }
}

/*System.out.println("vecchia posx: "+vecchiaPosX);
                System.out.println("vecchia posy: "+vecchiaPosY);
                System.out.println("nuova posx: "+nuovaPosX);
                System.out.println("nuova posy: "+nuovaPosY);*/
