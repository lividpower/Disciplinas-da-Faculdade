#include <stdio.h>
#define MAX_BYTES 256

int main(int argc, char* argv[]) {
    FILE *file_pointer;
    int array[MAX_BYTES];
    for(int i = 0; i < MAX_BYTES; i++) {
        array[i] = -1;
    }
    char caractere;
    int qtd_linhas = 1;
    if(argc == 2) {
        file_pointer = fopen(argv[0], "r");
        if(file_pointer == NULL) {
            return 1; //erro ao abrir o arquivo
        }
        caractere = fgetc(file_pointer);
        while(caractere != EOF) {
            if(array[caractere] == -1) {
                array[caractere] = 1;
            }
            else {
                array[caractere]++;
            }            
            if(caractere == '\n') { //estou comparando um caractere com outro! caso eu usasse "", eu teria que tomar cuidado porque aí estaria trabalhando com uma string!
                qtd_linhas++;
            }
            caractere = fgetc(file_pointer);
        }
        fclose(file_pointer);
        printf("número de linhas do arquivo: %d\n", qtd_linhas);
        for(int j = 0; j < MAX_BYTES; j++) {
            if(array[j] != -1) {
                printf("caractere ASCII %c:\t%d", array[j], array[j]);
            }
        }
    }
    return 0;
}