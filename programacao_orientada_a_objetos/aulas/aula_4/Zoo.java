public class Zoo {
    public static void main(String []args) {
        Leao x = new Leao();
        x.setNome("Simba");
        System.out.println(x.getVivo()); //variáveis do tipo "boolean" irão ser inicializadas como "false"
        x.rugir();
    }
}