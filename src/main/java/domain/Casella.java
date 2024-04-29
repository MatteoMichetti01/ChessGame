package domain;

import java.io.Serializable;
/**
 * Questa classe rappresenta una singola casella sulla scacchiera.
 * Implementa l'interfaccia Serializable per la serializzazione.
 */

public class Casella implements Serializable {
    String Posizione;

    String nome;

    Pezzo pezzo;
    public int posX,posY;
    public boolean occupata= false;
    /**
     * Restituisce la posizione della casella.
     *
     * @return la posizione della casella
     */
    public String getPosizione(){
        return Posizione;
    }
    /**
     * Costruisce una nuova casella con la posizione, il pezzo, le coordinate X e Y, e lo stato di occupazione specificati.
     *
     * @param Posizione la posizione della casella
     * @param pezzo il pezzo sulla casella
     * @param posX la coordinata X della casella sulla scacchiera
     * @param posY la coordinata Y della casella sulla scacchiera
     * @param occupata true se la casella è occupata da un pezzo, false altrimenti
     */

    public Casella(String Posizione, Pezzo pezzo, int posX, int posY, boolean occupata){
        this.Posizione = Posizione;
        this.pezzo=pezzo;
        this.posX=posX;
        this.posY=posY;
        this.occupata=occupata;

    }
    /**
     * Costruisce una nuova casella con il nome specificato.
     *
     * @param nome il nome della casella
     */

    public Casella(String nome){
        this.nome=nome;
    }

    /**
     * Costruisce una nuova casella con il nome, la posizione, e lo stato di occupazione specificati.
     *
     * @param nome il nome della casella
     * @param Posizione la posizione della casella
     * @param occupata true se la casella è occupata da un pezzo, false altrimenti
     */

    public Casella(String nome, String Posizione, boolean occupata){
        this.Posizione = Posizione;
        this.nome=nome;
        this.occupata=occupata;
    }


    /**
     * Restituisce il nome della casella, o il nome del pezzo se la casella è occupata.
     *
     * @return il nome della casella o il nome del pezzo se la casella è occupata
     */

    public String getNome() {
        if (occupata){return pezzo.getNome();}
        return nome;
    }

    /**
     * Imposta il nome della casella.
     *
     * @param nome il nuovo nome della casella
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il pezzo sulla casella.
     *
     * @return il pezzo sulla casella
     */

    public Pezzo getPezzo() {


        return pezzo;
    }

    /**
     * Imposta il pezzo sulla casella.
     *
     * @param pezzo il nuovo pezzo sulla casella
     */

    public void setPezzo(Pezzo pezzo) {
        this.pezzo = pezzo;
    }

    /**
     * Verifica se la casella è occupata da un pezzo.
     *
     * @return true se la casella è occupata da un pezzo, false altrimenti
     */
    public boolean isOccupata() {
        return this.occupata;
    }

}
