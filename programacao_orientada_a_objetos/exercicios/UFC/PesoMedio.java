public class PesoMedio extends Lutador{

    public PesoMedio(String nome, int idade, double peso) {
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.setModalidade(3);
        super.setInformacoesLutador(nome + "/" + idade + "/" + peso);
    }
}