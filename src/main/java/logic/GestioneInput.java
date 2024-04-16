package logic;

import java.util.Scanner;

public class GestioneInput {
    private static GestioneInput instance;
    private Scanner scanner;

    private GestioneInput() {
        scanner = new Scanner(System.in);
    }

    public static synchronized GestioneInput getInstance() {
        if (instance == null) {
            instance = new GestioneInput();
        }
        return instance;
    }

    public String leggiInput() {
        return scanner.nextLine();
    }

    public String leggiPezzoInput() throws MossaNonValida{
        while (true) {
            try {
                String pezzo = scanner.nextLine();
                if (!(pezzo.equals("p1")) && !(pezzo.equals("p2")) && !(pezzo.equals("p3")) && !(pezzo.equals("p4")) && !(pezzo.equals("p5"))
                        && !(pezzo.equals("p6")) && !(pezzo.equals("p7")) && !(pezzo.equals("p8")) && !(pezzo.equals("t1")) && !(pezzo.equals("t2"))
                        && !(pezzo.equals("c1")) && !(pezzo.equals("c2")) && !(pezzo.equals("a1")) && !(pezzo.equals("a2"))
                        && !(pezzo.equals("qn")) && !(pezzo.equals("re")) && !(pezzo.equals("qP")) && !(pezzo.equals("aP")) && !(pezzo.equals("cP")) && !(pezzo.equals("tP")) && !(pezzo.equals("o"))) {
                    throw new MossaNonValida("Inserisci un pezzo valido (ad esempio: p4)");
                }
                return pezzo;
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }
    public String leggiPezzoInputPromozione() throws MossaNonValida{
        while (true) {
            try {
                String pezzo = scanner.nextLine();
                if (!(pezzo.equals("regina")) && !(pezzo.equals("torre")) && !(pezzo.equals("cavallo")) && !(pezzo.equals("alfiere"))) {
                    throw new MossaNonValida("Inserisci un pezzo valido per la promozione ");
                }
                return pezzo;
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }


    public String leggiNumeroInput() throws MossaNonValida {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    return input;
                }

                throw new MossaNonValida("Inserisci una modalità valida (suggerimento: o 1 o 2):");
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }

    public String inputNonVuoto() throws MossaNonValida{
        while(true) {
            try{
                String input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new MossaNonValida("input non valido, riprova: ");
                }
                return input;
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }

    public String opzioniInput() throws MossaNonValida {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2") || input.equals("3")) {
                    return input;
                }

                throw new MossaNonValida("Inserisci un opzione valida (suggerimento: o 1 o 2 o 3):");
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }

    public int mosseIndieroInput() throws MossaNonValida {
        while(true) {
            try {
                int input = scanner.nextInt();
                if (input == 1 || input == 2 || input == 3 || input == 4 || input == 5) {
                    return input;
                }

                throw new MossaNonValida("Inserisci un numero di mosse valido (suggerimento: un numero da 1 a 5):");
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }

    public String inputColore() throws MossaNonValida{
        while(true) {
            try{
                String input = scanner.nextLine();
                if(!(input.equals("nero")) && !(input.equals("bianco"))) {
                    throw new MossaNonValida("input non valido, inserisci bianco o nero: ");
                }
                return input;
            } catch (MossaNonValida m) {
                System.out.println(m.getMessage());
            }
        }
    }



}
