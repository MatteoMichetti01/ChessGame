package logic;

import domain.Scacchiera;

public class ViewScacchiera {
    Scacchiera scacchiera;
    public ViewScacchiera(Scacchiera s){
        viewscacchiera(s);
    }
    public void viewscacchiera(Scacchiera s){
       for (int i =0;i<9;i++){
           System.out.println();
           for(int j=0;j<9;j++){
               System.out.print("| "+s.casella[i][j].getNome()+ " | ");

           }
       }

    }
}
