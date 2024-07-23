package game.configs;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public Font monogramExtended;
    public Font OldLondon;

    public Utils(){
        iniciandoFonte();
    }


    public void drawMensagem(Graphics2D g2, String mensagem) {

        g2.setFont(monogramExtended.deriveFont((float) 20));
        g2.setColor(Color.WHITE);
        int y = 450;
        int x = 60;

        for (String linha : mensagem.split("\n")) {
            g2.drawString(linha, x, y);
            y += 20;
        }
    }



    public void drawRetanguloTranslucidoComBordas(Graphics2D g2, int x, int y, int width, int height, float transparencia) {

        int arcWidth = 20 ;
        int arcHeight = 20 ;

//        transparencia = 0.5f;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AlphaComposite originalComposite = (AlphaComposite) g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

        g2.setComposite(originalComposite);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }


    public void iniciandoFonte() {
        try {
            monogramExtended = new Font("Times New Roman", Font.PLAIN, 15);
            OldLondon = new Font("SansSerif", Font.PLAIN, 20);   // Fonte padrão SansSerif
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
