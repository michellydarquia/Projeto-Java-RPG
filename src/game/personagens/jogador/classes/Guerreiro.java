package game.personagens.jogador.classes;

import game.personagens.Personagem;

public class Guerreiro extends Personagem {

    private int energia;

    public Guerreiro() {
        super(120, 15, 20); // Valores base para HP, Defesa e Ataque do Guerreiro
        this.energia = 100;
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
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 20;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar\nGolpe Furioso! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Guerreiro se concentra e\nrecupera energia!");
        energia += 20;
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        if (energia >= 15) {
            setStringHabilidadeUsada("Guerreiro usa Impacto Brutal! \nDano aplicado (+30).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 15;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar\nImpacto Brutal! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        if (energia >= 25) {
            setStringHabilidadeUsada("Guerreiro usa Ataque Devastador! \nDano aplicado (+50).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 25;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar\nAtaque Devastador! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void getImage() {

    }

    public String imprimiratributos() {

        return "GUERREIRO - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Energia: " + getEnergia() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

    public String getHabilidade1(){
        return "GOLPE FURIOSO";
    }

    public String getHabilidade2(){
        return "REGENERAR";
    }

    public String getHabilidade3(){
        return "IMPACTO BRUTAL";
    }

    public String getHabilidade4(){
        return "ATAQUE DEVASTADOR";
    }

}
