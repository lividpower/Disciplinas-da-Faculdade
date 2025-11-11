package exercicios;

public class TesteGerenciadorDeImpostoDeRenda {
    public static void main(String args[]) {
        Cliente y = new Cliente();
        ContaCorrente a = new ContaCorrente();
        SeguroDeVida b = new SeguroDeVida();
        ContaCorrente c = new ContaCorrente();
        a.deposita(100);
        c.deposita(500);
        Tributavel t[] = {a, b, c};
        GerenciadorDeImpostoDeRenda x = new GerenciadorDeImpostoDeRenda();
        y.setTributaveis(t);
        x.adiciona(t);
        System.out.println(x.getTotal());
    }
}