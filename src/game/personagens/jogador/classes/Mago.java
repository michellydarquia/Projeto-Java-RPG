package game.personagens.jogador.classes;

import game.personagens.Personagem;

public class Mago extends Personagem {

    private int mana;

    public Mago() {
        super(70, 8, 25); // Valores base para HP, Defesa e Ataque do Mago
        this.mana = 120;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void usarHabilidade1(Personagem inimigo) {
        if (mana >= 20) {
            setStringHabilidadeUsada("Mago usa Bola de Fogo! \nDano aplicado (+30).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 20;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nBola de Fogo! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Mago usa Escudo de Gelo\ne recupera mana!");
        mana += 20;
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        if (mana >= 15) {
            setStringHabilidadeUsada("Mago usa Raio Magico! \nDano aplicado (+25).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 15;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nRaio Magico! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        if (mana >= 25) {
            setStringHabilidadeUsada("Mago usa Magia Obscura! \nDano aplicado (+40).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 25;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nMagia Obscura! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void getImage() {

    }

    public String imprimiratributos() {

        return "MAGO  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Mana: " + getMana() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

    public String getHabilidade1(){
        return "BOLA DE FOGO";
    }

    public String getHabilidade2(){
        return "ESCUDO ARCANO";
    }

    public String getHabilidade3(){
        return "RAIO MAGICO";
    }

    public String getHabilidade4(){
        return "MAGIA OBSCURA";
    }


}
