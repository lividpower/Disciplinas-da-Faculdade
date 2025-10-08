import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;

public class Corretora {

    static final int NUM_IMOVEIS = 5; //criando uma constante em Java
    static final int TAMANHO_ENDERECO = 300;

    public static String buscaEnderecoAleatorio() throws IOException{  //caso exista algum problema ao tentar fechar o arquivo
        //busca um endereço aleatório dentro do arquivo cep.dat
        String file_name = "cep.dat";
        try(FileInputStream file = new FileInputStream(file_name)) {
            //file.close() pode gerar um IOException caso tenha problemas ao fechar o arquivo // no caso, a estrutura try...catch fecha o arquivo de forma automática!
            byte buffer[] = new byte[TAMANHO_ENDERECO]; //cada estrutura que será lida do arquivo cep.dat possui exatamente 300 bytes
            Random random = new Random();
            //Map<String, String> dicionarioEnderecos = new HashMap<>(); //uma espécie de polimorfismo
            int tamanhoArquivo = file.available();
            int numEnderecos = tamanhoArquivo / 300;
            file.skip(random.nextInt(numEnderecos) * 300); //estarei pulando a leitura de n bytes //estou fazendo a randomização de quantas estruturas eu estarei pulando
            file.read(buffer);
            char aux[] = new char[TAMANHO_ENDERECO]; //irá armazenar apenas a parte do endereço que interessa para a gente
            String endereco = "";
            for(int i = 0; i < 288; i++) { //288, no caso, refere-se ao tamanho do pedaço do endereço que estamos armazenando que é de fato interessante para nós
                aux[i] = (char) buffer[i]; //convertendo os bytes em caracteres
                endereco += aux[i]; //fazendo a conversão para o tipo String
            }
            String []t1 = endereco.split(" ", -1); //dividindo as strings presentes em endereço // If n is non-positive then the pattern will be applied as many times as possible and the array can have any length
            String []t2 = new String[t1.length];
            for(int i = 0; i < t1.length; i++) {
                //System.out.println(t1[i]);
                t2[i] = new String(); //inicializando os índices do array com null para que posteriormente eu possa conferir o que terá de conteúdo neles
                if(t1[i] != " ") {
                    t2[i] = t1[i];
                }
            }
            String stringEndereco = "";
            for(int i = 0; i < t2.length; i++) {
                if(t2[i] == null) {
                    break;
                }
                stringEndereco += t2[i];
            }
            return stringEndereco;            
        }
        catch (FileNotFoundException  err) {
            System.err.println("Erro ao abrir o arquivo: " + err.getMessage());
            return null; // retornar null é uma opção quando não temos nenhuma variável para retornar, mas precisamos obedecer o tipo de variável de retorno
            // se eu não garanto que meu método não irá retornar uma string, eu tenho um erro de compilação!
        }        
    }
    
    public static double somaPrecoImoveis(Imovel array[]) {
        double somador = 0;
        for(int i = 0; i < array.length; i++) {
            somador += array[i].getPreco();
        }
        return somador;
    }
    
    public static void main(String args[]) {

        Imovel array[] = new Imovel[NUM_IMOVEIS]; //esse array do tipo Imovel ainda está com todas as suas variáveis inicializadas como null, posto que ainda não foi alocado nenhuma instância da classe
        for(int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = new Imovel(); //inicializando o array com instâncias da classe
            array[i].setPreco(random.nextDouble() * Math.pow(10,7));
            //necessário fazer a leitura do endereço posteriormente...
            try {
                //Map<String, String> endereco = buscaEnderecoAleatorio();
                array[i].setEndereco(buscaEnderecoAleatorio()); 
            }
            catch (IOException err) {
                System.err.println("Erro ao tentar fechar o arquivo: " + err.getMessage());
            }
        }
        System.out.println("Preço de todos os imóveis somados: " + somaPrecoImoveis(array));
        for(int i = 0; i < array.length; i++) {
            System.out.println("Endereço dos imóveis: " + array[i].getEndereco());
        }
    }
} 