package logic;

//import Controller.PedoneServiceImpl;

public class ChessGame {
    public static void newGame() throws MossaNonValida {
        Partita partita = new Partita();
        partita.menuPrincipale();
    }

    public static void main(String[] args) throws MossaNonValida {
        ChessGame.newGame();
    }
}
