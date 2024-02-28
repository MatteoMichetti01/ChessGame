package domain;

public abstract class Pezzo {
        protected String colore;
        protected String nome;
        public Pezzo(String n, String c) {
            this.nome = n;
            this.colore = c;
        }
        public String getColore(){
            return this.colore;
        }
        public void setColore(String c){
            this.colore = c;
        }
        public String getNome(){
            return this.nome;
        }
    }
