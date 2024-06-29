package game.personagens;

import Menu.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, inventarioAberto;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
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
            gp.menuClass.navigateMenu2(code);

        }else if (gp.gameState == gp.stateBatalha){
            gp.menuBatalha.navigateMenuBatalha(code);
        }else {

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

            } if(code == KeyEvent.VK_I ){
                gp.gameState = gp.stateBatalha;
            }


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
            inventarioAberto = false;
        }

    }


}
