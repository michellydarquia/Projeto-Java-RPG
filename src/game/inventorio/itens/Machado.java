package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Machado extends Item {

    public BufferedImage imagem;

    public Machado() {
        super("Machado", "+25 ATAQUE ", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Machado.png")));
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
        if (personagem instanceof Guerreiro) {
            return 25;
        } else {
            return 10;
        }
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0; // Machado não dá bônus de defesa
    }
}
