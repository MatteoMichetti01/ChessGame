package domain;

public class Casella {
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
        this.occupata=true;

    }

    public Casella(String nome){
        this.nome=nome;
    }

    public Casella(String nome, String Posizione, boolean occupata){
        this.Posizione = Posizione;
        this.nome=nome;
        this.occupata=false;
    }

    public String getNome() {
        if (occupata){return pezzo.getNome();}
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPosizione(String posizione) {
        this.Posizione = posizione;
    }

    public int getPosPezzoX() {
        return posX;
    }

    public void setPosPezzoX(int posPezzoX) {
        this.posX = posPezzoX;
    }

    public Pezzo getPezzo() {


        return pezzo;
    }

    public void setPezzo(Pezzo pezzo) {
        this.pezzo = pezzo;
    }
}
