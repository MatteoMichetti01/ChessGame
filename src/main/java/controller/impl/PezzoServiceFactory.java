package controller.impl;

import controller.MossaNonValida;
import controller.PezzoService;
import domain.Pedone;
import domain.Pezzo;

public class PezzoServiceFactory {
    public static PezzoService getPezzoService (Pezzo p) throws MossaNonValida{
        switch (p.getNome().charAt(0)) {
            case 'p':
                return new PedoneServiceImpl();
            case 'a':
                return new AlfiereServiceImpl();
            case 't':
                return new TorreServiceImpl();
            case 'r':
                return new ReServiceImpl();
            case 'c':
                return new CavalloServiceImpl();
            case 'q':
                return new ReginaServiceImpl();
            default:
                throw new MossaNonValida("pezzo non trovato");
        }
    }
}
