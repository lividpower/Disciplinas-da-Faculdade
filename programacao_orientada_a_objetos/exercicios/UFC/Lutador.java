import java.util.ArrayList;
import java.lang.Math; //já seria incluída dentro do código de forma implícita

public abstract class Lutador { //quando declaramos a classe como abstrata, nenhum objeto poderá ser criado a partir dela
    private String nome;
    private int idade;
    private double peso;
    private int modalidade; //assim que criarmos uma classe para o lutador, a modalidade será pré-definida
    private String informacoesLutador;

    public Lutador(String nome, int idade, double peso) {
        //de forma implícita, temos uma chamada a super(), isto dentro de qualquer construtor, antes da execução do código do construtor que o chama
        //nesse caso, super() estaria chamando o construtor da classe Object
        //inicializando os atributos por meio do construtor
        //sobrescrevendo o construtor implícito Lutador(), o qual não possui parâmetros
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    //as subclasses estarão herdando estes métodos!!!
    public int getIdade() {
        return this.idade;
    }

    public double getPeso() {
        return this.peso;
    }

    public String getNome() {
        return this.nome;
    }

    public int getModalidade() {
        return this.modalidade;
    }

    public String getInformacoesLutador() {
        return this.informacoesLutador;

    public void possiveisLutas(Lutador arrayLutadores[]) {
        System.out.println("Lutadores aptos a lutar contra " + this.nome + ":");
        for(int i = 0; i < arrayLutadores.length; i++){
            if(arrayLutadores[i].modalidade == this.modalidade) {
                System.out.println(arrayLutadores[i].nome);
            }
        }
    }

    public String categoriaLutador() {
        switch (this.modalidade) {
            case 1: return "Peso Pesado";
            case 2: return "Meio Pesado";
            case 3: return "Peso Médio";
            case 4: return "Peso Pena";
        }
    }
    
    public Lutador sorteioLuta(Lutador arrayLutadores[]) {
        //é possível definir o tipo das variáveis que serão armazenadas dentor do ArrayList //esse ArrayList pode ser redimensionado de forma dinâmica
        ArrayList<Lutador> array = new ArrayList<>(arrayLutadores.length - 1); //inicializando com um tamanho inicial que, no caso, refere-se ao número máximo de lutadores que podem ser da mesma categoria do lutador que estamos analisando
        
        for(int i = 0, int j = 0; i < arrayLutadores.length; i++) { //inicializando duas variáveis dentro do for()!
            if(this.modalidade == arrayLutadores[i].modalidade & this.nome != arrayLutadores[i].nome) {
                array.add(j, arrayLutadores[i]); //adiciona o elemento dentro da posição j dentro do array
                j++; //conforme vou inserindo os elementos no novo array, vou incrementando o valor de j
            }
        }
        do {
            double indexAleatorio = Math.random(); //retorna um valor entre 0.0 e 1.0
            indexAleatorio = indexAleatorio * 10; //deslocando uma casa para a esquerda
            indexAleatorio = (int) indexAleatorio; //truncando a parte decimal, mantendo apenas o que nos interessa (de 0 a 9)
        }while(indexAleatorio == 9); //como temos apenas nove lutadores, o vetor que estamos indexando vai de 0 a 8 apenas, portanto, fazemos o sorteio novamente para caso 9 seja sorteado
        return array.get(indexAleatorio); //retornando o lutador selecionado
    }


}