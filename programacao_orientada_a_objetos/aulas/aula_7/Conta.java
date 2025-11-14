
//import java.lang.RuntimeException; //java.lang já é importado por padrão
public class Conta {
    private double saldo;
    public void debitar(double val) {
        if(val > saldo) {
            System.out.println("não tem saldo");
            throw new RuntimeException();
        }
        else {
            saldo -= val;
        }
    }
    
    public void depositar(double v) {
        saldo += v;
    }
}
