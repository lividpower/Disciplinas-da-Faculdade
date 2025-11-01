public class FuncionarioPJ extends Funcionario{

    public double getSalarioLiquido() {
        //aqui podemos usar de getSalario() sem usar de "this."
        return this.getSalario() * 0.8; //o salário líquido será 80% do salário total
    }
}