package logic;

//import Controller.PedoneServiceImpl;
import domain.Casella;
import domain.Pezzo;
import domain.Scacchiera;

import java.util.Scanner;

public class ChessGame {
    static Casella[][] casella = new Casella[9][9];
    static Scacchiera scacchiera = new Scacchiera(casella);

    public static void main(String[] args) {
        ViewScacchiera viewScacchiera= new ViewScacchiera(scacchiera);
        }
           /* Scanner scanner = new Scanner(System.in);
            System.out.println("Inserisci mossa: posX: ");
            int a = scanner.nextInt();
            System.out.println("Inserisci mossa: posY: ");
            int b = scanner.nextInt();
            PedoneServiceImpl p1 = new PedoneServiceImpl(c);

            c = p1.move("p1", a, b);
            for (int i = 0; i < 9; i++) {
                System.out.println();
                for (int j = 0; j < 9; j++) {
                    System.out.print(c.p[i][j].getNome() + " ");
                }
            }

*/

    }
