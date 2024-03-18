package logic;

import controller.*;
import domain.*;

import java.util.Scanner;

public class Partita {
Modalita modalita;

    public Partita() {

    }

    public void selezionaModalita() throws MossaNonValida {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona la modalità di gioco: ");
        System.out.println("- Giocatore contro giocatore (1)");
        System.out.println("- Giocatore contro computer (2)");
        String input = scanner.nextLine();
        if(input.equals("1")){
            System.out.println("Chi vuole essere il bianco?(inserisci il nome):  ");
            String nomeBianco = scanner.nextLine();
            Giocatore g1 = new Giocatore(nomeBianco,"bianco");
            System.out.println("Inserisci nome per giocatore nero:  ");
            String nomeNero = scanner.nextLine();
            Giocatore g2 = new Giocatore(nomeNero,"nero");
            this.modalita = new GiocatoreControGiocatore(g1, g2);
            this.modalita.startGame();
        }
        else{
        System.out.println("Quale colore vuoi essere?(bianco o nero):  ");
        String colore = scanner.nextLine();
        System.out.println("Inserisci il nome:  ");
        String nome = scanner.nextLine();
        Giocatore g1 = new Giocatore(nome,colore);
            this.modalita= new GiocatoreControComputer(g1);
            this.modalita.startGame();
        }

    }

    public void  menuPrincipale() throws MossaNonValida {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BENVENUTO IN SCACCHI MAC!");
        System.out.println("Nuova partita (1)");
        System.out.println("Carica partita (2)");
        String input = scanner.nextLine();
        if(input.equals("1")){this.selezionaModalita();}

    }


}
