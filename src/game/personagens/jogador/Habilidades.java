package game.personagens.jogador;

import game.personagens.inimigo.Inimigo;

public interface Habilidades {

    void usarHabilidade1(Inimigo inimigo);
    void usarHabilidade2(Inimigo inimigo);
    void usarHabilidade3(Inimigo inimigo);
    void usarHabilidade4(Inimigo inimigo);

    void redefinirAtributos();

    void getImage();
    void getImageGrande();



}
