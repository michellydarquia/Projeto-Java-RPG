package game.map;

import Menu.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BlocosManager extends Blocos{

    GamePanel gp;
    Blocos[] bloco;
    int mapBlocoNumero[][];

    public BlocosManager(GamePanel gp){
        this.gp = gp;

        bloco = new Blocos[10];
        mapBlocoNumero = new int[gp.maxTelaColunas][gp.maxTelaLinhas];

        getImagemdosBlocos();
        loadMap("/maps/map01.txt");

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

            bloco[2] = new Blocos(); // agua
            bloco[2].image = ImageIO.read(getClass().getResourceAsStream("/fundo/agua.png"));

            bloco[3] = new Blocos(); // arvore
            bloco[3].image = ImageIO.read(getClass().getResourceAsStream("/fundo/arvore1.png"));

            bloco[4] = new Blocos(); // arvore
            bloco[4].image = ImageIO.read(getClass().getResourceAsStream("/fundo/arvore2.png"));

            bloco[5] = new Blocos(); // pedra
            bloco[5].image = ImageIO.read(getClass().getResourceAsStream("/fundo/pedra.png"));


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
}



