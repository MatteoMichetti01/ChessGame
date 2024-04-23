package bozzascritturafile;
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

    public static List<String> getSavedGameFiles() {
        List<String> savedGames = new ArrayList<>();
        File saveDirectory = new File(DIRECTORY);
        File[] files = saveDirectory.listFiles();

        if (files != null) {
            Arrays.sort(files, (a, b) -> a.getName().compareTo(b.getName()));
            for (File file : files) {
                if (file.isFile()) {
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



}
