package game.inventorio;

import game.personagens.Personagem;

import java.awt.image.BufferedImage;

public abstract class Item {

    private String nome;
    private String descricao;
    private String tipo;
    private BufferedImage imagem;
    Personagem personagem;

    public Item(String nome, String descricao, String tipo){
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public BufferedImage getImagem(){
        return imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public abstract void uso(Personagem personagem);
    public abstract int getBonusAtaque(Personagem personagem);
    public abstract int getBonusDefesa(Personagem personagem);
}
