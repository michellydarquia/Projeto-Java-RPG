package game.personagens;


import Exceptions.ExceptionAtributo;
import java.awt.image.BufferedImage;

public abstract class Personagem implements Habilidades {

    private int saude;
    private int defesa;
    private int ataque;
    private int nivel;
    private int experiencia = 0;
    private int subirDeNivel;

    private String habilidadeUsada;
    private boolean usouHabilidade = false;
    private boolean vivo = true;
    public BufferedImage imagemGrande;

    public Personagem(int saude, int defesa, int ataque) {
        this.saude = saude;
        this.defesa = defesa;
        this.ataque = ataque;
        this.nivel = 1;

        subirDeNivel = 200 * nivel;
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



    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void alterarSaude(int valor) {

        int limiteSaude = 100 * nivel;
        int novaSaude = this.saude + valor;


        if (novaSaude < 0) {
            System.out.println("PERDI A BATALHA");
        } else if (novaSaude >  limiteSaude ) {
            this.saude = novaSaude + (limiteSaude - novaSaude);
        } else {
            this.saude = novaSaude;
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
        return saude;
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int valor) {

        if(valor < 0){
            throw new ExceptionAtributo("experiencia");
        } else if (valor == 0) {
            this.experiencia = 0;
        }
        this.experiencia += valor;
        verificadorExperiencia(this.experiencia);
    }
    public void verificadorExperiencia(int experiencia) {

        if(experiencia == this.subirDeNivel ){
            setExperiencia(0);
            this.nivel++;
        }
        subirDeNivel = 200 * nivel;

    }


    public boolean isUsouHabilidade() {
        return usouHabilidade;
    }

    public void setUsouHabilidade(boolean usouHabilidade) {
        this.usouHabilidade = usouHabilidade;
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

    public void setGanhouBatalha(){


    }
    public void setPerdeuBatalha(){

    }




    public String imprimiratributos() {

        return "ATRIBUTOS PERSONAGEM\n" +
                "Nível: " + getNivel() + "\n" +
                " " + "\n" +
                "Saúde: " + getSaude() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

}
