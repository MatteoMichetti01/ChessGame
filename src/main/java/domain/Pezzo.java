package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Questa classe astratta rappresenta un pezzo nel gioco degli scacchi.
 * Implementa l'interfaccia Serializable.
 */

public abstract class Pezzo implements Serializable {
        protected String colore;
        protected String nome;
        protected int VALORE;

         int posX;
         int posY;
    /**
     * Costruisce un nuovo pezzo con il nome, il colore, la posizione X e Y specificati.
     *
     * @param nome   il nome del pezzo
     * @param colore il colore del pezzo
     * @param posX   la posizione X del pezzo sulla scacchiera
     * @param posY   la posizione Y del pezzo sulla scacchiera
     */

    public Pezzo(String nome, String colore, int posX, int posY) {
        this.nome = nome;
        this.colore = colore;
        this.posX = posX;
        this.posY = posY;
    }
    /**
     * Costruisce un nuovo pezzo con il nome e il colore specificati.
     *
     * @param nome   il nome del pezzo
     * @param colore il colore del pezzo
     */
    public Pezzo(String nome, String colore){this.nome=nome; this.colore=colore;}


    /**
     * Restituisce il colore del pezzo.
     *
     * @return il colore del pezzo
     */
    public String getColore(){
            return this.colore;
        }

    /**
     * Imposta il colore del pezzo.
     *
     * @param c il nuovo colore del pezzo
     */
        public void setColore(String c){
            this.colore = c;
        }

    /**
     * Restituisce il nome del pezzo.
     *
     * @return il nome del pezzo
     */
        public String getNome(){
            return this.nome;
        }
    /**
     * Restituisce la posizione X del pezzo sulla scacchiera.
     *
     * @return la posizione X del pezzo
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Imposta la posizione X del pezzo sulla scacchiera.
     *
     * @param posX la nuova posizione X del pezzo
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    /**
     * Restituisce la posizione Y del pezzo sulla scacchiera.
     *
     * @return la posizione Y del pezzo
     */
    public int getPosY() {
        return posY;
    }
    /**
     * Imposta la posizione Y del pezzo sulla scacchiera.
     *
     * @param posY la nuova posizione Y del pezzo
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    /**
     * Restituisce il valore del pezzo.
     *
     * @return il valore del pezzo
     */
    public int getVALORE() {
        return VALORE;
    }
    /**
     * Confronta se due pezzi sono uguali.
     *
     * @param o l'oggetto da confrontare
     * @return true se i pezzi sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pezzo pezzo = (Pezzo) o;
        return posX == pezzo.posX && posY == pezzo.posY && Objects.equals(colore, pezzo.colore) && Objects.equals(nome, pezzo.nome);
    }
    /**
     * Restituisce il codice hash del pezzo.
     *
     * @return il codice hash del pezzo
     */
    @Override
    public int hashCode() {
        return Objects.hash(colore, nome, posX, posY);
    }
}
