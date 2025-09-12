import java.util.Scanner;

public class TurmaAlunos {
    public static double CalculaMedia(Aluno []turma) {
        
    }

    public static void main(String []args) {

        //declarações iniciais
        double acumulador = 0;
        Aluno []turma = new Aluno[3];
        for(int i = 0; i < turma.length; i++) { //essa variável "i" foi declarada internamente a função for(), portanto, ela não poderá ser utilizada fora desse for()
            turma[i] = new Aluno();
        }

        System.out.println("Registrando o nome e média dos alunos da turma...\n");

        for(int i = 0; i < turma.length; i++) {
            Scanner scanner = new Scanner(System.in); //estou criando um objeto diferente dentro de cada iteração do for()
            System.out.println("Digite o nome do respectivo aluno: ");
            turma[i].nome = scanner.nextLine(); 
            System.out.print("Digite a média do respectivo aluno: \n");
            turma[i].media = scanner.nextDouble(); //quando eu uso desse método, ele lê um double, porém, o buffer de leitura armazena também o \n referente a tecla Enter
            //esse \n não é lido pela função nextDouble() e daí a próxima chamada a nextLine() irá acabar lendo esse caractere!
            //quando eu crio um objeto "scanner" a cada iteração, eu estou evitando que isso aconteça, porém, estou criando um overhead apenas conseguir fugir desse bug
            acumulador += turma[i].media;
            System.out.println("");
        }
        System.out.println("Média da turma: " + (acumulador / turma.length));
    }
}