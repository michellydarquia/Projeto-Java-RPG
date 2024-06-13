package utils;

import java.util.concurrent.TimeUnit;

public class Utils {

    public void GerarIntro() throws InterruptedException {
        String intro = "BEM VINDOS AO MUNDO DE FANTASIA";
        Escrevendotextos(intro);

    }

    public void gerartextos(String texto){
        System.out.println(texto);

    }

    private void Escrevendotextos(String texto) throws InterruptedException {
        for (char letra: texto.toCharArray()){
            System.out.print(letra);
            System.out.flush();
            TimeUnit.MILLISECONDS.sleep(220); // Reduzindo o tempo de espera para 80 milissegundos
        }
    }
    


}




