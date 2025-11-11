package exercicios.exercicio1;

import java.util.List;
import java.util.ArrayList;

public class Utils {

    public static boolean existe(List x, Funcionario y) {
        //utilizarei do método .contains() de List, mas, para isso, terei que reescrever o método .equals() dentro da minha classe Funcionario para que eu possa realizar a comparação do jeito que eu quiser!
        //o método .contais() de List utiliza do método .equals() referente à classe do objeto que estamos comparando
        if(x.contains(y) == true) {
            return true;
        }
        else{
            return false;
        }
    }

    public static List retornaDados(String  arrayFuncionarios []) {
        List arrayDados = new ArrayList<>(arrayFuncionarios.length); //List<> recebe como parâmetro o tipo da variável que será armazenada dentro da lista
        for(int i = 0; i < arrayFuncionarios.length; i++) { 
            String aux[] = arrayFuncionarios[i].split("#");
            Funcionario obj = null; //eu atribuo null para conseguir escapar de um possível erro de compilação, porém, ainda corro o risco do erro em tempo de execução caso, por algum motivo, as instâncias do obj não seja inicializadas
            if(aux[3].equals("P")) { //isto é mais adequado do que usar "=="
                obj = new Programador(aux[0]);
            }
            else if(aux[3].equals("A")) { //compara as strings em si e não as referências a elas!
                obj = new AnalistaSistemas(aux[0]);
            }
            //obj.setID(aux[0]); //o id já estará sendo preenchido durante a chamada do construtor!
            obj.setNome(aux[1]);
            obj.setSalario(Double.parseDouble(aux[2]));
            arrayDados.add(obj); //adicionando os objetos à lista
        }
        return arrayDados;
    }
    
}
