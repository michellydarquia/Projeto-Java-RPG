package Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Menu {

    GamePanel gp;
    Graphics2D g2;
    public int comandomenu =0;
    public BufferedImage background;


    public Menu(GamePanel gp) {
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

        // Definindo o tamanho da fonte
        Font font = new Font("Arial", Font.BOLD, 48); // Alterar "48" para o tamanho desejado
        g2.setFont(font);

        String texto = "A Conspiraçao na Corte Real ";
        int x = getXdoCentroTexto(texto, g2);
        int y = gp.sizeLadrilho * 3;
        g2.setColor(Color.white);
        g2.drawString(texto, x, y);

        // Reduzindo o tamanho da fonte para as opções do menu
        Font menuFont = new Font("Arial", Font.PLAIN, 36); // alterar "36" para o tamanho desejado
        g2.setFont(menuFont);

        // Ajustando a posição das opções do menu
        String texto2 = "Jogar";
        int x2 = getXdoCentroTexto(texto2, g2);
        g2.drawString(texto2, x2, y + 180);
        if (comandomenu==0){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+180);
        }

        String texto3 = "Sair";
        int x3 = getXdoCentroTexto(texto3, g2);
        g2.drawString(texto3, x3, y + 220);
        if (comandomenu==1){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+220);
        }

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
                comandomenu = 1;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            comandomenu++;
            if (comandomenu > 1) {
                comandomenu = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            switch (comandomenu) {
                case 0: // Jogar
                    gp.gameState = gp.stateMenuclasses;
                    break;
                case 1: // Carregar o mesmo jogo
                    gp.gameState = gp.stateMenuBatalha;
                    break;
                case 2: // Quit
                    System.exit(0);
                    break;
            }
        }

    }
}

















