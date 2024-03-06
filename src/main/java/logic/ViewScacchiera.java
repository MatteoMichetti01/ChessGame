package logic;

import domain.Scacchiera;

public class ViewScacchiera {
    Scacchiera scacchiera;
    public ViewScacchiera(Scacchiera s){
        viewscacchiera(s);
    }
    public void viewscacchiera(Scacchiera s){
        for(int k=0;k<9;k++){
            System.out.print(" "+s.casella[0][k].getNome()+"  ");
        }
       for (int i =1;i<9;i++){
           System.out.println();
           for(int j=0;j<9;j++){
               System.out.print(s.casella[i][j].getNome()+ " | ");

           }
       }

    }
}
