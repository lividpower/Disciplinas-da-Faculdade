public class Zoo {
    public static void main(String []args) {
        Animal x = new Leao(); //essa linha de código compila porque a classe Leão está herdando a classe Animal!!!
        x.setNome("Simba");
        System.out.println(x.getVivo()); //variáveis do tipo "boolean" irão ser inicializadas como "false"
        x.rugir(); //já esta linha não compila, porque Animal.rugir() não estaria correto, posto que o método rugir() é exclusivo da classe Leao
    }
}