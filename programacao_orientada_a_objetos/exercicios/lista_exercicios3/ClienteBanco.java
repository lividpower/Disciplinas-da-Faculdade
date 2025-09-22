import java.util.Scanner;

public class ClienteBanco {
    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in); //sempre que iniciarmos uma instância referente à classe Scanner, devemos fechá-la ao final de sua utilização!
        ContaBancaria cliente = new ContaBancaria(); //inicializando a variável como uma instância da classe ContaBancaria
        
        //informando a data de abertura da conta
        System.out.println("Digite o dia de abertura da conta: ");
        int dia = scanner.nextInt();
        System.out.println("Digite o mês de abertura da conta: ");
        int mes = scanner.nextInt();
        System.out.println("Digite o ano de abertura da conta: ");
        int ano = scanner.nextInt();
        cliente.setDataAbertura(dia, mes, ano);
        String dataAberturaFormatada = cliente.getDataAberturaFormatada();
        System.out.println("Data de abertura da conta: " + dataAberturaFormatada + "\n"); 

        //informando o saldo 
        System.out.println("Digite o valor do saldo bancário: "); //esse método já realiza a quebra de linha implicitamente
        double saldo = scanner.nextDouble(); //é necessário declarar o tipo das variáveis antes de tentar utilizá-las!
        scanner.nextLine(); //consumindo o \n que havia sido deixado para trás pelo nextDouble(), evitando erros em possíveis leituras futuras
        cliente.setSaldo(saldo);
        String saldoFormatado = cliente.getSaldoFormatado();
        System.out.println("Saldo bancário: " + saldoFormatado + "\n");

        //simulando operações de saque e depósito
        while(true) {
            System.out.println("Digite 1 se deseja depositar, 0 se deseja sacar ou -1 se deseja encerrar sua operação: ");
            int input = scanner.nextInt();
            if(input == 1) {
                System.out.println("Digite o valor que deseja depositar: ");
                double deposito = scanner.nextDouble();
                cliente.depositar(deposito);
                System.out.println("Saldo atualizado: " + cliente.getSaldoFormatado() + "\n");
            }
            else if(input == 0) {
                System.out.println("Digite o valor que deseja sacar: ");
                double saque = scanner.nextDouble();
                int valor_retorno = cliente.sacar(saque);
                if (valor_retorno == 0) {
                    System.out.println("Saldo atualizado: " + cliente.getSaldoFormatado() + "\n");
                }
            }
            else if(input == -1) {
                System.out.println("Operação encerrada\n");
                break;
            }
            else {
                System.out.println("O valor digitado é inválido! Por favor, tente novamente!");
                continue;
            }
        }
        scanner.close();
    }
}