public class TestaCasa{
    public static void main(String []args) {
        Casa casa_1 = new Casa();
        Casa casa_2 = new Casa();

        casa_1.numero = 360;
        casa_2.numero = 424;

        casa_1.cor = "Azul";
        casa_2.cor = "Vermelho";

        Casa []array_casas = {casa_1, casa_2};
        for(int i = 0; i < 2; i++) {
            System.out.println("A casa de número " + array_casas[i].numero + " tem cor " + array_casas[i].cor + ".");
        }
    }
}