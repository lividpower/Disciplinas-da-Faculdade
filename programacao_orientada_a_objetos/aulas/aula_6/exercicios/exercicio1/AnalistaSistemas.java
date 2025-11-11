package exercicios.exercicio1;

public class AnalistaSistemas extends Funcionario {

    public AnalistaSistemas(String id) {
        super(id); //como a superclasse Funcionario apenas possui um construtor com parâmetros definidos, a subclasse é obrigada a passar estes parâmetros por meio de uma chamada a super()
    }

}