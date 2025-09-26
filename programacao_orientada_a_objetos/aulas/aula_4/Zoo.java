public class Zoo {
    public static void main(String []args) {
        Animal x = new Leao(); //esse código não compila, porque Animal não pode ser atribuído a um tipo Leao, no entanto, o contrário é válido!
        x.setNome("Simba");
        System.out.println(x.getVivo()); //variáveis do tipo "boolean" irão ser inicializadas como "false"
        teste(x); 
        x.correr();
    }
    public static void teste(Animal A) {
        System.out.println(A.getNome());
        if(A instanceof Leao) {
            Leao x = (Leao) A;
            x.rugir();
            x.correr();
        }
    }
}