#include <stdio.h>

typedef struct _Endereco Endereco;
//testando os possíveis erros de compilação que podem acontecer quando defino ou não minha struct

int main() {
    Endereco *e;
    printf("%p\n", e); //%p é utilizado para exibir endereços    
}