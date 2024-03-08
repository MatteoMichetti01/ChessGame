package logic;

//import Controller.PedoneServiceImpl;
import domain.*;
import controller.*;
import java.util.Scanner;

public class ChessGame {
    static Scacchiera scacchiera = new Scacchiera();

    public static void main(String[] args) throws MossaNonValida {
        /*boolean trovato=true;
        while (trovato) {

            ViewScacchiera viewScacchiera = new ViewScacchiera(scacchiera);
            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            System.out.println("Quale colore vuoi essere?(bianco o nero): ");
            String c = scanner.nextLine();
            System.out.println("");
            System.out.println("Inserisci il pezzo che vuoi spostare:");
            String a = scanner.nextLine();
            System.out.println("Inserisci mossa: ");
            String b = scanner.nextLine();
            PezzoService p1 = new PezzoService(scacchiera);

            scacchiera = p1.move(a, b, c);

            viewScacchiera.viewscacchiera(scacchiera);
            System.out.println();
            System.out.println();
        }*/
        Partita partita = new Partita();
        Modalita modalita = partita.selezionaModalita();
        modalita.startGame();

    }
}
