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
            System.out.println("Tocca a "+ this.giocatore1.getNome());
            System.out.println("Inserisci il pezzo che vuoi spostare:");
            String pezzoBianco = scanner.nextLine();
            System.out.println("Inserisci mossa: ");
            String mossaBianco= scanner.nextLine();
            scacchiera = p1.move(pezzoBianco, mossaBianco, this.giocatore1.getColore());
            scacchiera.viewscacchiera();
            System.out.println("");
            //TURNO GIOCATORE NERO
            System.out.println("Tocca a "+ this.giocatore2.getNome());
            System.out.println("Inserisci il pezzo che vuoi spostare:");
            String pezzoNero= scanner.nextLine();
            System.out.println("Inserisci mossa: ");
            String mossaNero= scanner.nextLine();
            scacchiera = p1.move(pezzoNero, mossaNero, this.giocatore2.getColore());
            scacchiera.viewscacchiera();
            System.out.println("");
        }

    }
}
