import java.util.Scanner;

public class Teste {

    private static int NUM_LUTADORES = 10;

    public static void main(String []args) {
        Lutador arrayLutadores[] = new Lutador[NUM_LUTADORES];
        System.out.println("Entre com as informações sobre os lutadores: ");
        for(int i = 0; i < arrayLutadores.length; i++) {

            Scanner scanner = new Scanner(System.in);
            boolean teste = false;
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
                Lutador lutador = new PesoPena(nome, idade, peso);
            }
            else if(peso <= 83.9) {
                Lutador lutador = new PesoMedio(nome, idade, peso);
            }
            else if(peso <= 93.0) {
                Lutador lutador = new MeioPesado(nome, idade, peso);
            }
            else if(peso <= 120.2) {
                Lutador lutador = new PesoPesado(nome, idade, peso);
            }
            else {
                //caso o lutador exceda o peso máximo
                teste = true;
            }
            if(teste = false) {
                System.out.println(""); //pulando uma linha
                System.out.println("----------------------");
                System.out.println("Lutador " + (i + 1));
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Peso: " + peso);
                System.out.println("----------------------");
            }
            else {
                System.out.println(""); //pulando uma linha
                System.out.println("----------------------");
                System.out.println("Lutador " + (i + 1));
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Peso: " + peso);
                System.out.prinln("Com este peso, o lutador não está apto para lutar");
                System.out.println("----------------------");
            }
        }
        System.out.println(""); //pulando uma linha
        System.out.prinln("Agora é hora de escolhermos um desses lutadores...");
        System.out.prinln("Digite um inteiro de 0 até 9 para escolher um entre os lutadores: ");
        int lutadorSelecionado = scanner.nextInt();
        System.out.println("Características deste lutador: " + lutador.getInformacoesLutador());
        System.out.println("Categoria deste lutador: " + arrayLutadores[lutadorSelecionado].categoriaLutador());
        arrayLutadores[lutadorSelecionado].possiveisLutas(arrayLutadores);
        System.out.println("");
        System.out.println("Agora é hora de sortearmos um lutador para conseguirmos marcar essa luta!");
        Lutador lutadorSorteado = lutador.sorteioLuta(arrayLutadores);
        System.out.println("Características sobre o lutador que foi sorteado: " + lutadorSorteado.getInformacoesLutador());
    }
}
