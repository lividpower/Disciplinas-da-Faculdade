public class Casa extends Imovel {
    public Casa(double preco) {
        super(preco); //chamando o construtor da classe acima de forma explícita
    }

    public double getPrecoFinal() { 
        //return getPrecoFinal() + (getPrecoFinal() * 0.1); //nesse caso, o método que é chamado é o referente a classe Casa, sobrescrevendo o método com mesma assinatura da classe Imovel
        return super.getPrecoFinal() + (super.getPrecoFinal() * 0.1); //uma forma de chamar o método da classe acima sem sobrescrever o método
    }   
}