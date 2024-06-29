package game.personagens.classes;
import game.personagens.BasePersonagem;

public class Arqueiro extends BasePersonagem {

    private int flechas;
    private int nivel;

    public Arqueiro() {
        super(80 + (1 * 4), // saude
                8 + (1 * 1), // defesa
                15 + (1 * 3), // ataque
                7 + (1 * 1));// agilidade);

        flechas = 15;


    }

    @Override
    public void usarHabilidade1() {

    }

    @Override
    public void usarHabilidade2() {

    }

    @Override
    public void usarHabilidade3() {

    }



}
