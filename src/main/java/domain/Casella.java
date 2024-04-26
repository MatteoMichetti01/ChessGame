package domain;

import java.io.Serializable;

public class Casella implements Serializable {
    String Posizione;

    String nome;

    Pezzo pezzo;
    public int posX,posY;
    public boolean occupata= false;
    public String getPosizione(){
        return Posizione;
    }
    public Casella(String Posizione, Pezzo pezzo, int posX, int posY, boolean occupata){
        this.Posizione = Posizione;
        this.pezzo=pezzo;
        this.posX=posX;
        this.posY=posY;
        this.occupata=occupata;

    }

    public Casella(String nome){
        this.nome=nome;
    }

    public Casella(String nome, String Posizione, boolean occupata){
        this.Posizione = Posizione;
        this.nome=nome;
        this.occupata=occupata;
    }



    public String getNome() {
        if (occupata){return pezzo.getNome();}
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pezzo getPezzo() {


        return pezzo;
    }

    public void setPezzo(Pezzo pezzo) {
        this.pezzo = pezzo;
    }

    public boolean isOccupata() {
        return this.occupata;
    }

}
