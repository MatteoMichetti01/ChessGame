package logic;

import java.io.IOException;

/**
 * La classe ChessGame è l'entry point dell'applicazione per avviare una nuova partita di scacchi.
 */
public class ChessGame {

    /**
     * Avvia una nuova partita di scacchi.
     *
     * @throws MossaNonValida se viene effettuata una mossa non valida durante la partita.
     * @throws IOException     se si verifica un errore di input/output durante l'esecuzione.
     * @throws InputNonValido se viene fornito un input non valido durante la partita.
     */
    public static void nuovaPartita() throws MossaNonValida, IOException, InputNonValido {
        Partita partita = new Partita();
        partita.menuPrincipale();
    }

    /**
     * Metodo principale per avviare l'applicazione e iniziare una nuova partita di scacchi.
     *
     * @param args gli argomenti della riga di comando (non utilizzati in questo caso).
     * @throws MossaNonValida se viene effettuata una mossa non valida durante la partita.
     * @throws IOException     se si verifica un errore di input/output durante l'esecuzione.
     * @throws InputNonValido se viene fornito un input non valido durante la partita.
     */
    public static void main(String[] args) throws MossaNonValida, IOException, InputNonValido {
        ChessGame.nuovaPartita();
    }
}
