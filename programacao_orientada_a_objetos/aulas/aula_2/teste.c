#include <stdio.h>

int main(int argc, char* argv[]) {
    int i = 5;
    int x;
    x = i++; //pós-incrementação
    //x = ++i //pré-incrementação 
    printf("%d\n", i);
    printf("%d\n", x);
    return 0;
}