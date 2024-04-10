package logic;

public class GiocatoreServiceFactory {

        public static GiocatoreService<? extends Giocatore> getGiocatoreService(Class claz) {
            if (claz.equals(Umano.class)) {
                return new UmanoServiceImpl();
            }
           // if (claz.equals(Computer.class)) {
             //   return new logic.ComputerServiceImpl();
           // }
            return null;

        }
    }

