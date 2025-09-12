import java.util.Scanner;

public class TurmaAlunos {
    public static void main(String []args) {

        //declarações iniciais
        double acumulador = 0;
        Aluno []turma = new Aluno[3];
        for(int i = 0; i < turma.length; i++) { //essa variável "i" foi declarada internamente a função for(), portanto, ela não poderá ser utilizada fora desse for()
            turma[i] = new Aluno();
        }

        System.out.println("Registrando o nome e média dos alunos da turma...\n");

        for(int i = 0; i < turma.length; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do respectivo aluno: ");
            turma[i].nome = scanner.nextLine();
            System.out.print("Digite a média do respectivo aluno: \n");
            turma[i].media = scanner.nextDouble();
            acumulador += turma[i].media;
            System.out.println("");
        }
        System.out.println("Média da turma: " + (acumulador / turma.length));
    }
}