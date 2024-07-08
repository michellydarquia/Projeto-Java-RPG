package Menu;

import game.personagens.jogador.Personagem;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Druida;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MenuClasses {

    GamePanel gp;
    Graphics2D g2;
    public int comandomenu =0;
    public BufferedImage background;
    Personagem jogador ;

    public MenuClasses(GamePanel gp) {
        this.gp = gp;
        getBlackGroundImage();

    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawBackGround(g2);
        drawTituloMenu(g2);

    }


    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/medium.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawTituloMenu(Graphics2D g2) {

        Font fonte = gp.OldLondon.deriveFont((float) 50);
        g2.setFont(fonte);
        g2.setColor(Color.black);

        String texto = "escolha a classe do personagem";
        int x = getXdoCentroTexto(texto, g2);
        int y = gp.sizeLadrilho * 3;
        g2.drawString(texto, x, y + 60);

        // Reduzindo o tamanho da fonte para as opções do menu
        Font font = gp.OldLondon.deriveFont((float) 40);
        g2.setFont(font);

        // Ajustando a posição das opções do menu
        String texto2 = "arqueiro";
        int x2 = getXdoCentroTexto(texto2, g2);
        g2.drawString(texto2, x2, y + 140);
        if (comandomenu==0){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+140);
        }

        String texto3 = "druida";
        int x3 = getXdoCentroTexto(texto3, g2);
        g2.drawString(texto3, x3, y + 180);
        if (comandomenu==1){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+180);
        }

        String texto4 = "guerreiro";
        int x4 = getXdoCentroTexto(texto4, g2);
        g2.drawString(texto4, x4, y + 220);
        if (comandomenu==2){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+220);
        }

        String texto5 = "mago";
        int x5 = getXdoCentroTexto(texto5, g2);
        g2.drawString(texto5, x5, y + 260);
        if (comandomenu==3){
            g2.drawString(">", x2 - gp.sizeLadrilho,y+260);
        }

    }
    public void drawBackGround(Graphics g2) {
        if (background  != null) {
            g2.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        }
    }
    public int getXdoCentroTexto(String txt, Graphics2D g2) {
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        int length = metrics.stringWidth(txt);
        return (gp.telaWidth - length) / 2;
    }


    public void navigateMenuClasses(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            comandomenu--;
            if (comandomenu < 0) {
                comandomenu = 3;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            comandomenu++;
            if (comandomenu > 3) {
                comandomenu = 0;
            }
        }if (code == KeyEvent.VK_ENTER) {
            switch (comandomenu) {
                case 0: // ARQUEIRO
                    jogador  = new Arqueiro();
                    break;
                case 1: // DRUIDA
                    jogador  = new Druida();
                    break;
                case 2: // GUERREIRO
                    jogador = new Guerreiro();
                    break;
                case 3: // MAGO
                    jogador = new Mago();
                    break;
            }
            gp.play.jogador.setClassePersonagem(jogador);
            gp.gameState = gp.statePlay;
        }

    }

}
