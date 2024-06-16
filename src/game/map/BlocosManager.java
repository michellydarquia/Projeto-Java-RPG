package game.map;

import gui.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BlocosManager extends Blocos{

    GamePanel gp;
    Blocos[] bloco;

    public BlocosManager(GamePanel gp){
        this.gp = gp;

        bloco = new Blocos[10];

        getImagemdosBlocos();

    }
   // grama1, grama2, agua1, arvore, pedra

    public void getImagemdosBlocos(){
        try {

            bloco[0] = new Blocos(); // grama1
            bloco[0].image = ImageIO.read(getClass().getResourceAsStream("/fundo/grama1.png"));

            bloco[1] = new Blocos(); // grama2
            bloco[1].image = ImageIO.read(getClass().getResourceAsStream("/fundo/grama2.png"));

            bloco[2] = new Blocos(); // agua
            bloco[2].image = ImageIO.read(getClass().getResourceAsStream("/fundo/agua.png"));

            bloco[3] = new Blocos(); // arvore
            bloco[3].image = ImageIO.read(getClass().getResourceAsStream("/fundo/arvore.png"));

            bloco[4] = new Blocos(); // pedra
            bloco[4].image = ImageIO.read(getClass().getResourceAsStream("/fundo/pedra.png"));


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

            g2.drawImage(bloco[0].image, x,y, gp.sizeLadrilho, gp.sizeLadrilho, null);
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



