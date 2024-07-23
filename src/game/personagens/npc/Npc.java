package game.personagens.npc;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Npc {
    private final int posx;
    private final int posy;
    private BufferedImage imagemPrinciapl;
    private BufferedImage imagem1;
    private BufferedImage imagem2;
    private BufferedImage imagem3;
    private BufferedImage imagem4;
    private String[] dialogos;

    private int dialogoAtual;


    public Npc(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;

        getGerarImagem();
        definirDialogos();
    }

    public void getGerarImagem() {
        try {
            imagem1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/girl.png")));
            imagem2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/girll.png")));
            imagem3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/girll1.png")));
            imagem4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/girl1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) {
        int contador = (int) (Math.random() * 70);
        switch (contador) {
            case 0:
                imagemPrinciapl = imagem1;
                break;
            case 1:
                imagemPrinciapl = imagem2;
                break;
            case 2:
                imagemPrinciapl = imagem3;
                break;
            case 3:
                imagemPrinciapl = imagem4;
                break;
        }
        g2.drawImage(imagemPrinciapl, getPosx(), getPosy(), 48, 48, null);
    }

    public void definirDialogos() {
        dialogos = new String[]{
                "Os goblins são terríveis, eles estão pela floresta.",
                "Por que você não está fazendo nada a respeito?"
        };
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public String getDialogo() {
        if (dialogoAtual < dialogos.length) {
            return dialogos[dialogoAtual++];
        } else {
            dialogoAtual = 0; // Reinicia o diálogo após a última fala
            return dialogos[dialogoAtual];
        }
    }

    public String[] getDialogos() {
        return dialogos;
    }

}
