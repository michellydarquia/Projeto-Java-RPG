package gui;

import javax.swing.JFrame;
import java.awt.*;

public class GUI extends Canvas implements Runnable {

    public static JFrame frame;

    // 16x16 será o tamanho nos personagens para criação
    // a escala é 3 então o personagem sera 16x6 = 48 pixels na tela
    //

    final int originalSizeLadrilho = 16;
    final int scale = 3;
    final int sizeLadrilho = originalSizeLadrilho * scale;

    final int maxTelaColunas = 16;
    final int maxTelaLinhas = 12;
    final int telaWidth = sizeLadrilho * maxTelaColunas; // 768 pixels
    final int telaHeight = sizeLadrilho * maxTelaLinhas; // 576 pixels


    public GUI(){
        this.setPreferredSize(new Dimension(telaWidth,telaHeight));
        frame = new JFrame(("Game 01"));


        // se der bug retire
        frame.setBackground(Color.BLACK);

    }



    @Override
    public void run() {
    }
}
