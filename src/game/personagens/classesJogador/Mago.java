package game.personagens.classesJogador;
import game.personagens.Personagem;


public class Mago extends Personagem {

    private int mana;


    public Mago() {
        super(100, 9, 17);
        mana = 100 ;

    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana += mana;
    }


    @Override
    public void usarHabilidade1(Personagem inimigo) { // Raio Mágico, -20 mana

        if (mana >= 20) {

            setHabilidadeUsada("Mago usou Raio Mágico! \nDano aplicado ( + 40 ).");
            setExperiencia(100);
            inimigo.alterarSaude(-40);

        } else {
            setMana(-20);
            setHabilidadeUsada("Mana insuficiente para usar Raio Mágico!");

        }
    }

    @Override
    public void usarHabilidade2() {

        if (mana < 40) {
            setHabilidadeUsada("Mana insuficiente para usar Bola de Fogo!");

        } else {
            setMana(-40);
            setExperiencia(100);
            setHabilidadeUsada("Mago usa Bola de Fogo! \nDano aplicado ( + 20 ).");
        }
    }


    @Override
    public void usarHabilidade3() {// Escudo Arcano, +20 mana
        alterarDefesa(20);
        setMana(30);
        setHabilidadeUsada("Mago ataca com escudo arcano! \nDefesa aumentada  ( + 20 ) \nMana recuperada (+ 30 ) ");


    }

    @Override
    public void usarHabilidade4() {
        alterarSaude(50);

    }

    @Override
    public void getPlayerImage() {

    }

    public String imprimiratributos() {

        return "MAGO  - Nível: " + getNivel() + "  " + getExperiencia() + "/" + getSubirDeNivel() + "\n" +
                "Mana: " + getMana() + "\n" +
                "    " +                "\n" +
                "Saúde: " + getSaude() + "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


}
