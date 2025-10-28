public class InverteString {
    public static void main(String args[]) {
        String string = "Matheus";
        String stringInvertida = inverteString(string);
        System.out.println(string);
        System.out.println(stringInvertida);
        //a ideia é conseguir inverter esta string!
        //não podemos simplesmente indexá-la como um mero array??!!
    }

    public static String inverteString(String string) {
        //é possível medir quantos caracteres contém uma string??!!
        String arrayCaracteres[] = string.split(""); //dividindo a string e armazenando as letras dela separadamente
        String stringInvertida = "";
        for(int i = 0; i < arrayCaracteres.length; i++) {
            stringInvertida += arrayCaracteres[(arrayCaracteres.length - 1) - i];
        }
        return stringInvertida;
    }
}