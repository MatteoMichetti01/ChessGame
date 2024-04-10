package logic;

import domain.Pezzo;
import domain.Scacchiera;
import java.util.*;

public class GiocatoreControComputer extends Modalita{
    public static boolean scaccoMatto1 = false;

    public GiocatoreControComputer(Giocatore giocatore1, Giocatore giocatore2) {

        super(giocatore1, giocatore2);

    }

    @Override
    public void startGame() throws MossaNonValida {
        if (giocatore2.getColore().equals("nero")) {
            Random random1 = new Random();
            Random random2 = new Random();
            Pezzo temp = null;
            Giocatore computer = new Giocatore("Computer", "bianco");
            String nomeResa = null;
            boolean resa = true;
            boolean mossaFatta = false;
            GestioneInput gestioneInput = GestioneInput.getInstance();
            Scacchiera scacchiera = new Scacchiera();
            MossaServiceImpl p1 = new MossaServiceImpl(scacchiera);
            scacchiera.viewscacchiera();
            System.out.println();
            System.out.println("Inizia il turno " + computer.getNome());
            while (resa && !(scaccoMatto1)) {
                while (!mossaFatta) {
                List<Pezzo> pezziComputer = new ArrayList<>();
                List<String> mosse = new ArrayList<>();

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (scacchiera.casella[i][j].getPezzo() != null) {
                            if (scacchiera.casella[i][j].getPezzo().getColore().equals(computer.getColore())) {
                                pezziComputer.add(scacchiera.casella[i][j].getPezzo());
                            }
                        }

                    }
                }

                while (mosse.isEmpty()) {
                    int c = random1.nextInt(pezziComputer.size());
                    temp = pezziComputer.get(c);


                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (scacchiera.casella[i][j].getPezzo() != null) {
                                if (temp.getNome().equals(scacchiera.casella[i][j].getPezzo().getNome())) {
                                    temp.setPosX(i);
                                    temp.setPosY(j);
                                }
                            }
                        }
                    }
                    for (int k = 1; k < 9; k++) {
                        for (int z = 1; z < 9; z++) {
                            try {
                                PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(temp.getClass());
                                service.controlloMossa(k, z, temp.getPosX(), temp.getPosY(), scacchiera);
                                mosse.add(scacchiera.casella[k][z].getPosizione());
                            } catch (MossaNonValida m) {
                            }
                        }
                    }
                }

                int m = random2.nextInt(mosse.size());
                String mossaTemp = mosse.get(m);
                try {
                    System.out.println("il computer ha mosso il pezzo: " + temp.getNome() + " nella casella: " + mossaTemp);
                    p1.move(temp.getNome(), mossaTemp, computer.getColore());
                    mossaFatta = true;
                } catch (MossaNonValida mos) {
                }
            }
            scacchiera.viewscacchiera();
                scacchiera.viewscacchieraPos();
            System.out.println();
            mossaFatta=false;

            while (!mossaFatta && resa && !(scaccoMatto1)) {
                System.out.println("Tocca a " + this.giocatore2.getNome());
                System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
                String pezzoNero = gestioneInput.leggiPezzoInput();
                if(pezzoNero.equals("o")){if(this.opzioni().equals("3")) resa=false;nomeResa= this.giocatore2.getNome();break;}
                System.out.println("Inserisci mossa: ");
                String mossaNero = gestioneInput.inputNonVuoto();
                try{
                    scacchiera = p1.move(pezzoNero+"B", mossaNero.toUpperCase(), this.giocatore2.getColore());
                    mossaFatta=true;
                }
                catch (MossaNonValida message) {
                    System.out.println(message.getMessage());
                    scacchiera.viewscacchiera();
                    System.out.println();
                }
            }
            if(mossaFatta && !(scaccoMatto1)){
                scacchiera.viewscacchiera();
                scacchiera.viewscacchieraPos();
                System.out.println();
                System.out.println();
                mossaFatta=false;
            }
            }

            }

    }

    @Override
    public String opzioni() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Salva partita (1)");
        System.out.println("Annulla mossa (2)");
        System.out.println("Arrenditi (3)");
        return scanner.nextLine();
    }
}
