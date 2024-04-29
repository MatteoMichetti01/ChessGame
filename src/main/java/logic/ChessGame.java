package logic;

import java.io.IOException;

public class ChessGame {
    public static void nuovaPartita() throws MossaNonValida, IOException, InputNonValido {
        Partita partita = new Partita();
        partita.menuPrincipale();
    }

    public static void main(String[] args) throws MossaNonValida, IOException, InputNonValido {
        ChessGame.nuovaPartita();
    }
}
