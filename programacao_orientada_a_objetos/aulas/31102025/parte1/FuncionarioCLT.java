public class FuncionarioCLT extends Funcionario {

    public double getSalarioLiquido() {
        return this.getSalario() * 0.7; //o salário líquido será apenas 70% do salário total
    }
}
