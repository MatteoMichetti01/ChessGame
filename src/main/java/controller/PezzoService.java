package controller;
import domain.*;

public interface PezzoService<T extends Pezzo> {
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida;
}