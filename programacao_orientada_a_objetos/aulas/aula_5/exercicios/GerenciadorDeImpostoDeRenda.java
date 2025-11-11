package exercicios;

public class GerenciadorDeImpostoDeRenda {

    double total;

    public void adiciona(Tributavel t) {
        total = total + t.calculaTributos();
    }

    public void adiciona(Tributavel t[]) {
        for(int i = 0; i < t.length; i++) {
            total = total + t[i].calculaTributos();
        }
    }

    public double getTotal() {
        return total;
    }
}