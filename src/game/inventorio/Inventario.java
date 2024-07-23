package game.inventorio;

import Estados.KeyHandler;
import game.configs.Utils;
import game.personagens.jogador.Jogador;
import game.personagens.jogador.classes.Arqueiro;
import game.personagens.jogador.classes.Druida;
import game.personagens.jogador.classes.Guerreiro;
import game.personagens.jogador.classes.Mago;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Inventario {

    KeyHandler keyH;
    Item item;
    Graphics2D g2;
    Utils utils;
    private int comandomenu = 0;
    private int sizeInventario;
//    BufferedImage[] imagens;
    boolean esperandoConfirmacao = false;
    private List<BufferedImage> imagens;
    private List<Item> itensDoInventario;
    private boolean inventarioAberto = false;
    private boolean equipado = false;
    Jogador jogador;

    public Inventario(Jogador jogador) {
        this.itensDoInventario = new ArrayList<>();
        sizeInventario = 9;
        this.imagens = new ArrayList<>();
        this.utils = new Utils();
        this.jogador = jogador;
    }

    public void draw(Graphics2D g2) {
        gerarImagensItens();
        telaInventario(g2);

    }

    public void telaInventario(Graphics2D g2) {
        int sizeLadrilho = 16 * 3;
        int y = sizeLadrilho + 180;
        int xDaBorda = 80;
        int itemSize = 48;
        int colunas = 6;
        int linhas = 3;

        int larguraRetangulo = colunas * (itemSize + 15) - 15 + 20;
        int alturaRetangulo = linhas * (itemSize + 15) - 15 + 20;
        utils.drawRetanguloTranslucidoComBordas(g2, xDaBorda, y - 3, larguraRetangulo, alturaRetangulo, 0.9f);

        //  itens em uma grade de 6 colunas por 3 linhas
        for (int i = 0; i < imagens.size(); i++) {
            if (imagens.get(i) != null) {
                int x = xDaBorda + (i % colunas) * (itemSize + 15);
                int yOffset = y + (i / colunas) * (itemSize + 15);
                g2.drawImage(imagens.get(i), x, yOffset, itemSize, itemSize, null);

                if (i == comandomenu) {
                    g2.setColor(Color.WHITE);
                    g2.drawRect(x - 1, yOffset - 1, itemSize + 4, itemSize + 4);
                    g2.drawRoundRect(x - 1, yOffset - 1, itemSize + 4, itemSize + 4, 20, 20);

                    Font fonte = utils.monogramExtended.deriveFont((float) 30);
                    g2.setFont(fonte);

                    String descricao = itensDoInventario.get(comandomenu).getDescricao();
                    utils.drawRetanguloTranslucidoComBordas(g2, xDaBorda, y - 150, 230, sizeLadrilho * 2, 0.9f);
                    g2.drawString(descricao, xDaBorda + 20, y - 80);
                }
            }
        }

        if (esperandoConfirmacao) {
            utils.drawRetanguloTranslucidoComBordas(g2, xDaBorda, y + 200, 170, sizeLadrilho - 10, 0.9f);
            g2.setColor(Color.WHITE);

            Item itemSelecionado = itensDoInventario.get(comandomenu);
            String acao = "";

            if (jogador.getClassePersonagem().getEquipamentos().contains(itemSelecionado)) {
                equipado = true;
                acao = "DESEQUIPAR";
            } else {
                equipado = false;
                 acao = itensDoInventario.get(comandomenu).getTipo();

            }

            Font fonte = utils.monogramExtended.deriveFont((float) 30);
            g2.setFont(fonte);
            g2.drawString(acao, xDaBorda + 50, y + 225);
        }

        infoPersonagem(g2);
    }

    public void infoPersonagem(Graphics2D g2) {
        int sizeLadrilho = 16 * 3;
        int y = sizeLadrilho + 60;
        int xDaBorda = 500;
        int itemSize = 48;

        // Desenha o retângulo de fundo
        utils.drawRetanguloTranslucidoComBordas(g2, xDaBorda, y, 200, sizeLadrilho + 330, 0.9f);

        Font fonte = utils.monogramExtended.deriveFont((float) 25);
        g2.setFont(fonte);
        g2.setColor(Color.WHITE);

        // Imprime os atributos do personagem
        String[] atributos = imprimiratributosinventario().split("\n");
        int yOffset = y + 30;

        for (String linha : atributos) {
            g2.drawString(linha, xDaBorda + 10, yOffset);
            yOffset += 20; // Espaçamento entre linhas
        }

        yOffset += 20;
        g2.drawString("Itens Equipados:", xDaBorda + 10, yOffset);

        yOffset += 20;
        ArrayList<Item> equipamentos = jogador.getClassePersonagem().getEquipamentos();

        int xPos = xDaBorda + 10;
        int yPos = yOffset;

        for (int i = 0; i < equipamentos.size(); i++) {
            Item item = equipamentos.get(i);
            if (item != null) {
                g2.drawImage(item.getImagem(), xPos, yPos, itemSize, itemSize, null);

                if ((i + 1) % 2 == 0) {
                    // Depois de duas colunas, vai para a próxima linha
                    xPos = xDaBorda + 10;
                    yPos += itemSize + 10; // Movendo para baixo
                } else {
                    // Próxima coluna
                    xPos += itemSize + 10;
                }
            }
        }
    }

    public String imprimiratributosinventario() {
        int energia = 0;
        String nomeenergia = "";

        if (jogador.getClassePersonagem() instanceof Guerreiro) {
            energia = ((Guerreiro) jogador.getClassePersonagem()).getEnergia();
            nomeenergia = "Energia";
        } else if (jogador.getClassePersonagem() instanceof Arqueiro) {
            energia = ((Arqueiro) jogador.getClassePersonagem()).getEnergia();
            nomeenergia = "Flechas";
        } else if (jogador.getClassePersonagem() instanceof Mago) {
            energia = ((Mago) jogador.getClassePersonagem()).getMana();
            nomeenergia = "Mana";
        } else if (jogador.getClassePersonagem() instanceof Druida) {
            energia = ((Druida) jogador.getClassePersonagem()).getMana();
            nomeenergia = "Essência Natural";
        }

        return jogador.getClassePersonagem().getClass().getSimpleName() + "\n" +
                "\n" +
                "Nível: " + jogador.getClassePersonagem().getNivel() + "  " +
                jogador.getClassePersonagem().getXp() + "/" + jogador.getClassePersonagem().getSubirDeNivel() + "\n" +
                "Defesa: " + jogador.getClassePersonagem().getDefesa() + "\n" +
                "Ataque: " + jogador.getClassePersonagem().getAtaque() + "\n" +
                nomeenergia + ": " + energia;
    }

    public void adicionarItem(Item item) {

        if (itensDoInventario.size() < sizeInventario){
            itensDoInventario.add(item);
        }else {
            System.out.println("LIMITE DO INVENTARIO");
        }
    }

    public void removerItem(Item item) {
        itensDoInventario.remove(item);
    }

    public List<Item> getItens() {
        return itensDoInventario;
    }

    public boolean isInventarioAberto() {
        return inventarioAberto;
    }

    public void setInventarioAberto(boolean inventarioAberto) {
        this.inventarioAberto = inventarioAberto;
    }

    public void setSizeInventario(int sizeInventario) {
        this.sizeInventario = sizeInventario;
    }

    public int procurarItens(Class<? extends  Item > tipoItem){
        int quantidade=0;

        for (Item item : itensDoInventario) {
            if(tipoItem.isInstance(item)){
                quantidade++;
            }
        }
        return quantidade;
    }


    public void gerarImagensItens() {
        imagens.clear();
        for (int i = 0; i < itensDoInventario.size(); i++) {
            Item item = itensDoInventario.get(i);
            if (item != null) {
                imagens.add(item.getImagem());
            }
        }
    }

    public void navigateInventario(int code) {

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            comandomenu--;
            if (comandomenu < 0) {
                comandomenu = imagens.size() - 1;
            }
            esperandoConfirmacao = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            comandomenu++;
            if (comandomenu >= imagens.size()) {
                comandomenu = 0;
            }
            esperandoConfirmacao = false;
        }
        if (code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE) {
            setInventarioAberto(false);
        }

            if (code == KeyEvent.VK_ENTER) {
            if (esperandoConfirmacao) {
                if (comandomenu >= 0 && comandomenu < itensDoInventario.size()) {
                    Item itemSelecionado = itensDoInventario.get(comandomenu);
                    if (equipado) {
                        jogador.getClassePersonagem().desequipar(itemSelecionado);
                    } else {
                        itemSelecionado.uso(jogador.getClassePersonagem());
                    }
                    esperandoConfirmacao = false;
                }
            } else {
                esperandoConfirmacao = true;
            }

        }

    }


}