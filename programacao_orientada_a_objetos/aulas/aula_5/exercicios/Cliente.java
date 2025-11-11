package exercicios;

import java.util.ArrayList;
import java.lang.Math;

public class Cliente {

    //somente a própria classe terá acesso a estas variáveis!
    private String nome;
    private long cpf;
    //private Tributavel arrayTributaveis = new Tributavel[10]; //criando um array a partir de uma interface
    private ArrayList<Tributavel> arrayTributaveis; //criando um arraylist de tamanho inicial 10
    //este operador <> permite que eu defina parâmetros para uma classe, assim como faço com métodos!
    
    //getters e settersw
    public long getCPF() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCPF(long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTributaveis(Tributavel t[]) {
        arrayTributaveis = new ArrayList<>(t.length);
        for(int i = 0; i < t.length; i++) {
            arrayTributaveis.add(t[i]);
        }
    }

}