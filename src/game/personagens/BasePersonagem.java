package game.personagens;

public abstract class BasePersonagem {

        private int saude;
        private int defesa;
        private int agilidade;
        private int ataque;
        private int nivel;



    public BasePersonagem(int saude, int defesa, int ataque, int agilidade, int nivel){

        this.saude = saude;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.ataque = ataque;
        this.nivel = nivel;

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


    public abstract void usarHabilidade1();
    public abstract void usarHabilidade2();
    public abstract void usarHabilidade3();

    public void imprimiratributos(){
        System.out.printf("---------- ATRIBUTOS PERSONAGEM --------- \n" +
                        "Saude: %s \n" +
                        "Defesa: %s \n" +
                        "Ataque: %s \n" +
                        "Agilidade: %s \n" +
                        "Nivel: %s \n" +
                        "------------------------------------ \n"
                , getSaude(), getDefesa(), getAtaque(),getAgilidade(),getNivel());
    }


}

