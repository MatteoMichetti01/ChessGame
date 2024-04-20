package logic;

import bozzascritturafile.ScriviSuFile;
import domain.Pezzo;
import domain.Scacchiera;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static bozzascritturafile.ScriviSuFile.*;

public class SessioneGioco extends Modalita implements Serializable {
    private static final long serialVersionUID = 1L;
    //Singleton
    private static SessioneGioco instance;
    //get instance della singleton
    public static SessioneGioco getInstance(Giocatore giocatore1, Giocatore giocatore2) {
        if (instance == null) {
            instance = new SessioneGioco(giocatore1, giocatore2);
        }
        return instance;
    }
    public static boolean scaccoMatto1 = false;
    static boolean selezioneMenu = false;

    private Scacchiera scacchiera;

    public static void setScaccoMatto1(boolean scaccoMatto) {
        scaccoMatto1 = scaccoMatto;
    }

    public static void setSelezioneMenu(boolean selezioneMenu1) {
        selezioneMenu = selezioneMenu1;
    }

    public SessioneGioco(Giocatore giocatore1, Giocatore giocatore2) {
        super(giocatore1, giocatore2);
    }


    public SessioneGioco() {
        // Costruttore vuoto necessario per la deserializzazione
    }
    /*private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Permette la deserializzazione dei campi di default

        // Inizializzazione dei giocatori dopo la deserializzazione
        Giocatore giocatore1 = (Giocatore) ois.readObject();
        Giocatore giocatore2 = (Giocatore) ois.readObject();

        System.out.println("Giocatore 1 deserializzato: " + giocatore1);
        System.out.println("Giocatore 2 deserializzato: " + giocatore2);

        // Inizializza la sessione di gioco con i giocatori deserializzati
        instance = new SessioneGioco(giocatore1, giocatore2);
    }*/
    GestioneInput gestioneInput = GestioneInput.getInstance();
    SalvataggioMosse salvataggioMosse = new SalvataggioMosse();
    @Override
    public void startGame() throws MossaNonValida, IOException {
        createSaveDirectory();
        String nomeResa = null;
        boolean resa = true;
        boolean mossaFatta = false;
        boolean undoMossa = false;
        boolean partitaSalvata = false;
        scacchiera = new Scacchiera();
        MossaServiceImpl p1 = new MossaServiceImpl(scacchiera);
        scacchiera.viewscacchiera();
        System.out.println();
        System.out.println("Inizia il turno " + giocatore1.getNome());
        salvataggioMosse.addMossa(scacchiera);
        while (resa && !(scaccoMatto1) && !(partitaSalvata)) {

            //TURNO GIOCATORE BIANCO
            while (!mossaFatta && resa && !(scaccoMatto1) && !(partitaSalvata)) {
                System.out.println("Tocca a " + giocatore1.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                GiocatoreService<? extends Giocatore> service = GiocatoreServiceFactory.getGiocatoreService(giocatore1.getClass());
                String pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                while (selezioneMenu) {
                    String scelta = this.opzioni();
                    if (pezzoBianco.equals("o")) {
                        if(scelta.equals("1")) {
                            System.out.println("inserisci il nome della partita: ");
                            String nomeFile = gestioneInput.leggiInput();
                            try {
                                ScriviSuFile.saveGame(this, nomeFile);
                                System.out.println("la partita è sata salvata con successo");
                                partitaSalvata = true;
                            } catch (IOException e) {
                                System.out.println("Errore durante il salvataggio della partita: " + e.getMessage());
                            }
                            selezioneMenu=false;
                            break;
                        }
                        if (scelta.equals("2")) {
                            undoMossa = false;
                            while (!undoMossa) {
                                System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                                try {
                                    scacchiera= salvataggioMosse.undoMosse(gestioneInput.mosseIndieroInput() * 2);
                                    p1 = new MossaServiceImpl(scacchiera);
                                    undoMossa = true;
                                    GestioneInput.annulla();
                                    selezioneMenu=false;
                                } catch (MossaNonValida m) {}
                            }
                            scacchiera.viewscacchiera();
                            System.out.println();
                            break;
                        }
                        if (scelta.equals("3")) {
                            resa = false;
                            nomeResa = giocatore1.getNome();
                            selezioneMenu=false;
                            break;
                        }
                    }
                }
                if(!resa)break;
                if(partitaSalvata)break;
                if(undoMossa){
                    System.out.println("Tocca a " + giocatore1.getNome());
                    System.out.println("Inserisci il pezzo che vuoi spostare: ");
                    pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                    undoMossa = false;
                }
                System.out.println("Inserisci mossa: ");
                String mossaBianco = service.getPosizioneMossa(pezzoBianco, scacchiera);
                try {
                    scacchiera = p1.move(pezzoBianco, mossaBianco.toUpperCase(), this.giocatore1.getColore());
                    mossaFatta = true;
                    salvataggioMosse.addMossa(scacchiera);
                } catch (MossaNonValida m) {
                    System.out.println(m.getMessage());
                    scacchiera.viewscacchiera();
                    System.out.println();
                }
                System.out.println(mossaFatta);
            }
            if (mossaFatta && !(scaccoMatto1)) {
                scacchiera.viewscacchiera();
                System.out.println();
                mossaFatta = false;
            }

            //TURNO GIOCATORE NERO
            while (!mossaFatta && resa && !(scaccoMatto1) && !(partitaSalvata)) {
                System.out.println("Tocca a " + giocatore2.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                GiocatoreService<? extends Giocatore> service2 = GiocatoreServiceFactory.getGiocatoreService(giocatore2.getClass());
                String pezzoNero = service2.getPezzo(giocatore2, scacchiera);
                while(selezioneMenu) {
                    if (pezzoNero.equals("o")) {
                        String scelta = this.opzioni();
                        if(scelta.equals("1")) {
                            System.out.println("inserisci il nome della partita: ");
                            String nomeFile = gestioneInput.leggiInput();
                            try {
                                ScriviSuFile.saveGame(this, nomeFile);
                                System.out.println("la partita è sata salvata con successo");
                                partitaSalvata = true;
                            } catch (IOException e) {
                                System.out.println("Errore durante il salvataggio della partita: " + e.getMessage());
                            }
                            break;
                        }
                        if (scelta.equals("2")) {
                            System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                            try {
                                scacchiera = salvataggioMosse.undoMosse(gestioneInput.mosseIndieroInput() * 2);
                                p1 = new MossaServiceImpl(scacchiera);
                                selezioneMenu = false;
                            }catch (MossaNonValida m){}

                            scacchiera.viewscacchiera();
                            System.out.println();
                            break;
                        }
                        if (scelta.equals("3")) {
                            resa = false;
                            nomeResa = giocatore2.getNome();
                            break;
                        }
                    }
                }
                if(!resa)break;
                if(partitaSalvata)break;
                if(undoMossa){
                    System.out.println("Tocca a " + giocatore2.getNome());
                    System.out.println("Inserisci il pezzo che vuoi spostare: ");
                    pezzoNero= service2.getPezzo(giocatore2, scacchiera);
                    undoMossa = false;
                }
                System.out.println("Inserisci mossa: ");
                String mossaNero = service2.getPosizioneMossa(pezzoNero, scacchiera);
                System.out.println("mossa nero" + mossaNero);
                try {
                    scacchiera = p1.move(pezzoNero, mossaNero.toUpperCase(), giocatore2.getColore());
                    mossaFatta = true;
                    salvataggioMosse.addMossa(scacchiera);
                } catch (MossaNonValida m) {
                    System.out.println(m.getMessage());
                    scacchiera.viewscacchiera();
                    System.out.println();
                }
            }
            if (mossaFatta && !(scaccoMatto1)) {
                scacchiera.viewscacchiera();
                System.out.println();
                mossaFatta = false;
            }
        }
        if (!resa) {
            System.out.println("Fine partita!");
            System.out.println(nomeResa + " si è arreso");
            System.out.println("Torna al menù principale (1)");
            System.out.println("Esci (2)");
            String input = gestioneInput.leggiNumeroInput();
            if (input.equals("1")) {
                ChessGame.newGame();
            }
        }
        if(partitaSalvata) {
            System.out.println("Torna al menù principale (1)");
            System.out.println("Esci (2)");
            String input = gestioneInput.leggiNumeroInput();
            if (input.equals("1")) {
                ChessGame.newGame();
            }
        }
        if (scaccoMatto1) {
            scaccoMatto1 = false;
            System.out.println("Torna al menù principale (1)");
            System.out.println("Esci (2)");
            String input = gestioneInput.leggiNumeroInput();
            if (input.equals("1")) {
                ChessGame.newGame();
            }
        }

    }


    @Override
    public String opzioni() throws MossaNonValida {
        System.out.println("Salva partita (1)");
        System.out.println("Annulla mosse (2)");
        System.out.println("Arrenditi (3)");
        return gestioneInput.opzioniInput();
    }
}