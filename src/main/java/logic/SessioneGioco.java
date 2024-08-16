package logic;

import domain.*;
import scritturaSuFile.ScriviSuFile;
import logic.impl.MossaServiceImpl;

import java.io.IOException;
import java.io.Serializable;

import static scritturaSuFile.ScriviSuFile.*;

/**
 * La classe SessioneGioco gestisce una singola partita di scacchi, includendo l'interazione
 * con i giocatori, la logica del gioco, le opzioni del menu e il salvataggio delle mosse.
 * Estende la classe Modalita e implementa l'interfaccia Serializable.
 */
public class SessioneGioco extends Modalita implements Serializable {
    int contatorePatta=50;
    int numeroPezziBianco;
    int numeroPezziNero;

    String salvataggioPartita= null;
    private static final long serialVersionUID = 1L;
    //Singleton
    private static SessioneGioco istanza;
    //get instance della singleton

    /**
     * Questo metodo viene utilizzato per pulire l'istanza dell'oggetto SessioneGioco.
     * Chiamando questo metodo, l'istanza corrente dell'oggetto SessioneGioco verrà
     * impostata su null, consentendo così la rimozione di eventuali riferimenti
     * all'oggetto esistente e agevolando il processo di garbage collection.
     * È importante notare che chiamare questo metodo non elimina l'istanza di
     * SessioneGioco, ma solo il riferimento ad essa. L'istanza stessa sarà ancora
     * presente in memoria fintanto che non sarà più referenziata da nessuna variabile
     * e sarà raccolta dal garbage collector in un momento successivo.
     */
    public static void pulisciIstanza() {
        istanza = null;
    }

    /**
     * Restituisce l'istanza corrente dell'oggetto SessioneGioco.
     * Se l'istanza non è stata ancora creata (ossia è null), viene creata
     * una nuova istanza di SessioneGioco utilizzando i giocatori specificati,
     * quindi restituita. Se l'istanza è già stata creata in precedenza,
     * viene semplicemente restituita l'istanza esistente.
     *
     * @param giocatore1 Il primo giocatore della sessione di gioco.
     * @param giocatore2 Il secondo giocatore della sessione di gioco.
     * @return L'istanza corrente dell'oggetto SessioneGioco.
     */
    public static SessioneGioco getInstanza(Giocatore giocatore1, Giocatore giocatore2) {
        if (istanza == null) {
            istanza = new SessioneGioco(giocatore1, giocatore2);
        }
        return istanza;
    }

    /**
     * Restituisce l'istanza corrente dell'oggetto SessioneGioco.
     * Se l'istanza non è stata ancora creata (ossia è null),
     * viene restituito null. Altrimenti, viene restituita
     * l'istanza esistente.
     *
     * @return L'istanza corrente dell'oggetto SessioneGioco, oppure null
     * se l'istanza non è stata ancora creata.
     */
    public static SessioneGioco getInstanza() {
        return istanza;
    }

    public static boolean scaccoMatto1 = false;

    Scacchiera scacchiera = new Scacchiera();
    public int numeroPezzi = scacchiera.contaPezzi(scacchiera);
    public int valorePezzi = scacchiera.contaValorePezzi(scacchiera);
    static boolean selezioneMenu = false;


    /**
     * Imposta lo stato del flag di scacco matto per il giocatore 1.
     * Questo metodo imposta lo stato del flag di scacco matto
     * in base al valore specificato.
     * @param scaccoMatto Lo stato del flag di scacco matto per il giocatore 1.
     */
    public static void setScaccoMatto1(boolean scaccoMatto) {
        scaccoMatto1 = scaccoMatto;
    }

    /**
     * Imposta lo stato del flag di selezione del menu per il giocatore 1.
     * Questo metodo imposta lo stato del flag di selezione del menu
     * in base al valore specificato.
     * @param selezioneMenu1 Lo stato del flag di selezione del menu per il giocatore 1.
     */
    public static void setSelezioneMenu(boolean selezioneMenu1) {
        selezioneMenu = selezioneMenu1;
    }

    /**
     * Costruttore della classe SessioneGioco.
     * Crea una nuova istanza di SessioneGioco con i due giocatori specificati.
     *
     * @param giocatore1 Il primo giocatore della sessione di gioco.
     * @param giocatore2 Il secondo giocatore della sessione di gioco.
     */
    public SessioneGioco(Giocatore giocatore1, Giocatore giocatore2) {
        super(giocatore1, giocatore2);
    }

    SalvataggioMosse salvataggioMosse = new SalvataggioMosse();

    public int numeroMosse = 0;

    /**
     * Avvia una nuova partita di scacchi. Questo metodo gestisce l'intero
     * processo di gioco, inclusa l'interazione con i giocatori, la logica
     * del gioco, le opzioni del menu e il salvataggio delle mosse.
     *
     * @throws MossaNonValida Se viene effettuata una mossa non valida durante la partita.
     * @throws IOException     Se si verifica un errore di I/O durante il salvataggio della partita.
     * @throws InputNonValido Se viene fornito un input non valido durante l'interazione con l'utente.
     */
    @Override
    public void avviaPartita() throws MossaNonValida, IOException, InputNonValido {
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
                        pezzoBianco = service.getPezzo(giocatore1, scacchiera);
                        undoMossa = false;
                    }
                    if (!chiudiMenu) {
                        if (!(giocatore1.getClass().equals(Computer.class))) {
                            System.out.println("Inserisci mossa: ");
                        }
                        String mossaBianco = service.getPosizioneMossa(pezzoBianco, scacchiera);
                        if (pezzoBianco.equals("t2W") && mossaBianco.equals("ARROCCO")) {
                            try {
                                p1.move(pezzoBianco, "F8", "bianco");
                            } catch (MossaNonValida m) {
                                System.out.println(m.getMessage());
                                scacchiera.viewScacchiera();
                                System.out.println();
                            }

                        }
                        if (pezzoBianco.equals("t1W") && mossaBianco.equals("arroccol")) {
                            try {
                                p1.move(pezzoBianco, "D8", "bianco");
                                scacchiera.casella[8][5] = new Casella("   ", "E8", false);
                                scacchiera.casella[8][3] = new Casella("C8", new Re("reW", "bianco"), 8, 3, true);
                            } catch (MossaNonValida m) {
                                System.out.println(m.getMessage());
                                scacchiera.viewScacchiera();
                                System.out.println();
                            }

                        } else {
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


    /**
     * Mostra le opzioni del menu e restituisce l'input dell'utente.
     *
     * @return L'opzione scelta dall'utente come stringa.
     */
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
