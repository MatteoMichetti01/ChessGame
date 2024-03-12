package domain;

import controller.PedoneService;
import controller.ReService;
import logic.MossaNonValida;

public class Re extends Pezzo {
    public Re(){
        super();
    }

    public Re(String nome, String colore, int posX, int posY) {
        super(nome, colore, posX, posY);
    }

    public Re(String nome, String colore) {
        super(nome, colore);
    }
    public void controlloScacco(Re re, Scacchiera scacchiera) throws MossaNonValida {
        Boolean[] prova = new Boolean[9];
        for(int y=0;y<prova.length;y++){
            prova[y]=false;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (scacchiera.casella[i][j].getNome() != "  ") {
                    if (scacchiera.casella[i][j].getNome().equals("p" + i) && re.getColore() != scacchiera.casella[i][j].getPezzo().getColore()) {
                        prova[i] = PedoneService.controlloPedone(re.posX, re.posY, i, j, scacchiera);
                    }
                }
            }
        }
        for(int k=1;k< prova.length;k++){
            if(prova[k])
                System.out.println("sei in scacco sposta il re");
        }
    }
}
