package game.personagens.jogador.classes;

import game.personagens.inimigo.Inimigo;
import game.personagens.jogador.Personagem;

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
    public void usarHabilidade1(Inimigo inimigo) {
        if (flechas >= 5) {
            setStringHabilidadeUsada("Arqueiro usa Flecha Veloz! \nDano aplicado (+15).");
            inimigo.alterarSaude(-15);
            flechas -= 5;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Flechas insuficientes para usar Flecha Veloz! \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade2(Inimigo inimigo) {
        if (flechas >= 10) {
            setStringHabilidadeUsada("Arqueiro usa Chuva de Flechas! \nDano aplicado (+30).");
            inimigo.alterarSaude(-30);
            flechas -= 10;
            setUsouHabilidade(true);
        } else {
            setStringHabilidadeUsada("Flechas insuficientes para usar Chuva de Flechas! \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void usarHabilidade3(Inimigo inimigo) {
        setDefesa(10);
        flechas += 10;
        setStringHabilidadeUsada("Arqueiro prepara Postura Defensiva! \nDefesa aumentada (+10).");
        setUsouHabilidade(true);
    }

    @Override
    public void usarHabilidade4(Inimigo inimigo) {

        if (flechas >= 60) {
            setStringHabilidadeUsada("Arqueiro usa Tiro Certeiro! \nDano aplicado (+25).");
            inimigo.alterarSaude(-25);
            flechas -= 60;
            setUsouHabilidade(true);
        }else {
            setStringHabilidadeUsada("Flechas insuficientes para usar Tiro Certeiro! \nEscolha outra habilidade");
            setUsouHabilidade(false);
        }
    }

    @Override
    public void redefinirAtributos() {
        setFlechas(300);
        setDefesa(getLimiteDefesa());
        setHp(getLimiteSaude());
        setAtaque(getLimiteAtaque());
        setVivo(true);
    }

    @Override
    public void getImage() {
        // Implementação específica do Arqueiro para obter a imagem do jogador
    }

    @Override
    public void getImageGrande() {

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
