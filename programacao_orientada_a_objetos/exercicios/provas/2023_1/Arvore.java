public class Arvore {

    //essas variáveis são declaradas em uma região de memória dinâmica
    private String nome;
    public int a; 

    //essa variável é declarada em uma região de memória estática
    //existirá apenas uma para todas as classes
    public static int b;

    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }

}