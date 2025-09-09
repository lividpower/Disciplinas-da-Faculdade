public class Soma2 {
    public static void main(String []args) {
        int acumulador = 0, i;
        for (i = 1; i <= (Soma.MAX_NUMEROS * 5); i++) {
            if(!(i > 100 && i < 137)) {
                acumulador += i;
            }
        }
        System.out.println("Soma dos números entre 1 a 50.000, com exceção daqueles entre 100 e 137: " + acumulador);
    }
}