package game.inventorio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Item {

    private String nome;
    private String descricao;
    private BufferedImage imagem;

    public Item(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;

        getImagem();

    }

    private void getImagem(){

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/ArtefatoMagico.png")));

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }








}
