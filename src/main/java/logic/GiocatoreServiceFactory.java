package logic;

import domain.Computer;
import domain.Giocatore;
import domain.Umano;
import logic.impl.ComputerServiceImpl;
import logic.impl.UmanoServiceImpl;

public class GiocatoreServiceFactory {

    public static GiocatoreService<? extends Giocatore> getGiocatoreService(Class claz) {
        if (claz.equals(Umano.class)) {
            return new UmanoServiceImpl();
        }
        if (claz.equals(Computer.class)) {
            return new ComputerServiceImpl();
        }
        return null;

    }
}

