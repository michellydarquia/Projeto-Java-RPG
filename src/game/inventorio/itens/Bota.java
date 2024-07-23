package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Bota extends Item {

    public BufferedImage imagem;

    public Bota() {
        super("Bota", "+5 DEFESA", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Bota.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {

            personagem.alterarDefesa(getBonusDefesa(personagem));
            personagem.equipar(this);
    }


    @Override
    public int getBonusAtaque(Personagem personagem) {
        return 0;
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 5;
    }

}
