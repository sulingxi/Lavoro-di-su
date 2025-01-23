import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Giocattore implements Runnable {
    private String nome;
    int[] numbers = new int[9];  // Array per memorizzare i numeri
    private int num = 0;  // Puntatore per controllare l'accesso all'array
    private final Object lockObj = new Object();  // Oggetto per la sincronizzazione tra i thread
    private Mappa mappa;
    private ArrayList<Integer> X = new ArrayList<>();
    private ArrayList<Integer> O = new ArrayList<>();
    private Boolean gameover = false;

    public Giocattore(String nome, Mappa mappa) {
        this.nome = nome;
        this.mappa = mappa;
    }

    @Override
    public void run() {
        while (!gameover) {
            synchronized (lockObj) {
                // Determina il turno del giocatore
                if (num % 2 == 0 && Thread.currentThread().getName().equals("Thread-1")) {
                    // Esecuzione del thread 1
                    System.out.println(Thread.currentThread().getName() + " Output: " + numbers[num]);
                    mappa.setGiocattoretipo(1);
                    mappa.postonum(numbers[num]);
                    X.add(numbers[num]);  // Aggiorna la posizione del giocatore 1
                    num++;
                    if (arbitro()) {
                        break;
                    }
                    lockObj.notify();  // Notifica al thread 2 di eseguire
                } else if (num % 2 != 0 && Thread.currentThread().getName().equals("Thread-2")) {
                    // Esecuzione del thread 2
                    System.out.println(Thread.currentThread().getName() + " Output: " + numbers[num]);
                    mappa.setGiocattoretipo(2);
                    mappa.postonum(numbers[num]);
                    O.add(numbers[num]);  // Aggiorna la posizione del giocatore 2
                    num++;
                    if (arbitro()) {
                        break;
                    }
                    lockObj.notify();  // Notifica al thread 1 di eseguire
                } else {
                    try {
                        lockObj.wait();  // Il thread corrente aspetta fino a quando non viene notificato
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    sleep();  // Simula il tempo di riflessione del giocatore
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Metodo per inizializzare l'array dei numeri
    public void initializeNumbers() {
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            numbers[i] = i + 1; // Inizializza l'array con numeri da 1 a 9
        }
        // Mescola i numeri nell'array
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // Seleziona casualmente un indice
            // Scambia due elementi
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    // Simula il tempo di riflessione del giocatore con un tempo casuale tra 500 e 1000 ms
    public void sleep() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(501) + 500;
        Thread.sleep(randomNumber);
    }

    // Controlla se un giocatore ha vinto
    public Boolean vittoria(ArrayList<Integer> a) {
        int[][] winningPositions = {
                {1, 2, 3},  // Prima riga
                {4, 5, 6},  // Seconda riga
                {7, 8, 9},  // Terza riga
                {1, 4, 7},  // Prima colonna
                {2, 5, 8},  // Seconda colonna
                {3, 6, 9},  // Terza colonna
                {1, 5, 9},  // Diagonale principale
                {3, 5, 7}   // Diagonale secondaria
        };
        for (int[] win : winningPositions) {
            if (a.contains(win[0]) && a.contains(win[1]) && a.contains(win[2])) {
                return true;
            }
        }
        return false;
    }

    // Controlla se c'è un pareggio
    public boolean cravatta() {
        if (X.size() + O.size() == 9) {
            return !vittoria(X) && !vittoria(O);
        }
        return false;
    }

    // Arbitro del gioco: controlla vittoria, pareggio o continua il gioco
    public boolean arbitro() {
        if (vittoria(X)) {
            System.out.println("Giocatore 1 vince!");
            JOptionPane.showMessageDialog(null, "Giocatore 1 vince!");
            gameover = true;
            lockObj.notify();
            return true;
        }
        if (vittoria(O)) {
            System.out.println("Giocatore 2 vince!");
            JOptionPane.showMessageDialog(null, "Giocatore 2 vince!");
            gameover = true;
            lockObj.notify();
            return true;
        }
        if (cravatta()) {
            System.out.println("cravatta!");
            JOptionPane.showMessageDialog(null, "cravatta!");
            gameover = true;
            lockObj.notify();
            return true;
        }
        return false;
    }
}
