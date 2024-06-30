package game.personagens;


import java.awt.image.BufferedImage;

public abstract class Personagem implements Habilidades {

    private int saude;
    private int defesa;
    private int ataque;
    private int nivel;
    private String habilidadeUsada;

    public BufferedImage imagemGrande;


    public void setImagemGrande(BufferedImage imagemGrande) {
        this.imagemGrande = imagemGrande;
    }
    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }

//////////////////////////////////////////////////////////////////////
//    COLOCAR O NOME DAS HABILIDADES NO LUGAR DE ATAQUE 1,2,3 ETC..
//    String[] NomeHabilidadeS = new String[5];

//    String nomeHabilidade1 = "Raio Mágico";
//    String nomeHabilidade2 = "Tormento Mágico";
//    String nomeHabilidade3 = "Bola de Fogo";
//    String nomeHabilidade4 = "Escudo Arcano";
//    String nomeHabilidade6 = "ESPECIAL";
//    String nomeHabilidade5 = "Barreira Elemental";
//////////////////////////////////////////////////////////////////

    public Personagem(int saude, int defesa, int ataque) {

        this.saude = saude;
        this.defesa = defesa;

        this.ataque = ataque;
        this.nivel = 1;


    }


    public void setSaude(int saude) {

        if (saude < 0) {
            this.saude = this.saude + saude;
        }else {
            this.saude = this.saude - saude;
        }
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }


    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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

    public String getHabilidadeUsada() {
        return habilidadeUsada;
    }

    public void setHabilidadeUsada(String habilidadeUsada) {
        if (this.habilidadeUsada == null) {
            habilidadeUsada = ""; // Garantir que a mensagem não seja nula
        }

        this.habilidadeUsada = habilidadeUsada;
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
