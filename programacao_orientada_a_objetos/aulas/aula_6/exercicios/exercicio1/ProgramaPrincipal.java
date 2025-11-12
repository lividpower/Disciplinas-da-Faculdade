package exercicios.exercicio1;

import java.util.Scanner;
import java.util.List;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class ProgramaPrincipal {

    static final int INPUTS = 5;

    public static String[] leInputUsuario() {
        String arrayFuncionarios[] = new String[INPUTS];
        for(int i = 0; i < INPUTS; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite as informações do funcionário respeitando o seguinte formato de entrada \"id#nome#salario#tipo\": ");
            arrayFuncionarios[i] = scanner.nextLine();
            //não iremos nos preocupar com o tratamento de erros de input do usuário...
        }
        return arrayFuncionarios;
    }

    public static String[] leArquivoInput() {
        
        //inicializando o array
        String arrayFuncionarios[] = new String[INPUTS]; //se o número de inputs fosse desconhecido, teríamos um tratamento diferente
        for(int i = 0; i < arrayFuncionarios.length; i++) {
            arrayFuncionarios[i] = ""; //irei incrementar estas strings posteriormente no código!
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo de input: ");
        String fileName = scanner.nextLine(); //deve-se passar o caminho relativo de acordo com o diretório que eu estiver acessando o arquivo (neste caso, o caminho é: "exercicios/exercicio1/inputUsuario.txt")
        //estamos criando uma referência para o arquivo!
        try(InputStreamReader fileStream = new FileReader(fileName)) { //try-with-resources
            int caractereLido; //não podemos simplesmente definir o tipo int dentro de uma estrutura try-with-resources, apenas podemos definir estruturas AutoClosabl
            caractereLido = fileStream.read(); //eu achei que seria necessário colocar esta estrutura dentro de um outro try-catch, mas, como já estamos dentro de uma estrutura try-with-resources, qualquer Exception já será capturada automaticamente!
            //caractereLido armazena o valor ASCII, por padrão, do determinado caractere!
            int cont = 0; //número de inputs presente dentro do arquivo
            while(caractereLido != -1 && cont < 5) { //se caractereLido == -1, chegamos ao fim do arquivo!
                if(caractereLido != '\n') { //verificando se o caractere lido equivale ao caractere \n
                    arrayFuncionarios[cont] += ((char) caractereLido); 
                }
                else {
                    cont++; //pulando para a próxima linha de input após a leitura de um caractere \n
                }
                caractereLido = fileStream.read();
            }
            return arrayFuncionarios;
            //fileStream.close() is called AUTOMATICALLY.
            //a estrutura try-with-resources fecha automaticamente o arquivo após este bloco de código ser finalizado!
        } 
        catch(IOException error) {
            // This ONE catch block handles ALL I/O errors:
            // - Error opening the file (e.g., FileNotFoundException) -> FileNotFoundException herda a classe IOException
            // - Error reading the file
            // - Error closing the file
            System.err.println("Erro durante a operação com o arquivo: " + error.getMessage());
            return null;
        }
    }

    public static void main(String args[]) { 

        //String arrayFuncionarios[] = leInputUsuario();
        String arrayFuncionarios[] = leArquivoInput();
        System.out.println("\n");
        List instances = Utils.retornaDados(arrayFuncionarios);
        for(int i = 0; i < instances.size(); i++) {
            Object aux = instances.get(i); //.get() retorna um Object, caso o tipo genérico não tenha sido passado previamente para a classe List
            System.out.println((Funcionario) aux);
        }

        HashMap MediaSalarial = Utils.calculaMediaSalarialFuncionarios(instances);
        Set keys = MediaSalarial.keySet(); 
        Iterator iterator = keys.iterator(); //estamos percorrendo o conjunto de chaves, as quais são do tipo String
        while(iterator.hasNext()) {
            String tipoFuncionario = (String) iterator.next(); //iterator.next() estaria retornando um Object, posto que não definimos o tipo genérico da classe Iterator
            if((tipoFuncionario).equals("AnalistaSistemas")) { 
                System.out.println("Média salarial dos Analistas de Sistema: " + MediaSalarial.get(tipoFuncionario));
            }
            else if((tipoFuncionario).equals("Programador")){
                System.out.println("Média salarial dos Programadores: " + MediaSalarial.get(tipoFuncionario));
            }
        }
    }
}








        


