public class TestaCasaJanela { 
    public static void main(String []args) {

        //Essas variáveis que estão sendo criadas são locais ao método main!
        Janela janela = new Janela();
        janela.cor = "Preto";
        janela.material = "Vidro";

        Casa casa_1 = new Casa(); //criando um novo objeto
        casa_1.numero = 360;
        casa_1.cor = "Azul";
        casa_1.janela = janela; //estou atribuindo um objeto a um outro objeto! muito interessante!
        //a compilação até aqui teve sucesso!

        System.out.println("Número da casa: " + casa_1.numero);
        System.out.println("Cor da casa: " + casa_1.cor);
        System.out.println("Cor da janela: " + casa_1.janela.cor);
        System.out.println("Material da janela: " + casa_1.janela.material);

    }
}