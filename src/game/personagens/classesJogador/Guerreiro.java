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
            setStringHabilidadeUsada("Guerreiro usa Golpe Furioso! \nDano aplicado (+20).");
            inimigo.alterarSaude(-20);
            energia -= 20;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar Golpe Furioso! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Guerreiro se concentra e recupera energia!");
        energia += 20;

        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        if (energia >= 15) {
            setStringHabilidadeUsada("Guerreiro usa Impacto Brutal! \nDano aplicado (+30).");
            inimigo.alterarSaude(-30);
            setUsouHabilidade(true);
        }else {
            setStringHabilidadeUsada("Energia insuficiente para usar Impacto Brutal! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        if(energia > 50){
            setStringHabilidadeUsada("Guerreiro se prepara para o próximo ataque!");
            setDefesa(10);
        }

    }

    @Override
    public void getImage() {
    }

    @Override
    public void getImageGrande() {

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