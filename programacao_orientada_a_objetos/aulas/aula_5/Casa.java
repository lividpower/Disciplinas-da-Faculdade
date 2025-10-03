public class Casa extends Imovel {
    public Casa(double preco) {
        super(preco); //chamando o construtor da classe acima de forma explícita
    }

    public Casa() {
        //super() aparece de forma implícita
        //esse código não compila porque não existe nenhum construtor na superclasse acima que não possui nenhum parâmetro declarado
    }   
}