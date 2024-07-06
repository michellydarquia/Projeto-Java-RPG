package game.map;

import Menu.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// resp por carregar, descarregar e trocar os mapas.



public class MapManager extends Blocos{

    GamePanel gp;
    Blocos[] bloco;
    private int[][] mapBlocoNumero;
    public Map<Point, String> pontosTransicao;
    private String mapaAtual;


    public String getMapaAtual() {
        return mapaAtual;
    }

    public MapManager(GamePanel gp){
        this.gp = gp;

        bloco = new Blocos[30];
        mapBlocoNumero = new int[gp.maxTelaColunas][gp.maxTelaLinhas];
        pontosTransicao = new HashMap<>();

        getImagemdosBlocos();
        mapaAtual = "/maps/map01.txt";
        loadMap("/maps/map01.txt");

        loadpontosTransicao();


    }

    private void loadpontosTransicao() {
        pontosTransicao.clear();
        switch (mapaAtual) {
            case "/maps/map01.txt":
                pontosTransicao.put(new Point(6, -2), "/maps/map03.txt"); // Caminho para mapa 02
                pontosTransicao.put(new Point(7, -2), "/maps/map03.txt"); // Caminho para mapa 02
                pontosTransicao.put(new Point(15, 5), "/maps/map02.txt"); // Exemplo de transição para o mapa 2
                pontosTransicao.put(new Point(15, 5), "/maps/map02.txt"); // Exemplo de transição para o mapa 2
                break;
            case "/maps/map02.txt":
                pontosTransicao.put(new Point(-1, 5), "/maps/map01.txt"); // Caminho de volta para mapa 01
                break;
            case "/maps/map03.txt":
//                pontosTransicao.put(new Point(6, 10), "/maps/map01.txt"); // Caminho de volta para mapa 01;
                pontosTransicao.put(new Point(7, 11), "/maps/map01.txt"); // Caminho de volta para mapa 01
                pontosTransicao.put(new Point(6, 11), "/maps/map01.txt"); // Caminho de volta para mapa 01
                break;
        }
}

