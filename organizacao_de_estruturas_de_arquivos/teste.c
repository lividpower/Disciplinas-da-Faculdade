#include <stdio.h>

int main(int argc, char* argv[]) {
    int i = 5;
    int x;
    x = i++;
    printf("%d\n", i);
    printf("%d\n", x);
    return 0;
}