package game.personagens.classesJogador;

import game.personagens.Personagem;

public class Guerreiro extends Personagem {

    private int energia;

    public Guerreiro() {
        super(100, 17, 15); // Ataque específico do Guerreiro
        energia = 100;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public void usarHabilidade1(Personagem inimigo) {
        if (energia >= 20) {
            setHabilidadeUsada("Guerreiro usa Golpe Furioso! \nDano aplicado (+20).");
            inimigo.alterarSaude(-20);
            energia -= 20;
        } else {
            setHabilidadeUsada("Energia insuficiente para usar Golpe Furioso!");
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setHabilidadeUsada("Guerreiro se prepara para o próximo ataque!");
        setDefesa(10);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        setHabilidadeUsada("Guerreiro utiliza Impacto Brutal! \nDano aplicado (+30).");
        inimigo.alterarSaude(-30);
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        setHabilidadeUsada("Guerreiro se concentra e recupera energia!");
        energia += 20;
    }

    @Override
    public void getPlayerImage() {
    }

    public String imprimiratributos() {
        return "Arqueiro  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Energia: " + getEnergia() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }
}