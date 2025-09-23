import java.util.Scanner;


public class TesteAluno {
    public static void main(String []args) {
        Aluno aluno = new Aluno();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        aluno.setNome(nome);

        System.out.println("Digite a nota da P1 do aluno: ");
        double p1 = scanner.nextDouble();
        aluno.setNota1(p1);
        System.out.println("Digite a nota da P2 do aluno: ");
        double p2 = scanner.nextDouble();
        aluno.setNota2(p2);
        double media = aluno.getMediaAluno();
        System.out.println("Situação do aluno: " + aluno.getSituacaoAluno(media));
    }
}