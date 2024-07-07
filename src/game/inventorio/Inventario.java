package game.inventorio;
//  gerenciar o inventário e os itens do jogador.


import Menu.GamePanel;
import Menu.KeyHandler;
import game.configs.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    KeyHandler keyH;
    Item item;
    Graphics2D g2;


    private List<Item> itens;
    public boolean inventarioAberto;


    public Inventario(){
        this.itens = new ArrayList<>();

    }

    public void draw(Graphics2D g2){


    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }


    public List<Item> getItens() {
        return itens;
    }


    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
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

