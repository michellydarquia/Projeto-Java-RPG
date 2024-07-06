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

//        iniciarBatalhaGoblinArvores();

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

        System.out.println(" cordenadas x: "+ x );
        System.out.println(" cordenadas y: "+ y );

//        gp.blocoM.verificarTransicaoMapa(x,y);
//        verificarTransicaoMapa();
        gp.blocoM.verificarBordasMapa();

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

    private void definirPeculiaridadesMapa() {
        if (gp.blocoM.getMapaAtual().equals("/maps/map01.txt")) {
            peculiaridadesMapa01();
        } else if (gp.blocoM.getMapaAtual().equals("/maps/map02.txt")) {
//            peculiaridadesMapa02();
        } else if (gp.blocoM.getMapaAtual().equals("/maps/map03.txt")) {
//            peculiaridadesMapa03();
        }
    }

    private void peculiaridadesMapa01() {
        int jogadorX = Math.floorDiv(x, gp.sizeLadrilho);
        int jogadorY = Math.floorDiv(y, gp.sizeLadrilho);
        if (jogadorX < 0 || jogadorX > 14) { // borda esqueda e direita
            if (x > 16) {
                x = 16;
            } else {
                x = 1;
            }

        } else if (y < 0 || y > 500) { // borda cima e baixo
            if (y > 500) {
                y = 500;
            } else {
                y = 0;
            }
        }
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

    public void verificarTransicaoMapa() {
        // Verifica se o jogador está na área específica para o mapa 1
        if(gp.blocoM.getMapaAtual().equals("/maps/map01.txt") ){
        int p = 0;
        int o = 0;
        if (y < -44) {
            System.out.println("CAMINHO PARA MAPA 02");
            p = 336;
            o = -36;
        }
        Point coordenadas = new Point(p, o);

        // Verifica se o jogador atingiu a área de transição para o mapa 3
        if (gp.blocoM.pontosTransicao.containsKey(coordenadas)) {
            System.out.println("ENTREI NA TRANSIÇÃO");
            gp.blocoM.switchMap("/maps/map03.txt");
            x = 324;
            y = -8;
        }
        }


    }

    public void verificarBordasMapa() {

        boolean colisaobordas = true;

        if(x >= 324 && x <= 356 && y < 0 ){
            System.out.println("CAMINHO PARA MAPA 02");
            colisaobordas = false;
        }
        if(y >= 252 && y <= 284 && x >= 704 ){
            System.out.println("CAMINHO PARA MAPA 03");
            colisaobordas = false;
        }

        if(colisaobordas) {
            if (x < 20 || x > 700) { // borda esqueda e direita
                if (x > 700) {
                    x = 700;
                } else {
                    x = 20;
                }

            } else if (y < 0 || y > 500) { // borda cima e baixo
                if (y > 500) {
                    y = 500;
                } else {
                    y = 0;
                }
            }
        }


    }

}




