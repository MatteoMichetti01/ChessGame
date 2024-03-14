package logic;

import controller.PezzoService;
import domain.Scacchiera;

import java.util.Scanner;

public class GiocatoreControGiocatore extends Modalita{

    public GiocatoreControGiocatore(Giocatore giocatore1, Giocatore giocatore2) {

        super(giocatore1, giocatore2);
    }


    @Override
    public void startGame() throws MossaNonValida {
        Boolean mossaFatta= false;
        Scanner scanner = new Scanner(System.in);
        Scacchiera scacchiera = new Scacchiera();
        PezzoService p1 = new PezzoService(scacchiera);
        scacchiera.viewscacchiera();
        System.out.println("");
        System.out.println("Inizia il turno "+ this.giocatore1.getNome());
        while (true) {
            //TURNO GIOCATORE BIANCO
            while (!mossaFatta) {
                System.out.println("Tocca a " + this.giocatore1.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare:");
                String pezzoBianco = scanner.nextLine()+"W";
                System.out.println("Inserisci mossa: ");
                String mossaBianco = scanner.nextLine();
                try {
                    scacchiera = p1.move(pezzoBianco, mossaBianco, this.giocatore1.getColore());
                    mossaFatta = true;
                } catch (MossaNonValida m) {
                    System.out.println(m);
                }
            }
            scacchiera.viewscacchiera();
            System.out.println("");
            mossaFatta=false;
            //TURNO GIOCATORE NERO
            while (!mossaFatta) {
                System.out.println("Tocca a " + this.giocatore2.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare:");
                String pezzoNero = scanner.nextLine()+"B";
                System.out.println("Inserisci mossa: ");
                String mossaNero = scanner.nextLine();
                try{
                    scacchiera = p1.move(pezzoNero, mossaNero, this.giocatore2.getColore());
                    mossaFatta=true;
                }
                catch (MossaNonValida m) {
                    System.out.println(m);
                }
            }
            scacchiera.viewscacchiera();
            System.out.println("");
            mossaFatta=false;
        }

    }
}
