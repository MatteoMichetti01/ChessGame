package logic;
import domain.*;
import java.util.*;

public class ComputerServiceImpl implements GiocatoreService<Computer> {
    Scacchiera scacchiera = new Scacchiera();
    Random random1 = new Random();
    Random random2 = new Random();

    //Non
    @Override
    public String getPezzo() {
        Pezzo temp = null;
        List<Pezzo> pezziComputer = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null) {
                    if (scacchiera.casella[i][j].getPezzo().getColore().equals("bianco")) {
                        pezziComputer.add(scacchiera.casella[i][j].getPezzo());
                    }
                }

            }
        }
        int c = random1.nextInt(pezziComputer.size());
        temp = pezziComputer.get(c);
        return temp.getNome();
    }

    @Override
    public String getPosizioneMossa() {
        List<String> mosse = new ArrayList<>();
        String mossaTemp;
        while (mosse.isEmpty()) {
            while (mosse.isEmpty()) {
                String temp = getPezzo();
                Pezzo temp1 = null;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (scacchiera.casella[i][j].getPezzo() != null) {
                            if (temp.equals(scacchiera.casella[i][j].getPezzo().getNome())) {
                                temp1.setPosX(i);
                                temp1.setPosY(j);
                            }
                        }
                    }
                }

                for (int k = 1; k < 9; k++) {
                    for (int z = 1; z < 9; z++) {
                        try {
                            PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(temp.getClass());
                            if (scacchiera.casella[k][z].isOccupata() && scacchiera.casella[temp1.getPosX()][temp1.getPosY()].getPezzo().getColore().equals(scacchiera.casella[k][z].getPezzo().getColore()))
                                throw new MossaNonValida("la casella è gia occupata");
                            service.controlloMossa(k, z, temp1.getPosX(), temp1.getPosY(), scacchiera);
                            mosse.add(scacchiera.casella[k][z].getPosizione());
                        } catch (MossaNonValida m) {
                        }
                    }
                }
            }
        }
        int m = random2.nextInt(mosse.size());
        return mossaTemp = mosse.get(m);

    }
}