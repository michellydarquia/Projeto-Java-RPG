package gui;

import game.personagens.KeyHandler;
import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{

    // constantes de config
    final int originalSizeLadrilho = 16;
    final int scale = 3;
    final int sizeLadrilho = originalSizeLadrilho * scale;

    // dimensões da tela
    final int maxTelaColunas = 16;
    final int maxTelaLinhas = 12;
    final int telaWidth = sizeLadrilho * maxTelaColunas; // 768 pixels
    final int telaHeight = sizeLadrilho * maxTelaLinhas; // 576 pixels

    // thread do jogo e FPS desejado
    int FPS = 60;
    Thread gameThread; // é uma classe que precisa do metodo run, com ela conseguimos iniciar e parar o game ( um fluxo de execução separado dentro de um programa.)

    // instanciando a movimentação do jogador
    KeyHandler keyH = new KeyHandler();


    // posição inicial
    int jogadorx = 100;
    int jogadory = 100;
    int jogadorSpeed = 4; // 4 pixels, no caso  ele anda 4 pixels


    public GamePanel(){
        this.setPreferredSize(new Dimension(telaWidth,telaHeight));
        this.setBackground(Color.BLACK);// esse this. se refere a gamepanel
        this.setDoubleBuffered(true); // melhorar performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void iniciarGameThread(){
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

    public void atualizar(){

        if(keyH.upPressed){
            jogadory -= jogadorSpeed;
        }else if (keyH.downPressed){
            jogadory += jogadorSpeed;

        }else if (keyH.leftPressed){
            jogadorx -= jogadorSpeed;

        }else if (keyH.rightPressed){
            jogadorx += jogadorSpeed;
        }

    }
@Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(jogadorx,jogadory,sizeLadrilho, sizeLadrilho);

        g2.dispose(); // Libera os recursos gráficos

    }

}
