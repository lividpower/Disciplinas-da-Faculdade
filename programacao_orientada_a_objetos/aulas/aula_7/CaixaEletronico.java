import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String args[]) {
        Conta m = new Conta();
        m.depositar(100);
        Scanner sc = new Scanner(System.in);
        System.out.println("Quando quer sacar?");
        double v = sc.nextInt();
        try{
            m.debitar(v);
            System.out.println("Liberando notas de dinheiro. Total: " + v + " reais");
        }catch(SenDinException e) {
            System.err.println(e);
        }
        System.out.println("fim");
    }
}