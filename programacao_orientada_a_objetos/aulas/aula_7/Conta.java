
//import java.lang.RuntimeException; //java.lang já é importado por padrão
public class Conta {
    private double saldo;
    public void debitar(double val) throws SenDinException{ //este "throws" posterga a necessidade de utilizarmos do try-catch para capturarmos exceções do tipo "checked"
        if(val > saldo) {
            //System.out.println("não tem saldo");
            throw new SenDinException("Saldo atual: " + saldo);
        }
        else {
            saldo -= val;
        }
    }
    
    public void depositar(double v) {
        saldo += v;
    }
}
