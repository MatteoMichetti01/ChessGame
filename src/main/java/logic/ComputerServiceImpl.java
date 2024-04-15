package logic;
import domain.*;
import java.util.*;

public class ComputerServiceImpl implements GiocatoreService<Computer> {
    Scacchiera scacchiera = new Scacchiera();
    Random random1 = new Random();
    Random random2 = new Random();
    Pezzo temp2;

    public String getPezzo(Giocatore g1) {
        Pezzo temp;
        List<Pezzo> pezziComputer = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getColore().equals(g1.getColore())) {
                        pezziComputer.add(scacchiera.casella[i][j].getPezzo());
                    }
                }

            }
        }
        int c = random1.nextInt(pezziComputer.size());
        temp = pezziComputer.get(c);
        System.out.print("il computer ha mosso " + temp.getNome()+" ");
        return temp.getNome();
    }


    public String getPosizioneMossa(String p) {
        List<String> mosse = new ArrayList<>();
        while (mosse.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (scacchiera.casella[i][j].getPezzo() != null) {
                        if (p.equals(scacchiera.casella[i][j].getPezzo().getNome())) {
                            temp2=scacchiera.casella[i][j].getPezzo();
                            temp2.setPosX(i);
                            temp2.setPosY(j);
                        }
                    }
                }
            }
            for (int k = 1; k < 9; k++) {
                for (int z = 1; z < 9; z++) {
                    try {
                        PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(temp2.getClass());
                        if (scacchiera.casella[k][z].isOccupata() && scacchiera.casella[temp2.getPosX()][temp2.getPosY()].getPezzo().getColore().equals(scacchiera.casella[k][z].getPezzo().getColore()))
                            throw new MossaNonValida("la casella è gia occupata");
                        service.controlloMossa(k, z, temp2.getPosX(), temp2.getPosY(), scacchiera);
                        mosse.add(scacchiera.casella[k][z].getPosizione());
                    } catch (MossaNonValida m) {}
                }
            }
        }

        int m = random2.nextInt(mosse.size());
        String mossaTemp = mosse.get(m);
        System.out.print("in "+ mossaTemp);
        return mossaTemp;
    }
}