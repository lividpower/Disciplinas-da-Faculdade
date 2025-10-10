public class PesoMedio {

    public PesoMedio(String nome, int idade, double peso) {
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.modalidade = 3;
        super.informacoesLutador = nome + "/" + idade + "/" + peso;
    }
}