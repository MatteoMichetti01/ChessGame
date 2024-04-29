package logic;

public class InputNonValido extends Exception{
    /**
     * Costruttore che accetta un messaggio per descrivere l'errore.
     *
     * @param messaggio Il messaggio che descrive l'errore.
     */
    public InputNonValido(String messaggio){
        super(messaggio);
    }
}
