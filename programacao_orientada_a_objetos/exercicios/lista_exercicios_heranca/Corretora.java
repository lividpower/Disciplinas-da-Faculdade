import java.util.Random;

public class Corretora {

    static final int NUM_IMOVEIS = 5; //criando uma constante em Java
    
    public static double somaPrecoImoveis(Imovel array[]) {
        double somador = 0;
        for(int i = 0; i < array.length; i++) {
            somador += array[i].getPreco();
        }
        return somador;
    }
    
    public static void main(String args[]) {

        Imovel array[] = new Imovel[NUM_IMOVEIS]; //esse array do tipo Imovel ainda está com todas as suas variáveis inicializadas como null, posto que ainda não foi alocado nenhuma instância da classe
        for(int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = new Imovel(); //inicializando o array com instâncias da classe
            array[i].setPreco(random.nextDouble() * Math.pow(10,7));
            //necessário fazer a leitura do endereço posteriormente...
        }
        System.out.println("Preço de todos os imóveis somados: " + somaPrecoImoveis(array));


    }
}