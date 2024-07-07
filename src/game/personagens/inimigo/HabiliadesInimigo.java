package game.personagens.inimigo;

import game.personagens.jogador.Personagem;

public interface HabiliadesInimigo {

        void usarHabilidade1(Personagem jogador);

        void usarHabilidade2(Personagem jogador);

        void usarHabilidade3(Personagem jogador);

        void usarHabilidade4(Personagem jogador);

        void getImage();

        void getImageGrande();

        String imprimirAtributos();



}
