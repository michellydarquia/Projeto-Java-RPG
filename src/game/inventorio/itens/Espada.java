package game.inventorio.itens;

import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;
import game.personagens.jogador.classes.Druida;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Espada extends Item  {

    public BufferedImage imagem;

    public Espada() {
        super("Espada", "+20 ATAQUE", "EQUIPAR");
    }

    @Override
    public BufferedImage getImagem(){
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Espada.png")));
        }catch (Exception e){
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
            return 20;
        } else if (personagem instanceof Arqueiro) {
            return 5;
        } else if (personagem instanceof Mago) {
            return 5;
        } else if (personagem instanceof Druida) {
            return 10;
        } else {
            return 0;
        }
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0; // Espada não dá bônus de defesa
    }
}
