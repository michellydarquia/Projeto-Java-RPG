package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Peitoral extends Item {

    public BufferedImage imagem;

    public Peitoral() {
        super("Peitoral", "+30 DEFESA", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Peitoral.png")));
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
        return 0; // Peitoral não dá bônus de ataque
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 30; // Peitoral dá +30 de defesa
    }
}
