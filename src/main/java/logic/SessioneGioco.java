package logic;

import bozzascritturafile.ScriviSuFile;
import domain.Scacchiera;

import java.io.IOException;
import java.io.Serializable;

import static bozzascritturafile.ScriviSuFile.*;

public class SessioneGioco extends Modalita implements Serializable {
    int contatorePatta=50;
    int numeroPezziBianco;
    int numeroPezziNero;

    String salvataggioPartita= null;
    private static final long serialVersionUID = 1L;
    //Singleton
    private static SessioneGioco istanza;
    //get instance della singleton

    public static void pulisciIstanza(){
        istanza =null;
    }
    public static SessioneGioco getInstanza(Giocatore giocatore1, Giocatore giocatore2) {
        if (istanza == null) {
            istanza = new SessioneGioco(giocatore1, giocatore2);
        }
        return istanza;
    }
    public static SessioneGioco getInstanza() {
        return istanza;
    }
    public static boolean scaccoMatto1 = false;

    Scacchiera scacchiera = new Scacchiera();
    public int numeroPezzi = scacchiera.contaPezzi(scacchiera);
    public int valorePezzi = scacchiera.contaValorePezzi(scacchiera);
    static boolean selezioneMenu = false;


    public static void setScaccoMatto1(boolean scaccoMatto) {
        scaccoMatto1 = scaccoMatto;
    }

    public static void setSelezioneMenu(boolean selezioneMenu1) {
        selezioneMenu = selezioneMenu1;
    }

    public SessioneGioco(Giocatore giocatore1, Giocatore giocatore2) {
        super(giocatore1, giocatore2);
    }
    SalvataggioMosse salvataggioMosse = new SalvataggioMosse();

