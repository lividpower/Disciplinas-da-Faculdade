#include <stdio.h>
#include <string.h>

int main() {
    //testando a função snprintf()
    char *string = NULL;
    int retorno = snprintf(string, 0, "Hello"); //retorna o tamanho da string "Hello" sem contar com o \0
    printf("%d\n", retorno);

    char *string_ = "Oi"; //estou definindo uma string literal em região de memória com permissão apenas de leitura!
    int retorno_ = snprintf(string_, strlen(string_) + 1, "Hello"); 
    //estou tentando escrever em uma string que foi alocada em região de memória de apenas leitura!!!!
    //este código gerou um segmentation fault por eu tentar escrever em região de memória não autorizada para escrita!!!!
    printf("%d\n", retorno_);

    return 0;

}