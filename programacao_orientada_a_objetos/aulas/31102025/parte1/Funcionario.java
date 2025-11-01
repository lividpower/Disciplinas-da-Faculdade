public abstract class Funcionario {

    private double salario;
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    //caso eu não implemente este método dentro das classes que extenderem esta classe abstrata, terei um erro de compilação!
    public abstract double getSalarioLiquido();
}