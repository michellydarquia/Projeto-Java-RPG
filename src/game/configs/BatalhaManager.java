//package game.configs;
//
//import Menu.MenuBatalha;
//import game.personagens.jogador.Personagem;
//
//import java.awt.*;
//
//public class BatalhaManager {
//
//    MenuBatalha menuBatalha;
//    Personagem jogador;
//    Personagem inimigo;
//    public boolean turnoDojogadorVez;
//    private boolean batalhaTerminada;
//    Graphics2D g2;
//
//    public BatalhaManager(Graphics2D g2, MenuBatalha menuBatalha, Personagem jogador, Personagem inimigo) {
//        this.g2 = g2;
//        this.menuBatalha = menuBatalha;
//        this.jogador = jogador;
//        this.inimigo = inimigo;
//        turnoDojogadorVez = true;
//        batalhaTerminada = false;
//    }
//
//    public void batalhaturno() {
//        if (isBatalhaAcabou()) {
//            encerrarBatalha();
//            return;
//        }
//
//        if (turnoDojogadorVez) {
//            // Mostrar o menu de batalha para o jogador
//
//            turnoJogador();
//            System.out.println("NO TURNO DO JOGADOR");
//            turnoDojogadorVez = false;
//
//        } else {
//
//            turnoInimigo();
////            menuBatalha.stateBatalha = menuBatalha.stateBatalhaInfoInimigo;
////            menuBatalha.drawInfoBatalha(g2, inimigo);  // Mostra as informações do inimigo
//            System.out.println("NO TURNO DO INIMIGO");
//            turnoDojogadorVez = true;
//        }
//
//        // Atualiza o turno
////        turnoDojogadorVez = !turnoDojogadorVez;
//        System.out.println("FINAL DO TURNO");
//    }
//
//    public void turnoJogador() {
//        System.out.println("Jogador Jogando");
//
//        if (jogador.isUsouHabilidade()) {
//            jogador.setUsouHabilidade(false); // Resete a flag de habilidade usada
//            // O turno vai avançar para o inimigo após a habilidade ser usada
//            turnoDojogadorVez = false;
//        } else {
//            // O jogador está no menu e deve usar uma habilidade
//            turnoDojogadorVez = false;
////            menuBatalha.stateBatalha = menuBatalha.stateBatalhaMenuP;
//
//        }
//    }
//
//    public void turnoInimigo() {
//
//        int acaoInimigo = (int) (Math.random() * 3); // Gera um número entre 0 e 2
//        switch (acaoInimigo) {
//            case 0:
//                inimigo.usarHabilidade1(jogador);
//                break;
//            case 1:
//                inimigo.usarHabilidade2();
//                break;
//            case 2:
//                inimigo.usarHabilidade3();
//                break;
//        }
//        turnoDojogadorVez = true;
//        menuBatalha.stateBatalha = menuBatalha.stateBatalhaInfoInimigo;
//
//        // Atualiza o estado para mostrar a mensagem do inimigo
//    }
//
//    public boolean isBatalhaAcabou() {
//        return !jogador.isVivo() || !inimigo.isVivo();
//    }
//
//    public void encerrarBatalha() {
//        menuBatalha.gp.gameState = menuBatalha.gp.statePlay;
//        System.out.println("Batalha Encerrada!");
//    }
//
//    public Personagem getVencedor() {
//        if (!jogador.isVivo()) {
//            return inimigo;
//        }
//        if (!inimigo.isVivo()) {
//            return jogador;
//        }
//        return null;
//    }
//}