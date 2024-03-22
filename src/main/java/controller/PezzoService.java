package controller;
import domain.*;

public interface PezzoService<T> {
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida;
}