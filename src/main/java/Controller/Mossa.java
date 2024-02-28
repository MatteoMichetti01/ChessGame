package Controller;

public class Mossa {
    protected int Posx, Posy;

    public Mossa(int posx, int posy) {
        Posx = posx;
        Posy = posy;
    }

    public int getPosx() {
        return Posx;
    }

    public void setPosx(int posx) {
        Posx = posx;
    }

    public int getPosy() {
        return Posy;
    }

    public void setPosy(int posy) {
        Posy = posy;
    }
}