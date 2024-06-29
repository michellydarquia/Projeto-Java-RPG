package Menu;


import game.personagens.BasePersonagem;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuBattleScreen {

    GamePanel gp;
    Graphics2D g2;
    public int comandomenu =0;
    public BufferedImage background;
    BasePersonagem jogador;



    public MenuBattleScreen(GamePanel gp, BasePersonagem jogador) {
        this.gp = gp;
        this.jogador = jogador;
        getBlackGroundImage();

    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawBack(g2);
        drawTituloMenu(g2);

    }


    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Floresta.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTituloMenu(Graphics2D g2) {
        Font menuFont = new Font("Arial", Font.PLAIN, 20);
        g2.setFont(menuFont);
        g2.setColor(Color.black);

        // Ajustando a posição das opções do menu
        String[] opcoes = {"Habilidade 1", "Habilidade 2", "Habilidade 3", "Desistir"};
        int[][] posicoes = {
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 1
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 2
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho},       // Habilidade 3
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho}        // Desistir
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcaoX = posicoes[i][0];
            int opcaoY = posicoes[i][1];
            String opcao = opcoes[i];
            int opcaoXCentro = opcaoX + (gp.sizeLadrilho / 2) - (g2.getFontMetrics().stringWidth(opcao) / 2);
            g2.drawString(opcao, opcaoXCentro, opcaoY);

            if (comandomenu == i) {
                int simboloX = opcaoX - gp.sizeLadrilho / 2 - g2.getFontMetrics().stringWidth(">") - 20;
                g2.drawString(" > ", simboloX, opcaoY);
            }
        }
    }

    public void drawBack(Graphics g2) {
        if (background  != null) {
            g2.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        }
    }

    public void drawPlayreInimigo(Graphics g2) {
        if (background  != null) {
            g2.drawImage(background, gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2, background.getWidth(), background.getHeight(), null);
        }
    }


    public void navigateMenuBatalha(int code) {


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
        }

        if (code == KeyEvent.VK_ENTER) {
            switch (gp.menuBatalha.comandomenu) {
                case 0: // HABILIDADE 1
                        jogador.usarHabilidade1();
                    break;
                case 1: // HABILIDADE 2
                        jogador.usarHabilidade2();
                    break;
                case 2: // HABILIDADE 3
                        jogador.usarHabilidade3();
                    break;
                case 3: // DESISTIR // Lógica para finalizar a batalha ou sair do menu
                    System.out.println("Você desistiu da batalha.");
                    break;
            }


        }

    }

}




