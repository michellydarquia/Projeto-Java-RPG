package game.personagens.inimigo;

import game.personagens.Habilidades;
import game.personagens.Personagem;

import java.awt.image.BufferedImage;

public abstract class Inimigo extends Personagem implements Habilidades {

    private int nivel;
    public BufferedImage imagemGrande;
    public int limiteHp;
    private int posx;
    private int posy;

    public Inimigo(int hp, int defesa, int ataque, int posx, int posy) {
        super(hp,defesa,ataque);
        this.posx = posx;
        this.posy = posy;
        this.nivel = 1;
        limiteHp =hp*nivel;
    }


    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public void redefinirAtributos(){
        alterarDefesa(100 * nivel);
        setHp(getLimiteHp());
        setAtaque(100 * nivel);
    }


    public int getLimiteHp() {
        return limiteHp;
    }

}
