import java.util.Scanner;

public class Teste {

    private static int NUM_LUTADORES = 10;

    public static void main(String []args) {
        Lutador arrayLutadores[] = new Lutador[NUM_LUTADORES]; //é necessário declarar essa variável fora do for(), posto que estarei utilizando dela ao longo de todo meu método main
        Scanner scanner = new Scanner(System.in); //declarando de forma generalizada para que o método main possa utilizar dessa instância a qualquer momento
        System.out.println("Entre com as informações sobre os lutadores: ");
        for(int i = 0; i < arrayLutadores.length; i++) {

            boolean teste = false; //inicializando para cada lutador que está sendo adicionado
            String nome;
            int idade;
            double peso; //apenas criando variáveis para facilitar a manipulação dos dados

            System.out.println("Lutador " + (i + 1));
            System.out.println("Entre com o nome: ");
            nome = scanner.nextLine();
            System.out.println("Entre com a idade: ");
            idade = scanner.nextInt();
            scanner.nextLine(); //lendo o \n que fica sobrando dentro do stdin, o qual não foi lido por .nextInt()
            System.out.println("Entre com o peso: ");
            peso = scanner.nextDouble();
            scanner.nextLine();
            if(peso <= 65.0) {
                arrayLutadores[i] = new PesoPena(nome, idade, peso);
            }
            else if(peso <= 83.9) {
                arrayLutadores[i] = new PesoMedio(nome, idade, peso);
            }
            else if(peso <= 93.0) {
                arrayLutadores[i] = new MeioPesado(nome, idade, peso);
            }
            else if(peso <= 120.2) {
                arrayLutadores[i] = new PesoPesado(nome, idade, peso);
            }
            else {
                //caso o lutador exceda o peso máximo
                teste = true;
            }
            if(teste == false) {
                System.out.println(""); //pulando uma linha
                System.out.println("----------------------");
                System.out.println("Lutador " + (i + 1));
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Peso: " + peso);
                System.out.println("----------------------");
            }
            else if(teste == true){
                System.out.println(""); //pulando uma linha
                System.out.println("----------------------");
                System.out.println("Lutador " + (i + 1));
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Peso: " + peso);
                System.out.println("Com este peso, o lutador não está apto para lutar");
                System.out.println("----------------------");
            }
        }
        System.out.println(""); //pulando uma linha
        System.out.println("Agora é hora de escolhermos um desses lutadores...");
        System.out.println("Digite um inteiro de 0 até 9 para escolher um entre os lutadores: ");
        int lutadorSelecionado = scanner.nextInt();
        System.out.println("Características deste lutador: " + arrayLutadores[lutadorSelecionado].getInformacoesLutador());
        System.out.println("Categoria deste lutador: " + arrayLutadores[lutadorSelecionado].categoriaLutador());
        arrayLutadores[lutadorSelecionado].possiveisLutas(arrayLutadores);
        System.out.println("");
        System.out.println("Agora é hora de sortearmos um lutador para conseguirmos marcar essa luta!");
        Lutador lutadorSorteado = arrayLutadores[lutadorSelecionado].sorteioLuta(arrayLutadores);
        System.out.println("Características sobre o lutador que foi sorteado: " + lutadorSorteado.getInformacoesLutador());
        scanner.close();
    }
}
