package game.personagens.inimigo;

import java.awt.image.BufferedImage;

public abstract class Inimigo implements HabiliadesInimigo {

    private int hp;
    private int defesa;
    private int ataque;
    private int nivel;
    private String stringHabilidadeUsada;
    private boolean usouHabilidade = false;
    private boolean vivo = true;
    public BufferedImage imagemGrande;
    public int limiteSaude;
    private int posx;
  private int posy;

    public Inimigo(int hp, int defesa, int ataque, int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.hp = hp;
        this.defesa = defesa;
        this.ataque = ataque;
        this.nivel = 1;
        limiteSaude =hp*nivel;
    }

    public void setImagemGrande(BufferedImage imagemGrande) {
        this.imagemGrande = imagemGrande;
    }

    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }


    public void redefinirAtributos(){

        setDefesa(100 * nivel);
        setHp(getLimiteSaude());
        setAtaque(100 * nivel);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }


    public void alterarSaude(int valor) {
        int novaSaude = this.hp + valor;
        if (novaSaude < 0) {
            this.hp = 0;
            vivo = false;
        } else {
            this.hp = novaSaude;
        }
    }

    public void setDefesa(int valor) {
        int novaDefesa = this.defesa + valor;
        int limiteDefesa = 30;

        if (novaDefesa > limiteDefesa) {
            this.defesa = limiteDefesa;
        } else {
            this.defesa = novaDefesa;
        }
    }

    public void alterarDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void alterarAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getSaude() {
        return hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getNivel() {
        return nivel;
    }


    public String getStringHabilidadeUsada() {
        return stringHabilidadeUsada;
    }

    public void setStringHabilidadeUsada(String habilidadeUsada) {
        if (habilidadeUsada == null) {
            habilidadeUsada = ""; // Garantir que a mensagem não seja nula
        }
        this.stringHabilidadeUsada = habilidadeUsada;
    }


    public int getLimiteSaude() {
        return limiteSaude;
    }






    @Override
    public void getImage() {

    }

    @Override
    public void getImageGrande() {

    }
}
