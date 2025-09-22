import java.util.Scanner;

public class ClienteBanco {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in); //sempre que iniciarmos uma instância referente à classe Scanner, devemos fechá-la ao final de sua utilização!
        ContaBancaria cliente = new ContaBancaria(); //inicializando a variável como uma instância da classe ContaBancaria
        System.out.println("Digite o valor do saldo bancário: "); //esse método já realiza a quebra de linha implicitamente
        double saldo = scanner.nextDouble(); //é necessário declarar o tipo das variáveis antes de tentar utilizá-las!
        scanner.nextLine(); //consumindo o \n que havia sido deixado para trás pelo nextDouble(), evitando erros em possíveis leituras futuras
        cliente.setSaldo(saldo);
        String saldoFormatado = cliente.getSaldoFormatado();
        System.out.println("Saldo bancário: " + saldoFormatado);
        scanner.close();
    }
}