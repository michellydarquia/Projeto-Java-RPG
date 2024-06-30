package game.personagens.classes;
import game.personagens.Personagem;

public class Arqueiro extends Personagem {

    private int flechas;


    private int nivel;

    public Arqueiro() {
        super(100, 9, 17);

        flechas = 15;

    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
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
    public void getPlayerImage() {

    }

    public String imprimiratributos() {

        return "ATRIBUTOS PERSONAGEM\n" +
                "Nível: " + getNivel() + "\n" +
                " " + "\n" +
                "Energia: " + getFlechas() + "\n" +
                "Saúde: " + getSaude() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n";
    }





}
