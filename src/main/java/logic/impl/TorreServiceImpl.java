package logic.impl;

import domain.Scacchiera;
import domain.Torre;
import logic.MossaNonValida;
import logic.PezzoService;

/**
 * Questa classe fornisce un'implementazione dei metodi per il controllo delle mosse della Torre.
 */
public class TorreServiceImpl implements PezzoService<Torre> {

    /**
     * Controlla la validità di una mossa per la Torre sulla scacchiera.
     *
     * @param nuovaPosX   La nuova posizione X del pezzo.
     * @param nuovaPosY   La nuova posizione Y del pezzo.
     * @param vecchiaPosX La posizione X attuale del pezzo.
     * @param vecchiaPosY La posizione Y attuale del pezzo.
     * @param scacchiera  La scacchiera su cui si sta giocando.
     * @throws MossaNonValida Se la mossa della Torre non è valida.
     */
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {

        //verifica che la torre non possa andare in diagonale
        if (nuovaPosY != vecchiaPosY) {
            if (nuovaPosX != vecchiaPosX) {
                throw new MossaNonValida("Mossa non valida, la torre non può andare in diagonale");
            } else {
                //controllo se nel fare la mossa non ci siano pezzi che la blocchino(avanti)
                if (vecchiaPosY > nuovaPosY) {
                    for (int i = vecchiaPosY - 1; i > nuovaPosY; i--) {
                        if (scacchiera.casella[vecchiaPosX][i].isOccupata())
                            throw new MossaNonValida("pezzo in mezzo");
                    }
                } else {
                    //controllo se nel fare la mossa non ci siano pezzi che la blocchino(indietro)
                    for (int i = vecchiaPosY + 1; i < nuovaPosY; i++) {
                        if (scacchiera.casella[vecchiaPosX][i].isOccupata())
                            throw new MossaNonValida("pezzo in mezzo");
                    }
                }
            }
        } else {
            //controllo se nel fare la mossa non ci siano pezzi che la blocchino(destra)
            if (vecchiaPosX > nuovaPosX) {
                for (int i = vecchiaPosX - 1; i > nuovaPosX; i--) {
                    if (scacchiera.casella[i][vecchiaPosY].isOccupata())
                        throw new MossaNonValida("pezzo in mezzo");
                }
            } else {
                //controllo se nel fare la mossa non ci siano pezzi che la blocchino(sinistra)
                for (int i = vecchiaPosX + 1; i < nuovaPosX; i++) {
                    if (scacchiera.casella[i][vecchiaPosY].isOccupata())
                        throw new MossaNonValida("pezzo in mezzo");
                }
            }
        }
    }
}
