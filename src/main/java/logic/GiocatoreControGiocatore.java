package logic;

import controller.MossaNonValida;
import controller.impl.MossaServiceImpl;
import domain.Scacchiera;

import java.util.Scanner;

public class GiocatoreControGiocatore extends Modalita{



    public static boolean scaccoMatto1 = false;

    public static void setScaccoMatto1(boolean scaccoMatto) {
        scaccoMatto1 = scaccoMatto;
    }


    public GiocatoreControGiocatore(Giocatore giocatore1, Giocatore giocatore2) {

        super(giocatore1, giocatore2);
    }


    @Override
    public void startGame() throws MossaNonValida {
        String nomeResa = null;
        boolean resa= true;
        boolean mossaFatta= false;
        Scanner scanner = new Scanner(System.in);
        Scacchiera scacchiera = new Scacchiera();
        MossaServiceImpl p1 = new MossaServiceImpl(scacchiera);
        scacchiera.viewscacchiera();
        System.out.println();
        System.out.println("Inizia il turno "+ this.giocatore1.getNome());
        while (resa && !(scaccoMatto1)) {

            //TURNO GIOCATORE BIANCO
            while (!mossaFatta && resa && !(scaccoMatto1)) {
                System.out.println("Tocca a " + this.giocatore1.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                String pezzoBianco = scanner.nextLine();
                if(pezzoBianco.equals("o")){if(this.opzioni().equals("3")) resa=false;nomeResa= this.giocatore1.getNome();break;}
                System.out.println("Inserisci mossa: ");
                String mossaBianco = scanner.nextLine();
                try {
                    scacchiera = p1.move(pezzoBianco+"W", mossaBianco.toUpperCase(), this.giocatore1.getColore());
                    mossaFatta = true;
                } catch (MossaNonValida m) {
                    System.out.println(m.getMessage());
                    scacchiera.viewscacchiera();
                    System.out.println();
                }
            }
            if(mossaFatta && !(scaccoMatto1)){
            scacchiera.viewscacchiera();
            System.out.println();
            mossaFatta=false;}

            //TURNO GIOCATORE NERO
            while (!mossaFatta && resa && !(scaccoMatto1)) {
                System.out.println("Tocca a " + this.giocatore2.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                String pezzoNero = scanner.nextLine();
                if(pezzoNero.equals("o")){if(this.opzioni().equals("3")) resa=false;nomeResa= this.giocatore2.getNome();break;}
                System.out.println("Inserisci mossa: ");
                String mossaNero = scanner.nextLine();
                try{
                    scacchiera = p1.move(pezzoNero+"B", mossaNero.toUpperCase(), this.giocatore2.getColore());
                    mossaFatta=true;
                }
                catch (MossaNonValida m) {
                    System.out.println(m.getMessage());
                    scacchiera.viewscacchiera();
                    System.out.println();
                }
            }
            if(mossaFatta && !(scaccoMatto1)){
                scacchiera.viewscacchiera();
                System.out.println();
                mossaFatta=false;
            }
        }
        if (!resa) {
            System.out.println("Fine partita!");
            System.out.println(nomeResa + " si è arreso");
            System.out.println("Torna al menù principale (1)");
            System.out.println("Esci (2)");
            if(scanner.nextLine().equals("1")){ChessGame.newGame();}
        }
        if (scaccoMatto1) {
            System.out.println("Torna al menù principale (1)");
            System.out.println("Esci (2)");
            if(scanner.nextLine().equals("1")){ChessGame.newGame();}
        }

    }

    @Override
    public String opzioni() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Salva partita (1)");
        System.out.println("Annulla mossa (2)");
        System.out.println("Arrenditi (3)");
        return scanner.nextLine();
    }
}
