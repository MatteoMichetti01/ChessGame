package logic;

import domain.Scacchiera;
import domain.Torre;

public class TorreServiceImpl implements PezzoService<Torre> {

    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");
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
