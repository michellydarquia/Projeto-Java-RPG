package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Arqueiro;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Arco extends Item {

    public BufferedImage imagem;

    public Arco() {
        super("Arco", "+15 ATAQUE (Arqueiro)", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Arco.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {
        personagem.alterarAtaque(getBonusAtaque(personagem));
        personagem.equipar(this);

    }

    @Override
    public int getBonusAtaque(Personagem personagem) {
        if (personagem instanceof Arqueiro) {
            return 15;
        } else {
            return 5;
        }
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0; // Arco não dá bônus de defesa
    }
}
