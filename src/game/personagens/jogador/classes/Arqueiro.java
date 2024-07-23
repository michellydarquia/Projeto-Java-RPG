package game.personagens.jogador.classes;

import game.personagens.Personagem;

public class Arqueiro extends Personagem {

    private int energia;

    public Arqueiro() {
        super(90, 12, 18); // Valores base para HP, Defesa e Ataque do Arqueiro
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
            setStringHabilidadeUsada("Arqueiro usa Flecha veloz! \nDano aplicado (+25).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 20;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar \nFlecha veloz! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Arqueiro se concentra e\nrecupera energia!");
        energia += 20;
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        if (energia >= 15) {
            setStringHabilidadeUsada("Arqueiro usa Chuva de Flechas! \nDano aplicado (+20).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 15;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar\nChuva de Flechas! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        if (energia >= 25) {
            setStringHabilidadeUsada("Arqueiro usa Tiro Explosivo! \nDano aplicado (+35).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            energia -= 25;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Energia insuficiente para usar\nTiro Explosivo! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void getImage() {

    }
    public String imprimiratributos() {

        return "Arqueiro - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Energia: " + getEnergia() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


    public String getHabilidade1(){
        return "FLECHA VELOZ";
    }

    public String getHabilidade2(){
        return "POSTURA DEFENSIVA";
    }

    public String getHabilidade3(){
        return "CHUVA DE FLECHAS";
    }

    public String getHabilidade4(){
        return "TIRO CERTEIRO";
    }

}
