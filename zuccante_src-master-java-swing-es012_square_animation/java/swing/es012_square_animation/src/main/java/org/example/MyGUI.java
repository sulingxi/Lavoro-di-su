package org.example;

import javax.swing.*;
import java.awt.*;

public class MyGUI extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    public MyGUI(String title) {
        super(title);
        setSize(WIDTH, HEIGHT);
        add(new Board());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}