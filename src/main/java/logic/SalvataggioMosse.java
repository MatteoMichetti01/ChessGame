package logic;
import domain.Scacchiera;

import java.io.Serializable;
import java.util.*;

/**
 * La classe SalvataggioMosse gestisce il salvataggio delle mosse di una partita di scacchi.
 *
 * La lista delle mosse memorizza le scacchiere in cui si trovava la partita in vari momenti.
 * Quando viene raggiunto il limite di 10 mosse, le mosse più vecchie vengono eliminate per
 * fare spazio alle nuove.
 */
public class SalvataggioMosse implements Serializable {

    List<Scacchiera> mosse;

    /**
     * Costruttore della classe SalvataggioMosse.
     * Crea una nuova istanza della classe e inizializza la lista delle mosse.
     */
    public SalvataggioMosse() {
        this.mosse = new LinkedList<>();
    }

    /**
     * Aggiunge una nuova mossa alla lista delle mosse.
     * Se il limite di 10 mosse è stato raggiunto, le mosse più vecchie vengono eliminate.
     *
     * @param scacchiera La scacchiera corrente da aggiungere alla lista delle mosse.
     */
    public void aggiungiMossa(Scacchiera scacchiera){
        if(mosse.size()==10){
            mosse.remove(0);
            mosse.remove(0);
        }
        Scacchiera clone = new Scacchiera();
        clone = clone.clone(scacchiera);
        mosse.add(clone);
    }

    /**
     * Annulla un certo numero di mosse e restituisce la scacchiera alla situazione precedente.
     *
     * @param quanto Il numero di mosse da annullare.
     * @return La scacchiera alla situazione precedente.
     * @throws MossaNonValida Se si tenta di annullare più mosse di quelle presenti.
     */
    public Scacchiera undoMosse(int quanto) throws MossaNonValida {
        if(quanto >= mosse.size()){
            if(mosse.size()%2==0) {

                System.out.println("Puoi tornare indietro al più " + (mosse.size() / 2 -1 ) + " mosse!");
            }
            else {

                System.out.println("Puoi tornare indietro al più " + mosse.size() / 2 + " mosse!");
            }
            throw new MossaNonValida("Non hai fatto abbastanza mosse");}
        Scacchiera vecchiaScacchiera;
        vecchiaScacchiera = mosse.get(mosse.size()-1-quanto);
        int size = mosse.size();
        for(int i = 0; i<quanto;i++){
            mosse.remove(size-i-1);

        }

        return vecchiaScacchiera;
    }

}
