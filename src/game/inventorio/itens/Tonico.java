package game.inventorio.itens;

import Exceptions.ExceptionAtributo;
import game.inventorio.Item;
import game.personagens.Personagem;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Druida;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Tonico extends Item {

    public BufferedImage imagem;

    public Tonico() {
        super("Tônico", "+30 ENERGIA", "USAR");
    }

    @Override
    public BufferedImage getImagem() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/Tonico.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @Override
    public void uso(Personagem personagem) {
        int recuperacao = 30;
        if (personagem instanceof Mago) {
            ((Mago) personagem).setMana(recuperacao);
        } else if (personagem instanceof Guerreiro) {
            ((Guerreiro) personagem).setEnergia(recuperacao);
        }
         else if (personagem instanceof Arqueiro) {
            ((Arqueiro) personagem).setEnergia(recuperacao);
        }else if (personagem instanceof Druida){
            ((Druida) personagem).setMana(recuperacao);
        }else {
             throw new ExceptionAtributo("energia");
        }
    }

    @Override
    public int getBonusAtaque(Personagem personagem) {
        return 0; // Tônico não dá bônus de ataque
    }

    @Override
    public int getBonusDefesa(Personagem personagem) {
        return 0; // Tônico não dá bônus de defesa
    }
}
