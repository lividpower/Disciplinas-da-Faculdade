//import java.util.Scanner;
import java.util.Random;
//java.lang.Math é importado implicitamente pelo compilador 
import static java.lang.Math.*; //importando os métodos static pertencentes à classe Math 

public class ArrayNumeros {
    public static int inverteArrayNumeros(int array[]) {
        return 0;
    }

    public static void main(String args[]) {
        int array[] = new int[20]; //criando um array com 20 posições
        //Scanner scanner = new Scanner(System.in); //criando uma instância da classe Scanner
        //Random random = new Random(((long) Math.random())); //passando uma semente uma semente aleatória para o método que irá gerar um número aleatório
        Random random = new Random((long) random()); //Math.random() retorna um double e o construtor Random() apenas pode receber um long, por padrão
        System.out.println("Preencha o array com números inteiros: ");
        for(int i = 0; i < array.length; i++) {
            System.out.printf("array[%d] = %d%n", i, random.nextInt(1000)); // %n, nesse caso, representa uma nova linha!
        }
    }
}