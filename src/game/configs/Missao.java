package game.configs;

import Exceptions.ExceptionAtributo;
import game.inventorio.Item;
import game.inventorio.itens.Artefato;
import game.personagens.jogador.Jogador;

import java.awt.*;

public class Missao {

    private String titulo;
    private String descricao;
    private boolean concluida;
    private boolean desbloqueada;
    private int index;
    private Jogador jogador;


  public Missao(int index , String titulo, String descricao, Jogador jogador){
        this.index = index;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.jogador = jogador;



    }


    public void controleMissoes(){

        if (index == 0){
            missao0();
        }else if (index == 1){
            missao1();
        }else if (index == 2){
            missao2();
        }else {
            throw new ExceptionAtributo("missao");
        }

    }

    public void missao0(){
        if(isDesbloqueada()) {

        }
    }

    public void missao1(){

        if(isDesbloqueada()) {
            int inimigosMortosLimite = 1;

            if ( jogador.getClassePersonagem().getInimigosMortos() >= inimigosMortosLimite){
                concluir();
                desbloquearProxMissao(2);

            }

        }

    }

    public void missao2(){

        if(isDesbloqueada()) {
            int limite =  2;
            int quantidadeArtefato = jogador.getInventario().procurarItens(Artefato.class);

            if (quantidadeArtefato >= limite ){
                concluir();
            }


        }



    }


    public void desbloquearProxMissao(int index){
        MissoesManager manager = jogador.getMissoesManager();
        for(Missao missao : manager.getMissoes()){
            if (missao.index == index) {
                missao.setDesbloqueada(true);
                break;
            }
        }
    }



    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }


    public void concluir() {

        setConcluida(true);
    }

    public boolean isConcluida() {
        return concluida;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


    public boolean isDesbloqueada() {
        return desbloqueada;
    }

    public void setDesbloqueada(boolean desbloqueada) {
        this.desbloqueada = desbloqueada;
    }



}

