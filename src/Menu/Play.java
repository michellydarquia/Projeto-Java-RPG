package Menu;

import game.configs.Utils;
import game.inventorio.Inventario;
import game.map.MapManager;
import game.personagens.jogador.Jogador;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Play {

    GamePanel gp;
    KeyHandler keyH ;
    Graphics2D g2;
    Utils utils;

    public Jogador jogador;
    public MapManager mapa;

    public Batalha menuBatalha;
    public  int statePlay = 0;
    public final int stateJogando = 1;
    public final int stateMenuBatalha = 2;
    public final int stateSubMenuBatalha = 3;
    public boolean tocandoMusica = true;

    public void setTocandoMusica(boolean tocandoMusica) {
        this.tocandoMusica = tocandoMusica;
    }

    public String music1 = "C:\\Users\\miche\\OneDrive\\Documentos\\FACUL_COMP\\GITHUB\\Projeto-Java-RPG\\res\\musica\\Medieval_Jogo.wav";

    public static Clip clip; // Variável para armazenar o clip de áudio


    public Play(GamePanel gp, KeyHandler keyH){

        this.gp= gp;
        this.keyH= keyH;
        jogador = new Jogador(gp, keyH);
        mapa = new MapManager(gp);
        statePlay = stateJogando;
        utils = new Utils(gp);
    }

    public void draw(Graphics2D g2)  {
        this.g2 = g2;

        if(statePlay== stateJogando){
            musicaPlay(music1);
            mapa.draw(g2);
            jogador.draw(g2);
            drawInventario();

        }
        if(statePlay == stateMenuBatalha || statePlay == stateSubMenuBatalha ){
//            musicaStop();
            if(menuBatalha != null) {
                System.out.println("DESENHANDO MENUBATALHA");
                menuBatalha.draw(g2);
            }
            if(menuBatalha == null) {
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


    public void musicaPlay(String soundFile) {
        if(tocandoMusica) {
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


    public void musicaStop(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

    }

    public void verificarmusica(){
        if(gp.play.clip.isRunning()){
            gp.play.musicaStop();
            gp.play.setTocandoMusica(true);

        }
    }


}
