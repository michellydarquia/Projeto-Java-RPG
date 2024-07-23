package game.personagens.inimigo.classes;

import game.personagens.inimigo.Inimigo;
import game.personagens.Personagem;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Goblin extends Inimigo {

    public BufferedImage imagemPrinGoblin;
    public BufferedImage imagem1;
    public BufferedImage imagem2;

    public Goblin(int posx, int posy) {
        super(60, 8, 12, posx, posy);
        mudarImageGrande();
        getGerarImagem();
    }

    public void draw(Graphics g2) {
        int contador = (int) (Math.random() * 20);
        switch (contador) {
            case 1:
                imagemPrinGoblin = imagem1;
                break;
            case 0:
                imagemPrinGoblin = imagem2;
                break;
        }
        g2.drawImage(imagemPrinGoblin, getPosx(), getPosy(), 48, 48, null);
    }

    @Override
    public void usarHabilidade1(Personagem inimigo) {
        setStringHabilidadeUsada("Goblin usa Ataque Veloz! \nDano aplicado (+10).");
        inimigo.alterarSaude(-calcularDano(inimigo, 10));
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Goblin se esconde, \nAumentando sua defesa!");
        this.alterarDefesa(5);
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        setStringHabilidadeUsada("Goblin lança uma Pedra! \nDano aplicado (+15).");
        inimigo.alterarSaude(-calcularDano(inimigo, 15));
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        setStringHabilidadeUsada("Goblin usa Investida Selvagem! \nDano aplicado (+20).");
        inimigo.alterarSaude(-calcularDano(inimigo, 20));
        setUsouHabilidade(true);
    }

    public void getGerarImagem() {
        try {
            imagem1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/Goblin1.png")));
            imagem2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemies/Goblin2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getImage() {}

    public String imprimiratributos() {
        return "GOBLIN - Nível: " + getNivel() + "\n" +
                " " + "\n" +
                " " + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n";
    }

    public void mudarImageGrande() {
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

    // calcular o dano baseado no ataque e defesa
    private int calcularDano(Personagem inimigo, int bonus) {
        int danoBase = this.getAtaque() + bonus - inimigo.getDefesa();
        return Math.max(danoBase, 0); // O dano não pode ser negativo
    }
}
