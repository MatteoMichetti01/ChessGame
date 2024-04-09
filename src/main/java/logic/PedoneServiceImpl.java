package logic;
import domain.*;
import domain.Scacchiera;

import java.util.Scanner;

public class PedoneServiceImpl implements PezzoService<Pedone> {

    @Override
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (nuovaPosY == vecchiaPosY && (vecchiaPosX == 2 || vecchiaPosX == 7)) {
            if (Math.abs(nuovaPosX - vecchiaPosX) > 2)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare alla prima mossa al massimo di due caselle");
        } else {
            if (nuovaPosY == vecchiaPosY && Math.abs(nuovaPosX - vecchiaPosX) > 1)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare una casella alla volta");
        }
        if (nuovaPosY != vecchiaPosY) {
            if (Math.abs(nuovaPosX - vecchiaPosX) != 1 || Math.abs(nuovaPosY - vecchiaPosY) != 1) {
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
            }
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null)
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
        }

        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        if (colore.equals("bianco")) {
            if (vecchiaPosX < nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        } else {
            if (vecchiaPosX > nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        }

        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && nuovaPosY == vecchiaPosY)
            throw new MossaNonValida("il pedone mangia solo in diagonale");


        //PROMOZIONE
        if (scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("bianco") && nuovaPosX == 1) {
            System.out.println("PROMOZIONE! SCEGLI IL NUOVO PEZZO");
            System.out.println("Scegli il nuovo pezzo (torre, regina, alfiere o cavallo):");
            GestioneInput gestioneInput = GestioneInput.getInstance();
            String pezzoBianco = gestioneInput.leggiPezzoInputPromozione();
            switch (pezzoBianco) {
                case "torre":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Torre("tPW","bianco"),nuovaPosX,nuovaPosY,true);
                    System.out.println("posizione " + scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione());
                    System.out.println("pezzo " + scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getNome());

                    break;
                case "regina":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Regina("qPW","bianco"),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "alfiere":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Alfiere("aPW","bianco"),nuovaPosX,nuovaPosY,true);

                    break;
                case "cavallo":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Cavallo("cPW","bianco"),nuovaPosX,nuovaPosY,true);

                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;

            }

        }
        if (scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("nero") && nuovaPosX == 8) {
            System.out.println("PROMOZIONE! SCEGLI IL NUOVO PEZZO");
            System.out.println("Scegli il nuovo pezzo (torre, regina, alfiere o cavallo):");
            GestioneInput gestioneInput = GestioneInput.getInstance();
            String pezzoNero = gestioneInput.leggiPezzoInputPromozione();
            switch (pezzoNero) {
                case "torre":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Torre("tPB","nero"),nuovaPosX,nuovaPosY,true);
                    break;
                case "regina":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Regina("qPB","nero"),nuovaPosX,nuovaPosY,true);
                    break;
                case "alfiere":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Alfiere("aPB","nero"),nuovaPosX,nuovaPosY,true);

                    break;
                case "cavallo":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Cavallo("cPB","nero"),nuovaPosX,nuovaPosY,true);

                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;

            }

        }
    }

}
