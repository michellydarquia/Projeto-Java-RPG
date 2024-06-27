package game.personagens;

import gui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) { //NÃO USAMOS
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // RETORNA UM NUMERO A CADA TECLA PRESSIONADA, PESQUISAR EXAMPLES OF KEYCODE PARA SABER MAIS


        if(gp.gameState == gp.menuinicialState) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.uiM.comandomenu--;
                if (gp.uiM.comandomenu == -1 ) {
                    gp.uiM.comandomenu = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.uiM.comandomenu++;
                if (gp.uiM.comandomenu == 3 ) {
                gp.uiM.comandomenu = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                int escolha = gp.uiM.comandomenu;

                if (escolha == 0 ) { //JOGAR

                    //IMPLEMENTAR MINI MENU DE ESCOLHA DA CLASSE DO PERSONAGEM

                    gp.gameState = gp.playState;

                }if (escolha == 1 ) { // CARREGAR O MESMO JOGO
                    gp.gameState = gp.playState;


                }if (escolha == 2 ) { // QUIT
                    System.exit(0);

                }

            }

        }

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN ){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT ){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D ||code == KeyEvent.VK_RIGHT ){
            rightPressed = true;
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
        }



    }
}
