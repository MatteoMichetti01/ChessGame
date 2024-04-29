package domain;

/**
 * Rappresenta un giocatore controllato dal computer nel gioco degli scacchi.
 * Estende la classe Giocatore.
 */

public class Computer extends Giocatore {

    /**
     * Costruisce un nuovo giocatore controllato dal computer con il nome e il colore specificati.
     *
     * @param nome    il nome del giocatore
     * @param colore  il colore delle pedine del giocatore
     */
    public Computer(String nome,String colore){
        super(nome,colore);
    }
}