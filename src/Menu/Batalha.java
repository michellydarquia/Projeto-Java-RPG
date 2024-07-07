package Menu;

import game.configs.Utils;
import game.personagens.inimigo.Inimigo;
import game.personagens.jogador.Personagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Batalha {

    GamePanel gp;
    Graphics2D g2;
    Personagem jogador;
    Inimigo inimigo;
    Utils utils;

    public int comandomenu = 0;
    public BufferedImage background;
    public boolean turnoDojogadorVez;
    private boolean batalhaTerminada;

    public final int stateBatalhaInicioBatalha = 0;
    public final int stateBatalhaMenuP = 1;
    public final int stateBatalhaSubMenu = 2;
    public final int stateBatalhaInfoJogador = 3;
    public final int stateBatalhaInfoInimigo = 4;
    public final int stateBatalhaFinalizado = 5;
    public final int stateBatalhaMensagemFinal = 6;
    public int stateBatalha;

    public long tempoFinalizarBatalha;

    public Batalha(GamePanel gp, Personagem jogador, Inimigo inimigo) {
        this.gp = gp;
        this.jogador = jogador;
        this.inimigo = inimigo;
        getBlackGroundImage();
        getImagemPersonagem();
        stateBatalha = stateBatalhaInicioBatalha;
        turnoDojogadorVez = true;

        utils = new Utils(gp);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        int y = gp.sizeLadrilho;
        drawBackGround(g2);
        drawPlayerAtributos1(g2, jogador, 0, true);
        drawPlayerAtributos1(g2, inimigo, 1, false);
        drawImagemPersonagens(g2, inimigo, jogador);
        utils.drawRetanguloTranslucidoComBordas(g2, 70, y + 400, 576, y + 70);
        inimigo.getImageGrande();

        if (isBatalhaAcabou() && !batalhaTerminada) {
            batalhaTerminada = true;
            tempoFinalizarBatalha = System.currentTimeMillis();
        }

        switch (stateBatalha) {
            case stateBatalhaInicioBatalha:
                batalhaTurno();
                break;
            case stateBatalhaMenuP:
                if (turnoDojogadorVez) {
                    drawTituloMenu(g2);
                    gp.play.statePlay = gp.play.stateMenuBatalha;
                } else {
                    batalhaTurno();
                }
                break;
            case stateBatalhaSubMenu:
                if (jogador.getStringHabilidadeUsada() == null || inimigo.getStringHabilidadeUsada() == null) {
                    jogador.setStringHabilidadeUsada("NENHUMA HABILIDADE USADA");
                    inimigo.setStringHabilidadeUsada("NENHUMA HABILIDADE USADA");
                }
                gp.play.statePlay = gp.play.stateSubMenuBatalha;
                drawTituloSubMenu(g2);
                break;
            case stateBatalhaInfoJogador:
                System.out.println("TENTANDO INFO 1");
                drawInfoBatalha(g2, jogador);
                System.out.println("TENTANDO INFO 2");
                break;
            case stateBatalhaInfoInimigo:
                drawInfoBatalha(g2, inimigo);
                break;
            case stateBatalhaMensagemFinal:
                drawMensagemWinOrNot(g2);
                break;
            case stateBatalhaFinalizado:

                encerrarBatalha();
                break;
        }
    }

////////////////////////////////////// CONFIG DA BATALHA /////////////////////////////////////
    public void batalhaTurno() {

        System.out.println("Turno inicio");
        if (turnoDojogadorVez) {
            System.out.println("Turno do Jogador 1");
            turnoJogador();
        } else {
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
            case 4:
                inimigo.usarHabilidade4(jogador);
                break;
        }

        turnoDojogadorVez = true;
        stateBatalha = stateBatalhaInfoInimigo;

    }

    public void drawInfoBatalha(Graphics2D g2, Object personagem) {
        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - gp.tempoInicial;

        int segundos = 3000; // 3 segundos de espera

        String qualtaprintando = "\"INFO DO JOGADOR\"";

        if (personagem instanceof Inimigo) {
            segundos = 6000; // 6 segundos de espera para o inimigo
            qualtaprintando = " INFO DO INIMIGO";
        }

//        drawMensagem(g2, personagem);
        utils.drawMensagem(g2,personagem);
        System.out.println(qualtaprintando);
        System.out.println(tempoDecorrido);

        if (tempoDecorrido >= segundos) {
            if (isBatalhaAcabou()) {
                stateBatalha = stateBatalhaMensagemFinal;
            } else {
                stateBatalha = stateBatalhaInicioBatalha;
            }
        }
    }

    public boolean isBatalhaAcabou() {

        return !jogador.isVivo() || !inimigo.isVivo();
    }

    public void encerrarBatalha() {
        System.out.println("Batalha Encerrada!");
        if(getVencedor() == jogador) {
            jogador.setXp(100); // Adiciona 40 de experiência ao jogador
            gp.play.mapa.removerInimigo(inimigo);

        }else {
            inimigo.redefinirAtributos();
        }

        jogador.redefinirAtributos();
        gp.play.statePlay = gp.play.stateJogando;

    }

    public Object getVencedor() {
        if (!jogador.isVivo()) {
            return inimigo;
        } else if (!inimigo.isVivo()) {
            return jogador;
        }
        return null;
    }


    //////////////////////////// NAVEGAR NO MENU //////////////////////////////////////

    public void gerenciarVez(){
        if(jogador.isUsouHabilidade()) {
            System.out.println("USOU HABILIDADE");
            turnoDojogadorVez = false;
        }else {
            System.out.println("NÃO USOU HABILIDADE");
            turnoDojogadorVez = true;
        }
        stateBatalha = stateBatalhaInfoJogador;
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
            gp.tempoInicial = System.currentTimeMillis(); // Iniciar o timer ao selecionar uma ação
            switch (comandomenu) {
                case 0: // ATACAR
                    stateBatalha = stateBatalhaSubMenu;
                    break;
                case 1: // ESPECIAL
                    jogador.usarHabilidade4(inimigo);
                    gerenciarVez();

                    break;
                case 2: // USAR ITEM
                    //COLOCAR INVENTARIO AQUIIIII
                    stateBatalha = stateBatalhaMenuP;
                    break;
                case 3: // DESISTIR
                    jogador.setVivo(false);
                    System.out.println(getVencedor());
                    stateBatalha = stateBatalhaFinalizado;
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
                    gerenciarVez();

                    break;
                case 1: // HABILIDADE 2
                    jogador.usarHabilidade2(inimigo);
                    gerenciarVez();

                    break;
                case 2: // HABILIDADE 3
                    jogador.usarHabilidade3(inimigo);
                    gerenciarVez();

                    break;
                case 3: // VOLTAR
                    stateBatalha = stateBatalhaMenuP;
                    break;
            }
        }
    }


    //////////////////////////////// DRAW E GETS ////////////////////////////////////////////////////////////////////


    public void drawMensagemWinOrNot(Graphics2D g2) {
        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - tempoFinalizarBatalha;

        int segundosEspera = 8000; // 3 segundos de espera para exibir a mensagem

        String mensagem = "";
        int x = 150; // Coordenada X ajustada para a mesma posição do método drawMensagem
        int y = 440; // Coordenada Y ajustada para a mesma posição do método drawMensagem
        Font fonte = gp.monogramExtended.deriveFont((float) 35);
        g2.setFont(fonte);

        if (getVencedor() == jogador) {
            g2.setColor(Color.green);
            mensagem = "VOCÊ GANHOU A BATALHA +40 EXPERIÊNCIA";
            drawInfoBatalha(g2, jogador); // Imprime informações do jogador
        } else if (getVencedor() == inimigo) {
            g2.setColor(Color.red);
            mensagem = "VOCÊ PERDEU A BATALHA";
            drawInfoBatalha(g2, inimigo); // Imprime informações do inimigo
        }

        for (String line : mensagem.split("\n")) {
            g2.drawString(line, x, y); // Ajuste as coordenadas conforme necessário
            y += 20;
        }

        if (tempoDecorrido >= segundosEspera) {
            stateBatalha = stateBatalhaFinalizado;
        }
    }

    public void drawMensagem(Graphics2D g2, Object  personagem) {
        String mensagem = "";

        if (personagem instanceof Personagem) {
            mensagem = ((Personagem) personagem).getStringHabilidadeUsada();
        } else if (personagem instanceof Inimigo) {
            mensagem = ((Inimigo) personagem).getStringHabilidadeUsada();
        }

        if (mensagem != null && !mensagem.isEmpty()) {

            Font fonte = gp.monogramExtended.deriveFont((float) 35);

            g2.setFont(fonte);

            if (personagem instanceof Personagem) {
                g2.setColor(Color.white);
            } else {
                g2.setColor(Color.black);
            }

            int x = 150;
            int y = 490;

            for (String line : mensagem.split("\n")) {
                g2.drawString(line, x, y); // Ajuste as coordenadas conforme necessário
                y += 20;
            }
        }
    }


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
        Font fonte = gp.monogramExtended.deriveFont((float) 35);
        g2.setFont(fonte);
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
        Font fonte = gp.monogramExtended.deriveFont((float) 35);
        g2.setFont(fonte);
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

    public void drawImagemPersonagens(Graphics2D g2, Inimigo inimigo, Personagem jogador) {

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


    public void drawPlayerHP(Graphics2D g2, Object personagem, int pos) {
        int x = gp.sizeLadrilho - 5;
        int y = 35 + gp.sizeLadrilho + (gp.sizeLadrilho * 4 * pos);
        int width = gp.sizeLadrilho * 4;
        int height = gp.sizeLadrilho / 2;
        int hp;
        int limiteSaude;

        if(personagem instanceof  Inimigo){
            y = 55;
            x =450;
        }

        if (personagem instanceof Personagem) {
            hp = ((Personagem) personagem).getSaude();
            limiteSaude = ((Personagem) personagem).getLimiteSaude();
        } else if (personagem instanceof Inimigo) {
            hp = ((Inimigo) personagem).getSaude();
            limiteSaude = ((Inimigo) personagem).getLimiteSaude();
        } else {
            throw new IllegalArgumentException("Objeto personagem inválido");
        }

        g2.setColor(new Color(238, 144, 144));
        g2.fillRect(x, y, width, height);

        // barra de HP
        // Calcula e define a cor da barra de HP de acordo com a porcentagem de saúde restante
        g2.setColor(new Color(144, 238, 144)); // cor  suave
        g2.fillRect(x, y, (int) (width * (hp / (double) limiteSaude)), height);
        g2.setColor(new Color(169, 169, 169));
        g2.drawRect(x, y, width, height);

        // txto
        g2.setColor(Color.black);
        g2.drawString("HP: " + hp + "/" + limiteSaude, x + 10, y + 15);
    }


    public void drawPlayerAtributos1(Graphics g2, Object personagem, int indice, boolean isPlayer) {
        int x = gp.sizeLadrilho; // 48
        int y = gp.sizeLadrilho;

        Font fonte = gp.monogramExtended.deriveFont((float) 25);
        g2.setFont(fonte);

        String atributos;

        if (personagem instanceof Personagem) {
            atributos = ((Personagem) personagem).imprimiratributos();
        } else if (personagem instanceof Inimigo) {
            atributos = ((Inimigo) personagem).imprimirAtributos();
        } else {
            throw new IllegalArgumentException("Objeto personagem inválido");
        }

        if (indice == 0) {
            utils.drawRetanguloTranslucidoComBordas((Graphics2D) g2, x - 40, y - 30, 280, 150);
            drawPlayerHP((Graphics2D) g2, personagem, 0 );
        } else {
            utils.drawRetanguloTranslucidoComBordas((Graphics2D) g2, x + 380, y - 30, 280, 150);
            drawPlayerHP((Graphics2D) g2, personagem, 1);
            x = x + 408;
        }

        g2.setColor(Color.black);
        for (String line : atributos.split("\n")) {
            g2.drawString(line, x, y);
            y += 20;
        }
    }


}





