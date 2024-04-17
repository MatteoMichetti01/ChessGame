package logic;

import domain.*;

import java.util.Random;

public class Promozione {
    public static void promozione(Giocatore g, Scacchiera scacchiera, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY) throws MossaNonValida {
        if(g.getClass().equals(Umano.class)){
            GestioneInput gestioneInput = GestioneInput.getInstance();
            String pezzoPromosso = gestioneInput.leggiPezzoInputPromozione();
        switch (pezzoPromosso) {
            case "torre":
                scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Torre("tP"+g.getColore().charAt(0),g.getColore()),nuovaPosX,nuovaPosY,true);
                break;
            case "regina":
                scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Regina("qP"+g.getColore().charAt(0),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                break;
            case "alfiere":
                scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Alfiere("aP"+g.getColore().charAt(0),g.getColore()),nuovaPosX,nuovaPosY,true);

                break;
            case "cavallo":
                scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Cavallo("cP"+g.getColore().charAt(0),g.getColore()),nuovaPosX,nuovaPosY,true);

                break;

            default:
                System.out.println("Scelta non valida.");
                break;

        }

    }
        else {
            Random r = new Random();
            String A[]={"torre","regina","alfiere","cavallo"};
            int c = r.nextInt(A.length);
            String scelta = A[c];
            switch (scelta) {
                case "torre":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Torre("tP"+g.getColore().charAt(0), g.getColore()),nuovaPosX,nuovaPosY,true);
                    break;
                case "regina":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Regina("qP"+g.getColore().charAt(0),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "alfiere":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Alfiere("aP"+g.getColore().charAt(0),g.getColore()),nuovaPosX,nuovaPosY,true);

                    break;
                case "cavallo":
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), new Cavallo("cP"+g.getColore().charAt(0),g.getColore()),nuovaPosX,nuovaPosY,true);

                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;

            }


        }
    }
}
