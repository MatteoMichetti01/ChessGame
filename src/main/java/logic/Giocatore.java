package logic;

public class Giocatore {
    String nome;
    String colore;
    int punteggio=0;

    public Giocatore(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio_) {
        punteggio = punteggio_;
    }
}


