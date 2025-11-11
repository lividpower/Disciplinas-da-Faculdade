package exercicios;

public class TestaTributavel {

    public static void main(String args[]) {
        ContaCorrente cc = new ContaCorrente();
        cc.deposita(100);
        System.out.println(cc.calculaTributos());  //testando o polimorfismo
        Tributavel t = cc;
        System.out.println(t.calculaTributos());
        //System.out.println(t.getSaldo()); //o que acontece? teremos um erro de compilação, porque t é do tipo Tributavel! Devemos convertê-lo para o tipo (ContaCorrente)
        //t = (SeguroDeVida) t; //teremos um erro em tempo de execução, posto que estaríamos tentando converter um objeto do tipo ContaCorrente para um tipo SeguroDeVida
        //System.out.println(t.getSaldo());
        System.out.println(((ContaCorrente)t).getSaldo());
    }
}