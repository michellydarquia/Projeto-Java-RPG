package game.personagens.jogador;

import Estados.GamePanel;
import Estados.KeyHandler;
import game.configs.MissoesManager;
import game.inventorio.Inventario;
import game.inventorio.Item;
import game.inventorio.itens.*;
import game.personagens.Personagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Jogador {

    private int speed;
    public int y;
    public int x;
    private BufferedImage up1, up2, down1, down2 ,left1, left2, rigth1, rigth2, principal;
    private String direcao;
    private int spriteCounter = 0;
    private int spriteNum = 0;

    private Inventario inventario;
    private MissoesManager missoesManager;
    GamePanel gp;
    KeyHandler keyH;
    Personagem classePersonagem;


    public Jogador(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

        inventario = new Inventario(this);
        this.missoesManager = new MissoesManager(this);

        Item tonico = new Tonico();
        Item peitoral = new Peitoral();

        getInventario().adicionarItem(peitoral);
        getInventario().adicionarItem(tonico);

        int quantidade = inventario.procurarItens(Artefato.class);
        System.out.println(quantidade);

    }

    public Inventario getInventario() {
        return inventario;
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

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_left2.png")));
            rigth1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_rigth1.png")));
            rigth2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_rigth2.png")));



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

//        System.out.printf(" cordenadas x: %s - cordenadas y: %s ", x,y );


        gp.play.mapa.verificarColisaoNpc();
        gp.play.mapa.verificarColisaoInimigo();
        gp.play.mapa.verificarBordasMapa();
//        gp.play.mapa.verificarColisaoInimigo();


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

    public MissoesManager getMissoesManager() {
        return missoesManager;
    }




}




