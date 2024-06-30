package game.personagens.classes;
import game.personagens.Personagem;

public class Guerreiro extends Personagem {

    private int energia;


    public Guerreiro() {
        // iniciando com esses atributos
        super(100, 17, 15); // ataque

        energia = 100;


    }

    @Override
    public void usarHabilidade1(Personagem inimigo) {
        // Ataque Físico: Dano Moderado, não consome energia



        if (energia < 10) {

            System.out.println("Energia insuficiente para usar Ataque Físico!");


        } else {
            setEnergia(-10);
            System.out.println("Guerreiro usa Ataque Físico!");

        }
    }


    @Override
    public void usarHabilidade2() {
        // Proteção: Aumenta Defesa Temporária, recupera energia


        setEnergia(20);
        System.out.println("Guerreiro usa Proteção! Defesa aumentada temporariamente e energia recuperada.");
    }

    @Override
    public void usarHabilidade3() {
        // Investida: Ataque Poderoso com Redução de Defesa do Alvo, consome energia



        if (energia >= 30) {
            setEnergia(-30);

            System.out.println("Guerreiro usa Investida! Ataque poderoso com redução de defesa do alvo.");
        } else {

            System.out.println("Energia insuficiente para usar Investida!");
        }
    }

    @Override
    public void getPlayerImage() {

    }


    public void setEnergia(int energia) {

            this.energia +=  energia;

    }

    public int getEnergia() {
        return energia;
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
