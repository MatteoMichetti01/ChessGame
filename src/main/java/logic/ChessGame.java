package logic;

import Controller.PedoneServiceImpl;
import domain.Pezzo;
import domain.Scacchiera;

import java.util.Scanner;

public class ChessGame {
    static Pezzo[][] p = new Pezzo[9][9];
    static Scacchiera c = new Scacchiera(p);

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(c.p[i][j].getNome() + " ");
            }
        }
            Scanner scanner = new Scanner(System.in);
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



    }
}