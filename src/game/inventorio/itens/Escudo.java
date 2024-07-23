package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Escudo extends Item {

    public BufferedImage imagem;

    public Escudo() {
        super("Escudo", "+20 DEFESA", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Escudo.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {
        personagem.alterarDefesa(getBonusDefesa(personagem));
        personagem.alterarAtaque(getBonusAtaque(personagem));
        personagem.equipar(this);
    }

    @Override
    public int getBonusAtaque(Personagem personagem) {
        return -5; // Escudo não dá bônus de ataque
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        if (personagem instanceof Guerreiro) {
            return 20;
        } else {
            return 5;
        }
    }
}
