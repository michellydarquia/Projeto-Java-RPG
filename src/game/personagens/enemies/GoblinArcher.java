package game.personagens.enemies;

import game.personagens.BasePersonagem;

public class GoblinArcher extends BasePersonagem {


    public GoblinArcher() {
        super(84, 9 , 18 , 8 );
    }

    @Override
    public void usarHabilidade1() { // Flecha Venenosa, causa dano e envenena
        System.out.println("Goblin Archer usa Flecha Venenosa! Causa dano e envenena o alvo.");
        // Implementar lógica de dano e envenenamento
    }

    @Override
    public void usarHabilidade2() { // Ataque Rápido, causa dano duplo
        System.out.println("Goblin Archer usa Ataque Rápido! Causa dano duplo.");
        // Implementar lógica de dano duplo
    }

    @Override
    public void usarHabilidade3() { // Esquiva Ágil, aumenta a agilidade temporariamente
        setAgilidade(5);
        System.out.println("Goblin Archer usa Esquiva Ágil! Agilidade aumentada temporariamente.");
        // Implementar lógica de aumento temporário de agilidade
    }
}
