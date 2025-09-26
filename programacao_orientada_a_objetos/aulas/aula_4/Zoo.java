public class Zoo {
    public static void main(String []args) {
        Animal x = new Zebra();
        x.setNome("Simba");
        System.out.println(x.getVivo()); //variáveis do tipo "boolean" irão ser inicializadas como "false"
        teste(x); //esse código funciona!
    }
    public static void teste(Animal A) {
        System.out.println(A.getNome());
    }
}