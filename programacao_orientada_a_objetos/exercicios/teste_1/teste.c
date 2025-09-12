#include <stdio.h>
//testando a atribuição de um valor não inicializado, só que agora na linguagem C
int main() {
    int x, y;
    printf("%d\n", x); 
    x = y; //a linguagem C não bloqueia esse tipo de operação, mesmo a variável y não tendo sido inicializada!
    printf("%d\n", x); //o que é exibido na tela corresponde a um lixo de memória!!!
    return 0;
    //o código funciona e o compilador de C não bloqueia essa operação!!!!
}