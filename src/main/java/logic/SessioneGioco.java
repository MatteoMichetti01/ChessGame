package logic;

import domain.Pezzo;
import domain.Scacchiera;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SessioneGioco extends Modalita{


    Scacchiera scacchiera = new Scacchiera();
    public static boolean scaccoMatto1 = false;

    public static void setScaccoMatto1(boolean scaccoMatto) {
        scaccoMatto1 = scaccoMatto;
    }


    public SessioneGioco(Giocatore giocatore1, Giocatore giocatore2) {

        super(giocatore1, giocatore2);
    }

    GestioneInput gestioneInput = GestioneInput.getInstance();
    SalvataggioMosse salvataggioMosse = new SalvataggioMosse();
    @Override
    public void startGame() throws MossaNonValida {
            String nomeResa = null;
            boolean resa = true;
            boolean mossaFatta = false;
            boolean prova = false;
            boolean prova1 = true;
           // Scacchiera scacchiera = new Scacchiera();
            MossaServiceImpl p1 = new MossaServiceImpl(scacchiera);
            scacchiera.viewscacchiera();
            System.out.println();
            System.out.println("Inizia il turno " + giocatore1.getNome());
            salvataggioMosse.addMossa(scacchiera);
            while (resa && !(scaccoMatto1)) {
                //TURNO GIOCATORE BIANCO
                while (!mossaFatta && resa && !(scaccoMatto1)) {
                        System.out.println("Tocca a " + giocatore1.getNome());
                        System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                        GiocatoreService<? extends Giocatore> service = GiocatoreServiceFactory.getGiocatoreService(giocatore1.getClass());
                        String pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                        if (pezzoBianco.equals("o")) {
                            while (prova1) {
                                if (this.opzioni().equals("2")) {
                                    while (!prova) {
                                        System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                                        int numMosse = gestioneInput.mosseIndieroInput();
                                        try {
                                            scacchiera = scacchiera.clone(salvataggioMosse.undoMosse(numMosse * 2));
                                            prova = true;
                                        } catch (MossaNonValida m) {
                                            System.out.println(m.getMessage());
                                        }
                                    }
                                    scacchiera.viewscacchiera();
                                    System.out.println();
                                    prova1=false;
                                }
                                if (this.opzioni().equals("3")) {
                                    resa = false;
                                    nomeResa = giocatore2.getNome();
                                    prova1=false;
                                }
                            }
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
                while (!mossaFatta && resa && !(scaccoMatto1)) {
                    System.out.println("Tocca a " + giocatore2.getNome());
                    System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                    GiocatoreService<? extends Giocatore> service2 = GiocatoreServiceFactory.getGiocatoreService(giocatore2.getClass());
                    String pezzoNero = service2.getPezzo(giocatore2, scacchiera);
                    if (pezzoNero.equals("o")) {
                        if(this.opzioni().equals("2")) {
                            System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                            int numMosse = gestioneInput.mosseIndieroInput();
                            scacchiera = salvataggioMosse.undoMosse(numMosse*2);
                            scacchiera.viewscacchiera();
                            mossaFatta=false;
                            break;
                        }
                        if (this.opzioni().equals("3")) {
                            resa = false;
                            nomeResa = giocatore2.getNome();
                            break;
                        }
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
