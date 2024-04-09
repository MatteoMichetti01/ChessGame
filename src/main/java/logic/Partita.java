package logic;

import java.util.Scanner;

public class Partita {
    private Giocatore giocatoreAttuale;
    Modalita modalita;

    public Partita() {

    }

    public void selezionaModalita() throws MossaNonValida {

        GestioneInput gestioneInput = GestioneInput.getInstance();
        System.out.println("Seleziona la modalità di gioco: ");
        System.out.println("- Giocatore contro giocatore (1)");
        System.out.println("- Giocatore contro computer (2)");
        String input = gestioneInput.leggiNumeroInput();
        if(input.equals("1")){
            System.out.println("Chi vuole essere il bianco?(inserisci il nome):  ");
            String nomeBianco = gestioneInput.inputNonVuoto();
            Giocatore g1 = new Giocatore(nomeBianco,"bianco");
            System.out.println("Inserisci nome per giocatore nero:  ");
            String nomeNero = gestioneInput.inputNonVuoto();
            Giocatore g2 = new Giocatore(nomeNero,"nero");
            this.modalita = new GiocatoreControGiocatore(g1, g2);
            this.modalita.startGame();
        }
        else{
        System.out.println("Quale colore vuoi essere?(bianco o nero):  ");
        String colore = gestioneInput.inputNonVuoto();
        System.out.println("Inserisci il nome:  ");
        String nome = gestioneInput.inputNonVuoto();
        Giocatore g2 = new Giocatore(nome,colore);
        Giocatore g1 = new Giocatore("computer","bianco");
            this.modalita= new GiocatoreControComputer(g1,g2);
            this.modalita.startGame();
        }

    }



    public void menuPrincipale() throws MossaNonValida {
        GestioneInput gestioneInput = GestioneInput.getInstance();
        System.out.println("BENVENUTO IN SCACCHI MAC!");
        System.out.println("Nuova partita (1)");
        System.out.println("Carica partita (2)");
        String input = gestioneInput.leggiNumeroInput();
        if (input.equals("1")) {
            this.selezionaModalita();
        }
    }


}
