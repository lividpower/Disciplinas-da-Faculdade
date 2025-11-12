package exercicios.exercicio1;

public abstract class Funcionario {

    public Funcionario(String id) { //único construtor existente para esta classe!
        this.id = id;
    }

    private String id;
    private String nome;
    private double salario;

    public String getID() {
        return this.id;
    }   

    public double getSalario() {
        return this.salario;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if(this.getID() == ((Funcionario) o).getID()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return (this.id + " - " + this.nome + " - " + this.salario);
    }

}

