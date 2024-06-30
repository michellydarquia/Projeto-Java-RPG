package game.personagens.enemies;

import game.personagens.Personagem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GoblinArcher extends Personagem {

    public GoblinArcher() {
        super(100, 9 , 18  );

        getPlayerImage();
        System.out.println(imprimiratributos());


        System.out.println(getImagemGrande());

    }


    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }




    @Override
    public void usarHabilidade1(Personagem jogador) { // Flecha Venenosa, causa dano e envenena

        setHabilidadeUsada("Goblin Archer usa Flecha Venenosa! \nCausa dano e envenena o alvo ( -20 ). ");
        jogador.setSaude(-20);

    }

    @Override
    public void usarHabilidade2() { // Ataque Rápido, causa dano duplo
        System.out.println("Goblin Archer usa Ataque Rápido! Causa dano duplo.");

    }

    @Override
    public void usarHabilidade3() { // Esquiva Ágil, aumenta a agilidade temporariamente
        System.out.println("Goblin Archer usa Esquiva Ágil! Agilidade aumentada temporariamente.");
    }

    public String imprimiratributos() {

        return "ATRIBUTOS GOBLIN \n" +
                "Nível: " + getNivel() + "\n" +
                " " + "\n" +
                "Saúde: " + getSaude() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


    public void getPlayerImage() {
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

//            setImagemGrande(ImageIO.read(getClass().getResourceAsStream("/enemies/GoblinArcher1.png")));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
