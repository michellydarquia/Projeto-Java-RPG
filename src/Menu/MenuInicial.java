package Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MenuInicial {

    GamePanel gp;
    Graphics2D g2;
    public int comandomenu = 0;
    public BufferedImage background;


    public MenuInicial(GamePanel gp) {
        this.gp = gp;
        getBlackGroundImage();

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawBack(g2);
        drawTituloMenu(g2);

    }


    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Background.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTituloMenu(Graphics2D g2) {
        String[] opcoes = {"A Ameaça dos Goblins", "jogar", "instruções", "sair"};
        int y = gp.sizeLadrilho * 3;
        g2.setColor(Color.white);

        for (int i = 0; i < opcoes.length; i++) {
            String opcao = opcoes[i];
            // Ajuste o tamanho da fonte baseado na posição do texto no menu
            Font fonte;
            if (i == 0) {
                fonte = gp.OldLondon.deriveFont((float) 70);
            } else {
                fonte = gp.OldLondon.deriveFont((float) 40);


            }
            g2.setFont(fonte);

            int opcaoX = getXdoCentroTexto(opcao, g2);
            int opcaoY = y + (i == 0 ? 0 : (180 + (i - 1) * 40));

            g2.drawString(opcao, opcaoX, opcaoY);
        }

        int x2 = getXdoCentroTexto(opcoes[comandomenu + 1], g2) - gp.sizeLadrilho;
        int[] ys = {y + 180, y + 220, y + 260};
        g2.drawString(">", x2 - 30, ys[comandomenu]);
    }




    public void drawBack(Graphics g2) {
        if (background  != null) {
            g2.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        }
    }
    public int getXdoCentroTexto(String txt, Graphics2D g2) {
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        int length = metrics.stringWidth(txt);
        return (gp.telaWidth - length) / 2;
    }



    public void navigateMenu1(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            comandomenu--;
            if (comandomenu < 0) {
                comandomenu = 2;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            comandomenu++;
            if (comandomenu > 2) {
                comandomenu = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            switch (comandomenu) {
                case 0: // Jogar
                    gp.gameState = gp.stateMenuclasses;
                    break;
                case 1: // Carregar o mesmo jogo
                    gp.play.statePlay = gp.play.stateMenuBatalha;
                    break;
                case 2: // Quit
                    System.exit(0);
                    break;
            }
        }

    }
}

















