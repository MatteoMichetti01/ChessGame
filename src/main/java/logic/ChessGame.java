package logic;

//import Controller.PedoneServiceImpl;
import domain.*;
import Controller.*;
import java.util.Scanner;
import java.util.*;

public class ChessGame {
    static Casella[][] casella = new Casella[9][9];
    static Scacchiera scacchiera = new Scacchiera(casella);

    public static void main(String[] args) {

        ViewScacchiera viewScacchiera = new ViewScacchiera(scacchiera);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il pezzo che vuoi spostare:");
        String a = scanner.nextLine();
        System.out.println("Inserisci mossa: ");
        String b = scanner.nextLine();
        PedoneService p1 = new PedoneService(scacchiera);

        scacchiera = p1.move(a,b);

        viewScacchiera.viewscacchiera(scacchiera);

    }
}
