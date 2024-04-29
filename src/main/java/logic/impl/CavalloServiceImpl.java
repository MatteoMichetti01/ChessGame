package logic.impl;

import domain.Cavallo;
import domain.Scacchiera;
import logic.MossaNonValida;
import logic.PezzoService;

/**
 * Implementazione del servizio per il pezzo Cavallo.
 */
public class CavalloServiceImpl implements PezzoService<Cavallo> {

    /**
     * Controlla se la mossa del Cavallo è valida.
     *
     * @param nuovaPosX     La nuova posizione X del Cavallo.
     * @param nuovaPosY     La nuova posizione Y del Cavallo.
     * @param vecchiaPosX   La vecchia posizione X del Cavallo.
     * @param vecchiaPosY   La vecchia posizione Y del Cavallo.
     * @param scacchiera    La scacchiera attuale.
     * @throws MossaNonValida Se la mossa del Cavallo non è valida.
     */
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (!((nuovaPosX == vecchiaPosX + 2 && nuovaPosY == vecchiaPosY + 1) || (nuovaPosX == vecchiaPosX + 2 && nuovaPosY == vecchiaPosY - 1)
                || (nuovaPosX == vecchiaPosX - 2 && nuovaPosY == vecchiaPosY - 1) || (nuovaPosX == vecchiaPosX - 2 && nuovaPosY == vecchiaPosY + 1)
                || (nuovaPosX == vecchiaPosX + 1 && nuovaPosY == vecchiaPosY + 2) || (nuovaPosX == vecchiaPosX + 1 && nuovaPosY == vecchiaPosY - 2)
                || (nuovaPosX == vecchiaPosX - 1 && nuovaPosY == vecchiaPosY - 2) || (nuovaPosX == vecchiaPosX - 1 && nuovaPosY == vecchiaPosY + 2))) {
            throw new MossaNonValida("Il cavallo può muoversi solo ad L");
        }
    }

}
