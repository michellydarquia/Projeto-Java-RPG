package Estados;

import game.configs.Utils;
import game.inventorio.Item;
import game.inventorio.itens.Artefato;
import game.personagens.inimigo.Inimigo;
import game.personagens.Personagem;
import game.personagens.jogador.Jogador;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Batalha {

    GamePanel gp;
    Graphics2D g2;
    Jogador jogador;
    Inimigo inimigo;
    Utils utils;

    public int comandomenu = 0;
    public BufferedImage background;
    public boolean turnoDojogadorVez;
    private boolean batalhaTerminada;

    private final int stateBatalhaInicioBatalha = 0;
    private final int stateBatalhaMenuP = 1;
    private final int stateBatalhaSubMenu = 2;
    private final int stateBatalhaInfoJogador = 3;
    private final int stateBatalhaInfoInimigo = 4;
    private final int stateBatalhaFinalizado = 5;
    private final int stateBatalhaMensagemFinal = 6;
    private int stateBatalha;

    public long tempoFinalizarBatalha;
    private String music2 = "res\\musica\\Medieval_Batalha.wav";

    public Batalha(GamePanel gp, Jogador jogador, Inimigo inimigo) {
        this.gp = gp;
        this.jogador = jogador;
        this.inimigo = inimigo;
        getBlackGroundImage();
        getImagemPersonagem();
        stateBatalha = stateBatalhaInicioBatalha;
        turnoDojogadorVez = true;

        if (jogador == null) {
            throw new IllegalArgumentException("O objeto jogador deve ser uma instância de Jogador");
        }else {
            System.out.println(jogador.getClass());
        }
        if (inimigo == null) {
            throw new IllegalArgumentException("O objeto inimigo deve ser uma instância de Inimigo");
        }else {
            System.out.println(inimigo.getClass());
        }

        utils = new Utils();

        gp.play.verificarmusica();
        gp.play.musicaPlay(music2);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        int y = gp.sizeLadrilho;
        drawBackGround(g2);
        drawPlayerAtributos1(g2, jogador, 0, true);
        drawPlayerAtributos1(g2, inimigo, 1, false);
        drawImagemPersonagens(g2, inimigo, jogador.getClassePersonagem());
        utils.drawRetanguloTranslucidoComBordas(g2, 90, y + 400, 576, y + 70, 0.5f);
        inimigo.mudarImageGrande();

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
                if (jogador.getClassePersonagem().getStringHabilidadeUsada() == null || inimigo.getStringHabilidadeUsada() == null) {
                    jogador.getClassePersonagem().setStringHabilidadeUsada("NENHUMA HABILIDADE USADA");
                    inimigo.setStringHabilidadeUsada("NENHUMA HABILIDADE USADA");
                }
                gp.play.statePlay = gp.play.stateSubMenuBatalha;
                drawTituloSubMenu(g2);
                break;
            case stateBatalhaInfoJogador:
                drawInfoBatalha(g2, jogador);
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
                inimigo.usarHabilidade1(jogador.getClassePersonagem());
                break;
            case 1:
                inimigo.usarHabilidade2(jogador.getClassePersonagem());
                break;
            case 2:
                inimigo.usarHabilidade3(jogador.getClassePersonagem());
                break;
            case 4:
                inimigo.usarHabilidade4(jogador.getClassePersonagem());
                break;
        }

        turnoDojogadorVez = true;
        stateBatalha = stateBatalhaInfoInimigo;

    }

    public void drawInfoBatalha(Graphics2D g2, Object personagem) {
        long tempofinal = System.currentTimeMillis();
        long tempoDecorrido = tempofinal - gp.tempoInicial;

        int segundos = 3000; // 3 segundos de espera

        if (personagem instanceof Inimigo) {
            segundos = 6000; // 6 segundos de espera para o inimigo
        }

        drawMensagem(g2,personagem);
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
        return !jogador.getClassePersonagem().isVivo() || !inimigo.isVivo();
    }

    public void encerrarBatalha() {
        System.out.println("Batalha Encerrada!");
        if(getVencedor() == jogador) {
            jogador.getClassePersonagem().setXp(40); // Adiciona 40 de experiência ao jogador
            Item fragmento = new Artefato();
            jogador.getInventario().adicionarItem(fragmento);
            gp.play.mapa.removerInimigo(inimigo);
            jogador.getClassePersonagem().alterarInimigosMortos();


        }else {
            inimigo.redefinirAtributos();
        }
        gp.play.mapa.colisaoInimigo = false;
        gp.play.verificarmusica();



        jogador.getClassePersonagem().redefinirAtributos();
        gp.play.statePlay = gp.play.stateJogando;

    }

    public Object getVencedor() {
        if (!jogador.getClassePersonagem().isVivo()) {
            return inimigo;
        } else if (!inimigo.isVivo()) {
            return jogador;
        }
        return null;
    }



    //////////////////////////// NAVEGAR NO MENU //////////////////////////////////////

    public void gerenciarVez(){
        if(jogador.getClassePersonagem().isUsouHabilidade()) {
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
                    jogador.getClassePersonagem().usarHabilidade4(inimigo);
                    gerenciarVez();

                    break;
                case 2: // USAR ITEM
                    //COLOCAR INVENTARIO AQUIIIII
                    gp.play.drawInventario();
                    break;
                case 3: // DESISTIR
                    jogador.getClassePersonagem().setVivo(false);
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
                    jogador.getClassePersonagem().usarHabilidade1(inimigo);
                    gerenciarVez();

                    break;
                case 1: // HABILIDADE 2
                    jogador.getClassePersonagem().usarHabilidade2(inimigo);
                    gerenciarVez();

                    break;
                case 2: // HABILIDADE 3
                    jogador.getClassePersonagem().usarHabilidade3(inimigo);
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
        Font fonte = utils.monogramExtended.deriveFont((float) 35);
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

        if (personagem instanceof Jogador) {
            mensagem = ((Jogador) personagem).getClassePersonagem().getStringHabilidadeUsada();
        } else if (personagem instanceof Inimigo) {
            mensagem = ((Inimigo) personagem).getStringHabilidadeUsada();
        }

        if (mensagem != null && !mensagem.isEmpty()) {

            Font fonte = utils.monogramExtended.deriveFont((float) 35);

            g2.setFont(fonte);

            if (personagem instanceof Personagem) {
                g2.setColor(Color.white);
            } else {
                g2.setColor(Color.black);
            }

            int x = 150;
            int y = 490;

            for (String line : mensagem.split("\n")) {
                g2.drawString(line, x, y);
                y += 20;
            }
        }
    }


    public void getImagemPersonagem() {
        try {
            jogador.getClassePersonagem().setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Archer_down1.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBlackGroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/Floresta.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawBackGround(Graphics2D g2) {

        g2.drawImage(background, 0, 0, gp.telaWidth, gp.telaHeight, null);
    }

    public void drawTituloMenu(Graphics2D g2) {
        Font fonte = utils.monogramExtended.deriveFont((float) 35);
        g2.setFont(fonte);
        g2.setColor(Color.black);

        String[] opcoes = {"ATACAR", jogador.getClassePersonagem().getHabilidade4(), "USAR ITEM", "DESISTIR"};
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
        Font fonte = utils.monogramExtended.deriveFont((float) 35);
        g2.setFont(fonte);
        g2.setColor(Color.black);

        String[] opcoes = {
                jogador.getClassePersonagem().getHabilidade1(),
                jogador.getClassePersonagem().getHabilidade2(),
                jogador.getClassePersonagem().getHabilidade3(),
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

    public void drawImagemPersonagens(Graphics2D g2, Inimigo inimigo, Personagem jogador) {

        g2.drawImage(inimigo.getImagemGrande(), 420, 120, inimigo.getImagemGrande().getWidth() *4 , inimigo.getImagemGrande().getHeight() *4, null);
        g2.drawImage(jogador.getImagemGrande(), 80, 200, jogador.getImagemGrande().getWidth() * 12 , jogador.getImagemGrande().getHeight() * 12, null);;


    }



    public void drawPlayerHP(Graphics2D g2, Object personagem, int pos) {
        int x = gp.sizeLadrilho - 5;
        int y = 35 + gp.sizeLadrilho + (gp.sizeLadrilho * 4 * pos);
        int width = gp.sizeLadrilho * 4;
        int height = gp.sizeLadrilho / 2;
        int hp;
        int limiteSaude;

        if (personagem instanceof Jogador) {
            hp = jogador.getClassePersonagem().getSaude();
            limiteSaude = jogador.getClassePersonagem().getBaseHp();

        } else if (personagem instanceof Inimigo) {
            hp = inimigo.getSaude();
            limiteSaude = inimigo.getLimiteHp();
            y = 60;
            x =455;
        } else {
            throw new IllegalArgumentException("Objeto personagem inválido");
        }

        g2.setColor(new Color(238, 144, 144));
        g2.fillRect(x, y, width, height);

        // barra de HP
        // define a cor da barra de HP de acordo com a porcentagem de saúde restante
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

        Font fonte = utils.monogramExtended.deriveFont((float) 25);
        g2.setFont(fonte);

        String atributos;

        if (personagem instanceof Jogador) {
            atributos = ((Jogador) personagem).getClassePersonagem().imprimiratributos();
        } else if (personagem instanceof Inimigo) {
            atributos = ((Inimigo) personagem).imprimiratributos();
        } else {
            throw new IllegalArgumentException("Objeto personagem inválido");
        }

        if (indice == 0) {
            utils.drawRetanguloTranslucidoComBordas((Graphics2D) g2, x - 18, y - 30, 280, 150,0.5f);
            drawPlayerHP((Graphics2D) g2, personagem, 0 );
        } else {
            utils.drawRetanguloTranslucidoComBordas((Graphics2D) g2, x + 380, y - 30, 280, 150,0.5f);
            drawPlayerHP((Graphics2D) g2, personagem, 1);
            x += 408;
        }

        g2.setColor(Color.black);
        for (String line : atributos.split("\n")) {
            g2.drawString(line, x, y);
            y += 20;
        }
    }


}





