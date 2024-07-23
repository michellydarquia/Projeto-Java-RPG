package game.personagens;

public interface Habilidades {

    void usarHabilidade1(Personagem alvo);

    void usarHabilidade2(Personagem alvo);

    void usarHabilidade3(Personagem alvo);

    void usarHabilidade4(Personagem alvo);

    void redefinirAtributos();


    //OBTER AS IMAGENS DE CADA CLASSE
    void getImage();

    default String imprimiratributos() {
        return null;
    }

    default void mudarImageGrande() {

    }

    default String getHabilidade1() {
        return null;
    }default String getHabilidade2() {
        return null;
    }default String getHabilidade3() {
        return null;
    }default String getHabilidade4() {
        return null;
    }

}



