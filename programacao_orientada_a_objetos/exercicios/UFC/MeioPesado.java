public class MeioPesado extends Lutador{

    public MeioPesado(String nome, int idade, double peso) {
        super(nome, idade, peso); //a chamada de super() deve vir antes de tudo dentro do construtor
        super.setModalidade(2); //como essas variáveis são privadas na classe Lutador, não é possível acessá-las de forma direta
        super.setInformacoesLutador(nome + "/" + idade + "/" + peso);
    }
}