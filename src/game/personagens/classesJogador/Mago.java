package game.personagens.classesJogador;
import game.personagens.Personagem;


public class Mago extends Personagem {

    private int mana;


    public Mago() {
        super(100, 9, 17);
        mana = 80 ;

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

            setStringHabilidadeUsada("Mago usou Raio Mágico! \nDano aplicado ( + 40 ).");
            inimigo.alterarSaude(-40);
            setUsouHabilidade(true);

        } else {
            setMana(-20);
            setStringHabilidadeUsada("Mana insuficiente para usar Raio Mágico!");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Personagem inimigo) {

        if (mana < 40) {
            setStringHabilidadeUsada("Mana insuficiente para usar Bola de Fogo!");
            setUsouHabilidade(false);

        } else {
            setMana(-40);
            inimigo.alterarSaude(-20);
            setStringHabilidadeUsada("Mago usa Bola de Fogo! \nDano aplicado ( + 20 ).");
            setUsouHabilidade(true);

        }
    }


    @Override
    public void usarHabilidade3(Personagem inimigo) {// Escudo Arcano, +20 mana
        alterarDefesa(20);
        setMana(30);
        setStringHabilidadeUsada("Mago usa com escudo arcano! \nDefesa aumentada  ( + 20 ) \nMana recuperada (+ 30 ) ");
        setUsouHabilidade(true);

    }

    @Override
    public void usarHabilidade4(Personagem inimigo) {
        alterarSaude(50);

    }

    @Override
    public void getPlayerImage() {

    }

    public String imprimiratributos() {

        return "MAGO  - Nível: " + getNivel() + "  " + getXp() + "/" + getSubirDeNivel() + "\n" +
                "Mana: " + getMana() + "\n" +
                "    " +                "\n" +
                "    " +                "\n" +
                "Defesa: " + getDefesa() + "\n" +
                "Ataque: " + getAtaque() + "\n" ;
    }


}
