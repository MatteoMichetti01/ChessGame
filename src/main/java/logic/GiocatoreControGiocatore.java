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
        Boolean resa= true;
        Boolean mossaFatta= false;
        Scanner scanner = new Scanner(System.in);
        Scacchiera scacchiera = new Scacchiera();
        PezzoService p1 = new PezzoService(scacchiera);
        scacchiera.viewscacchiera();
        System.out.println("");
        System.out.println("Inizia il turno "+ this.giocatore1.getNome());
        while (true && resa) {

            //TURNO GIOCATORE BIANCO
            while (!mossaFatta && resa) {
                System.out.println("Tocca a " + this.giocatore1.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                String pezzoBianco = scanner.nextLine();
                if(pezzoBianco.equals("o")){if(this.opzioni().equals("3")) resa=false; break;}
                System.out.println("Inserisci mossa: ");
                String mossaBianco = scanner.nextLine();
                try {
                    scacchiera = p1.move(pezzoBianco+"W", mossaBianco.toUpperCase(), this.giocatore1.getColore());
                    mossaFatta = true;
                } catch (MossaNonValida m) {
                    System.out.println(m);
                }
            }
            scacchiera.viewscacchiera();
            System.out.println("");
            mossaFatta=false;

            //TURNO GIOCATORE NERO
            while (!mossaFatta && resa) {
                System.out.println("Tocca a " + this.giocatore2.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare:");
                String pezzoNero = scanner.nextLine();
                System.out.println("Inserisci mossa: ");
                String mossaNero = scanner.nextLine();
                try{
                    scacchiera = p1.move(pezzoNero+"B", mossaNero.toUpperCase(), this.giocatore2.getColore());
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
        System.out.println("Fine partita!");

    }

    @Override
    public String opzioni() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Salva partita (1)");
        System.out.println("Annulla mossa (2)");
        System.out.println("Arrenditi (3)");
        return input = scanner.nextLine();
    }
}