    public int numeroMosse = 0;
    @Override
    public void avviaPartita() throws MossaNonValida, IOException {
        creazioneDirectory();
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        gestioneInput.pulisci();
        String nomeResa = null;
        boolean resa = true;
        boolean mossaFatta = false;
        boolean undoMossa = false;
        boolean partitaSalvata = false;
        boolean chiudiMenu = false;
        MossaServiceImpl p1 = new MossaServiceImpl(scacchiera);
        scacchiera.viewScacchiera();
        System.out.println();
        if(this.numeroMosse== 0){
        System.out.println("Inizia il turno " + giocatore1.getNome());}

        //Questo if serve perche se la partita viene ricaricata si aggiunge una scacchiera in piu e l'undo non funzione bene
        if(this.numeroMosse==0) {
            salvataggioMosse.aggiungiMossa(scacchiera);
        }


        while (resa && !(scaccoMatto1) && !(partitaSalvata)&& contatorePatta>=0) {
            if (salvataggioPartita == null || salvataggioPartita.equals("bianco")) {
                if(numeroPezziBianco == numeroPezziNero)contatorePatta-=2;
                    else{contatorePatta=50;}
                //TURNO GIOCATORE BIANCO
                while (!mossaFatta && resa && !(scaccoMatto1) && !(partitaSalvata)) {
                    System.out.println("Tocca a " + giocatore1.getNome());
                    if (!(giocatore1.getClass().equals(Computer.class))) {
                        System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                    }
                    GiocatoreService<? extends Giocatore> service = GiocatoreServiceFactory.getGiocatoreService(giocatore1.getClass());
                    String pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                    while (selezioneMenu) {
                        if (pezzoBianco.equals("o")) {
                            String scelta = this.opzioni();
                            if (scelta.equals("1")) {
                                System.out.println("inserisci il nome della partita: ");
                                String nomeFile = gestioneInput.leggiInput();
                                try {
                                    ScriviSuFile.salvaPartita(this, nomeFile);
                                    System.out.println("la partita è sata salvata con successo");
                                    partitaSalvata = true;
                                    salvataggioPartita="bianco";
                                    selezioneMenu=false;
                                } catch (IOException e) {
                                    System.out.println("Errore durante il salvataggio della partita: " + e.getMessage());
                                }
                                break;
                            }
                            if (scelta.equals("2")) {
                                System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                                while (!undoMossa) {
                                    try {
                                        scacchiera = salvataggioMosse.undoMosse(gestioneInput.mosseIndietroInput() * 2);
                                        p1 = new MossaServiceImpl(scacchiera);
                                        undoMossa = true;
                                        selezioneMenu = false;
                                    } catch (MossaNonValida m) {
                                        selezioneMenu = false;
                                        System.out.println(m.getMessage());
                                        chiudiMenu=true;
                                        break;
                                    }
                                }
                                if(!chiudiMenu) scacchiera.viewScacchiera();
                                System.out.println();
                                break;

                            }
                            if (scelta.equals("3")) {
                                resa = false;
                                nomeResa = giocatore1.getNome();
                                selezioneMenu = false;
                                break;
                            }
                            if (scelta.equals("4")) {
                                chiudiMenu = true;
                                selezioneMenu = false;
                                break;
                            }
                        }
                    }
                    if (!resa) break;
                    if (partitaSalvata) break;
                    if (undoMossa) {
                        System.out.println("Tocca a " + giocatore1.getNome());
                        System.out.println("Inserisci il pezzo che vuoi spostare: ");
                        pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                        undoMossa = false;
                    }
                    if (!chiudiMenu) {
                        if (!(giocatore1.getClass().equals(Computer.class))) {
                            System.out.println("Inserisci mossa: ");
                        }
                        String mossaBianco = service.getPosizioneMossa(pezzoBianco, scacchiera);
                        try {
                            scacchiera = p1.move(pezzoBianco, mossaBianco.toUpperCase(), this.giocatore1.getColore());
                            mossaFatta = true;
                            numeroMosse++;
                            numeroPezzi = scacchiera.contaPezzi(scacchiera);
                            valorePezzi = scacchiera.contaValorePezzi(scacchiera);
                            salvataggioMosse.aggiungiMossa(scacchiera);
                            if (p1.pezzoMangiato != null) {
                                giocatore1.punteggio += p1.pezzoMangiato.getVALORE();
                                p1.pezzoMangiato = null;
                                System.out.println("punteggio bianco " + giocatore1.getPunteggio());
                            }

                        } catch (MossaNonValida m) {
                            System.out.println(m.getMessage());
                            scacchiera.viewScacchiera();
                            System.out.println();
                        }
                    } else {
                        scacchiera.viewScacchiera();
                        System.out.println();
                    }
                    chiudiMenu = false;
                }
                if (mossaFatta && !(scaccoMatto1)) {
                    scacchiera.viewScacchiera();
                    System.out.println();
                    mossaFatta = false;
                }
                numeroPezziBianco= scacchiera.contaPezzi(scacchiera);
                salvataggioPartita = "nero";
            }if(salvataggioPartita.equals("nero")){
                //TURNO GIOCATORE NERO
                while (!mossaFatta && resa && !(scaccoMatto1) && !(partitaSalvata)) {
                    System.out.println("Tocca a " + giocatore2.getNome());
                    if (!(giocatore2.getClass().equals(Computer.class))) {
                        System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                    }
                    GiocatoreService<? extends Giocatore> service2 = GiocatoreServiceFactory.getGiocatoreService(giocatore2.getClass());
                    String pezzoNero = service2.getPezzo(giocatore2, scacchiera);
                    while (selezioneMenu) {
                        if (pezzoNero.equals("o")) {
                            String scelta = this.opzioni();
                            if (scelta.equals("1")) {
                                System.out.println("inserisci il nome della partita: ");
                                String nomeFile = gestioneInput.leggiInput();
                                try {
                                    ScriviSuFile.salvaPartita(this, nomeFile);
                                    System.out.println("la partita è sata salvata con successo");
                                    partitaSalvata = true;
                                    salvataggioPartita="nero";
                                    selezioneMenu=false;
                                } catch (IOException e) {
                                    System.out.println("Errore durante il salvataggio della partita: " + e.getMessage());
                                }
                                break;
                            }
                            if (scelta.equals("2")) {
                                System.out.println("inserisci di quante mosse vuoi tornare indietro (inserisci un numero da 1 a 5): ");
                                while (!undoMossa) {
                                    try {
                                        scacchiera = salvataggioMosse.undoMosse(gestioneInput.mosseIndietroInput() * 2);
                                        p1 = new MossaServiceImpl(scacchiera);
                                        undoMossa = true;
                                        selezioneMenu = false;
                                    } catch (MossaNonValida m) {
                                        System.out.println(m.getMessage());
                                    }
                                }
                                scacchiera.viewScacchiera();
                                System.out.println();
                                break;
                            }
                            if (scelta.equals("3")) {
                                resa = false;
                                nomeResa = giocatore2.getNome();
                                selezioneMenu = false;
                                break;
                            }
                            if (scelta.equals("4")) {
                                chiudiMenu = true;
                                selezioneMenu = false;
                                break;
                            }
                        }
                    }
                    if (!resa) break;
                    if (partitaSalvata) break;
                    if (undoMossa) {
                        System.out.println("Tocca a " + giocatore2.getNome());
                        System.out.println("Inserisci il pezzo che vuoi spostare: ");
                        pezzoNero = service2.getPezzo(giocatore2, scacchiera);
                        undoMossa = false;
                    }
                    if (!chiudiMenu) {
                        if (!(giocatore2.getClass().equals(Computer.class))) {
                            System.out.println("Inserisci mossa: ");
                        }
                        String mossaNero = service2.getPosizioneMossa(pezzoNero, scacchiera);
                        try {
                            scacchiera = p1.move(pezzoNero, mossaNero.toUpperCase(), giocatore2.getColore());
                            numeroMosse++;
                            numeroPezzi = scacchiera.contaPezzi(scacchiera);
                            valorePezzi = scacchiera.contaValorePezzi(scacchiera);
                            mossaFatta = true;
                            salvataggioMosse.aggiungiMossa(scacchiera);
                            if (p1.pezzoMangiato != null) {
                                giocatore2.punteggio += p1.pezzoMangiato.getVALORE();
                                System.out.println("punteggio nero " + giocatore2.getPunteggio());
                                p1.pezzoMangiato = null;
                            }

                        } catch (MossaNonValida m) {
                            System.out.println(m.getMessage());
                            scacchiera.viewScacchiera();
                            System.out.println();
                        }
                    } else {
                        scacchiera.viewScacchiera();
                        System.out.println();
                    }
                    chiudiMenu = false;
                }
                if (mossaFatta && !(scaccoMatto1)) {
                    scacchiera.viewScacchiera();
                    System.out.println();
                    mossaFatta = false;
                }
            }
            numeroPezziNero=scacchiera.contaPezzi(scacchiera);
            salvataggioPartita = "bianco";

            if(contatorePatta==0){
                System.out.println("Partita Patta (Regola 50 mosse!)");
                System.out.println("Torna al menù principale (1)");
                System.out.println("Esci (2)");
                String input = gestioneInput.leggiNumeroInput();
                if (input.equals("1")) {
                    contatorePatta=50;
                    ChessGame.nuovaPartita();
                }
                if(input.equals("2")){
                    break;
                }
            }
        }

            if (!resa) {
                resa = true;
                scacchiera = new Scacchiera();
                System.out.println("Fine partita!");
                System.out.println(nomeResa + " si è arreso");
                System.out.println("Torna al menù principale (1)");
                System.out.println("Esci (2)");
                String input = gestioneInput.leggiNumeroInput();
                if (input.equals("1")) {
                    ChessGame.nuovaPartita();
                }
            }
            if (partitaSalvata) {
                partitaSalvata = false;
                numeroMosse = 0;
                scacchiera = new Scacchiera();
                System.out.println("Torna al menù principale (1)");
                System.out.println("Esci (2)");
                String input = gestioneInput.leggiNumeroInput();
                if (input.equals("1")) {
                    ChessGame.nuovaPartita();
                }
            }
            if (scaccoMatto1) {
                scaccoMatto1 = false;
                System.out.println("Torna al menù principale (1)");
                System.out.println("Esci (2)");
                String input = gestioneInput.leggiNumeroInput();
                if (input.equals("1")) {
                    ChessGame.nuovaPartita();
                }
            }

        }



    @Override
    public String opzioni() {
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        System.out.println("Salva partita (1)");
        System.out.println("Annulla mosse (2)");
        System.out.println("Arrenditi (3)");
        System.out.println("Esci dal menù (4)");
        return gestioneInput.opzioniInput();
    }

}
