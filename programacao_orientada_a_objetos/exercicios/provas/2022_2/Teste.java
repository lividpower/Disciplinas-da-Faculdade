public class Teste{
    public static void main(String args[]){
        for(int i = 0; i < 1; i++) {
        Caneca c = new Caneca();
        }
        System.out.println(c.x);
        //uma variável de instância definida dentro do for() não poderá ser acessada fora desse for()
        //o mesmo ocorre com variáveis locais
    }
}