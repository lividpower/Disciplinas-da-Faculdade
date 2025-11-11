import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class Teste {
    public static void main(String []args) {
        HashMap m = new HashMap(); //as chaves serão únicassss
        m.put("abc123", new Carro("abc123", "Juarez"));
        m.put("3az1821", new Carro("3az1821", "Jonas"));
        Set x = m.keySet();
        int i = 0;
        double soma = 0;
        Iterator<String> it = x.iterator(); //.iterator() retorna uma variável do tipo Iterator 
        //ao definir o tipo genérico como String, consigo que .next() retorne uma String
        while(it.hasNext()) {
            String placa = it.next();
            Carro y = (Carro) m.get(placa);
            soma += (y.getDonoCarro()).length();
            i++;
        }
        double media = soma / m.size();
        System.out.println(media);
    }
}