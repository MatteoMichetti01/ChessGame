package domain;

abstract class Pezzo {
        protected String nome;
        protected String colore;
        protected int posX,posY;
        public Pezzo(String n, String c,int pX, int pY) {
            this.nome = n;
            this.colore = c;
            this.posX = pX;
            this.posY = pY;
        }
        public String getColore(){
            return this.colore;
        }
        public void setColore(String c){
            this.colore = c;
        }
    }
