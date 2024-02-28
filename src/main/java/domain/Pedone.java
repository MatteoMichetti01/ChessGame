package domain;

abstract class Pedone {
        protected String nome;
        protected Boolean mangiato = false;
        protected int colore;

        public Pedone(String n, Boolean mangiato, int c) {
            this.nome = n;
            this.colore = c;
            this.mangiato = mangiato;
        }
    }
