package logic;

import ScritturaSuFile.ScriviSuFile;
import domain.Computer;
import domain.Giocatore;
import domain.Umano;

import java.io.IOException;
import java.util.List;

public class Partita {

    Modalita modalita;

    /**
     * Costruttore della classe Partita.
     * Crea una nuova istanza della classe Partita.
     */
    public Partita() {}

    /**
     * Gestisce la selezione della modalità di gioco e avvia la partita corrispondente.
     * L'utente può selezionare tra "Giocatore contro giocatore" e "Giocatore contro computer".
     *
     * @throws MossaNonValida Se viene effettuata una mossa non valida durante la partita.
     * @throws IOException     Se si verifica un errore di I/O durante il salvataggio della partita.
     * @throws InputNonValido Se viene fornito un input non valido durante l'interazione con l'utente.
     */
    public void selezionaModalita() throws MossaNonValida, IOException, InputNonValido {
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        System.out.println("Seleziona la modalità di gioco: ");
        System.out.println("- Giocatore contro giocatore (1)");
        System.out.println("- Giocatore contro computer (2)");
        String input = gestioneInput.leggiNumeroInput();
        if(input.equals("1")){
            System.out.println("Chi vuole essere il bianco?(inserisci il nome):  ");
            String nomeBianco = gestioneInput.inputNonVuoto();
            Giocatore g1 = new Umano(nomeBianco,"bianco");
            System.out.println("Inserisci nome per giocatore nero:  ");
            String nomeNero = gestioneInput.inputNonVuoto();
            Giocatore g2 = new Umano(nomeNero,"nero");
            SessioneGioco sessione = SessioneGioco.getInstanza(g1,g2);
            sessione.avviaPartita();
        }
        else {
            SessioneGioco.pulisciIstanza();
            System.out.println("Quale colore vuoi essere?(bianco o nero):  ");
            String colore = gestioneInput.inputColore();
            System.out.println("Inserisci il nome:  ");
            String nome = gestioneInput.inputNonVuoto();

            if (colore.equals("nero")) {
                Giocatore g1 = new Computer("computer", "bianco");
                Giocatore g2 = new Umano(nome, colore);
                SessioneGioco sessione = SessioneGioco.getInstanza(g1,g2);
                sessione.avviaPartita();
            } else {
                Giocatore g1 = new Umano(nome, colore);
                Giocatore g2 = new Computer("computer", "nero");
                SessioneGioco sessione = SessioneGioco.getInstanza(g1,g2);
                sessione.avviaPartita();
            }
        }
    }


    /**
     * Gestisce il menu principale del gioco.
     * L'utente può scegliere tra "Nuova partita" e "Carica partita".
     *
     * @throws MossaNonValida Se viene effettuata una mossa non valida durante la partita.
     * @throws IOException     Se si verifica un errore di I/O durante il salvataggio della partita.
     * @throws InputNonValido Se viene fornito un input non valido durante l'interazione con l'utente.
     */
    public void menuPrincipale() throws MossaNonValida, IOException, InputNonValido {
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        System.out.println("BENVENUTO IN SCACCHI MAC!");
        System.out.println("Nuova partita (1)");
        System.out.println("Carica partita (2)");
        String input = gestioneInput.leggiNumeroInput();
        if (input.equals("1")) {
            this.selezionaModalita();
        }
        if(input.equals("2")) {
            List<String> fileSalvati = ScriviSuFile.elencoPartiteSalvate();
            for (String fileName : fileSalvati) {
                System.out.println(fileName);
            }
            System.out.println("Inserisci il nome della partita che vuoi caricare:");
            try {
                this.modalita = ScriviSuFile.caricaPartita(gestioneInput.leggiInput());
                System.out.println("Partita caricata con successo.");
                this.modalita.avviaPartita();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Errore durante il caricamento della partita: " + e.getMessage());
            }

        }
    }


}
