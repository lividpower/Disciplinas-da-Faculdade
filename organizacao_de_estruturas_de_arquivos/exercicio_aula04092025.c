#include <stdio.h>
#define MAX_BYTES 256

int main(int argc, char* argv[]) {
    FILE *file_pointer;
    int array[MAX_BYTES];
    for(int i = 0; i < MAX_BYTES; i++) {
        array[i] = -1;
    }
    char caractere;
    int qtd_linhas;
    if(argc == 2) { 
        file_pointer = fopen(argv[1], "rb"); //não estou lendo um arquivo texto, portanto, talvez seja interessante realizar a leitura em binário...
        if(file_pointer == NULL) {
            return 1; //erro ao abrir o arquivo
        }
        caractere = fgetc(file_pointer);
        //printf("%X\n", caractere); //imprime o valor hexadecimal do caractere lido

        //construindo um if...else para tratar a questão da inicialização do número de linhas
        if(caractere == EOF) { //EOF, por padrão, é igual a -1
            //nesse caso, o arquivo está vazio, portanto, o primeiro caractere lido já indica o fim do arquivo!
            qtd_linhas = 0; 
        }
        else {
            qtd_linhas = 1;
        }

        while(caractere != EOF) {
            if(array[caractere] == -1) { //perceba que o array está sendo indexado pelo valor do caractere de 0 até 255
                array[caractere] = 1; //a primeira ocorrência sendo inicializada!
            }
            else {
                array[caractere]++; //outras ocorrências de um mesmo caractere
            }            
            if(caractere == '\n') { //estou comparando um caractere com outro! caso eu usasse "", eu teria que tomar cuidado porque aí estaria trabalhando com uma string!
                qtd_linhas++;
            }
            caractere = fgetc(file_pointer); //busca o próximo caractere
            //printf("%X\n", caractere); 

        }
        fclose(file_pointer);
        printf("\nNúmero de linhas do arquivo: %d\n", qtd_linhas); // \n equivale a posição 10 na tabela ASCII
        printf("-------------------------------");
        for(int j = 0; j < MAX_BYTES; j++) {
            printf("Caractere ASCII de n°: %d\n", j); //caracteres ASCII indo de 0 a 255
            printf("Numéro de ocorrências: %d\n", (array[j] == -1) ? 0 : array[j]);
            printf("-------------------------------");
            printf("\n");
        }
    }
    else {
        fprintf(stderr, "Número inadequado de argumentos sendo passados pelo terminal de comandos\n");
        fprintf(stderr, "Uso: %s <nome_do_arquivo>\n", argv[0]);
        return 1;
    }
    return 0;
}