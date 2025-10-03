public class Casa extends Imovel {
    public Casa(double preco) {
        super(preco); //chamando o construtor da classe acima de forma explícita
    }

    public Casa() {
        //nesse caso, por já estarmos chamando um construtor, não existirá uma chamada a super() 
        this(0); //estou chamando o construtor da minha própria classe
    }   
}