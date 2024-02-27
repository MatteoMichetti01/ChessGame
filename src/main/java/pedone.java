import com.sun.org.apache.xpath.internal.operations.Bool;
    abstract class pedone {
        protected String nome;
        protected Boolean mangiato = false;
        protected int colore;

        public pedone(String n, Boolean mangiato, int c) {
            this.nome = n;
            this.colore = c;
            this.mangiato = mangiato;
        }
    }
