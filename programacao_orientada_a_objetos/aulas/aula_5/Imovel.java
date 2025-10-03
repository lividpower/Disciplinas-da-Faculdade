public class Imovel { //extends Object
    private double preco;

    public Imovel(double preco) { //construtor dessa classe
        //super() //chamada a superclasse acima dessa classe
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoFinal() {
        return preco * (preco * 0.1);
    }
}