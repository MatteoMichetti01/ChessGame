package logic;

import domain.*;
import logic.impl.*;

public class PezzoServiceFactory {
    /**
     * Restituisce un'istanza del servizio corretto in base al tipo di pezzo passato come parametro.
     *
     * @param clas La classe del pezzo per cui ottenere il servizio.
     * @return Un'istanza del servizio corretto per il tipo di pezzo specificato.
     * @throws MossaNonValida Se si verifica un errore durante l'ottenimento del servizio.
     */
    public static PezzoService<? extends Pezzo> getPezzoService (Class<?> clas) throws MossaNonValida {
        if (clas.equals(Pedone.class))
            return new PedoneServiceImpl();
        if (clas.equals(Alfiere.class))
            return new AlfiereServiceImpl();
        if (clas.equals(Torre.class))
            return new TorreServiceImpl();
        if (clas.equals(Re.class))
            return new ReServiceImpl();
        if (clas.equals(Cavallo.class))
            return new CavalloServiceImpl();
        if (clas.equals(Regina.class))
            return new ReginaServiceImpl();


        return null;
    }
}
