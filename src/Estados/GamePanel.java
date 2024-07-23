package Estados;

import game.configs.Historia;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {


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
    public final int statePause = 3;
    public final int stateHistoria = 4;
    public final int stateMenuclasses = 5;
    public final int stateInstrucoes = 6;


    Thread gameThread; // é uma classe que precisa do metodo run, com ela conseguimos iniciar e parar o game ( um fluxo de execução separado dentro de um programa.)
    KeyHandler keyH = new KeyHandler(this);

    public MenuInicial menu = new MenuInicial(this);
    public MenuClasses menuClass = new MenuClasses(this);
    public Play play = new Play(this, keyH);
    public Historia historia;


    public GamePanel() {
        this.setPreferredSize(new Dimension(telaWidth, telaHeight));
        this.setBackground(Color.BLACK);// esse this. se refere a gamepanel
        this.setDoubleBuffered(true); // melhorar performance
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.historia = new Historia(this);
        configurarGame();


    }


    public void configurarGame() {
        gameState = stateMenuInicial;
    }


    public void iniciarGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
            if (play.statePlay == play.stateJogando) {
                play.jogador.atualizar();
            }
        }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == stateMenuInicial) {   // TELA DE MENU
            menu.draw(g2);
        }
        if (gameState == stateHistoria){
            historia.draw(g2);
        }


        if (gameState == stateMenuclasses) { // TELA DE MENU CLASSES
            menuClass.draw(g2);

        }
        if (gameState == stateInstrucoes) { // TELA instruções
            menu.drawInstruções(g2);

        } if (gameState == statePause) { // TELA DE pause
            play.drawMenuPause(g2);

        }
        if (gameState == statePlay) { // JOGANDO
            play.draw(g2);
        }

        g2.dispose();

    }


}
