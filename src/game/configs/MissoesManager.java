package game.configs;

import game.personagens.jogador.Jogador;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MissoesManager {


    private ArrayList<Missao> missoes;
    private Jogador jogador;
    private BufferedImage backgroundMissao;
    private Utils utils;

    public MissoesManager(Jogador jogador) {
        this.missoes = new ArrayList<>();
        this.jogador = jogador;
        this.utils = new Utils();
        getBlackGroundImage();

        iniciarMissoes();


    }

    public void iniciarMissoes(){
        missoes.add(new Missao(0, "A Caçada ao Xamã Goblin", "Derrote o Xamã Goblin na Floresta Sombria.\nBusque informações do seu covil.", jogador));
        missoes.add(new Missao(1,"Explorar a Floresta Sombria", "Encontre e derrote 1 goblin espalhado pela floresta.", jogador));
        missoes.add(new Missao(2,"Recuperar Artefatos Perdidos", "Encontre 3 artefatos mágicos perdidos.\nConverse com moradores locais para descobrir pistas.", jogador));

        missoes.get(0).setDesbloqueada(true);
        missoes.get(1).setDesbloqueada(true);


    }

    public void verificadorStatusMissao(){

        for (Missao missao : missoes) {
            if(missao.isDesbloqueada() && !missao.isConcluida()) {
                missao.controleMissoes();
            }
        }

    }

    public void drawMissoes(Graphics2D g2) {

        verificadorStatusMissao();

        int x = 190;
        int y = 160;
        int lineHeight = 30; // Altura entre as linhas
        int margin = 20; // Margem ao redor do texto
        int boxWidth = 600; // Largura do box das missões

        g2.drawImage(backgroundMissao,100 ,90 - margin, backgroundMissao.getWidth(), backgroundMissao.getHeight(), null );

        // Configurações de fonte
        Font tituloFont = utils.monogramExtended.deriveFont(Font.BOLD, 20f);
        Font descricaoFont = utils.monogramExtended.deriveFont(Font.PLAIN, 16f);

        for (int i = 0; i < missoes.size(); i++) {
            Missao missao = missoes.get(i);

            if(missoes.get(i).isDesbloqueada()){

                // Desenha o título da missão
                g2.setFont(tituloFont);
                g2.setColor(Color.black);
                g2.drawString(missao.getTitulo(), x, y + (i * lineHeight * 2));

                // Desenha a descrição da missão
                g2.setFont(descricaoFont);
                g2.setColor(Color.darkGray);

                for (String line : missao.getDescricao().split("\n")) {
                    g2.drawString(line, x, y + (i * lineHeight * 2) + lineHeight);
                    y += 10;
                }

                // Desenha o indicador de conclusão
                if (missao.isConcluida()) {
                    g2.setColor(new Color(13, 81, 13)); // cor  suave

                    g2.drawString("[Concluída]", x + boxWidth - 320, y + (i * lineHeight * 2));
                } else {
                    g2.setColor(Color.RED);

                    g2.drawString("[Em Progresso]", x + boxWidth - 320, y + (i * lineHeight * 2));

                }
            }
        }

    }


    public ArrayList<Missao> getMissoes() {
        return missoes;
    }



    public void getBlackGroundImage() {
        try {
            backgroundMissao = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/tall.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
