package game.configs;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public Font monogramExtended;
    public Font OldLondon;
    private Font fonte;

    public Utils(){

        iniciandoFonte();
    }


    public void drawMensagem(Graphics2D g2, String mensagem) {

        g2.setFont(monogramExtended.deriveFont((float) 35));
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
            InputStream is = getClass().getResourceAsStream("/fonte/monogramExtended.ttf");
            monogramExtended = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonte/OldLondon.ttf");
            OldLondon = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
