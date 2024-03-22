package logic;

//import Controller.PedoneServiceImpl;

import controller.MossaNonValida;

public class ChessGame {
    public static void newGame() throws MossaNonValida {
        Partita partita = new Partita();
        partita.menuPrincipale();
    }

    public static void main(String[] args) throws MossaNonValida {
        ChessGame.newGame();
    }
}
