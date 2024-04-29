package domain;

/**
 * Rappresenta un giocatore umano nel gioco degli scacchi.
 * Estende la classe Giocatore.
 */

public class Umano extends Giocatore {

    /**
     * Costruisce un nuovo giocatore umano con il nome e il colore specificati.
     *
     * @param nome    il nome del giocatore
     * @param colore  il colore delle pedine del giocatore
     */
    public Umano(String nome , String colore){
        super(nome,colore);
    }


}