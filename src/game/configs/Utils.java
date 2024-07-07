package game.configs;

import game.personagens.inimigo.Inimigo;
import game.personagens.jogador.Personagem;
import Menu.GamePanel;

import java.awt.*;

public class Utils {


    GamePanel gp;

    public Utils(GamePanel gp){
        this.gp = gp;
    }


    public void drawMensagem(Graphics2D g2, Object  personagem) {
        String mensagem = "";

        if (personagem instanceof Personagem) {
            mensagem = ((Personagem) personagem).getStringHabilidadeUsada();
        } else if (personagem instanceof Inimigo) {
            mensagem = ((Inimigo) personagem).getStringHabilidadeUsada();
        }

        if (mensagem != null && !mensagem.isEmpty()) {

            Font fonte = gp.monogramExtended.deriveFont((float) 35);

            g2.setFont(fonte);

            if (personagem instanceof Personagem) {
                g2.setColor(Color.white);
            } else {
                g2.setColor(Color.black);
            }

            int x = 150;
            int y = 490;

            for (String line : mensagem.split("\n")) {
                g2.drawString(line, x, y); // Ajuste as coordenadas conforme necessário
                y += 20;
            }
        }
    }


    public void drawRetanguloTranslucidoComBordas(Graphics2D g2, int x, int y, int width, int height) {

        int arcWidth = 20 ;
        int arcHeight = 20 ;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AlphaComposite originalComposite = (AlphaComposite) g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

        g2.setComposite(originalComposite);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }


}
