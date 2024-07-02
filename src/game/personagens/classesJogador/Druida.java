package game.personagens.classesJogador;

import game.personagens.Personagem;

public class Druida extends Personagem {

    private int energia;

    public Druida() {
        super(100, 9, 17);
        energia = 50;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public void usarHabilidade1(Personagem inimigo) {
        setHabilidadeUsada("Druida usa Raízes Entrelaçadas! \nInimigo paralisado por um turno.");
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setHabilidadeUsada("Druida invoca Enxame de Abelhas! \nDano aplicado (+10).");
        inimigo.alterarSaude(-10);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        setHabilidadeUsada("Druida utiliza Escudo de Espinhos! \nDefesa aumentada (+15).");
        alterarDefesa(15);
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        setHabilidadeUsada("Druida cura a si mesmo! \nSaúde restaurada (+30).");
        alterarSaude(30);
    }

    @Override
    public void getPlayerImage() {
        // Implementação específica do Druida para obter a imagem do jogador
    }

    public String imprimiratributos() {
        return "Druida  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Energia: " + getEnergia() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }
}