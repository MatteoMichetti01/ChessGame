package bozzascritturafile;
import logic.GestioneInput;
import logic.MossaNonValida;
import logic.SessioneGioco;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ScriviSuFile {
    private static final String DIRECTORY = "partite_salvate/";

    public static void createSaveDirectory() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Cartella delle partite salvate creata con successo.");
            } else {
                System.out.println("Errore durante la creazione della cartella delle partite salvate.");
            }
        }
    }

    public static List<String> getSavedGameFiles() throws MossaNonValida {
        List<String> savedGames = new ArrayList<>();
        File saveDirectory = new File(DIRECTORY);
        File[] files = saveDirectory.listFiles();

        if (files != null) {
            String scelta1 = scelta();
            switch (scelta1) {
                case "1":
                    System.out.println("Lista di partite salvate per numero di mosse effettuate, in ordine crescente: ");
                    Arrays.sort(files, (a, b) -> {
                        int numMosseA = getNumeroMosse1(a);
                        int numMosseB = getNumeroMosse1(b);
                        return Integer.compare(numMosseA, numMosseB);
                    });
                    break;
                case "2":

                    break;
                case"3":
                    break;
            }
            for (File file : files) {
                if (file.isFile() && !file.getName().equals(".DS_Store")) {
                    savedGames.add(file.getName());
                }
            }
        }
        return savedGames;
    }

    public static void saveGame(SessioneGioco gioco, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(DIRECTORY + filename)))) {
            oos.writeObject(gioco);
        }
    }

    public static SessioneGioco loadGame(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DIRECTORY + filename)))) {
            return (SessioneGioco) ois.readObject();
        }
    }

    public static int getNumeroMosse1(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            SessioneGioco s1 = (SessioneGioco) inputStream.readObject();
            return s1.numeroMosse;
        }catch (IOException | ClassNotFoundException e) {
            return -1;
        }
    }

    public static String scelta() throws MossaNonValida {
        GestioneInput gestioneInput = GestioneInput.GetInstance();
        System.out.println("Inserisci come vuoi che siano ordinate le partite: ");
        System.out.println("Ordinamento per mosse effettuate (1)");
        System.out.println("Numero complessivo di personaggi sulla scacchiera (2)");
        System.out.println("Valore complessivo dei personaggi sulla scacchiera (3)");
        return gestioneInput.LeggiSceltaInput();
    }



}
