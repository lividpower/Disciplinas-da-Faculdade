public class PesoPena extends Lutador{

    public PesoPena(String nome, int idade, double peso) {
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.setModalidade(4);
        super.setInformacoesLutador(nome + "/" + idade + "/" + peso);
    }
}