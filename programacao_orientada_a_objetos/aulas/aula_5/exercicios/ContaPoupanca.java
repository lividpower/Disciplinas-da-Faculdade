package exercicios;

public class ContaPoupanca {


    private double saldo;
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

    public void deposita(double valor) {
        saldo = saldo + valor;
    }
}