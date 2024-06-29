package game.personagens.classes;

import game.personagens.BasePersonagem;

public class Druida extends BasePersonagem {

    private int nivel;
    private int energia;

    public Druida() {
        super(80 + (1 * 4), // saude
                8 + (1 * 1), // defesa
                15 + (1 * 3), // ataque
                7 + (1 * 1) // agilidade
                );

        energia = 50;

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
