package domain;

import java.util.Objects;

public abstract class Pezzo {
        protected String colore;
        protected String nome;
        protected int valore;

        protected int posX;
        protected int posY;


    public Pezzo(String nome, String colore, int posX, int posY) {
        this.nome = nome;
        this.colore = colore;
        this.posX = posX;
        this.posY = posY;
    }
    public Pezzo(String nome, String colore){this.nome=nome; this.colore=colore;}

    public String getColore(){
            return this.colore;
        }
        public void setColore(String c){
            this.colore = c;
        }
        public String getNome(){
            return this.nome;
        }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pezzo pezzo = (Pezzo) o;
        return posX == pezzo.posX && posY == pezzo.posY && Objects.equals(colore, pezzo.colore) && Objects.equals(nome, pezzo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colore, nome, posX, posY);
    }
}
