#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    //testando a função snprintf()
    char* i = "olá";
    char *string = (char*) malloc(2 * sizeof(char)); //estou alocando espaço para apenas um caractere e não estou especificando o tipo do ponteiro que está sendo retornado 
    //size_t len = strlen(string);
    //printf("%d\n", (int) len);
    int retorno = snprintf(string, 2, "Hello"); //retorna o tamanho da string "Hello" sem contar com o \0
    printf("%d\n", retorno);
    printf("%s\n", string);

    //char *string_ = "Oi"; //estou definindo uma string literal em região de memória com permissão apenas de leitura!
    //int retorno_ = snprintf(string_, strlen(string_) + 1, "Hello"); 
    //estou tentando escrever em uma string que foi alocada em região de memória de apenas leitura!!!!
    //este código gerou um segmentation fault por eu tentar escrever em região de memória não autorizada para escrita!!!!
    //printf("%d\n", retorno_);


    return 0;

}