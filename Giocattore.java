
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Giocattore extends Thread  {
    private String nome;
    private Mappa mappa;
    public Semaphore s1;
    public Semaphore s2;
    private String simbolo;

    public Giocattore(String nome, Mappa mappa,Semaphore s1,Semaphore s2,String simbolo) {
        this.nome = nome;
        this.mappa = mappa;
        this.s1 = s1;
        this.s2=s2;
        this.simbolo = simbolo;
    }

    @Override
    public void run() {
        while (true) {
            if(s1.tryAcquire()){

                if (simbolo.equals("X")) {
                    boolean m=true;
                    int a=0;
                    while(m){
                        m=false;
                        a = (int)(Math.random() * 9) + 1;
                        if(Main.X.contains(a) || Main.O.contains(a)){
                            m=true;
                        }
                    }
                    mappa.setGiocattoretipo(1);
                    mappa.postonum(a);
                    Main.X.add(a);
                }

                if (simbolo.equals("O")) {
                    boolean m=true;
                    int a=0;
                    while(m){
                        m=false;
                        a = (int)(Math.random() * 9) + 1;
                        if(Main.X.contains(a) || Main.O.contains(a)){
                            m=true;
                        }
                    }
                    mappa.setGiocattoretipo(2);
                    mappa.postonum(a);
                    Main.O.add(a);
                }

               if (Scacchiera.arbitro(Main.X, Main.O)){
                   break;
               }

                try {
                    sleep();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                s2.release();
            }else {
                try{
                    sleep();
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }



    // Simula il tempo di riflessione del giocatore con un tempo casuale tra 500 e 1000 ms
    public void sleep() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(1501) + 500;
        Thread.sleep(randomNumber);
    }


}
