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

public class BlocosManager extends Blocos{

    GamePanel gp;
    Blocos[] bloco;
    int mapBlocoNumero[][];

    public Map<Point, String> pontosTransicao;


    public BlocosManager(GamePanel gp){
        this.gp = gp;

        bloco = new Blocos[30];
        mapBlocoNumero = new int[gp.maxTelaColunas][gp.maxTelaLinhas];
        pontosTransicao = new HashMap<>();

        getImagemdosBlocos();
        loadMap("/maps/map01.txt");

        // Adicionando pares chave-valor ao HashMap
        pontosTransicao.put(new Point(200, 180), "/maps/map02.txt"); // Saída pelo topo
        pontosTransicao.put(new Point(332, -44), "/maps/map03.txt");  // Saída pela esquerda


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

        }
    }

    public void getImagemdosBlocos(){
        try {

            bloco[0] = new Blocos(); // grama1
            bloco[0].image = ImageIO.read(getClass().getResourceAsStream("/fundo/grama1.png"));

            bloco[1] = new Blocos(); // grama2
            bloco[1].image = ImageIO.read(getClass().getResourceAsStream("/fundo/grama2.png"));

            bloco[2] = new Blocos(); // pedra
            bloco[2].image = ImageIO.read(getClass().getResourceAsStream("/fundo/pedra.png"));

            bloco[3] = new Blocos(); // arvore
            bloco[3].image = ImageIO.read(getClass().getResourceAsStream("/fundo/arvore1.png"));

            bloco[4] = new Blocos(); // arvore
            bloco[4].image = ImageIO.read(getClass().getResourceAsStream("/fundo/arvore2.png"));

            bloco[5] = new Blocos(); // caminho
            bloco[5].image = ImageIO.read(getClass().getResourceAsStream("/fundo/caminho1.png"));

            // -------------  AGUAAAAAS ----------------
            bloco[6] = new Blocos(); // agua centro
            bloco[6].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguacentro.png"));

            bloco[7] = new Blocos(); // agua borda cima esquerda
            bloco[7].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordacimaesquerda.png"));

            bloco[8] = new Blocos();  // agua borda cima
            bloco[8].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordacima.png"));

            bloco[9] = new Blocos(); // agua borda cima direita
            bloco[9].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordacimadireita.png"));

            bloco[10] = new Blocos(); // agua borda esquerda
            bloco[10].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordaesquerda.png"));

            bloco[11] = new Blocos(); // agua borda direita
            bloco[11].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordadireita.png"));

            bloco[12] = new Blocos(); // agua borda  baixo esquerda
            bloco[12].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordabaixoesquerda.png"));

            bloco[13] = new Blocos(); // agua borda baixo direita
            bloco[13].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordabaixodireita.png"));

            bloco[14] = new Blocos(); // agua borda baixo
            bloco[14].image = ImageIO.read(getClass().getResourceAsStream("/fundo/aguabordabaixo.png"));


            // -----------------------------


        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void trocarMapa(Point coordenadas) {
        String novoMapa = pontosTransicao.get(coordenadas);
        System.out.println("ENTREI NO TROCAR DE MAPA");
        if (novoMapa != null) {
            System.out.println("Novo mapa encontrado: " + novoMapa);
            loadMap(novoMapa);
            gp.repaint();
            System.out.println("Mapa trocado para: " + novoMapa);
        }
    }

}



