package logic;

import bozzascritturafile.ScriviSuFile;

import java.io.IOException;
import java.util.List;

public class Partita {

    Modalita modalita;

    public Partita() {

    }

    public void selezionaModalita() throws MossaNonValida, IOException {

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



    public void menuPrincipale() throws MossaNonValida, IOException {
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
            String fileName = gestioneInput.leggiInput();
            try {
                //da aggiustare qui perchè non prende l'istanza, bisogna vedere come prendere l'istanza dal singleton (getinstance)
                System.out.println("Partita caricata con successo.");
                this.modalita = ScriviSuFile.caricaPartita(fileName);
                this.modalita.avviaPartita();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Errore durante il caricamento della partita: " + e.getMessage());
            }

        }
    }


}
