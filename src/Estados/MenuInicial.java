package Estados;

import game.configs.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MenuInicial {

    GamePanel gp;
    Graphics2D g2;
    Utils utils;
    public int comandomenu = 0;
    public BufferedImage background;
    public BufferedImage backgroundInstru;


    public MenuInicial(GamePanel gp) {
        this.gp = gp;
        this.utils = new Utils();
        getBlackGroundImage();

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawBack(g2);
        drawTituloMenu(g2);

    }

    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/square.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTituloMenu(Graphics2D g2) {
        String[] opcoes = {"A Ameaça dos Goblins", "jogar", "instruções", "sair"};
        int y =0 ;  ;
        g2.setColor(Color.black);

        for (int i = 0; i < opcoes.length; i++) {
            String opcao = opcoes[i];
            Font fonte;
            if (i == 0) {
                fonte = utils.OldLondon.deriveFont((float) 25);
                y =  gp.sizeLadrilho * 3 + 100;
            } else {
                y = gp.sizeLadrilho * 3;
                fonte = utils.OldLondon.deriveFont((float) 15);
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

    public void drawInstruções(Graphics g2){
        int x = 100 ;
        int y = gp.sizeLadrilho *3 ;
        getBlackGroundImageInstrucoes();

        g2.setColor(Color.darkGray);


        String[] instrucoes = {
                "Tecla U = Parar música",
                "Tecla I = Abrir inventário",
                "Tecla M = Abrir Missoes",
                "Tecla P = Pausa",

                "Teclas de Seta = Navegar menus",
                "Enter = Selecionar opção",
                "  ",
                "Batalha em Turnos:",
                "- Setas Laterais = Selecionar ação",
                "- Enter = Confirmar ação",
                "  ",
                "Exploração:",
                "- Setas = Mover personagem",
                "- Espaço = Interagir",

        };

        if (backgroundInstru  != null) {
            g2.drawImage(backgroundInstru, 0, 0, backgroundInstru.getWidth(), backgroundInstru.getHeight(), null);
        }
        Font fonte = utils.monogramExtended.deriveFont((float) 5);
        g2.setFont(fonte);
        g2.drawString("aperte enter para voltar", x + 190 ,y + 330);

        Font fonte2 = utils.monogramExtended.deriveFont((float) 10);
        g2.setFont(fonte2);
        for (int i = 0; i < instrucoes.length; i++) {
            g2.drawString(instrucoes[i], x, y);
            y+= 25;

        }


    }

    public void getBlackGroundImageInstrucoes() {
        try {
            backgroundInstru = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/square.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    gp.gameState = gp.stateHistoria;
                    break;
                case 1: // Instrucoes
                    gp.gameState = gp.stateInstrucoes;
                    break;
                case 2: // Quit
                    System.exit(0);
                    break;
            }
        }

    }

    public void navigateMenuInstrucao(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.stateMenuInicial;
        }
    }
}

















