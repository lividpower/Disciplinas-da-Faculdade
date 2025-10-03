public class Teste {
    public static void main(String args[]) {
        String x[] = {"Ana Maria#18", "Julia#21"};
        double soma = 0;
        for(int i = 0; i < x.length; i++) {
            String j[] = x[i].split("#");
            //int i; //se eu crio duas variáveis locais ao for() com nomes iguais, isso me gerará um erro de compilação!
            int aux = Integer.parseInt(j[1]); //o método parseInt() é estático!
            soma += aux;
        }
        double mediaIdades = soma / x.length;
        System.out.println("Média das idades: " + mediaIdades);
    }
}