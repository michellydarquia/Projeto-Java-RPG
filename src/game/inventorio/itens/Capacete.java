package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Capacete extends Item {

    public BufferedImage imagem;

    public Capacete() {
        super("Capacete", "+10 DEFESA", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Capacete.png")));
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
        return 0; // Capacete não dá bônus de ataque
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 10; // Capacete dá +10 de defesa
    }
}
