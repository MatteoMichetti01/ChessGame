package logic;

import java.io.Serializable;
import java.util.Scanner;

public class GestioneInput implements Serializable {
    private static GestioneInput istanza;
    private final Scanner scanner;

    private GestioneInput() {
        scanner = new Scanner(System.in);
    }

    public static synchronized GestioneInput getIstanza() {
        if (istanza == null) {
            istanza = new GestioneInput();
        }
        return istanza;
    }

    public String leggiInput() {
        return scanner.nextLine();
    }

    public void pulisci() {
        istanza =null;
    }

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
