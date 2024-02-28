package logic;

import domain.Pezzo;
import domain.Scacchiera;

public class ChessGame {
    static Pezzo[][] p = new Pezzo[8][8];
    static Scacchiera c = new Scacchiera(p);
    public static void main(String[] args){
    for(int i = 0; i<8; i++){
        System.out.println();
        for(int j = 0; j<8; j++){
            System.out.print(c.p[i][j].getNome()+" ");
        }
    }
    }

}
