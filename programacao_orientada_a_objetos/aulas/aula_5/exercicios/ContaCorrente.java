package exercicios;

public class ContaCorrente implements Tributavel { 
    
    private double saldo; //durante a criação de um objeto desta determinada classe, a variável saldo será inicializada com 0!
    private double limite;
    private int numeroConta; //gerar um número aleatório!

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return this.limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public int getnumeroConta() {
        return this.numeroConta;
    }

    @Override
    public double calculaTributos() {
        return (0.01 * this.saldo); //1% do saldo da conta em tributos
    }

    public void deposita(double valor) {
        saldo = saldo + valor;
    }

}