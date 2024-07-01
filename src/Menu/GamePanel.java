package Menu;

import game.map.BlocosManager;
import game.personagens.KeyHandler;
import game.personagens.Jogador;
import game.personagens.Personagem;
import game.personagens.enemies.GoblinArcher;

import javax.swing.JPanel;
import java.awt.*;


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
    public int gameState=0;
    public final int stateMenuInicial = 1;
    public final int statePlay = 2;
    public final int statePause =3;
    public final int stateDialoge =4;
    public final int stateMenuclasses = 5;
    public final int stateMenuBatalha =6;
    public final int stateSubMenuBatalha = 7;
    public final int stateInfoBatalha = 8;
    public final int stateInimigoAcao = 9;
    public final int stateinicioBatalha = 10;



    // instanciando a atualização de tela, movimentação do jogador, player, blocos, inimgios, menus , batalhas

    Thread gameThread; // é uma classe que precisa do metodo run, com ela conseguimos iniciar e parar o game ( um fluxo de execução separado dentro de um programa.)
    KeyHandler keyH = new KeyHandler(this);

    BlocosManager blocoM = new BlocosManager(this);

    Jogador jogador = new Jogador(this, keyH);
    Personagem inimigo = new GoblinArcher();

    public Menu menu = new Menu(this);
    public MenuClasses menuClass = new MenuClasses(this);
    public MenuBatalha menuBatalha;


    public GamePanel(){
        this.setPreferredSize(new Dimension(telaWidth,telaHeight));
        this.setBackground(Color.BLACK);// esse this. se refere a gamepanel
        this.setDoubleBuffered(true); // melhorar performance
        this.addKeyListener(keyH);
        this.setFocusable(true);


        configurarGame();
    }


    public void configurarGame(){
        gameState = stateMenuInicial;
    }


    public void iniciarGameThread(){
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

    public void atualizar(){

        jogador.atualizar();

    }


@Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == stateMenuInicial){   // TELA DE MENU
            menu.draw(g2);

        }
        if(gameState == stateMenuclasses){ // TELA DE MENU CLASSES
        System.out.println("SELECIONANDO CLASSES");
        menuClass.draw(g2);

        }
        if(gameState == statePlay){  // JOGANDO
            blocoM.draw(g2);
            jogador.draw(g2);// tem q ficar dps des blocos
        }

        if(gameState == stateMenuBatalha || gameState == stateSubMenuBatalha  || gameState == stateInfoBatalha  || gameState == stateInimigoAcao || gameState == stateinicioBatalha )  { // BATALHANDO
            if(menuBatalha == null) {
                menuBatalha = new MenuBatalha(this, jogador.getClassePersonagem(), inimigo);
            }
            menuBatalha.draw(g2);

        }


        g2.dispose();

    }


}
