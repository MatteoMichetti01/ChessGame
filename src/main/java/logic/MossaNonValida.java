package logic;

public class MossaNonValida extends Exception{
    /**
     * Costruttore che accetta un messaggio per descrivere l'errore.
     *
     * @param messaggio Il messaggio che descrive l'errore.
     */
    public MossaNonValida(String messaggio){
        super(messaggio);
    }
}
