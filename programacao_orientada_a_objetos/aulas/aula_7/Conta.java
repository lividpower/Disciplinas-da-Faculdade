public class Conta {
    private double saldo;
    public boolean debitar(double val) {
        if(val > saldo) {
            System.out.println("não tem saldo");
            return false;
        }
        else {
            saldo -= val;
            return true;
        }
    }
    
    public void depositar(double v) {
        saldo += v;
    }
}
