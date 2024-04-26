package logic;

import java.io.IOException;

public class ChessGame {
    public static void NewGame() throws MossaNonValida, IOException {
        Partita partita = new Partita();
        partita.menuPrincipale();
    }

    public static void main(String[] args) throws MossaNonValida, IOException {
        ChessGame.NewGame();
    }
}
