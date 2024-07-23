package game.personagens;

import Exceptions.ExceptionAtributo;
import game.inventorio.Item;
import game.inventorio.itens.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Personagem implements Habilidades {

    private int hp;
    private int defesa;
    private int ataque;
    private int nivel;
    private int xp;
    private int subirDeNivel;
    private int baseHp;
    private int baseAtaque;
    private int baseDefesa;
    private int inimigosMortos;
    private boolean usouHabilidade = false;
    private boolean vivo = true;
    private ArrayList<Item> equipamentos;
    private String stringHabilidadeUsada;
    public BufferedImage imagemGrande;
    public BufferedImage imagem;

    public Personagem(int baseHp, int baseDefesa, int baseAtaque) {
        this.nivel = 1;
        this.xp = 0;
        this.subirDeNivel = 200 * nivel;

        this.baseHp = baseHp;
        this.baseDefesa = baseDefesa;
        this.baseAtaque = baseAtaque;

        this.hp = calcularAtributo(baseHp, nivel);
        this.defesa = calcularAtributo(baseDefesa, nivel);
        this.ataque = calcularAtributo(baseAtaque, nivel);

        this.equipamentos = new ArrayList<>(5);
    }

    private int calcularAtributo(int base, int nivel) {
        return base * nivel;
    }

    public void setImagemGrande(BufferedImage imagemGrande) {
        this.imagemGrande = imagemGrande;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setXp(int valor) {
        if(valor < 0){
            throw new ExceptionAtributo("experiencia");
        } else if (valor == 0) {
            this.xp = 0;
        }

        this.xp += valor;
        verificadorExperiencia();
    }

    public boolean isVivo() {
        return vivo;
    }

    public void alterarSaude(int valor) {
        int novaSaude = this.hp + valor;
        if (novaSaude < 0) {
            this.hp = 0;
            vivo = false;
        } else if (novaSaude >  calcularAtributo(baseHp, nivel)) {
            this.hp = calcularAtributo(baseHp, nivel);
        } else {
            this.hp = novaSaude;
        }
    }

    public void alterarDefesa(int valor) {
        int novaDefesa = this.defesa + valor;
        if (novaDefesa < 0) {
            this.defesa = 0;
        } else if (novaDefesa > calcularAtributo(baseDefesa, nivel)) {
            this.defesa = calcularAtributo(baseDefesa, nivel);
        } else {
            this.defesa = novaDefesa;
        }
    }

    public void alterarAtaque(int valor) {
        int novoAtaque = this.ataque + valor;
        if (novoAtaque < 0) {
            this.ataque = 0;
        } else if (novoAtaque > calcularAtributo(baseAtaque, nivel)) {
            this.ataque = calcularAtributo(baseAtaque, nivel);
        } else {
            this.ataque = novoAtaque;
        }
    }

    public void alterarInimigosMortos(){
        inimigosMortos++;
    }

    public BufferedImage getImagemGrande() {
        return imagemGrande;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getSaude() {
        return hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSubirDeNivel() {
        return subirDeNivel;
    }

    public int getXp() {
        return xp;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getBaseDefesa() {
        return baseDefesa;
    }

    public int getBaseAtaque() {
        return baseAtaque;
    }

    public int getInimigosMortos() {
        return inimigosMortos;
    }

    public void verificadorExperiencia() {
        if(this.xp >= this.subirDeNivel ){
            this.xp = 0;
            this.nivel++;
            this.subirDeNivel = 200 * nivel;
            this.hp = calcularAtributo(baseHp, nivel);
            this.defesa = calcularAtributo(baseDefesa, nivel);
            this.ataque = calcularAtributo(baseAtaque, nivel);
        }
    }

    public boolean isUsouHabilidade() {
        return usouHabilidade;
    }

    public void setUsouHabilidade(boolean usouHabilidade) {
        this.usouHabilidade = usouHabilidade;
    }

    public String getStringHabilidadeUsada() {
        return stringHabilidadeUsada;
    }

    public void setStringHabilidadeUsada(String habilidadeUsada) {
        if (this.stringHabilidadeUsada == null) {
            habilidadeUsada = ""; // garantir que a mensagem não seja nula
        }
        this.stringHabilidadeUsada = habilidadeUsada;
    }

    public void redefinirAtributos(){
        this.hp = calcularAtributo(baseHp, nivel);
        this.defesa = calcularAtributo(baseDefesa, nivel);
        this.ataque = calcularAtributo(baseAtaque, nivel);
        setVivo(true);
    }

    public void equipar(Item equipamento) {
        if (equipamento == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }

        int index = -1;

        if (equipamento instanceof Capacete) {
            index = 0;
        } else if (equipamento instanceof Peitoral) {
            index = 1;
        } else if (equipamento instanceof Calca) {
            index = 2;
        } else if (equipamento instanceof Bota) {
            index = 3;
        } else if (equipamento instanceof Espada || equipamento instanceof Escudo ||
                equipamento instanceof Machado || equipamento instanceof Arco || equipamento instanceof Cajado) {
            index = 4;
        } else {
            throw new ExceptionAtributo("Tipo de equipamento desconhecido.");
        }

        while (index >= equipamentos.size()) {
            equipamentos.add(null);
        }

        Item itemAntigo = equipamentos.get(index);
        if (itemAntigo != null) {
            desequipar(itemAntigo);
        }

        equipamentos.set(index, equipamento);

    }

    public void desequipar(Item equipamento) {
        if (equipamento == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }

        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i) == equipamento) {
                equipamentos.set(i, null);
                alterarAtaque(-equipamento.getBonusAtaque(this));
                alterarDefesa(-equipamento.getBonusDefesa(this));
                return;
            }
        }
        throw new IllegalArgumentException("Equipamento não encontrado.");
    }


    public ArrayList<Item> getEquipamentos() {
        return equipamentos;
    }

    public int calcularDano(Personagem alvo) {
        int danoBase = this.ataque - alvo.getDefesa();
        return Math.max(danoBase, 0); // O dano não pode ser negativo
    }


}
