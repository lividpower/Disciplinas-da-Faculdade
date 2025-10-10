public class PesoPesado extends Lutador {

    public PesoPesado(String nome, int idade, double peso) {
        //quando chamamos super() de forma explícita, estamos fazendo com que a chamada implícita de super() seja sobrescrita e, portanto, não realizada
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.modalidade = 1; 
        super.informacoesLutador = nome + "/" + idade + "/" + peso;
    }
}