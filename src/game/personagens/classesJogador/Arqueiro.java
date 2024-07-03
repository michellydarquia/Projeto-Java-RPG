package game.personagens.classesJogador;

import game.personagens.Personagem;

public class Arqueiro extends Personagem {

    private int flechas;

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
        if (flechas >= 5) {
            setHabilidadeUsada("Arqueiro usa Flecha Veloz! \nDano aplicado (+15).");
            inimigo.alterarSaude(-15);
            flechas -= 5;
        } else {
            setHabilidadeUsada("Flechas insuficientes para usar Flecha Veloz!");
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        if (flechas >= 10) {
            setHabilidadeUsada("Arqueiro usa Chuva de Flechas! \nDano aplicado (+30).");
            inimigo.alterarSaude(-30);
            flechas -= 10;
        } else {
            setHabilidadeUsada("Flechas insuficientes para usar Chuva de Flechas!");
        }
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        setDefesa(10);
        setHabilidadeUsada("Arqueiro prepara Postura Defensiva! \nDefesa aumentada (+10).");
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        setHabilidadeUsada("Arqueiro usa Tiro Certeiro! \nDano aplicado (+25).");
        inimigo.alterarSaude(-25);
    }

    @Override
    public void getPlayerImage() {
        // Implementação específica do Arqueiro para obter a imagem do jogador
    }

    public String imprimiratributos() {
        return "Arqueiro  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Flechas: " + getFlechas() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

}
