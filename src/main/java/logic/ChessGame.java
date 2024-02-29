package logic;

import domain.Pezzo;
import domain.Scacchiera;

public class ChessGame {
    static Pezzo[][] p = new Pezzo[9][9];
    static Scacchiera c = new Scacchiera(p);
    public static void main(String[] args){
    for(int i = 0; i<9; i++){
        System.out.println();
        for(int j = 0; j<9; j++){
            System.out.print(c.p[i][j].getNome()+" ");
        }
    }
    }

}
