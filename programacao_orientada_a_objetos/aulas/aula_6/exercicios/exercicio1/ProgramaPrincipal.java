package exercicios.exercicio1;

import java.util.Scanner;
import java.util.List;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;

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

    public static String[] leArquivoInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo de input: ");
        String fileName = scanner.nextLine();
        //estamos criando uma referência para o arquivo!
        try(InputStreamReader fileStream = new FileReader(fileName)) { //try-with-resources
            int caractereLido; //não podemos simplesmente definir o tipo int dentro de uma estrutura try-with-resources, apenas podemos definir estruturas AutoClosabl
            caractereLido = fileStream.read() //eu achei que seria necessário colocar esta estrutura dentro de um outro try-catch, mas, como já estamos dentro de uma estrutura try-with-resources, qualquer Exception já será capturada automaticamente!


            // fileStream.close() is called AUTOMATICALLY.
            //a estrutura try-with-resources fecha automaticamente o arquivo após este bloco de código ser finalizado!
        } 
        catch(IOException error) {
            // This ONE catch block handles ALL I/O errors:
            // - Error opening the file (e.g., FileNotFoundException)
            // - Error reading the file
            // - Error closing the file
            System.err.println("Erro durante a operação com o arquivo: " + error.getMessage());
            System.exit(1); //finaliza a execução de todo o programa, retornando um código de erro!
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
    }
}








        


