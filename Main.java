import javax.swing.*;

public class Main extends JFrame {
    Mappa mp = null;

    public static void main(String[] args) {
        Main mainFrame = new Main();
        Giocattore giocattore = new Giocattore("paolo", mainFrame.mp);
        giocattore.initializeNumbers();

        // Inizializzazione dell'array
        System.out.println("Inizializzazione di un array:");
        for (int i = 0; i < 9; i++) {
            System.out.print(giocattore.numbers[i] + " ");
        }
        System.out.println();

        // Creazione e avvio dei thread
        Thread thread = new Thread(giocattore, "Thread-1");
        Thread thread1 = new Thread(giocattore, "Thread-2");
        thread.start();
        thread1.start();
    }

    public Main() {
        mp = new Mappa(); // Creazione di un oggetto pannello personalizzato
        this.add(mp);     // Aggiunta del pannello personalizzato alla finestra

        this.setSize(900, 900); // Imposta la dimensione della finestra (larghezza 900, altezza 900)
        this.setVisible(true);  // Imposta la finestra come visibile
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Imposta l'operazione di chiusura
    }
}
