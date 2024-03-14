package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class ControlloMosse {
    public static void controlloMossa (String nomePezzo, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        switch (nomePezzo.charAt(0)) {

            case 'p':
                PedoneService.controlloPedone(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;

            case 'a':
                AlfiereService.controlloAlfiere(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;

            case 't':
                TorreService.controlloTorre(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;

            case 'c':
                CavalloService.controlloCavallo(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;

            case 'q':
                ReginaService.controlloRegina(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;

            case 'r':
                ReService.controlloRe(nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY, scacchiera);
                break;
        }
    }
}
