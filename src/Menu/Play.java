package Menu;

import game.configs.Utils;
import game.inventorio.Inventario;
import game.map.MapManager;
import game.personagens.jogador.Jogador;

import java.awt.*;

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

    public Play(GamePanel gp, KeyHandler keyH){

        this.gp= gp;
        this.keyH= keyH;
        jogador = new Jogador(gp, keyH);
        mapa = new MapManager(gp);
        statePlay = stateJogando;
        utils = new Utils(gp);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(statePlay== stateJogando){
            mapa.draw(g2);
            jogador.draw(g2);
            drawInventario();

        }
        if(statePlay == stateMenuBatalha || statePlay == stateSubMenuBatalha ){
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

}
