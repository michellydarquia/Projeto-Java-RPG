package game.personagens.classes;
import game.personagens.BasePersonagem;

public class Ladrao extends BasePersonagem {


    public Ladrao(int nivel) {
        super(80 + (nivel * 4), // saude
                8 + (nivel * 1), // defesa
                15 + (nivel * 3), // ataque
                7 + (nivel * 1), // agilidade
                nivel);
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
