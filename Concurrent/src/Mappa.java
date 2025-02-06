import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Mappa extends JPanel {
    private int postonum;
    private int giocattoretipo;
    private ArrayList<int[]> pg;  // Utilizza int[] per memorizzare le informazioni di una casella (posto, giocatore)

    public Mappa() {
        this.postonum = -1;  // Inizializza con nessuna casella occupata
        pg = new ArrayList<>();  // Inizializza l'array pg
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Richiama il metodo paint della superclasse per garantire il rendering corretto del pannello

        g.setColor(Color.white);
        g.fillRect(0, 0, 900, 900);  // Riempie lo sfondo (pulisce il canvas)

        g.setColor(Color.black);  // Il colore delle linee della griglia è nero
        for (int i = 1; i < 3; i++) {
            g.drawLine(0, i * 300, getWidth(), i * 300);  // Linee orizzontali
            g.drawLine(i * 300, 0, i * 300, getHeight());  // Linee verticali
        }

        // Scorre l'array pg per disegnare tutte le caselle occupate
        for (int i = 0; i < pg.size(); i++) {
            int[] pos = pg.get(i);
            giocattore(g, pos[0], pos[1]);
        }

        if (postonum != -1) {
            giocattore(g, postonum, giocattoretipo);  // Disegna il simbolo del giocatore
            pg.add(new int[]{postonum, giocattoretipo});  // Aggiunge alla lista pg
        }
    }

    // Disegna il simbolo del giocatore sulla casella corrispondente
    public void giocattore(Graphics g, int posto, int giocattore) {
        int x1 = 0;
        int y1 = 0;
        if (posto <= 3) {
            x1 = (posto - 1) * 300;
            y1 = 0;
        } else if (posto <= 6) {
            x1 = (posto - 4) * 300;
            y1 = 300;
        } else if (posto <= 9) {
            x1 = (posto - 7) * 300;
            y1 = 600;
        }
        if (giocattore == 1) {
            disegnax(g, x1, y1);
        } else if (giocattore == 2) {
            disegnaO(g, x1, y1);
        }
    }

    // Disegna una "X" nella posizione specificata
    public void disegnax(Graphics g, int x, int y) {
        g.drawLine(x, y, x + 300, y + 300);
        g.drawLine(x + 300, y, x, y + 300);
    }

    // Disegna un "O" nella posizione specificata
    public void disegnaO(Graphics g, int x, int y) {
        g.drawOval(x, y, 300, 300);
    }

    // Aggiorna il numero di posizione selezionato
    public void postonum(int index) {
        this.postonum = index;
        repaint();
    }

    // Imposta il tipo di giocatore
    public void setGiocattoretipo(int giocattoretipo) {
        this.giocattoretipo = giocattoretipo;
    }
}
