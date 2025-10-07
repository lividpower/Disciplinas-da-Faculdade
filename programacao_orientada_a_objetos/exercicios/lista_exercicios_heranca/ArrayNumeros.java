//import java.util.Scanner;
import java.util.Random;
//java.lang.Math é importado implicitamente pelo compilador 
import static java.lang.Math.*; //importando os métodos static pertencentes à classe Math 
import java.util.Arrays;

public class ArrayNumeros {
    public static void inverteArrayNumeros(int array[]) { //neste caso, estou fazendo alterações explícitas dentro do array, logo, quando eu voltar para a main, este array terá sido alterado!
        for(int i = 0; i < (array.length / 2); i++) { //o número de modificações reduz pela metade, independente se o array.length é par ou ímpar!
            int aux = array[(array.length - 1) - i]; 
            array[(array.length - 1) - i] = array[i];
            array[i] = aux;
        }
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
        inverteArrayNumeros(array); //estou modificando o conteúdo do array por meio deste método!
        System.out.println("Array de Inteiros invertido: " + Arrays.toString(array));
    }
}