package logic;

import domain.*;

public class PezzoServiceFactory {
    public static PezzoService<? extends Pezzo> getPezzoService (Class clas) throws MossaNonValida {
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
