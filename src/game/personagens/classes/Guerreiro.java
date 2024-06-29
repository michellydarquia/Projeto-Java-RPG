package game.personagens.classes;
import game.personagens.BasePersonagem;

public class Guerreiro extends BasePersonagem{

    private int energia;

    public Guerreiro() {
        // iniciando com esses atributos
        super(100+(1*5), // saude
               15+(1*2), // defesa
                10+(1*3), // ataque
                5 +(1*1)); // agilidade

        energia = 100;


    }

    @Override
    public void usarHabilidade1() {
        // Ataque Físico: Dano Moderado, não consome energia

        imprimirenergia();

        if (energia < 10) {

            System.out.println("Energia insuficiente para usar Ataque Físico!");
            imprimirenergia();

        } else {
            setEnergia(-10);
            System.out.println("Guerreiro usa Ataque Físico!");
            imprimirenergia();
        }
    }


    @Override
    public void usarHabilidade2() {
        // Proteção: Aumenta Defesa Temporária, recupera energia

        imprimirenergia();
        setEnergia(20);
        System.out.println("Guerreiro usa Proteção! Defesa aumentada temporariamente e energia recuperada.");
    }

    @Override
    public void usarHabilidade3() {
        // Investida: Ataque Poderoso com Redução de Defesa do Alvo, consome energia

        imprimirenergia();

        if (energia >= 30) {
            setEnergia(-30);
            imprimirenergia();
            System.out.println("Guerreiro usa Investida! Ataque poderoso com redução de defesa do alvo.");
        } else {
            imprimirenergia();
            System.out.println("Energia insuficiente para usar Investida!");
        }
    }

    public void setEnergia(int energia) {

            this.energia +=  energia;

    }

    public int getEnergia() {
        return energia;
    }

    public void imprimirenergia(){
        System.out.println("Energia: " + getEnergia());
    }


}
