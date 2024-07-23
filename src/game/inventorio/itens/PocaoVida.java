package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class PocaoVida extends Item {

    public BufferedImage imagem;

    public PocaoVida() {
        super("Poção de Vida", "+50 HP", "USAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/PocaoVida.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {
        int cura = 50;
        personagem.setHp(cura);
    }

    @Override
    public int getBonusAtaque(Personagem personagem) {
        return 0; // Poção de Vida não dá bônus de ataque
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0; // Poção de Vida não dá bônus de defesa
    }
}
