#include <stdio.h>
#include <string.h>

int main() {
    //testando a função snprintf()
    char *string = NULL;
    int retorno = snprintf(string, 0, "Hello"); //retorna o tamanho da string "Hello" sem contar com o \0
    printf("%d\n", retorno);

    char *string_ = "Oi";
    int retorno_ = snprintf(string_, strlen(string_) + 1, "Hello"); //este código gerou um segmentation fault!
    printf("%d\n", retorno_);

    return 0;

}