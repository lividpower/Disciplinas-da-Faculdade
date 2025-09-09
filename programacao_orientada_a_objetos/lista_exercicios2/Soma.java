
public class Soma {

    //criando uma constante que poderá ser utilizada sem a necessidade de criação de nenhuma instância de objeto!
    public static final int MAX_NUMEROS = 10000; 

    public static void main(String []args) {
        int i, acumulador = 0;
        for(i = 1; i <= MAX_NUMEROS; i++){
            if((i%2) != 0) { //verificando se o número "i" é impar
                acumulador += i; //se sim, acumulador = acumulador + i
            }
        }
        System.out.println("Soma dos números ímpares de 1 até 10000: " + acumulador);
    }
}