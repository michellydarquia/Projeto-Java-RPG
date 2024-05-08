package gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends Canvas implements Runnable {

    public static JFrame frame;

    public static int largura = 240;
    public static int altura = 160;
    public static int escala =3;

    public GUI(){
        this.setPreferredSize(new Dimension(altura*escala,largura*escala));
        frame = new JFrame(("Game 01"));
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    @Override
    public void run() {
    }
}
