public class Teste {
    public static void main(String args[]) {
        //String x[] = {" Ana    Maria#18", "Julia#21"}; //quando eu tento usar split(" "), o fato de eu ter múltiplos espaços seguidos um do outro não é algo capturado pelo split()
        String x[] = {"Ana Maria#18", "Julia#21"}; 
        double soma = 0;
        String []nomeAlunos = new String[x.length];
        for(int i = 0; i < x.length; i++) { //quando crio essas variáveis locais ao for(), garanto que não irei precisar 
            String j[] = x[i].split("#");
            //int i; //se eu crio duas variáveis locais ao for() com nomes iguais, isso me gerará um erro de compilação!
            int aux = Integer.parseInt(j[1]); //o método parseInt() é estático!
            soma += aux;
            nomeAlunos[i] = j[0];
        }
        double mediaIdades = soma / x.length;
        System.out.println("Primeiro nome dos alunos: ");
        for(int i = 0; i < x.length; i++) {
            String []aux = nomeAlunos[i].split(" ");
            System.out.println(aux[0]);
        }
        System.out.println("Média das idades dos alunos: " + mediaIdades);
    }
}