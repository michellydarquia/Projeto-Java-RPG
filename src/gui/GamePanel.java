package gui;

import game.map.BlocosManager;
import game.personagens.KeyHandler;
import game.personagens.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.util.EventListener;


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

    // thread do jogo e FPS desejado
    int FPS = 60;

    // ESTADOS DO JOGO
    public int gameState = 0;
    public int menuinicialState = 0;


    // instanciando a atualização de tela, movimentação do jogador, player, blocos

    Thread gameThread; // é uma classe que precisa do metodo run, com ela conseguimos iniciar e parar o game ( um fluxo de execução separado dentro de um programa.)
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    BlocosManager blocoM = new BlocosManager(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(telaWidth, telaHeight));
        this.setBackground(Color.BLACK);// esse this. se refere a gamepanel
        this.setDoubleBuffered(true); // melhorar performance
        this.addKeyListener(keyH);
        this.setFocusable(true);


        configurarGame();
    }

    public void configurarGame() {

        gameState = menuinicialState;

    }


    public void iniciarGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

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
    }

    public void atualizar() {

        player.atualizar();

    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // TELA DE MENU

//        if(gameState == menuinicialState){
//
//
//            g2.setColor(Color.black);
//            g2.fillRect(0,0, telaWidth,telaHeight);
//
//
//        }else {
        blocoM.draw(g2);
        player.draw(g2);// tem q ficar dps des blocos
        g2.dispose(); // Libera os recursos gráficos


    }





    }


