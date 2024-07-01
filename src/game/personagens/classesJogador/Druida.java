package game.personagens.classesJogador;

import game.personagens.Personagem;

public class Druida extends Personagem {

    private int nivel;
    private int energia;


    public Druida() {
        super(100, 9, 17);

        energia = 50;

    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }


    @Override
    public void usarHabilidade1(Personagem inimigo) {

    }

    @Override
    public void usarHabilidade2() {

    }

    @Override
    public void usarHabilidade3() {

    }

    @Override
    public void usarHabilidade4() {

    }

    @Override
    public void getPlayerImage() {

    }

    public String imprimiratributos() {

        return "ATRIBUTOS PERSONAGEM\n" +
                "Nível: " + getNivel() + "\n" +
                " " + "\n" +
                "Energia: " + getEnergia() + "\n" +
                "Saúde: " + getSaude() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


}
