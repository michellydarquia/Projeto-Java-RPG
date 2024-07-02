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
        System.out.println(getHabilidadeUsada());
        jogador.alterarSaude(-30);

    }

    @Override
    public void usarHabilidade2(Personagem jogador) { // Ataque Rápido, causa dano duplo

        setHabilidadeUsada("Goblin Archer usa Ataque Rápido!  \nCausa dano duplo.");
        jogador.alterarSaude(23);


    }

    @Override
    public void usarHabilidade3(Personagem jogador) { // Esquiva Ágil, aumenta a agilidade temporariamente
        setHabilidadeUsada("Goblin Archer usa Defesa Ágil! \nDefesa aumentada temporariamente.");
        alterarDefesa(5);

    }

    @Override
    public void usarHabilidade4(Personagem jogador) {

    }

    public String imprimiratributos() {

        return "ATRIBUTOS GOBLIN - Nível: " + getNivel() + "\n" +
                " " + "\n" +
                " " + "\n" +
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
