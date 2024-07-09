package Menu;

import Exceptions.ExceptionBatalha;
import game.inventorio.Inventario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    Play play;
    public boolean upPressed, downPressed, leftPressed, rightPressed, inventarioAberto, pPressed;;

    public KeyHandler(GamePanel gp){
        this.gp = gp;

        play = gp.play;

    }

    @Override
    public void keyTyped(KeyEvent e) { //NÃO USAMOS
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // RETORNA UM NUMERO A CADA TECLA PRESSIONADA, PESQUISAR EXAMPLES OF KEYCODE PARA SABER MAIS


        // Conferindo qual o estado do jogo para pode trazer as configuraçoes especificas do teclado de cada menu
        if (gp.gameState == gp.stateMenuInicial) {
            gp.menu.navigateMenu1(code);

        } else if (gp.gameState == gp.stateMenuclasses) {
            gp.menuClass.navigateMenuClasses(code);

        } else if (gp.play.statePlay == gp.play.stateMenuBatalha) {
            if(gp.play.menuBatalha.turnoDojogadorVez) {
                gp.play.menuBatalha.navigateMenuBatalha(code);
            }else {
                System.out.println("DENTRO DO ELSE DO KEYHANDLER");
            }

        } else if (gp.play.statePlay == gp.play.stateSubMenuBatalha) {
            if (gp.play.menuBatalha.turnoDojogadorVez) {
                gp.play.menuBatalha.navigateSubMenuBatalha(code);
            }
        }
        else if (gp.gameState == gp.stateInstrucoes) {
                System.out.println("INSTR");
                gp.menu.navigateMenuInstrucao(code);

        } else if (gp.gameState == gp.statePause) {
            System.out.println("PAUSE");
            gp.play.navigateMenuPause(code);
//            gp.gameState = gp.statePause;




        } else if (gp.gameState == gp.statePlay) {

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN ){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT ){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D ||code == KeyEvent.VK_RIGHT ) {
                rightPressed = true;

            } if(code == KeyEvent.VK_I ){

                System.out.println(gp.play.jogador.getInventario().getItens());

                gp.play.jogador.getInventario().inventarioAberto = !gp.play.jogador.getInventario().inventarioAberto ;

            }if(code == KeyEvent.VK_P ){
                gp.gameState = gp.statePause;

            }if (code == KeyEvent.VK_U){

                if(Play.clip.isRunning()){
                    System.out.println("Musica DESATIVADA");
                    gp.play.musicaStop();
                }else {
                    gp.play.setTocandoMusica(true);
                    System.out.println("Musica ATIVADA");
                    gp.play.musicaPlay(gp.play.music1);
                }

            }

        }else {
            throw new ExceptionBatalha("SEM CONFIGURAÇÃO PARA O KEYHANDLER");
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();



        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP ){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT ){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D ||code == KeyEvent.VK_RIGHT ){
            rightPressed = false;
        }if(code == KeyEvent.VK_I ){
            gp.play.jogador.getInventario().inventarioAberto = false;
        }

    }


}
