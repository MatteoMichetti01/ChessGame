package bozzascritturafile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

class ScriviSuFile {

    public static void main(String[] args) {
        List<String> mosseG1  = new ArrayList<String>();
        List<String> mosseG2 = new ArrayList<>();
        try {
            // Apre uno scanner per l'input da tastiera
            Scanner scanner = new Scanner(System.in);

            // Apre il file "mosse.txt" in modalità di scrittura
            BufferedWriter writer = new BufferedWriter(new FileWriter("mosse.txt"));

            // Scrive la stringa "mosse giocatore 1" nel file una sola volta



            // Ciclo interattivo per ottenere le mosse alternativamente dai giocatori
            while (true) {

                // Chiede all'utente di inserire le mosse del giocatore 1
                System.out.print("Giocatore 1, inserisci le tue mosse (separate da spazio): ");
                String mosseGiocatore1 = scanner.nextLine();
                mosseG1.add(mosseGiocatore1);



                // Scrive le mosse del giocatore 1 nel file



                // Chiede all'utente di inserire le mosse del giocatore 2
                System.out.print("Giocatore 2, inserisci le tue mosse (separate da spazio): ");
                String mosseGiocatore2 = scanner.nextLine();
                mosseG2.add(mosseGiocatore2);



                // Chiede se si vuole continuare a inserire mosse
                System.out.print("Vuoi continuare? (sì/no): ");
                String risposta = scanner.nextLine().toLowerCase();

                // Se la risposta non è "sì", esce dal ciclo
                if (!risposta.equals("si")) {
                    break;
                }
            }
            writer.write("mosse giocatore 1 :\n");
            for (String mossa : mosseG1){
                writer.write(mossa + " ");

            }
            writer.newLine();

            writer.write("mosse giocatore 2 :\n");
            for (String mossa2 : mosseG2){
                writer.write(mossa2 + " ");

            }


            // Chiude lo scanner
            scanner.close();

            // Chiude il writer
            writer.close();

            System.out.println("Le mosse sono state scritte nel file 'mosse.txt'.");

        } catch (IOException e) {
            // Gestisce le eccezioni di IO (ad esempio, se non è possibile scrivere sul file)
            e.printStackTrace();
        }
    }
}
