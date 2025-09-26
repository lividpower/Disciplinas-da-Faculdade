public class Zoo {
    public static void main(String []args) {
        Leao x = new Animal(); //esse código não compila, porque Animal não pode ser atribuído a um tipo Leao, no entanto, o contrário é válido!
        x.setNome("Simba");
        System.out.println(x.getVivo()); //variáveis do tipo "boolean" irão ser inicializadas como "false"
        teste(x); //esse código funciona!
    }
    public static void teste(Animal A) {
        System.out.println(A.getNome());
    }
}