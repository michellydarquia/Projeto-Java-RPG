package Menu;


import Exceptions.ExceptionBatalha;
import game.configs.BatalhaManager;
import game.personagens.Personagem;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuBatalha {

    public GamePanel gp;
    Graphics2D g2;
    public int comandomenu =0;
    public BufferedImage background;
    Personagem jogador;
    Personagem inimigo;
    BatalhaManager batalhaManager;




    public MenuBatalha(GamePanel gp, Personagem jogador, Personagem inimigo) {
        this.gp = gp;
        this.jogador = jogador;
        this.inimigo = inimigo;
        getBlackGroundImage();
        getImagemPersonagem();
        inimigo.getPlayerImage();
        drawMensagem(g2, jogador);
        drawMensagem(g2, inimigo);
    }

    public void draw(Graphics2D g2) {

        if (jogador == null || inimigo == null) {
            throw new ExceptionBatalha("INIMIGO ou JOGADOR NULL");
        }else {

            this.g2 = g2;
            int y = gp.sizeLadrilho;

            drawBackGround(g2);
            drawPlayerAtributos(g2, jogador, 0);
            drawPlayerAtributos(g2, inimigo, 1);

            drawImagemPersonagens(g2, inimigo, jogador);

            drawRetanguloTranslucido(g2, 70, y + 400, 576, y + 70);

            if (batalhaManager == null) {
                batalhaManager = new BatalhaManager(g2, this, jogador, inimigo);
            }

            switch (gp.gameState) {

                case 10: // gp.stateinicioBatalha:

                    batalhaManager.batalhaturno();
                    break;

                case 6: // gp.stateMenuBatalha:

                    drawTituloMenu(g2);

                    break;

                case 7: // gp.stateSubMenuBatalha:
                    if (jogador.getHabilidadeUsada() == null || inimigo.getHabilidadeUsada() == null) {
                        jogador.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
                        inimigo.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
                    }

                    drawTituloSubMenu(g2);
                    break;

                case 8: // gp.stateInfoBatalha:

                    drawInfoBatalha(g2, jogador);
                    break;

                case 9: // gp.stateInimigoAcao:
                    drawInfoBatalha(g2, inimigo);
                    break;


//
            }

        }


    }



    public void navigateMenuBatalha(int code) {


        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            comandomenu--;
            if (comandomenu < 0) {
                comandomenu = 3;
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            comandomenu++;
            if (comandomenu > 3) {
                comandomenu = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {

            switch (gp.menuBatalha.comandomenu) {
                case 0: // HABILIDADE 1
                    gp.gameState = gp.stateSubMenuBatalha;
                    break;
                case 1: // HABILIDADE 2
                    jogador.usarHabilidade4();
                    break;
                case 2: // HABILIDADE 3
                    jogador.usarHabilidade3();
                    break;
                case 3: // DESISTIR // Lógica para finalizar a batalha ou sair do menu
                    gp.gameState = gp.statePlay;
                    break;
            }


        }

    }

    public void navigateSubMenuBatalha(int code) {

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            comandomenu--;
            if (comandomenu < 0) {
                comandomenu = 3;
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            comandomenu++;
            if (comandomenu > 3) {
                comandomenu = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            gp.tempoInicial = System.currentTimeMillis();
            switch (gp.menuBatalha.comandomenu) {
                case 0: // HABILIDADE 1

                    jogador.usarHabilidade1(inimigo);


                    gp.gameState = gp.stateInfoBatalha;


                    break;
                case 1: // HABILIDADE 2
                    jogador.usarHabilidade2();

                    gp.gameState = gp.stateInfoBatalha;

                    break;
                case 2: // HABILIDADE 3
                    jogador.usarHabilidade3();

                    gp.gameState = gp.stateInfoBatalha;

                    break;
                case 3: // VOLTAR
                    gp.gameState = gp.stateMenuBatalha;
                    break;
            }

        }
    }


///////////////////////////////////// DRAW E GETS ////////////////////////////////////////////////////////////////////

    public void drawInfoBatalha(Graphics2D g2, Personagem personagem){

        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - gp.tempoInicial ;
        System.out.println( tempoDecorrido);

        int mudarestado = gp.stateInimigoAcao;
        int segundos = 2000;
        if(personagem == inimigo){

            segundos = segundos * 2;
            mudarestado = gp.stateMenuBatalha;

        }

        drawMensagem(g2, personagem);

        if(tempoDecorrido >= segundos ){// ALTERAR ESSE VALOR PARA MAIS NEVAGAR APOS TERMINAR
            gp.gameState = mudarestado;
            inimigo.setUsouHabilidade(false);
        }


    }


    public void inimigoUsandoHabilidade(){

        inimigo.usarHabilidade1(jogador);


    }



    public void drawTituloMenu(Graphics2D g2) {


        Font menuFont = new Font("Arial", Font.BOLD, 20);

        g2.setFont(menuFont);
        g2.setColor(Color.black);

        // Ajustando a posição das opções do menu
        String[] opcoes = {"ATACAR", "ESPECIAL", "USAR ITEM", "DESISTIR"};
        int[][] posicoes = {
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 1
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 2
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho},       // Habilidade 3
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho}        // Desistir
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcaoX = posicoes[i][0];
            int opcaoY = posicoes[i][1];
            String opcao = opcoes[i];
            int opcaoXCentro = opcaoX + (gp.sizeLadrilho / 2) - (g2.getFontMetrics().stringWidth(opcao) / 2);


            int rectX = opcaoXCentro - 5;
            int rectY = opcaoY - g2.getFontMetrics().getHeight() + 5;
            int rectWidth = g2.getFontMetrics().stringWidth(opcao) + 10;
            int rectHeight = g2.getFontMetrics().getHeight();

            g2.setColor(Color.black);
            g2.drawString(opcao, opcaoXCentro, opcaoY);
            g2.drawRect(rectX, rectY, rectWidth, rectHeight);


            if (comandomenu == i) {
                int simboloX = opcaoX - gp.sizeLadrilho / 2 - g2.getFontMetrics().stringWidth(">") - 20;

                g2.drawString(" > ", simboloX, opcaoY);

            }
        }
    }
    public void drawTituloSubMenu(Graphics2D g2) {


        Font menuFont = new Font("Arial", Font.BOLD, 20);

        g2.setFont(menuFont);
        g2.setColor(Color.black);

        // Ajustando a posição das opções do menu
        String[] opcoes = {"ATAQUE 1", "ATAQUE 2", "DEFESA  ", "VOLTAR  "};
        int[][] posicoes = {
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 1
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho * 2},   // Habilidade 2
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho},       // Habilidade 3
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho}        // Desistir
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcaoX = posicoes[i][0];
            int opcaoY = posicoes[i][1];
            String opcao = opcoes[i];
            int opcaoXCentro = opcaoX + (gp.sizeLadrilho / 2) - (g2.getFontMetrics().stringWidth(opcao) / 2);


            int rectX = opcaoXCentro - 5;
            int rectY = opcaoY - g2.getFontMetrics().getHeight() + 5;
            int rectWidth = g2.getFontMetrics().stringWidth(opcao) + 10;
            int rectHeight = g2.getFontMetrics().getHeight();

            g2.setColor(Color.black);
            g2.drawString(opcao, opcaoXCentro, opcaoY);
            g2.drawRect(rectX, rectY, rectWidth, rectHeight);


            if (comandomenu == i) {
                int simboloX = opcaoX - gp.sizeLadrilho / 2 - g2.getFontMetrics().stringWidth(">") - 20;

                g2.drawString(" > ", simboloX, opcaoY);

            }
        }
    }
    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Floresta.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawBackGround(Graphics g2) {
        if (background  != null) {
            g2.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        }
    }
    public void drawMensagem(Graphics2D g2, Personagem personagem) {

        String mensagem = personagem.getHabilidadeUsada();

        if (mensagem != null && !mensagem.isEmpty()) {

            g2.setFont(new Font("Arial", Font.BOLD, 20));

            if (personagem == inimigo){
                g2.setColor(Color.red);
            } else {
                g2.setColor(Color.black);
            }
            int x = 150;
            int y = 490;

            for (String line : mensagem.split("\n")){
                g2.drawString(line, x, y); // Ajuste as coordenadas conforme necessário
                y+= 20;
            }

        }
    }
    public void drawPlayerAtributos(Graphics g2, Personagem jogador, int indice) {

        int x = gp.sizeLadrilho; // 48
        int y = gp.sizeLadrilho;

        Font fm = new Font("Arial", Font.BOLD, 15); // Alterar "48" para o tamanho desejado

        g2.setFont(fm);
        String atributos = jogador.imprimiratributos();

        if(indice == 0) {
            drawRetanguloTranslucido((Graphics2D) g2, x - 40, y - 30, x + 240, y + 120);


        }else {
            drawRetanguloTranslucido((Graphics2D) g2, x + 380, y - 30, x + 240, y + 100);
            x = x + 408;
        }
        g2.setColor(Color.black);
        for (String line : atributos.split("\n")) {
            g2.drawString(line, x, y);
            y += 20;
        }


    }
    public void drawRetanguloTranslucido(Graphics2D g2, int x, int y, int width, int height) {

        // Salva o estado atual do Graphics2D
        Composite originalComposite = g2.getComposite();

        // Define a cor com transparência
        g2.setColor(new Color(255, 255, 255, 191)); // Branco com 75% de transparência (alfa = 191)

        // Define a transparência
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f)); // 75% de transparência

        // Desenha o retângulo preenchido com bordas arredondadas
        g2.fillRoundRect(x, y, width, height, 30, 30);

        // Restaura o estado original do Graphics2D
        g2.setComposite(originalComposite);
    }
    public void getImagemPersonagem(){
        try {
            jogador.setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_frente3.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawImagemPersonagens(Graphics2D g2 , Personagem inimigo , Personagem jogador){

        BufferedImage imageInimigo;
        BufferedImage imageJogador;
        imageInimigo = inimigo.getImagemGrande();
        imageJogador = jogador.getImagemGrande();

        if ( imageInimigo == null || imageJogador == null){
            throw new IllegalArgumentException("IMAGEM NULL");
        }
        g2.drawImage(imageInimigo, 420, 120, imageInimigo.getWidth() *4 , imageInimigo.getHeight() *4, null);

        g2.drawImage(imageJogador, 80, 200, imageJogador.getWidth() * 12 , imageJogador.getHeight() * 12, null);



    }



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}




