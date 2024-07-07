package game.personagens.enemies;

import game.personagens.Inimigo;
import game.personagens.Personagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Goblin extends Inimigo {

    private int acumulo;
    public BufferedImage imagemPrinGoblin;



    public Goblin(int posx, int posy) {
        super(100, 9, 18, posx, posy);
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
            imagemPrinGoblin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinMap.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) {
        g2.drawImage(imagemPrinGoblin, getPosx(), getPosy(), 48, 48, null);
    }

    @Override
    public void usarHabilidade1(Personagem jogador) { // Flecha Venenosa, causa dano e envenena
        setStringHabilidadeUsada("Goblin Archer usa Flecha Venenosa! \nCausa dano e envenena o alvo ( -30 ). ");
        jogador.alterarSaude(-30);
        acumulo += 10;
    }

    @Override
    public void usarHabilidade2(Personagem jogador) { // Ataque Rápido, causa dano duplo
        setStringHabilidadeUsada("Goblin Archer usa Ataque Rápido! \nCausa dano duplo ( -25 ).");
        jogador.alterarSaude(-25);
        acumulo += 10;
    }

    @Override
    public void usarHabilidade3(Personagem jogador) { // Esquiva Ágil, aumenta a agilidade temporariamente
        setStringHabilidadeUsada("Goblin Archer usa Defesa Ágil! \nDefesa aumentada ( +5 ).");
        alterarDefesa(5);
        acumulo += 5;
    }

    @Override
    public void usarHabilidade4(Personagem jogador) {
        if (acumulo == 50) {
            setStringHabilidadeUsada("Goblin Archer usa Golpe Fatal! \nDano causado ( -60 ).");
            jogador.alterarSaude(-60);
        }
    }

    public String imprimirAtributos() {
        return "ATRIBUTOS GOBLIN - Nível: " + getNivel() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n";
    }

    public void getImageGrande() {
        try {
            if (getSaude() >= 80) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinHappyGD.png"))));
            } else if (getSaude() >= 40 && getSaude() < 80) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinAngryGD.png"))));
            } else if (getSaude() < 40) {
                setImagemGrande(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/GoblinStunningGD.png"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
