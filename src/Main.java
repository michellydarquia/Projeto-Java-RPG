import game.personagens.classes.Guerreiro;
import game.personagens.classes.Mago;
import gui.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("A Conspiração na Corte Real");


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.iniciarGameThread();


        Guerreiro guerreiro = new Guerreiro(2);
        Mago mago = new Mago(2);

        System.out.println("Guerreiro:");
        guerreiro.usarHabilidade1();
        guerreiro.usarHabilidade2();
        guerreiro.usarHabilidade3();

        System.out.println("\nMago:");
        mago.usarHabilidade1();
        mago.usarHabilidade2();
        mago.usarHabilidade3();


    }
}
