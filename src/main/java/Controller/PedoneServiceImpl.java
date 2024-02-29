package Controller;
import domain.*;

public class PedoneServiceImpl implements Mossa{

    //prendere la scacchiera già esistente
    Scacchiera scacchiera;

    public PedoneServiceImpl(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }

    public Scacchiera move (String nomePezzo, int newPosX, int newPosY) {
        // il pedone si muove
        for (int i=1; i<9; i++) {
            for (int j = 1; j < 9; j++) {
                if (scacchiera.p[i][j].getNome().equals(nomePezzo)) {
                    Pedone p1 = new Pedone("nomePezzo", "bianco", newPosX, newPosY);
                    scacchiera.p[i][j] = new CasellaVuota(" ", null, i, j);
                    scacchiera.p[newPosX][newPosY] = p1;
                    System.out.println("il pedone" + nomePezzo + "si è mosso verso la casella: " + newPosX+""+newPosY);
                }
            }
        }
        return this.scacchiera;
    }

}
