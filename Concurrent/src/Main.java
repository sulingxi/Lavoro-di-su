import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main extends JFrame {
    Mappa mp = null;
    static Semaphore s1=new Semaphore(1);
    static Semaphore s2=new Semaphore(0);;
    public static ArrayList<Integer> X = new ArrayList<>();
    public static ArrayList<Integer> O = new ArrayList<>();

    public static void main(String[] args) {
        Main mainFrame = new Main();
        Giocattore giocattore1 = new Giocattore("Giocatore1", mainFrame.mp,s1,s2,"X");
        Giocattore giocattore2 = new Giocattore("Giocatore2", mainFrame.mp,s2,s1,"O");
        giocattore1.start();
        giocattore2.start();
    }

    public Main() {
        mp = new Mappa();
        this.add(mp);
        this.setSize(900, 900);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}