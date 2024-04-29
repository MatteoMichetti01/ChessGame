package domain;

import java.io.Serializable;
/**
 * Rappresenta un giocatore nel gioco degli scacchi.
 * È una classe astratta che implementa l'interfaccia Serializable.
 */
public abstract class Giocatore implements Serializable {
    String nome;
    String colore;
    public int punteggio=0;

    /**
     * Costruisce un nuovo giocatore con il nome e il colore specificati.
     *
     * @param nome    il nome del giocatore
     * @param colore  il colore delle pedine del giocatore
     */

    public Giocatore(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return il nome del giocatore
     */

    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del giocatore.
     *
     * @param nome il nuovo nome del giocatore
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il colore delle pedine del giocatore.
     *
     * @return il colore delle pedine del giocatore
     */

    public String getColore() {
        return colore;
    }

    /**
     * Imposta il colore delle pedine del giocatore.
     *
     * @param colore il nuovo colore delle pedine del giocatore
     */

    public void setColore(String colore) {
        this.colore = colore;
    }

    /**
     * Restituisce il punteggio del giocatore.
     *
     * @return il punteggio del giocatore
     */

    public int getPunteggio() {
        return punteggio;
    }

}


