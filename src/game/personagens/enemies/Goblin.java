package game.personagens.enemies;

import game.personagens.Personagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Goblin extends Personagem {

    private int acumulo;
    public BufferedImage imagemPrinGoblin;


    public Goblin() {
        super(100, 9, 18);

        getImageGrande();
        acumulo = 0;

        getGerarImagem();

    }


    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }

    public BufferedImage getImagem() {
        return imagemGrande;
    }


    public void getGerarImagem() {
        try {
            int gerearimagemaleatoria = (int) (Math.random() * 3);
            switch (gerearimagemaleatoria) {
                case 0:
                    imagemPrinGoblin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinMap.png")));
                    break;
                case 1:
                    imagemPrinGoblin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinArcher1.png")));
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g2) {
        g2.drawImage(imagemPrinGoblin, 0, 0, 48, 48, null);
    }




    @Override
    public void usarHabilidade1(Personagem jogador) { // Flecha Venenosa, causa dano e envenena

        setStringHabilidadeUsada("Goblin Archer usa Flecha Venenosa! \nCausa dano e envenena o alvo ( -20 ). ");
        jogador.alterarSaude(-30);
        acumulo += 10;
    }

    @Override
    public void usarHabilidade2(Personagem jogador) { // Ataque Rápido, causa dano duplo

        setStringHabilidadeUsada("Goblin Archer usa Ataque Rápido!  \nCausa dano duplo ( - 25 )");
        jogador.alterarSaude(-25);
        acumulo += 10;
    }

    @Override
    public void usarHabilidade3(Personagem jogador) { // Esquiva Ágil, aumenta a agilidade temporariamente
        setStringHabilidadeUsada("Goblin Archer usa Defesa Ágil! \nDefesa aumentada ( +5 )).");
        alterarDefesa(5);
        acumulo += 5;
    }

    @Override
    public void usarHabilidade4(Personagem jogador) {
        if(acumulo == 50) {
            setStringHabilidadeUsada("Goblin Archer usa Golpe Fatal! \nDano causado ( - 60 )).");
            jogador.alterarSaude(-60);
        }
    }

    @Override
    public void getImage() {

    }

//    @Override
//    public void getImageGrande() {
//
//    }

    public String imprimiratributos() {

        return "ATRIBUTOS GOBLIN - Nível: " + getNivel() + "\n" +
                " " + "\n" +
                " " + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


    public void getImageGrande() {
        try {

            if (getSaude() >= 80) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinHappyGD.png"))));

            }
            if (getSaude() >= 40 &&  getSaude() < 80) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinAngryGD.png"))));

            }
            if (getSaude() < 40) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinStunningGD.png"))));

            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