    public void loadMap(String numMap){
        try {
            InputStream is = getClass().getResourceAsStream(numMap);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int coluna=0;
            int linha=0;

            while (coluna < gp.maxTelaColunas && linha < gp.maxTelaLinhas){
                String line = br.readLine();

                while (coluna < gp.maxTelaColunas){
                    String numeros[] = line.split(" ");
                    int num = Integer.parseInt(numeros[coluna]);

                    mapBlocoNumero[coluna][linha]= num;
                    coluna++;
                }
                if (coluna == gp.maxTelaColunas) {
                    coluna = 0;
                    linha++;
                }
            }


            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void unloadMap(){

    }

    public void switchMap(String novoMapa){
        System.out.println("ENTREI EM SWITHMAP");
        unloadMap();        // chamo unload para descarregar o mapa atual
        loadMap(novoMapa); // depois chamo load para carregar o novo mapa
        loadpontosTransicao();

        mapaAtual = novoMapa;
        definirPeculiaridadesMapa();
    }


    private void definirPeculiaridadesMapa() {
        if (mapaAtual.equals("/maps/map01.txt")) {
            peculiaridadesMapa01();
        } else if (mapaAtual.equals("/maps/map02.txt")) {
            peculiaridadesMapa02();
        } else if (mapaAtual.equals("/maps/map03.txt")) {
            peculiaridadesMapa03();
        }
    }

    private void peculiaridadesMapa01() {



        System.out.println("Configurações específicas para o mapa 01");

    }



    public void verificarBordasMapa() {
        boolean colisaoBordas = true;
        int x = Math.floorDiv(gp.jogador.x, gp.sizeLadrilho);
        int y = Math.floorDiv(gp.jogador.y, gp.sizeLadrilho);

        Point jogadorPosicaoMatriz = new Point(x, y);
        System.out.println(jogadorPosicaoMatriz);
        System.out.println(mapaAtual);


        // Verificações específicas para cada mapa
        if (mapaAtual.equals("/maps/map01.txt")) {
            if ((x == 6 ) && y < 0) { // Transição para mapa 03
                colisaoBordas = false;
                verificarTransicaoMapa(gp.jogador.x, gp.jogador.y);
            } else if ((x == 14 || x == 15) && y == 5) { // Transição para mapa 02
                colisaoBordas = false;
                verificarTransicaoMapa(gp.jogador.x, gp.jogador.y);
            }
        } else if (mapaAtual.equals("/maps/map02.txt")) {
            if (x <= 0 && y == 5) { // Transição para mapa 01
                colisaoBordas = false;
                System.out.println("INDO AO MAPA 1");
                verificarTransicaoMapa(gp.jogador.x, gp.jogador.y);
            }
        } else if (mapaAtual.equals("/maps/map03.txt")) {
            if ((x == 6 || x == 7) && y >= 10) { // Transição para mapa 01
                colisaoBordas = false;
                verificarTransicaoMapa(gp.jogador.x, gp.jogador.y);
                System.out.println("INDO AO MAPA 11");
            }
        }

        // Verifica as bordas padrão se não houver transição
        if (colisaoBordas) {
            if (gp.jogador.x < 20 || gp.jogador.x > 700) {
                gp.jogador.x = gp.jogador.x < 20 ? 20 : 700;
            }

            if (gp.jogador.y < 0 || gp.jogador.y > 484) {
                gp.jogador.y = gp.jogador.y < 0 ? 0 : 484;
            }
        }
    }


    private void peculiaridadesMapa02() {
        System.out.println("Configurações específicas para o mapa 02");

        // spawn de inimigos, eventos, etc.
    }

    private void peculiaridadesMapa03() {
        System.out.println("Configurações específicas para o mapa 03");
    }
    public void draw(Graphics2D g2){
        int coluna = 0;
        int linha = 0;
        int x = 0;
        int y = 0;


        while (coluna < gp.maxTelaColunas && linha < gp.maxTelaLinhas){

            int blocoNum = mapBlocoNumero[coluna][linha];
            g2.drawImage(bloco[blocoNum].image, x,y, gp.sizeLadrilho, gp.sizeLadrilho, null);
            coluna++;
            x += gp.sizeLadrilho;

            if (coluna == gp.maxTelaColunas){
                coluna=0;
                x=0;
                linha++;
                y+= gp.sizeLadrilho;
            }

        }



    }
    public void verificarTransicaoMapa(int jogadorX, int jogadorY) {
        int x = Math.floorDiv(jogadorX, gp.sizeLadrilho);
        int y=Math.floorDiv(jogadorY, gp.sizeLadrilho);
        Point jogadorPosicaoMatriz = new Point(x, y);

        loadpontosTransicao();

        String novoMapa = pontosTransicao.get(jogadorPosicaoMatriz);
        System.out.println("AAAAAAAAAAAAAAAAAAA");
        System.out.println(jogadorPosicaoMatriz);
        System.out.println("novo mapa: " + novoMapa);
        System.out.println("atual mapa: " + mapaAtual);
        System.out.println("AAAAAAAAAAAAAAAAAAA");

        if (novoMapa != null) {

            System.out.println("NAO É NULL");
            System.out.println(novoMapa);
            System.out.println(mapaAtual);
            if(novoMapa.equals("/maps/map03.txt") && mapaAtual.equals("/maps/map01.txt")) {
                gp.jogador.x = 336;
                gp.jogador.y = 488;
            }else if (novoMapa.equals("/maps/map02.txt") && mapaAtual.equals("/maps/map01.txt")){
                gp.jogador.x = 28;
                gp.jogador.y = 276;
            }else if (novoMapa.equals("/maps/map01.txt") && mapaAtual.equals("/maps/map03.txt")){
                gp.jogador.x = 328;
                gp.jogador.y = 8;
            }else if (novoMapa.equals("/maps/map01.txt") && mapaAtual.equals("/maps/map02.txt")){
                gp.jogador.x = 664;
                gp.jogador.y = 276;
            }
            switchMap(novoMapa);




        }


    }


    public void getImagemdosBlocos(){
        try {

            bloco[0] = new Blocos(); // grama1
            bloco[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/grama1.png")));

            bloco[1] = new Blocos(); // grama2
            bloco[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/grama2.png")));

            bloco[2] = new Blocos(); // pedra
            bloco[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/pedra.png")));

            bloco[3] = new Blocos(); // arvore
            bloco[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/arvore1.png")));

            bloco[4] = new Blocos(); // arvore
            bloco[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/arvore2.png")));

            bloco[5] = new Blocos(); // caminho
            bloco[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/caminho1.png")));

            // -------------  AGUAAAAAS ----------------
            bloco[6] = new Blocos(); // agua centro
            bloco[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguacentro.png")));

            bloco[7] = new Blocos(); // agua borda cima esquerda
            bloco[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordacimaesquerda.png")));

            bloco[8] = new Blocos();  // agua borda cima
            bloco[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordacima.png")));

            bloco[9] = new Blocos(); // agua borda cima direita
            bloco[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordacimadireita.png")));

            bloco[10] = new Blocos(); // agua borda esquerda
            bloco[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordaesquerda.png")));

            bloco[11] = new Blocos(); // agua borda direita
            bloco[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordadireita.png")));

            bloco[12] = new Blocos(); // agua borda  baixo esquerda
            bloco[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordabaixoesquerda.png")));

            bloco[13] = new Blocos(); // agua borda baixo direita
            bloco[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordabaixodireita.png")));

            bloco[14] = new Blocos(); // agua borda baixo
            bloco[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/aguabordabaixo.png")));

            // ---------------------------------------
            bloco[15] = new Blocos(); // caminho2
            bloco[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/caminho2.png")));



        } catch (IOException e) {
            System.out.println("ERRO NAS IMAGENS BLOCO");
        }
    }






}



