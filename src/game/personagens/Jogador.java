package game.personagens;

import Menu.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Jogador {

    public int speed;
    public int y;
    public int x;
    public BufferedImage up1, up2, down1, down2 ,left1, left2, rigth1, rigth2, principal;
    public String direcao;
    public int spriteCounter = 0;
    public int spriteNum = 0;

    GamePanel gp;
    KeyHandler keyH;
    Personagem classePersonagem;


    public Jogador(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direcao = "down";
        spriteNum = 1;
        spriteCounter = 0;
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_costa1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_costa2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_frente3.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_frente2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_left2.png")));
            rigth1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_right1.png")));
            rigth2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_right2.png")));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizar() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            moverJogador();
            //MUDANDO A IMAGEM A CADA MOVIMENTO
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum = spriteNum == 1 ? 2 : 1;
                spriteCounter = 0;
            }
        }

    }

    public void moverJogador(){
        if (keyH.upPressed) {
            direcao = "up";
            y -= speed;
        } else if (keyH.downPressed) {
            direcao = "down";
            y += speed;
        } else if (keyH.leftPressed) {
            direcao = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direcao = "right";
            x += speed;
        }

    }


    public void draw(Graphics g2) {
        BufferedImage image = null;

        switch (direcao) {
            case "up":
                image = spriteNum == 1 ?  up1 : up2;
                break;
            case "down":
                image = spriteNum == 1 ?  down1 : down2;
                break;
            case "left":
                image = spriteNum == 1 ?  left1 : left2;
                break;
            case "right":
                image = spriteNum == 1 ?  rigth1  : rigth2 ;
                break;
        }

        g2.drawImage(image, x, y, gp.sizeLadrilho, gp.sizeLadrilho, null);
    }


    public Personagem getClassePersonagem() {
        return classePersonagem;
    }

    public void setClassePersonagem(Personagem classePersonagem) {

        if(classePersonagem == null){
            throw new IllegalArgumentException("SEM INSTANCIA DO PERSONAGEM");
        }
        this.classePersonagem = classePersonagem;

    }


}




