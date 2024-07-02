package game.personagens;


import Exceptions.ExceptionAtributo;
import java.awt.image.BufferedImage;

public abstract class Personagem implements Habilidades {

    private int hp;
   private int defesa;
    private int ataque;
    private int nivel;
    private int xp = 0;
    private int subirDeNivel;
    private int limiteSaude;;

    private String habilidadeUsada;
    private boolean usouHabilidade = false;
    private boolean vivo = true;
    public BufferedImage imagemGrande;

    public Personagem(int hp, int defesa, int ataque) {
        this.hp = hp;
        this.defesa = defesa;
        this.ataque = ataque;
        this.nivel = 1;

        subirDeNivel = 200 * nivel;
        limiteSaude = 100 * nivel;
    }

    public void setImagemGrande(BufferedImage imagemGrande) {
        this.imagemGrande = imagemGrande;
    }
    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }


    public void verificadorLimiteAtributos(String atributo, int valor){
        switch (atributo.toLowerCase()){
            case "saude":

            case "defesa":

            case "ataque":

            case "nivel":

            case "experiencia":



        }

    }



    public int getLimiteSaude() {
        return limiteSaude;
    }


    public boolean isVivo() {
        return vivo;
    }

    public void alterarSaude(int valor) {
        int novaSaude = this.hp + valor;
        if (novaSaude < 0) {
            System.out.println("MORREU");
            this.hp = 0;
            vivo = false;
            System.out.println("ESTOU VIVO?: " + isVivo());

        } else if (novaSaude >  limiteSaude ) {
            this.hp = novaSaude + (limiteSaude - novaSaude);
        } else {
            this.hp = novaSaude;
        }
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
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

    public int getSubirDeNivel() {
        return subirDeNivel;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int valor) {

        if(valor < 0){
            throw new ExceptionAtributo("experiencia");
        } else if (valor == 0) {
            this.xp = 0;
        }
        this.xp += valor;
        verificadorExperiencia(this.xp);
    }
    public void verificadorExperiencia(int experiencia) {

        if(experiencia == this.subirDeNivel ){
            setXp(0);
            this.nivel++;
        }
        subirDeNivel = 200 * nivel;

    }



    public String getHabilidadeUsada() {
        return habilidadeUsada;
    }

    public void setHabilidadeUsada(String habilidadeUsada) {
        if (this.habilidadeUsada == null) {
            habilidadeUsada = ""; // Garantir que a mensagem não seja nula
        }

        this.habilidadeUsada = habilidadeUsada;
    }

    public void RedefinirAtributos(){
        this.hp = hp * nivel;
        this.ataque =  ataque * nivel;
        this.defesa = defesa * nivel;
    }




    public String imprimiratributos() {

        return "ATRIBUTOS PERSONAGEM\n" +
                "Nível: " + getNivel() + "\n" +
                " " + "\n" +
                ": " +   "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

}
