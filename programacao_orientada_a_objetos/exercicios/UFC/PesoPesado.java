public class PesoPesado extends Lutador {

    public PesoPesado(String nome, int idade, double peso) {
        //quando chamamos super() de forma explícita, estamos fazendo com que a chamada implícita de super() seja sobrescrita e, portanto, não realizada
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.setModalidade(1);  //essas variáveis são privadas dentro da classe, logo, não podemos acessá-las dessa forma
        super.setInformacoesLutador(nome + "/" + idade + "/" + peso);
    }
}