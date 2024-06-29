package game.personagens;

public abstract class BasePersonagem implements Habilidades {

    private int saude;
    private int defesa;
    private int agilidade;
    private int ataque;
    private int nivel;


    public BasePersonagem(int saude, int defesa, int ataque, int agilidade){

        this.saude = saude;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.ataque = ataque;
        this.nivel = 1;

        imprimiratributos();
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
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

    public int getAgilidade() {
        return agilidade;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getNivel() {
        return nivel;
    }



    public void imprimiratributos(){
        System.out.printf("---------- ATRIBUTOS PERSONAGEM --------- \n" +
                        "Classe: %s \n" +
                        "Saude: %s \n" +
                        "Defesa: %s \n" +
                        "Ataque: %s \n" +
                        "Agilidade: %s \n" +
                        "Nivel: %s \n" +
                        "------------------------------------ \n"
                ,getClass(), getSaude(), getDefesa(), getAtaque(),getAgilidade(),getNivel());
    }


}

