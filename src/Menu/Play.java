package Menu;

import game.configs.Utils;
import game.inventorio.Inventario;
import game.map.MapManager;
import game.personagens.jogador.Jogador;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Play {

    GamePanel gp;
    KeyHandler keyH;
    Graphics2D g2;
    Utils utils;

    public Jogador jogador;
    public MapManager mapa;

    public Batalha menuBatalha;
    public int statePlay = 0;
    public final int stateJogando = 1;
    public final int stateMenuBatalha = 2;
    public final int stateSubMenuBatalha = 3;
    public boolean tocandoMusica = true;
    public BufferedImage backgroundPause;
    public String music1 = "C:\\Users\\miche\\OneDrive\\Documentos\\FACUL_COMP\\GITHUB\\Projeto-Java-RPG\\res\\musica\\Medieval_Jogo.wav";
    public static Clip clip;
    public int comandomenu = 0;

    public Play(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        jogador = new Jogador(gp, keyH);
        mapa = new MapManager(gp);
        statePlay = stateJogando;
        utils = new Utils(gp);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (statePlay == stateJogando) {
            musicaPlay(music1);
            mapa.draw(g2);
            jogador.draw(g2);
            drawInventario();

        }
        if (statePlay == stateMenuBatalha || statePlay == stateSubMenuBatalha) {
            if (menuBatalha != null) {
                System.out.println("DESENHANDO MENUBATALHA");
                menuBatalha.draw(g2);
            }
            if (menuBatalha == null) {
                System.out.println("nulo ");
                menuBatalha.draw(g2);
            }
        }

    }


    public void drawInventario() {
        if (jogador.getInventario().inventarioAberto) {
            utils.drawRetanguloTranslucidoComBordas(g2, 70, 400, 576, 70);
        }
    }

    // -----------------------------------------------MUSICA ------------------------------------------------------------
    public void musicaPlay(String soundFile) {
        if (tocandoMusica) {
            try {
                File file = new File(soundFile);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                tocandoMusica = false;

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void musicaStop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

    }

    public void verificarmusica() {
        if (gp.play.clip.isRunning()) {
            gp.play.musicaStop();
            gp.play.setTocandoMusica(true);

        }
    }

    public void setTocandoMusica(boolean tocandoMusica) {
        this.tocandoMusica = tocandoMusica;
    }

// -----------------------------------------------------------------------------------------------------------


    public void getBlackGroundImagePause() {
        try {
            backgroundPause = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/square2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMenuPause(Graphics2D g2) {

        int x = 100;
        int y = gp.sizeLadrilho * 3;
        ;
        getBlackGroundImagePause();

        g2.setColor(Color.darkGray);

        Font fonte = gp.monogramExtended.deriveFont((float) 30);
        g2.setFont(fonte);


        String[] instrucoes = {
                "pausado",
                "retornar ao jogo",
                "retornar ao menu"
        };

        if (backgroundPause != null) {
            g2.drawImage(backgroundPause, 0, 0, backgroundPause.getWidth(), backgroundPause.getHeight(), null);
        }

        for (int i = 0; i < instrucoes.length; i++) {
            String opcao = instrucoes[i];
            if (i == 0) {
                fonte = gp.OldLondon.deriveFont((float) 50);
                y = gp.sizeLadrilho * 3 + 100;
            } else {
                y = gp.sizeLadrilho * 3;
                fonte = gp.monogramExtended.deriveFont((float) 35);


            }
            g2.setFont(fonte);

            int opcaoX = gp.menu.getXdoCentroTexto(opcao, g2) - 30;
            int opcaoY = y + (i == 0 ? 0 : (160 + (i - 1) * 40));

            g2.drawString(opcao, opcaoX, opcaoY);

        }




    }


    public void navigateMenuPause(int code) {

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
                case 0: // VOLTAR AO JOGO
                    gp.gameState = gp.statePlay;
                    break;
                case 1: // sair do jogo e ir AO MENU
                    gp.gameState = gp.stateMenuInicial;
                    break;

            }
        }

    }


}
