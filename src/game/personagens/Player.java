package game.personagens;

import Menu.GamePanel;
import game.personagens.classes.Arqueiro;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player  {

    public int speed;
    public int y;
    public int x;
    public BufferedImage up1, up2, down1, down2 ,left1, left2, rigth1, rigth2;
    public String direcao;
    public int spriteCounter = 0;
    public int spriteNum = 0;



    GamePanel gp;
    KeyHandler keyH;
    BasePersonagem classePersonagem;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        if(classePersonagem == null){
            System.out.println("SEM INSTANCIA DO PERSONAGEM");
        }else {
            System.out.println("COM INSTANCIA DO PERSONAGEM");
        }

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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_costa1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_costa2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_frente3.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_frente2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_left2.png"));
            rigth1 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_right1.png"));
            rigth2 = ImageIO.read(getClass().getResourceAsStream("/player/guerreiro_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizar() {

        if(keyH.upPressed == true || keyH.downPressed  == true || keyH.leftPressed  == true || keyH.rightPressed == true ){
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

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics g2) {
        BufferedImage image = null;

        switch (direcao) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = rigth1;
                } else if (spriteNum == 2) {
                    image = rigth2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.sizeLadrilho, gp.sizeLadrilho, null);
    }


    public BasePersonagem getClassePersonagem() {
        return classePersonagem;
    }

    public void setClassePersonagem(BasePersonagem classePersonagem) {
        this.classePersonagem = classePersonagem;
        if(classePersonagem == null){
            System.out.println("SEM INSTANCIA DO PERSONAGEM");
        }else {
            System.out.println("COM INSTANCIA DO PERSONAGEM");
        }

    }



}




