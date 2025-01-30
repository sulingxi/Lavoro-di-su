import javax.swing.*;
import java.util.ArrayList;


public class Scacchiera   {
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


    public boolean cravatta(ArrayList<Integer> X, ArrayList<Integer> O) {
        if (X.size() + O.size() == 9) {
            return !vittoria(X) && !vittoria(O);
        }
        return false;
    }


    public static boolean arbitro(ArrayList<Integer> X, ArrayList<Integer> O) {
        Scacchiera scacchiera = new Scacchiera();
        if (scacchiera.vittoria(X)) {
            System.out.println("Giocatore 1 vince!");
            JOptionPane.showMessageDialog(null, "Giocatore 1 vince!");
            return true;
        }
        if (scacchiera.vittoria(O)) {
            System.out.println("Giocatore 2 vince!");
            JOptionPane.showMessageDialog(null, "Giocatore 2 vince!");
            return true;
        }
        if (scacchiera.cravatta(X,O)) {
            System.out.println("cravatta!");
            JOptionPane.showMessageDialog(null, "cravatta!");
            return true;
        }
        return false;
    }
}
