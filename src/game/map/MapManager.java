package game.map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import Estados.GamePanel;
import Estados.Batalha;
import game.personagens.inimigo.Inimigo;
import game.personagens.inimigo.classes.Goblin;
import game.personagens.npc.Npc;

import javax.imageio.ImageIO;

// resp por carregar, descarregar e trocar os mapas.

public class MapManager extends Blocos{

    GamePanel gp;
    Blocos[] bloco;
    private int[][] mapBlocoNumero;
    public Map<Point, String> pontosTransicao;
    private String mapaAtual;
    public List<Goblin> inimigos;
    public List<Npc> npcs;
    public boolean colisaoInimigo;


    public MapManager(GamePanel gp){
        this.gp = gp;

        bloco = new Blocos[30];
        mapBlocoNumero = new int[gp.maxTelaColunas][gp.maxTelaLinhas];
        pontosTransicao = new HashMap<>();
        inimigos = new ArrayList<>();
        npcs = new ArrayList<>();

        getImagemdosBlocos();
        mapaAtual = "/maps/map01.txt";
        loadMap(mapaAtual);

        loadpontosTransicao();
        loadInimigos();
        loadNpc();

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

    public void loadMap(String numMap) {
        try (InputStream is = getClass().getResourceAsStream(numMap);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int linha = 0; linha < gp.maxTelaLinhas; linha++) {
                String line = br.readLine();
                String[] numeros = line.split(" ");
                for (int coluna = 0; coluna < gp.maxTelaColunas; coluna++) {
                    int num = Integer.parseInt(numeros[coluna]);
                    mapBlocoNumero[coluna][linha] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unloadMap(){
        inimigos.clear();
        npcs.clear();
    }

    public void switchMap(String novoMapa){
        unloadMap();        // chamo unload para descarregar o mapa atual
        loadMap(novoMapa); // depois chamo load para carregar o novo mapa
        loadpontosTransicao();
        loadInimigos();
        loadNpc();
        mapaAtual = novoMapa;

    }


    public void loadInimigos() {
        switch (mapaAtual) {
            case "/maps/map01.txt":
                inimigos.add(new Goblin(233, 125));
                break;
            case "/maps/map02.txt":
                inimigos.add(new Goblin(133, 111));
                break;
            case "/maps/map03.txt":
                inimigos.add(new Goblin(123, 123));
                inimigos.add(new Goblin(123, 11));
                inimigos.add(new Goblin(442, 244));
                inimigos.add(new Goblin(423, 121));
                break;
        }
    }

    public void loadNpc() {
        switch (mapaAtual) {
            case "/maps/map01.txt":
                npcs.add(new Npc(342, 222));
                npcs.add(new Npc(765, 222));
                break;
            case "/maps/map02.txt":
                npcs.add(new Npc(255, 222));
                break;
            case "/maps/map03.txt":
                // Adicione os NPCs específicos do mapa 03 aqui
                break;
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    public void verificarBordasMapa() {
        boolean colisaoBordas = true;
        int x = Math.floorDiv(gp.play.jogador.x, gp.sizeLadrilho);
        int y = Math.floorDiv(gp.play.jogador.y, gp.sizeLadrilho);

        // Verificações específicas para cada mapa para poder passar a borda e mduar de mapa
        if (mapaAtual.equals("/maps/map01.txt")) {
            if ((x == 6 ) && y < 0) { // Transição para mapa 03
                colisaoBordas = false;
                verificarTransicaoMapa(gp.play.jogador.x, gp.play.jogador.y);
            } else if ((x == 14 || x == 15) && y == 5) { // Transição para mapa 02
                colisaoBordas = false;
                verificarTransicaoMapa(gp.play.jogador.x, gp.play.jogador.y);
            }
        } else if (mapaAtual.equals("/maps/map02.txt")) {
            if (x <= 0 && y == 5) { // Transição para mapa 01
                colisaoBordas = false;
//                System.out.println("INDO AO MAPA 1");
                verificarTransicaoMapa(gp.play.jogador.x, gp.play.jogador.y);
            }
        } else if (mapaAtual.equals("/maps/map03.txt")) {
            if ((x == 6 || x == 7) && y >= 10) { // Transição para mapa 01
                colisaoBordas = false;
                verificarTransicaoMapa(gp.play.jogador.x, gp.play.jogador.y);
//                System.out.println("INDO AO MAPA 11");
            }
        }

        // verif as bordas padrão se não houver transição
        if (colisaoBordas) {
            if (gp.play.jogador.x < 20 || gp.play.jogador.x > 700) {
                gp.play.jogador.x = gp.play.jogador.x < 20 ? 20 : 700;
            }

            if (gp.play.jogador.y < 0 || gp.play.jogador.y > 484) {
                gp.play.jogador.y = gp.play.jogador.y < 0 ? 0 : 484;
            }
        }
    }


    public void verificarColisaoInimigo() {
        int jogadorX = gp.play.jogador.x ;
        int jogadorY = gp.play.jogador.y ;

        for (Goblin inimigo : inimigos){
            int inimigoX = inimigo.getPosx();
            int inimigoY = inimigo.getPosy();

            if ((Math.abs(inimigoX - jogadorX) <= 8) && (Math.abs(inimigoY - jogadorY) <= 8)) {// POSSSO COLOCAR UM DIALOGO AQUII
                System.out.println("COLIDIU COM INIMIGO, INICIANDO BATALHA");
                colisaoInimigo = true;
                gp.play.menuBatalha = new Batalha(gp, gp.play.jogador, inimigo);
                gp.play.statePlay = gp.play.stateMenuBatalha;
                break;
            }else {
                colisaoInimigo = false;
            }
        }

    }

    public void verificarColisaoNpc() {
        int jogadorX = gp.play.jogador.x;
        int jogadorY = gp.play.jogador.y;

        for (Npc npc : npcs) {
            int npcX = npc.getPosx();
            int npcY = npc.getPosy();

            if ((Math.abs(npcX - jogadorX) <= 8) && (Math.abs(npcY - jogadorY) <= 8)) {
                System.out.println("COLIDIU COM NPC, INICIANDO DIALOGO");
//                gp.play.statePlay = gp.play.stateDialogo;

                gp.historia.iniciarDialogo(npc);
                return;
            }
        }
    }


    public void removerInimigo(Inimigo inimigo) {
        inimigos.remove(inimigo);
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

        for (Goblin  inimigo : inimigos) {
            inimigo.draw(g2);
        }
        for (Npc  npc : npcs) {
            npc.draw(g2);
        }


    }

    public void verificarTransicaoMapa(int jogadorX, int jogadorY) {
        int x = Math.floorDiv(jogadorX, gp.sizeLadrilho);
        int y=Math.floorDiv(jogadorY, gp.sizeLadrilho);
        Point jogadorPosicaoMatriz = new Point(x, y);
        loadpontosTransicao();
        String novoMapa = pontosTransicao.get(jogadorPosicaoMatriz);

        if (novoMapa != null) {
            ajustarPosicaoJogadorParaNovoMapa(novoMapa);
            switchMap(novoMapa);
        }

    }

    private void ajustarPosicaoJogadorParaNovoMapa(String novoMapa) {
        if (novoMapa.equals("/maps/map03.txt") && mapaAtual.equals("/maps/map01.txt")) {
            gp.play.jogador.x = 336;
            gp.play.jogador.y = 488;
        } else if (novoMapa.equals("/maps/map02.txt") && mapaAtual.equals("/maps/map01.txt")) {
            gp.play.jogador.x = 28;
            gp.play.jogador.y = 276;
        } else if (novoMapa.equals("/maps/map01.txt") && mapaAtual.equals("/maps/map03.txt")) {
            gp.play.jogador.x = 328;
            gp.play.jogador.y = 8;
        } else if (novoMapa.equals("/maps/map01.txt") && mapaAtual.equals("/maps/map02.txt")) {
            gp.play.jogador.x = 664;
            gp.play.jogador.y = 276;
        }
    }


    public void getImagemdosBlocos(){
        try {

            bloco[0] = new Blocos(); // grama1
            bloco[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_1.png")));

            bloco[1] = new Blocos(); // grama2
            bloco[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_2.png")));

            bloco[2] = new Blocos(); // arvore1
            bloco[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_5.png")));

            bloco[3] = new Blocos(); // arvore2
            bloco[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_7.png")));

            bloco[4] = new Blocos(); // mato1
            bloco[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_28.png")));


            // -------------  CAMINHOS ----------------

            bloco[5] = new Blocos(); // caminho horizontal
            bloco[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_72.png")));

            bloco[6] = new Blocos(); // caminho vertical
            bloco[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_78.png")));

            bloco[7] = new Blocos(); // caminho cruzada
            bloco[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_79.png")));



            // -------------  BORDAS REINO ----------------

            bloco[8] = new Blocos(); // borda superior direita
            bloco[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_220.png")));

            bloco[9] = new Blocos();  // borda horinzontal
            bloco[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_221.png")));


            bloco[10] = new Blocos(); // borda superior esquerda
            bloco[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_222.png")));


            bloco[11] = new Blocos(); // borda vertical
            bloco[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_227.png")));


            bloco[12] = new Blocos(); // borda inferior esquerda
            bloco[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_236.png")));

            bloco[13] = new Blocos(); // borda inferior direita
            bloco[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_234.png")));

            // -------------------- CASTELO E CASAS -------------------

            bloco[14] = new Blocos(); // castelo cima direita
            bloco[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_54.png")));

            bloco[15] = new Blocos(); // castelo cima esquerda
            bloco[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_55.png")));

            bloco[16] = new Blocos(); // castelo baixo direita
            bloco[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_61.png")));

            bloco[17] = new Blocos(); // castelo baixo esquerda
            bloco[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_62.png")));

            bloco[18] = new Blocos(); // 3 casas
            bloco[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_16.png")));

            bloco[19] = new Blocos(); // uma casa andar meio palha
            bloco[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_15.png")));

            bloco[20] = new Blocos(); //uma casa teto vermelho
            bloco[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_18.png")));

            bloco[21] = new Blocos(); // cabanas vermelhas
            bloco[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_38.png")));

            bloco[22] = new Blocos(); // cabanas amarelas
            bloco[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_41.png")));

            bloco[23] = new Blocos(); // acampamento verde
            bloco[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_44.png")));

            bloco[24] = new Blocos(); // acampamento amarelo
            bloco[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_43.png")));

            bloco[25] = new Blocos(); // casa destruida
            bloco[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_36.png")));

            bloco[26] = new Blocos(); // casa destruid 2
            bloco[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_29.png")));

            bloco[27] = new Blocos(); // torre medieval
            bloco[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_26.png")));



        } catch (IOException e) {
            System.out.println("ERRO NAS IMAGENS BLOCO");
        }
    }






}



