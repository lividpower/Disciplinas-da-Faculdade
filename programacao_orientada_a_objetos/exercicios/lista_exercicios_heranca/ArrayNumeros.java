//import java.util.Scanner;
import java.util.Random;
//java.lang.Math é importado implicitamente pelo compilador 
import static java.lang.Math.*; //importando os métodos static pertencentes à classe Math 
import java.util.Arrays;

public class ArrayNumeros {
    public static int[] inverteArrayNumeros(int array[]) { 
        //int []array_ = array; //estou atribuindo o endereço do meu array inicial para essa nova variável temporária array_ //quando faço isso, o endereço que será alterado é basicamente o mesmo, logo, acaba não surtindo muita diferença
        int []array_ = new int[array.length]; 
        for(int i = 0; i < array.length; i ++) {
            array_[i] = array[i]; //agora sim eu estou criando a cópia do meu array!!!!!!!!!!!!!!!!!
        }
        for(int i = 0; i < (array.length / 2); i++) { //o número de modificações reduz pela metade, independente se o array.length é par ou ímpar!
            int aux = array_[(array.length - 1) - i]; 
            array_[(array.length - 1) - i] = array_[i];
            array_[i] = aux;
        }
        return array_; //agora sim eu fiz uma cópia do array original e não alterei ele diretamente, logo, quando eu acessar a main, terei acesso ao meu array original que se manteve intacto 
    }

    public static void main(String args[]) {
        int array[] = new int[20]; //criando um array com 20 posições
        //Scanner scanner = new Scanner(System.in); //criando uma instância da classe Scanner
        //Random random = new Random(((long) Math.random())); //passando uma semente uma semente aleatória para o método que irá gerar um número aleatório
        Random random = new Random((long) random()); //Math.random() retorna um double e o construtor Random() apenas pode receber um long, por padrão
        for(int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        System.out.println("Array de Inteiros: " + Arrays.toString(array)); //essa é uma forma bem interessante de imprimirmos um array!!!
        System.out.println("Array de Inteiros invertido: " + Arrays.toString(inverteArrayNumeros(array)));
        System.out.println("Array de Inteiros: " + Arrays.toString(array)); //Isto prova que o array original não foi alterado
    }
}