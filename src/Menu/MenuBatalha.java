package Menu;

import Exceptions.ExceptionBatalha;
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
    public int comandomenu = 0;
    public BufferedImage background;
    Personagem jogador;
    Personagem inimigo;
    public boolean turnoDojogadorVez;
    private boolean batalhaTerminada;

    public final int stateBatalhaInicioBatalha = 0;
    public final int stateBatalhaMenuP = 1;
    public final int stateBatalhaSubMenu = 2;
    public final int stateBatalhaInfoJogador = 3;
    public final int stateBatalhaInfoInimigo = 4;
    public final int stateBatalhaFinalizado = 5;
    public int stateBatalha;

    public long tempoFinalizarBatalha;

    public MenuBatalha(GamePanel gp, Personagem jogador, Personagem inimigo) {
        this.gp = gp;
        this.jogador = jogador;
        this.inimigo = inimigo;
        getBlackGroundImage();
        getImagemPersonagem();
        stateBatalha = stateBatalhaInicioBatalha;
        turnoDojogadorVez = true;
        batalhaTerminada = false;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        int y = gp.sizeLadrilho;
        drawBackGround(g2);
        drawPlayerAtributos1(g2, jogador, 0, true);
        drawPlayerAtributos1(g2, inimigo, 1, false);
        drawImagemPersonagens(g2, inimigo, jogador);
        drawRetanguloTranslucidoComBordas(g2, 70, y + 400, 576, y + 70);

        getVencedor();


        if (isBatalhaAcabou()) {
            System.out.println("ENTRANDO NO FINALIZAR BATALHA 1");
            stateBatalha = stateBatalhaFinalizado;
            tempoFinalizarBatalha = System.currentTimeMillis();
        }

        switch (stateBatalha) {
            case stateBatalhaInicioBatalha:
                batalhaTurno();
                break;
            case stateBatalhaMenuP:
                if (turnoDojogadorVez) {
                    drawTituloMenu(g2);
                }else {
                    batalhaTurno();
                }
                break;
            case stateBatalhaSubMenu:
                if (jogador.getHabilidadeUsada() == null || inimigo.getHabilidadeUsada() == null) {
                    jogador.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
                    inimigo.setHabilidadeUsada("NENHUMA HABILIDADE USADA");
                }
                gp.gameState = gp.stateSubMenuBatalha;
                drawTituloSubMenu(g2);
                break;
            case stateBatalhaInfoJogador:
                drawInfoBatalha(g2, jogador);
                break;
            case stateBatalhaInfoInimigo:
                drawInfoBatalha(g2, inimigo);
                break;
            case stateBatalhaFinalizado:
                drawMensagemWinOrNot(g2);

                encerrarBatalha();
                break;
        }

    }

