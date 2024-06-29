package game.personagens.classes;
import game.personagens.BasePersonagem;


public class Mago extends BasePersonagem {

    private int mana;
    private int nivel;


    public Mago() {
        super(80 + (1 * 4), // saude
                8 + (1 * 1), // defesa
                15 + (1 * 3), // ataque
                7 + (1 * 1));// agilidade);

        mana = 50 + (nivel * 5) / 100;

    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana += mana;
    }


    @Override
    public void usarHabilidade1() { // Raio Mágico, -20 mana

        if (mana < 20) {
            System.out.println("Mana insuficiente");
        } else {
            setMana(-20);
            System.out.println("Mago usa Raio Mágico!");
        }
    }

    @Override
    public void usarHabilidade2() { // Escudo Arcano, +20 mana

        setDefesa(20);
        setMana(30);
        System.out.println("Mago usa Escudo Arcano! Defesa aumentada temporariamente e mana recuperada.");
    }


    @Override
    public void usarHabilidade3() {

        if (mana < 40) {
            System.out.println("Mana insuficiente para usar Barreira Elemental!");
        } else {
            setMana(-40);
            System.out.println("Mago usa Barreira Elemental! Dano recebido reduzido por um turno.");
        }
    }


}
