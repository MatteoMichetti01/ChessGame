package scritturaSuFile;
import logic.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Questa classe fornisce metodi per la gestione della scrittura e della lettura di partite su file.
 * Le partite vengono salvate e caricate utilizzando la serializzazione di oggetti.
 */
public class ScriviSuFile {
    private static final String DIRECTORY = "partite_salvate/";

    /**
     * Crea la directory di base per le partite salvate, se non esiste già.
     */
    public static void creazioneDirectory() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Cartella delle partite salvate creata con successo.");
            } else {
                System.out.println("Errore durante la creazione della cartella delle partite salvate.");
            }
        }
    }

    /**
     * Restituisce un elenco ordinato dei nomi dei file delle partite salvate.
     *
     * @return Un elenco di nomi di file ordinato secondo il criterio specificato dall'utente.
     */
    public static List<String> elencoPartiteSalvate() {
        //In questa lista ci andranno i nomi dei file già ordinati
        List<String> partiteSalvate = new ArrayList<>();
        File cartellaSalvataggio = new File(DIRECTORY);
        File[] file1 = cartellaSalvataggio.listFiles();

        if (file1 != null) {
            String scelta1 = scelta();
            switch (scelta1) {
                case "1":
                    System.out.println("Lista di partite salvate per numero di mosse effettuate, in ordine crescente: ");
                    Arrays.sort(file1, (a, b) -> {
                        int numMosseA = getNumeroMosse1(a);
                        int numMosseB = getNumeroMosse1(b);
                        return Integer.compare(numMosseA, numMosseB);
                    });
                    break;
                case "2":
                    System.out.println("Lista di partite salvate per numero di pezzi sulla scacchiera, in ordine crescente: ");
                    Arrays.sort(file1, (a, b) -> {
                        int numMosseA = getNumeroPezzi(a);
                        int numMosseB = getNumeroPezzi(b);
                        return Integer.compare(numMosseA, numMosseB);
                    });
                    break;
                case"3":
                    System.out.println("Lista di partite salvate per valore dei pezzi sulla scacchiera, in ordine crescente: ");
                    Arrays.sort(file1, (a, b) -> {
                        int numMosseA = getValorePezzi(a);
                        int numMosseB = getValorePezzi(b);
                        return Integer.compare(numMosseA, numMosseB);
                    });
                    break;
            }
            for (File file : file1) {
                if (file.isFile() && !file.getName().equals(".DS_Store")) {
                    partiteSalvate.add(file.getName());
                }
            }
        }
        return partiteSalvate;
    }

    /**
     * Salva una partita su file utilizzando la serializzazione dell'oggetto SessioneGioco.
     *
     * @param gioco     La sessione di gioco da salvare.
     * @param filename  Il nome del file in cui salvare la partita.
     * @throws IOException Se si verifica un errore di input/output durante il salvataggio.
     */
    public static void salvaPartita(SessioneGioco gioco, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(DIRECTORY + filename)))) {
            oos.writeObject(gioco);
        }
    }

    /**
     * Carica una partita da file utilizzando la deserializzazione dell'oggetto SessioneGioco.
     *
     * @param filename  Il nome del file da cui caricare la partita.
     * @return La sessione di gioco caricata.
     * @throws IOException            Se si verifica un errore di input/output durante il caricamento.
     * @throws ClassNotFoundException Se la classe dell'oggetto caricato non viene trovata.
     * @throws InputNonValido         Se l'input non è valido durante la creazione di una nuova partita.
     * @throws MossaNonValida         Se viene effettuata una mossa non valida durante la creazione di una nuova partita.
     */
    public static SessioneGioco caricaPartita(String filename) throws IOException, ClassNotFoundException, InputNonValido, MossaNonValida {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DIRECTORY + filename)))) {
            return (SessioneGioco) ois.readObject();
        } catch (IOException io) {
            System.out.println("La partita non esiste o non può essere caricata!");
            ChessGame.nuovaPartita();
        }
        return null;
    }

    /**
     * Ottiene il numero di mosse effettuate in una partita.
     *
     * @param file Il file della partita di cui ottenere il numero di mosse.
     * @return Il numero di mosse effettuate, o -1 se si verifica un'eccezione.
     */
    public static int getNumeroMosse1(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.numeroMosse;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    /**
     * Ottiene il numero complessivo di pezzi sulla scacchiera in una partita.
     *
     * @param file Il file della partita di cui ottenere il numero di pezzi.
     * @return Il numero complessivo di pezzi, o -1 se si verifica un'eccezione.
     */
    public static int getNumeroPezzi(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.numeroPezzi;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    /**
     * Ottiene il valore complessivo dei pezzi sulla scacchiera in una partita.
     *
     * @param file Il file della partita di cui ottenere il valore dei pezzi.
     * @return Il valore complessivo dei pezzi, o -1 se si verifica un'eccezione.
     */
    public static int getValorePezzi(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.valorePezzi;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    /**
     * Richiede all'utente di inserire il criterio di ordinamento delle partite.
     *
     * @return La scelta dell'utente come stringa.
     */
    public static String scelta() {
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        System.out.println("Inserisci come vuoi che siano ordinate le partite: ");
        System.out.println("Ordinamento per mosse effettuate (1)");
        System.out.println("Numero complessivo di pezzi sulla scacchiera (2)");
        System.out.println("Valore complessivo dei pezzi sulla scacchiera (3)");
        return gestioneInput.leggiSceltaInput();
    }



}
