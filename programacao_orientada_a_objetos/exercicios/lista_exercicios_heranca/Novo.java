public class Novo extends Imovel {
    
    public double getPreco() {
        return (super.getPreco()  * 1.1); //super.preco busca o valor de preço presente na classe acima desta
    }
}