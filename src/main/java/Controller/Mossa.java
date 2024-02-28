package controller;
import java.io.*;
import java.util.Scanner;

public class Mossa {
    protected int Posx, Posy;
    public Scanner input = new Scanner(System.in);

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