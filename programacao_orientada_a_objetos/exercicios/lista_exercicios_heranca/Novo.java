public class Novo extends Imovel {
    
    public double getPreco() {
        return (super.preco  * 1,1); //super.preco busca o valor de preço presente na classe acima desta
    }
}