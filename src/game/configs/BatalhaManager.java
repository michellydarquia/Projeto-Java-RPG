package game.configs;

// CONFIGURAÇÃO DO TURNO DA BATALHA

import Menu.MenuBatalha;
import game.personagens.Personagem;

import java.awt.*;

public class BatalhaManager {

    Personagem jogador;
    Personagem inimigo;
    private boolean turnoDojogadorVez;
    MenuBatalha menuBatalha;
    Graphics2D g2;


    public BatalhaManager(Graphics2D g2,Personagem jogador, Personagem inimigo){
        this.g2 = g2;
        this.jogador = jogador;
        this.inimigo = inimigo;
        turnoDojogadorVez = true;

    }

    public void batalhaturno(){

        if (turnoDojogadorVez){
            turnoJogador();
        }else {
            turnoInimigo();
        }

    turnoDojogadorVez = false;

    }


    public void turnoJogador(){
        menuBatalha.drawInfoBatalha(g2,jogador);


    }

    public void turnoInimigo(){
        inimigo.usarHabilidade1(jogador);
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

