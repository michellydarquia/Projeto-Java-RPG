package Estados;


import game.configs.Historia;
import game.configs.Utils;

import game.map.MapManager;
import game.personagens.jogador.Jogador;
import game.personagens.npc.Npc;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Objects;

public class Play {

    GamePanel gp;
    KeyHandler keyH;
    Graphics2D g2;
    Utils utils;
    Historia historia;

    public Jogador jogador;
    public MapManager mapa;

    public Batalha menuBatalha;
    public int statePlay = 0;
    public final int stateJogando = 1;
    public final int stateMenuBatalha = 2;
    public final int stateSubMenuBatalha = 3;
    public final int stateInventario = 4;
    public final int stateDialogo = 5;
    public boolean mostrarMissao ;
    public boolean tocandoMusica = true;
    public BufferedImage backgroundPause;
    public String music1 = "res\\musica\\Medieval_Jogo.wav";
    public static Clip clip;
    public int comandomenu = 0;



    public Play(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.jogador = new Jogador(gp, keyH);
        this.mapa = new MapManager(gp);
        this.utils = new Utils();

        statePlay = stateJogando;
        getBlackGroundImagePause();
    }



    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (statePlay == stateJogando || statePlay == stateInventario) {
            musicaPlay(music1);
            mapa.draw(g2);
            jogador.draw(g2);
            drawInventario();
            if(mostrarMissao){
                jogador.getMissoesManager().drawMissoes(g2);
            }
            if(gp.historia.isDialogoAtivo()){
                gp.historia.draw(g2);
            }

        }


        if (statePlay == stateMenuBatalha || statePlay == stateSubMenuBatalha ) {
            if (menuBatalha != null) {
                menuBatalha.draw(g2);
            }
            if (menuBatalha == null) {
                menuBatalha.draw(g2);
            }
        }

    }


    public void drawInventario() {
        if (jogador.getInventario().isInventarioAberto()) {
            statePlay = stateInventario;
            jogador.getInventario().draw(g2);
        }else {
            statePlay = stateJogando;
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

                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f); // Reduz o volume em 10 decibéis

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
            backgroundPause = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/square2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMenuPause(Graphics2D g2) {

        int x = 100;
        int y = gp.sizeLadrilho * 3;
        ;


        g2.setColor(Color.darkGray);

        Font fonte = utils.monogramExtended.deriveFont((float) 30);
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
                fonte = utils.OldLondon.deriveFont((float) 50);
                y = gp.sizeLadrilho * 3 + 100;
            } else {
                y = gp.sizeLadrilho * 3;
                fonte = utils.monogramExtended.deriveFont((float) 35);


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
