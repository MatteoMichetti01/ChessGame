package logic;

import domain.Computer;
import domain.Giocatore;
import domain.Umano;
import logic.impl.ComputerServiceImpl;
import logic.impl.UmanoServiceImpl;

/**
 * Factory per ottenere il servizio del giocatore corrispondente alla classe del giocatore.
 * Questa factory fornisce l'implementazione corretta del servizio del giocatore in base alla classe del giocatore fornita.
 */
public class GiocatoreServiceFactory {

    /**
     * Restituisce il servizio del giocatore corrispondente alla classe del giocatore specificata.
     *
     * @param claz La classe del giocatore per cui ottenere il servizio.
     * @return Il servizio del giocatore corrispondente alla classe specificata.
     */
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