////////////////////// CONFIG DA BATALHA ////////////////////////
    public void batalhaTurno() {

        System.out.println("Turno inicio");
        if (turnoDojogadorVez) {
            System.out.println("Turno do Jogador 1");
            turnoJogador();
        } else {
//            turnoInimigo();
            System.out.println("Turno do inimigo");
            turnoInimigo();
        }
        System.out.println("Turno fim");



    }

    public void turnoJogador() {

        stateBatalha = stateBatalhaMenuP;
    }

    public void turnoInimigo() {


        int acaoInimigo = (int) (Math.random() * 3);
        switch (acaoInimigo) {
            case 0:
                inimigo.usarHabilidade1(jogador);
                break;
            case 1:
                inimigo.usarHabilidade2(jogador);
                break;
            case 2:
                inimigo.usarHabilidade3(jogador);
                break;
        }

        turnoDojogadorVez = true;
        stateBatalha = stateBatalhaInfoInimigo;

    }

    public void drawInfoBatalha(Graphics2D g2, Personagem personagem) {
        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - gp.tempoInicial;

        int segundos = 3000; // 2 segundos de espera

        String qualtaprintando = "\"INFO DO JOGADOR\"";

        if (personagem == inimigo) {
            segundos = segundos * 2; // 3 segundos de espera para o inimigo
            qualtaprintando = " INFO DO INIMIGO";
        }

        drawMensagem(g2, personagem);
        System.out.println(qualtaprintando);
        System.out.println(tempoDecorrido);

        if (tempoDecorrido >= segundos) {
            stateBatalha = stateBatalhaInicioBatalha;
        }
    }

    public boolean isBatalhaAcabou() {

        return !jogador.isVivo() || !inimigo.isVivo();
    }

    public void encerrarBatalha() {
        gp.gameState = gp.statePlay;
        System.out.println("Batalha Encerrada!");
    }

    public Personagem getVencedor() {
        if (!jogador.isVivo()) {
            return inimigo;
        } else if (!inimigo.isVivo()) {
            return jogador;
        }
        return null;
    }

    //////////////////////////// NAVEGAR NO MENU //////////////////////////////////////

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
            gp.tempoInicial = System.currentTimeMillis(); // Iniciar o timer ao selecionar uma ação
            switch (comandomenu) {
                case 0: // ATACAR
                    stateBatalha = stateBatalhaSubMenu;
                    break;
                case 1: // ESPECIAL
                    jogador.usarHabilidade4(inimigo);
                    turnoDojogadorVez = false;
                    stateBatalha = stateBatalhaInfoJogador;
                    break;
                case 2: // USAR ITEM
                    //COLOCAR INVENTARIO AQUIIIII
                    turnoDojogadorVez = false;
                    stateBatalha = stateBatalhaInfoJogador;
                    break;
                case 3: // DESISTIR
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
            switch (comandomenu) {
                case 0: // HABILIDADE 1
                    jogador.usarHabilidade1(inimigo);

                    turnoDojogadorVez = false;
                    stateBatalha = stateBatalhaInfoJogador;
                    break;
                case 1: // HABILIDADE 2
                    jogador.usarHabilidade2(inimigo);

                    turnoDojogadorVez = false;
                    stateBatalha = stateBatalhaInfoJogador;
                    break;
                case 2: // HABILIDADE 3
                    jogador.usarHabilidade3(inimigo);

                    turnoDojogadorVez = false;
                    stateBatalha = stateBatalhaInfoJogador;
                    break;
                case 3: // VOLTAR
                    stateBatalha = stateBatalhaMenuP;
                    break;
            }
        }
    }




    //////////////////////////////// DRAW E GETS ////////////////////////////////////////////////////////////////////







    public void getImagemPersonagem() {
        try {
            jogador.setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/guerreiro_frente3.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Floresta.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void drawTituloMenu(Graphics2D g2) {
        Font menuFont = new Font("Arial", Font.BOLD, 20);
        g2.setFont(menuFont);
        g2.setColor(Color.black);

        String[] opcoes = {"ATACAR", "ESPECIAL", "USAR ITEM", "DESISTIR"};
        int[][] posicoes = {
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2},
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho * 2},
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho},
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho}
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcaoX = posicoes[i][0];
            int opcaoY = posicoes[i][1];
            String opcao = opcoes[i];
            int opcaoXCentro = opcaoX + (gp.sizeLadrilho / 2) - (g2.getFontMetrics().stringWidth(opcao) / 2);

            int rectX = opcaoXCentro - 5;
            int rectY = opcaoY - 20;
            int rectWidth = g2.getFontMetrics().stringWidth(opcao) + 10;
            int rectHeight = 30;

            if (i == comandomenu) {
                g2.setColor(Color.white);
                g2.fillRect(rectX, rectY, rectWidth, rectHeight);
                g2.setColor(Color.black);
            } else {
                g2.setColor(Color.black);
            }
            g2.drawString(opcao, opcaoXCentro, opcaoY);
        }
    }

    public void drawTituloSubMenu(Graphics2D g2) {
        Font menuFont = new Font("Arial", Font.BOLD, 20);
        g2.setFont(menuFont);
        g2.setColor(Color.black);

        String[] opcoes = {
//                jogador.getHabilidade1().getNome(),
//                jogador.getHabilidade2().getNome(),
//                jogador.getHabilidade3().getNome(),
                "ATAQUE",
                "ATAQUE 2",
                "DEFESA",
                "VOLTAR"
        };

        int[][] posicoes = {
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho * 2},
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho * 2},
                {gp.telaWidth * 5 / 16, gp.telaHeight - gp.sizeLadrilho},
                {gp.telaWidth * 9 / 16, gp.telaHeight - gp.sizeLadrilho}
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcaoX = posicoes[i][0];
            int opcaoY = posicoes[i][1];
            String opcao = opcoes[i];
            int opcaoXCentro = opcaoX + (gp.sizeLadrilho / 2) - (g2.getFontMetrics().stringWidth(opcao) / 2);

            int rectX = opcaoXCentro - 5;
            int rectY = opcaoY - 20;
            int rectWidth = g2.getFontMetrics().stringWidth(opcao) + 10;
            int rectHeight = 30;

            if (i == comandomenu) {
                g2.setColor(Color.white);
                g2.fillRect(rectX, rectY, rectWidth, rectHeight);
                g2.setColor(Color.black);
            } else {
                g2.setColor(Color.black);
            }
            g2.drawString(opcao, opcaoXCentro, opcaoY);
        }
    }

    public void drawBackGround(Graphics2D g2) {
        g2.drawImage(background, 0, 0, gp.telaWidth, gp.telaHeight, null);
    }

    public void drawImagemPersonagens(Graphics2D g2, Personagem inimigo, Personagem jogador) {

        g2.drawImage(inimigo.getImagemGrande(), 420, 120, inimigo.getImagemGrande().getWidth() *4 , inimigo.getImagemGrande().getHeight() *4, null);
        g2.drawImage(jogador.getImagemGrande(), 80, 200, jogador.getImagemGrande().getWidth() * 12 , jogador.getImagemGrande().getHeight() * 12, null);;


    }


    public void drawRetanguloTranslucidoComBordas(Graphics2D g2, int x, int y, int width, int height) {

        int arcWidth = 20 ;
        int arcHeight = 20 ;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AlphaComposite originalComposite = (AlphaComposite) g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

        g2.setComposite(originalComposite);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }



    public void drawMensagemWinOrNot(Graphics2D g2) {
        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - tempoFinalizarBatalha;

        int segundos = 3000; // 2 segundos de espera

        System.out.println(tempoDecorrido);


        String mensagem = "" ;

        int x = gp.sizeLadrilho * 2 + 40;
        int y = gp.sizeLadrilho * 7+ 40;
        Font mensagemFont = new Font("Arial", Font.BOLD, 20);
        g2.setFont(mensagemFont);

        if (getVencedor() == jogador) {
            g2.setColor(Color.green);
            mensagem = " VOCE GANHOU A BATALHA +40 EXPERIENCIA" ;
            jogador.setXp(40);

        }else if(getVencedor() == inimigo){
            mensagem = " VOCE PERDEU A BATALHA";
            g2.setColor(Color.red);

        }

        g2.drawString(mensagem, x, y);

        if (tempoDecorrido >= segundos) {
            encerrarBatalha();
        }
    }

    public void drawMensagem(Graphics2D g2, Personagem personagem) {

    String mensagem = personagem.getHabilidadeUsada();
    if (mensagem != null && !mensagem.isEmpty()) {
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        if (personagem == inimigo){
            g2.setColor(Color.white);
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

    public void drawPlayerHP(Graphics2D g2, Personagem personagem, int pos, boolean isPlayer) {
        int x =  gp.sizeLadrilho - 5;
        int y = 35 + gp.sizeLadrilho + (gp.sizeLadrilho * 4 * pos);
        int width = gp.sizeLadrilho * 4;
        int height = gp.sizeLadrilho / 2; // Reduz a altura da barra de HP

        if (!isPlayer) {
            y = 55;
            x =450;
        }
        g2.setColor(new Color(144, 238, 144)); // Cor verde suave
        g2.fillRect(x, y, width, height);

        // Barra de HP
        g2.setColor(new Color(220, 220, 220)); // Cor mais suave
        g2.fillRect(x, y, (int) (width * (personagem.getSaude() / (double) (100 * personagem.getSaude()))), height);

        // Borda da barra de HP
        g2.setColor(new Color(169, 169, 169)); // Cor cinza suave
        g2.drawRect(x, y, width, height);

        // Texto de HP
        g2.setColor(Color.black);
        g2.drawString("HP: " + personagem.getSaude() + "/" + (personagem.getLimiteSaude()), x + 10, y + 15);
    }

    public void drawPlayerAtributos1(Graphics g2, Personagem jogador, int indice, boolean isPlayer) {
        int x = gp.sizeLadrilho; // 48
        int y = gp.sizeLadrilho;

        Font fm = new Font("Arial", Font.BOLD, 15); // Alterar "48" para o tamanho desejado
        g2.setFont(fm);
        String atributos = jogador.imprimiratributos();

        if (indice == 0) {
            drawRetanguloTranslucidoComBordas((Graphics2D) g2, x - 40, y - 30, 280, 150);
            drawPlayerHP((Graphics2D) g2, jogador, 0, isPlayer);
        } else {
            drawRetanguloTranslucidoComBordas((Graphics2D) g2, x + 380, y - 30, 280, 150);
            drawPlayerHP((Graphics2D) g2, jogador, 1, isPlayer);
            x = x + 408;
        }

        g2.setColor(Color.black);
        for (String line : atributos.split("\n")) {
            g2.drawString(line, x, y);
            y += 20;
        }
    }



}





