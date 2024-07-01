package game.configs;

// CONFIGURAÇÃO DO TURNO DA BATALHA

import Menu.MenuBatalha;
import game.personagens.Personagem;

import java.awt.*;

public class BatalhaManager {

    MenuBatalha menuBatalha;
    Personagem jogador;
    Personagem inimigo;
    private boolean turnoDojogadorVez;
    private boolean batalhaTerminada;

    Graphics2D g2;



    public BatalhaManager(Graphics2D g2,MenuBatalha menuBatalha, Personagem jogador, Personagem inimigo){
        this.g2 = g2;
        this.menuBatalha = menuBatalha;
        this.jogador = jogador;
        this.inimigo = inimigo;
        turnoDojogadorVez = true;
        batalhaTerminada = false;
        inimigo.getPlayerImage();

    }

    public void batalhaturno(){

        menuBatalha.drawImagemPersonagens(g2,jogador,inimigo);

        if (jogador.getHabilidadeUsada() == null || inimigo.getHabilidadeUsada() == null) {
            jogador.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
            inimigo.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
        }

        if(batalhaTerminada){
            System.out.println("ENCERADAAAAAAA");
            menuBatalha.gp.gameState = menuBatalha.gp.statePlay;
            return;
        }

        if (isBatalhaAcabou()) {
            System.out.println("BATALHA TERMINOU");
            batalhaTerminada = true;
            menuBatalha.gp.gameState = menuBatalha.gp.statePlay; // Saia do estado de batalha
            return;
        }

        if (turnoDojogadorVez){
            System.out.println("JOGADOR JOGANDO");
            turnoJogador();


        }if(!turnoDojogadorVez) {
            System.out.println("INIMIGO JOGANDO");
            turnoInimigo();
        }

    }

    public void turnoJogador(){
        menuBatalha.gp.gameState = menuBatalha.gp.stateMenuBatalha;
        turnoDojogadorVez = false;

    }

    public void turnoInimigo(){
        inimigo.getPlayerImage();
        inimigo.usarHabilidade1(jogador); // O inimigo usa uma habilidade
        menuBatalha.drawInfoBatalha(g2, inimigo);
        turnoDojogadorVez = true;

    }



    public boolean isBatalhaAcabou(){
        return !jogador.isVivo() || !inimigo.isVivo();
    }

    public Personagem getVencedor(){
        if(!jogador.isVivo()) {
            return inimigo;
        }if ((!inimigo.isVivo())){
            return jogador;
        }
        return null;
    }
}

