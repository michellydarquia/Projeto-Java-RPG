import gui.GUI;
import utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        GUI gui = new GUI();

        // Criar uma instância da classe Utils
        Utils utils = new Utils();

        // Chamar o método GerarIntro() usando a instância da classe Utils
        utils.GerarIntro();
    }
}
