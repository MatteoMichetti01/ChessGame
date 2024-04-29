package ScritturaSuFile;
import logic.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ScriviSuFile {
    private static final String DIRECTORY = "partite_salvate/";

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

    public static void salvaPartita(SessioneGioco gioco, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(DIRECTORY + filename)))) {
            oos.writeObject(gioco);
        }
    }

    public static SessioneGioco caricaPartita(String filename) throws IOException, ClassNotFoundException, InputNonValido, MossaNonValida {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DIRECTORY + filename)))) {
            return (SessioneGioco) ois.readObject();
        } catch (IOException io) {
            System.out.println("La partita non esiste o non può essere caricata!");
            ChessGame.nuovaPartita();
        }
        return null;
    }

    public static int getNumeroMosse1(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.numeroMosse;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    public static int getNumeroPezzi(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.numeroPezzi;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    public static int getValorePezzi(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.valorePezzi;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    public static String scelta() {
        GestioneInput gestioneInput = GestioneInput.getIstanza();
        System.out.println("Inserisci come vuoi che siano ordinate le partite: ");
        System.out.println("Ordinamento per mosse effettuate (1)");
        System.out.println("Numero complessivo di pezzi sulla scacchiera (2)");
        System.out.println("Valore complessivo dei pezzi sulla scacchiera (3)");
        return gestioneInput.leggiSceltaInput();
    }



}
