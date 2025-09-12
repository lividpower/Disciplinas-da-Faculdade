/*public class LeaoTeste {
    public static void main(String []args) {
        //esse código não funcionaaaaa!!!!
        Leao leao_1, leao_2, leao_3;
        Leao []array_leoes = {leao_1, leao_2, leao_3}; //Java não aceita a atribuição de valores que ainda não foram sequer inicializados
        for(int i = 0; i < 3; i++) {
            array_leoes[i] = new Leao();
        }

    }
}*/

public class LeaoTeste {

    //método gerado pelo Gemini
    public static void imprimirArray(Leao[] array) { // Exemplo de como ficaria em um método
        // Cabeçalho para referência (opcional, mas ajuda a ver as colunas)
        System.out.printf("%-15s %-12s %-15s %s\n", "Nome", "Está vivo", "Cor", "Idade");
        System.out.printf("--------------- ------------ --------------- -----\n");

        for (int i = 0; i < array.length; i++) {
            System.out.printf("%-15s %-12b %-15s %d\n",
                    array[i].nome,
                    array[i].vivo,
                    array[i].cor,
                    array[i].idade);
        }
    }

    public static void main(String []args) {
        Leao leao_1 = new Leao();
        Leao leao_2 = new Leao();
        Leao leao_3 = new Leao();

        leao_1.nome = "Simba";
        leao_1.vivo = true;
        leao_1.cor = "Amarelado";
        leao_1.idade = 10;

        leao_2.nome = "Rei leao";
        leao_2.vivo = true;
        leao_2.cor = "Marrom claro";
        leao_2.idade = 15;

        leao_3.nome = "Jumanji";
        leao_3.vivo = false;
        leao_3.cor = "Alaranjado";
        leao_3.idade = 5;

        /*Leao []array = {leao_1, leao_2, leao_3};
        for(int i = 0; i < 3; i++) {
            System.out.println("Nome: " + array[i].nome + "\tEstá vivo: " + (array[i].vivo ? "Sim" : "Não") + "\tCor: " + array[i].cor + "\tIdade: " + array[i].idade);
        }*/

       Leao []array = {leao_1, leao_2, leao_3};
       imprimirArray(array); //para conseguir executar esse método localmente dentro do método main sem ter criado nenhuma instância da classe LeaoTeste anteriormente, é necessário declarar esse método como estático

    }
}