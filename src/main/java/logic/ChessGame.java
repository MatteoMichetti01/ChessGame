package logic;

//import Controller.PedoneServiceImpl;
import domain.*;
import controller.*;
import java.util.Scanner;

public class ChessGame {
    static Scacchiera scacchiera = new Scacchiera();

    public static void main(String[] args) throws MossaNonValida {
        Partita partita = new Partita();
        Modalita modalita = partita.selezionaModalita();
        modalita.startGame();

    }
}
