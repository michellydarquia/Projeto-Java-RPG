package game.personagens.jogador.classes;

import game.personagens.Personagem;

public class Druida extends Personagem {

    private int mana;

    public Druida() {
        super(80, 10, 15); // Valores base para HP, Defesa e Ataque do Druida
        this.mana = 100;
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
            setStringHabilidadeUsada("Druida usa Raizes Entrelaçadas! \nDano aplicado (+20).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 20;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nRaizes Entrelaçadas! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {
        setStringHabilidadeUsada("Druida usa Escudo de Espinhos e\nrecupera mana!");
        mana += 20;
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade3(Personagem inimigo) {
        if (mana >= 15) {
            setStringHabilidadeUsada("Druida usa Tempestade de Folhas! \nDano aplicado (+30).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 15;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nTempestade de Folhas! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        if (mana >= 25) {
            setStringHabilidadeUsada("Druida usa Ira da Natureza! \nDano aplicado (+50).");
            inimigo.alterarSaude(-calcularDano(inimigo));
            mana -= 25;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Mana insuficiente para usar\nIra da Natureza! \nEscolha outra habilidade ");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void getImage() {

    }

    public String imprimiratributos() {

        return "DRUIDA  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Mana: " + getMana() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }

    public String getHabilidade1(){
        return "RAIZES ENTRELAÇADAS";
    }

    public String getHabilidade2(){
        return "ESCUDO DE ESPINHOS";
    }

    public String getHabilidade3(){
        return "TEMPESTADE DE FOLHAS";
    }

    public String getHabilidade4(){
        return "IRA NATURAL";
    }


}
