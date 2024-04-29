package logic;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Questa classe gestisce l'input da parte dell'utente durante una partita di scacchi.
 * È progettata come un singleton per garantire che vi sia una sola istanza attiva per l'intero programma.
 */
public class GestioneInput implements Serializable {
    private static GestioneInput istanza;
    private final Scanner scanner;

    /**
     * Costruttore privato per garantire che l'istanza sia creata solo internamente alla classe.
     * Inizializza lo scanner per l'input da tastiera.
     */
    private GestioneInput() {
        scanner = new Scanner(System.in);
    }

    /**
     * Restituisce l'istanza attuale di GestioneInput, creandola se non esiste (Singleton pattern).
     *
     * @return L'istanza di GestioneInput.
     */
    public static synchronized GestioneInput getIstanza() {
        if (istanza == null) {
            istanza = new GestioneInput();
        }
        return istanza;
    }

    /**
     * Legge una riga di input dall'utente.
     *
     * @return La riga di input letta.
     */
    public String leggiInput() {
        return scanner.nextLine();
    }
    /**
     * Elimina l'istanza corrente di GestioneInput.
     */
    public void pulisci() {
        istanza =null;
    }

    /**
     * Legge l'input dell'utente per un pezzo di scacchi.
     * Restituisce l'input solo se corrisponde a un pezzo valido.
     * Altrimenti, lancia un'eccezione InputNonValido.
     *
     * @return Il pezzo di scacchi inserito dall'utente.
     * @throws InputNonValido Se l'input non corrisponde a un pezzo valido.
     */
    public String leggiPezzoInput() {
        while (true) {
            try {
                String pezzo = scanner.nextLine();
                if (!(pezzo.equals("p1")) && !(pezzo.equals("p2")) && !(pezzo.equals("p3")) && !(pezzo.equals("p4")) && !(pezzo.equals("p5"))
                        && !(pezzo.equals("p6")) && !(pezzo.equals("p7")) && !(pezzo.equals("p8")) && !(pezzo.equals("t1")) && !(pezzo.equals("t2"))
                        && !(pezzo.equals("c1")) && !(pezzo.equals("c2")) && !(pezzo.equals("a1")) && !(pezzo.equals("a2"))
                        && !(pezzo.equals("qn")) && !(pezzo.equals("re")) && !(pezzo.equals("q1")) && !(pezzo.equals("q2"))&& !(pezzo.equals("q3"))&& !(pezzo.equals("q4"))&& !(pezzo.equals("q5"))
                        && !(pezzo.equals("q6"))&& !(pezzo.equals("q7"))&& !(pezzo.equals("q8"))&& !(pezzo.equals("a3"))&& !(pezzo.equals("a4"))&& !(pezzo.equals("a5"))&& !(pezzo.equals("a6"))&& !(pezzo.equals("a7"))&& !(pezzo.equals("a8"))
                        && !(pezzo.equals("c3"))&& !(pezzo.equals("c4"))&& !(pezzo.equals("c5"))&& !(pezzo.equals("c6"))&& !(pezzo.equals("c7"))&& !(pezzo.equals("c8"))
                        && !(pezzo.equals("t3")) && !(pezzo.equals("t4"))&& !(pezzo.equals("t5"))&& !(pezzo.equals("t6"))&& !(pezzo.equals("t7"))&& !(pezzo.equals("t8"))&& !(pezzo.equals("o"))) {
                    throw new InputNonValido("Inserisci un pezzo valido (ad esempio: p4)");
                }
                return pezzo;
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per la scelta di un pezzo durante la promozione di un pedone.
     * Restituisce l'input solo se corrisponde a una delle opzioni valide (regina, torre, cavallo, alfiere).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return Il pezzo scelto per la promozione.
     * @throws InputNonValido Se l'input non corrisponde a un pezzo valido.
     */
    public String leggiPezzoInputPromozione() {
        while (true) {
            try {
                String pezzo = scanner.nextLine();
                if (!(pezzo.equals("regina")) && !(pezzo.equals("torre")) && !(pezzo.equals("cavallo")) && !(pezzo.equals("alfiere"))) {
                    throw new InputNonValido("Inserisci un pezzo valido per la promozione ");
                }
                return pezzo;
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per la scelta di una modalità (1 o 2).
     * Restituisce l'input solo se corrisponde a una delle opzioni valide (1 o 2).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return La modalità scelta dall'utente.
     */

    public String leggiNumeroInput()  {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    return input;
                }
                throw new InputNonValido("Inserisci una modalità valida (suggerimento: o 1 o 2):");
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per la scelta di un'opzione (1, 2 o 3).
     * Restituisce l'input solo se corrisponde a una delle opzioni valide (1, 2 o 3).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return L'opzione scelta dall'utente.
     */
    public String leggiSceltaInput() {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2") || input.equals("3")) {
                    return input;
                }
                throw new InputNonValido("Inserisci una modalità valida (suggerimento: o 1 o 2 o 3):");
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente e verifica che non sia vuoto.
     * Restituisce l'input solo se non è vuoto.
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return L'input non vuoto inserito dall'utente.
     */
    public String inputNonVuoto() {
        while(true) {
            try{
                String input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new InputNonValido("input non valido, riprova: ");
                }
                return input;
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per la scelta di un'opzione (1, 2, 3 o 4).
     * Restituisce l'input solo se corrisponde a una delle opzioni valide (1, 2, 3 o 4).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return L'opzione scelta dall'utente.
     */
    public String opzioniInput() {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
                    return input;
                }

                throw new InputNonValido("Inserisci un opzione valida (suggerimento: o 1 o 2 o 3 o 4):");
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per il numero di mosse indietro (da 1 a 5).
     * Restituisce l'input solo se corrisponde a un numero di mosse valido (da 1 a 5).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return Il numero di mosse indietro scelto dall'utente.
     */
    public int mosseIndietroInput()  {
        while(true) {
            try {
                int input = scanner.nextInt();
                if (input == 1 || input == 2 || input == 3 || input == 4 || input == 5) {
                    return input;
                }

                throw new InputNonValido("Inserisci un numero di mosse valido (suggerimento: un numero da 1 a 5):");
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * Legge l'input dell'utente per la scelta del colore (nero o bianco).
     * Restituisce l'input solo se corrisponde a una delle opzioni valide (nero o bianco).
     * Altrimenti, stampa un messaggio di errore e richiede un nuovo input.
     *
     * @return Il colore scelto dall'utente.
     */
    public String inputColore() {
        while(true) {
            try{
                String input = scanner.nextLine();
                if(!(input.equals("nero")) && !(input.equals("bianco"))) {
                    throw new InputNonValido("input non valido, inserisci bianco o nero: ");
                }
                return input;
            } catch (InputNonValido i) {
                System.out.println(i.getMessage());
            }
        }
    }



}
