package domain;

public class Casella {
    String nome;
    Pezzo pezzo;
    public int posX,posY;
    public boolean occupata= false;

    public Casella(String nome, Pezzo pezzo, int posX, int posY,boolean occupata){
        this.nome=nome;
        this.pezzo=pezzo;
        this.posX=posX;
        this.posY=posY;
        this.occupata=true;

    }
    public Casella(String nome){this.nome=nome;}

    public String getNome() {
        if (occupata){return pezzo.getNome();}
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosPezzoX() {
        return posX;
    }

    public void setPosPezzoX(int posPezzoX) {
        this.posX = posPezzoX;
    }

    public String getPezzo() {
        if(this.pezzo==null){
            return "|    |";
        }
        return pezzo.nome;
    }

    public void setPezzo(Pezzo pezzo) {
        this.pezzo = pezzo;
    }
}
