public class GatoTeste {
    public static void main(String []args) {  //[]args armazena os argumentos passados via linha de comando
        //o arquivo só poderá ser executado se possuir o método main() sendo declarado!
        
        //declarando variáveis
        Gato instancia_1, instancia_2, instancia_3;  //assim que criamos uma variável referente a uma classe, por padrão ela é inicializada com "null"

        //criando uma instância de uma classe        
        instancia_1 = new Gato(); 
        instancia_2 = new Gato(); //construtor de uma classe
        instancia_3 = new Gato();

        //atribuindo valores aos objetos
        //atributo "vivo"
        instancia_1.vivo = true;
        instancia_2.vivo = false;
        instancia_3.vivo = false;

        //atributo "cor"
        instancia_1.cor = "Azul";
        instancia_2.cor = "Vermelho";
        instancia_3.cor = "Amarelo";

        //atributo "idade"
        instancia_1.idade = 5;
        instancia_2.idade = 2;
        instancia_3.idade = 6;

        //atributo "nome"
        instancia_1.nome = "Tangerina";
        instancia_2.nome = "Happy";
        instancia_3.nome = "Garfield";

        //Criando um array com os objetos para conseguir iterar em cima deles
        Gato []array_objetos = {instancia_1, instancia_2, instancia_3};

        //impressão dos atributos no console
        System.out.println("Imprimindo atributos dos Gatos...\n");
        for(int i = 0; i < 3; i++) {
            System.out.println("Nome: " + array_objetos[i].nome);
            System.out.println("Idade: " + array_objetos[i].idade + " anos");
            System.out.println("Cor: " + array_objetos[i].cor);
            System.out.println("Está vivo? " + (array_objetos[i].vivo ? "Sim" : "Não")); //operador ternário para realizar a verificação semelhante a um if...else
            System.out.println(""); //essa chamada já realiza uma quebra de linha de forma automática!
        }
    }
}     