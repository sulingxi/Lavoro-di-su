import javax.swing.*;
import java.awt.*;

public class EnigmaZGUI extends JFrame {

  JTextField input;
  JTextField output;

  public EnigmaZGUI() {
    setTitle("EnigmaZ 2");
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);


    JLabel inserisci = new JLabel("inserisci：");
    inserisci.setBounds(20, 20, 100, 25);
    add(inserisci);

    input = new JTextField();
    input.setBounds(120, 20, 400, 25);
    add(input);


    JLabel Risposta = new JLabel("Risposta：");
    Risposta.setBounds(20, 60, 100, 25);
    add(Risposta);

    output = new JTextField();
    output.setBounds(120, 60, 400, 25);
    output.setEditable(false);
    add(output);


    JButton codifica = new JButton("codifica");
    codifica.setBounds(120, 100, 80, 30);
    codifica.addActionListener(e -> {
      String text = input.getText();
      String Cesare = CaesarCipher.metodocodifica(text, 3);
      output.setText(Cesare);
    });
    add(codifica);


    JButton decodifica = new JButton("decodifica");
    decodifica.setBounds(220, 100, 80, 30);
    decodifica.addActionListener(e -> {
      String text = input.getText();
      String Cesare = CaesarCipher.decodifica(text, 3);
      output.setText(Cesare);
    });
    add(decodifica);


    JPanel tastiera = new JPanel();
    tastiera.setLayout(new GridLayout(3, 9));
    tastiera.setBounds(120, 150, 420, 150);

    for (char c = 'A'; c <= 'Z'; c++) {
      String letter = String.valueOf(c);
      JButton key = new JButton(letter);
      key.addActionListener(e -> {
        input.setText(input.getText() + letter);
      });
      tastiera.add(key);
    }


    JButton spaceButton = new JButton("spazio");
    spaceButton.addActionListener(e -> {
      input.setText(input.getText() + " ");
    });
    tastiera.add(spaceButton);

    add(tastiera);
  }


  public static void main(String[] args) {
    EnigmaZGUI gui = new EnigmaZGUI();
    gui.setVisible(true);
  }
}
