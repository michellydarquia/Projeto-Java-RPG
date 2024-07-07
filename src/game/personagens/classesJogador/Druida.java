package game.personagens.classesJogador;

import game.personagens.Inimigo;
import game.personagens.Personagem;

public class Druida extends Personagem {

    private int essenciaNatural;

    public Druida() {
        super(100, 9, 17);
        essenciaNatural = 70;
    }

    public int getEssenciaNatural() {
        return essenciaNatural;
    }

    public void setEssenciaNatural(int energia) {
        this.essenciaNatural = energia;
    }

    @Override
    public void usarHabilidade1(Inimigo inimigo) {
        if (essenciaNatural >= 20){
            setStringHabilidadeUsada("Druida usa Raízes Entrelaçadas! \nInimigo paralisado (-10) defesa.");
            inimigo.alterarDefesa(-10);
            setUsouHabilidade(true);
        }else{
            setStringHabilidadeUsada("Energia insuficiente para usar Raízes Entrelaçadas! \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Inimigo inimigo) {

        if (essenciaNatural >= 30){
            setStringHabilidadeUsada("Druida invoca Enxame de Abelhas! \nDano aplicado (+10).");
            inimigo.alterarSaude(-10);
            setUsouHabilidade(true);
        }else{
            setStringHabilidadeUsada("Energia insuficiente para invoca Enxame de Abelhas \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }


    }

    @Override
    public void usarHabilidade3(Inimigo inimigo) {
        setStringHabilidadeUsada("Druida utiliza Escudo de Espinhos! \nDefesa aumentada (+15). .");
        alterarDefesa(15);
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade4(Inimigo inimigo) {

        if (essenciaNatural >= 40){
            setStringHabilidadeUsada("Druida cura a si mesmo! \nSaúde restaurada (+30).");
            alterarSaude(30);
            setUsouHabilidade(true);
        }else{
            setStringHabilidadeUsada("Energia insuficiente para curar a si mesmo \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void getImage() {
        // Implementação específica do Druida para obter a imagem do jogador
    }

    @Override
    public void getImageGrande() {

    }

    public String imprimiratributos() {
        return "Druida  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Essencia Natural: " + getEssenciaNatural() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }
}