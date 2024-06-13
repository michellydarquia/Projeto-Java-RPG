import game.personagens.classes.Guerreiro;
import game.personagens.classes.Mago;
import gui.GUI;
import utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        GUI gui = new GUI();

        Utils utils = new Utils();

        //utils.GerarIntro();

        Guerreiro guerreiro = new Guerreiro(2);
        Mago mago = new Mago(2);

        System.out.println("Guerreiro:");
        guerreiro.usarHabilidade1();
        guerreiro.usarHabilidade2();
        guerreiro.usarHabilidade3();

        System.out.println("\nMago:");
        mago.usarHabilidade1();
        mago.usarHabilidade2();
        mago.usarHabilidade3();


    }
}
