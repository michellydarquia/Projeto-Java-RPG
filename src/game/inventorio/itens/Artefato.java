package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Artefato extends Item {

    public BufferedImage imagem;

    public Artefato() {
        super("Artefato Magico", "Enfraquece o Xamã","USAR");

    }

    @Override
    public BufferedImage getImagem(){
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Artefato.png")));

        }catch (Exception e){
            e.printStackTrace();
        }

        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {

    }

    @Override
    public int getBonusAtaque(Personagem personagem) {
        return 0;
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0;
    }

}
