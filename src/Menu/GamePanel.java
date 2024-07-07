package Menu;

import game.map.MapManager;
import game.personagens.jogador.Jogador;
import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel implements Runnable{


    // constantes de config
    public final int originalSizeLadrilho = 16;
    public final int scale = 3;
    public final int sizeLadrilho = originalSizeLadrilho * scale;

    // dimensões da tela
    public final int maxTelaColunas = 16;
    public final int maxTelaLinhas = 12;
    public final int telaWidth = sizeLadrilho * maxTelaColunas; // 768 pixels
    public final int telaHeight = sizeLadrilho * maxTelaLinhas; // 576 pixels

    public long tempoInicial;

    // thread do jogo e FPS desejado
    int FPS = 60;

    // ESTADOS DO JOGO
    public int gameState;
    public final int stateMenuInicial = 1;
    public final int statePlay = 2;
    public final int statePause =3;
    public final int stateDialogeeHistoria =4;
    public final int stateMenuclasses = 5;
//    public final int stateMenuBatalha =6;
//    public final int stateSubMenuBatalha = 7;







    // instanciando a atualização de tela, movimentação do jogador, player, blocos, inimgios, menus , batalhas

    Thread gameThread; // é uma classe que precisa do metodo run, com ela conseguimos iniciar e parar o game ( um fluxo de execução separado dentro de um programa.)
    KeyHandler keyH = new KeyHandler(this);
//    public Jogador jogador = new Jogador(this, keyH);
//    public MapManager mapa = new MapManager(this);
//    public Inimigo inimigo = new Goblin(12,55);

    public MenuInicial menu = new MenuInicial(this);
    public MenuClasses menuClass = new MenuClasses(this);
//    public Batalha menuBatalha;
    public Play play = new Play(this,keyH);

    public Font monogramExtended;
    Font OldLondon;


    public GamePanel(){
        this.setPreferredSize(new Dimension(telaWidth,telaHeight));
        this.setBackground(Color.BLACK);// esse this. se refere a gamepanel
        this.setDoubleBuffered(true); // melhorar performance
        this.addKeyListener(keyH);
        this.setFocusable(true);


        configurarGame();
        iniciandoFonte();


    }


    public void configurarGame(){
        gameState = stateMenuInicial;
    }


    public void iniciarGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void iniciandoFonte(){


        try {
            InputStream is = getClass().getResourceAsStream("/fonte/monogramExtended.ttf");
            monogramExtended = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonte/OldLondon.ttf");
            OldLondon = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void run() {
////////////////////////////////////////  FPS NO GAME     //////////////////////////////////////////////////////////////////////////////
        // config do intervalo de desenho baseado no FPS
        double desenharintervalo = 1000000000.0 / FPS; // atualizando a cada 0,0166 seg (1 segundo / 60 FPS)
        double delta = 0;
        long lasttime = System.nanoTime(); // pega o tempo inicial em nanossegundos
        long timecorrido;
        //mostrar fps
        long timer = 0;
        long desenharcontador = 0;

        while (gameThread != null) {

            timecorrido = System.nanoTime(); // pega o tempo atual em nanossegundos
            delta += (timecorrido - lasttime) / desenharintervalo;  // Atualiza o delta com a diferença de tempo ajustada pelo intervalo de desenho
            timer += (timecorrido - lasttime); //mostrar fps
            lasttime = timecorrido; // atualiza o tempo anterior para o próximo cálculo
            // Se delta for maior ou igual a 1 signf que é hora de atualizar e desenhar a tela
            if (delta >= 1) {
                atualizar();//  1 atualiza posição do personagem ou estado do jogo
                repaint(); // repinta a tela
                delta--; // reduz delta para continuar o controle de tempo
                desenharcontador++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + desenharcontador);
                desenharcontador = 0;
                timer = 0;
            }

        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void atualizar() {
        play.jogador.atualizar();

    }


@Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == stateMenuInicial){   // TELA DE MENU
            menu.draw(g2);
        }
        if(gameState == stateMenuclasses){ // TELA DE MENU CLASSES
        menuClass.draw(g2);

        }
        if(gameState == statePlay){  // JOGANDO
            play.draw(g2);
        }

        g2.dispose();

    }


}
