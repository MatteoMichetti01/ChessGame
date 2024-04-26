package logic;

import domain.*;

import java.util.Random;

public class Promozione {
    static int contaTorri=2;
    static int contaRegine=1;
    static int contaCavalli=2;
   static int contaAlfieri=2;


    public static void promozione(Giocatore g, Scacchiera scacchiera, int vecchiaPosX, int vecchiaPosY)  {
        System.out.println("PROMOZIONE! SCEGLI IL NUOVO PEZZO");
        System.out.println("Scegli il nuovo pezzo (torre, regina, alfiere o cavallo):");
        if(g.getClass().equals(Umano.class)){
            GestioneInput gestioneInput = GestioneInput.getIstanza();
            String pezzoPromosso = gestioneInput.leggiPezzoInputPromozione();
            switch (pezzoPromosso) {
                case "torre":
                    contaTorri++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Torre("t"+contaTorri+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "regina":
                    contaRegine++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Regina("q"+contaRegine+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "alfiere":
                    contaAlfieri++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Alfiere("a"+contaAlfieri+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "cavallo":
                    contaCavalli++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Cavallo("c"+contaCavalli+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;

            }

        }
        else {
            Random r = new Random();
            String[] A ={"torre","regina","alfiere","cavallo"};
            int c = r.nextInt(A.length);
            String scelta = A[c];
            switch (scelta) {
                case "torre":
                    contaTorri++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Torre("t"+contaTorri+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "regina":
                    contaRegine++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Regina("q"+contaRegine+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "alfiere":
                    contaAlfieri++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Alfiere("a"+contaAlfieri+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;
                case "cavallo":
                    contaCavalli++;
                    scacchiera.casella[vecchiaPosX][vecchiaPosY]=new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), new Cavallo("c"+contaCavalli+scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getNome().charAt(2),g.getColore()),vecchiaPosX,vecchiaPosY,true);
                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;

            }


        }
    }
}
