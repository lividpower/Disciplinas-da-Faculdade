#include <stdio.h>
#include <string.h>
//estou utilizando desse código para testar a busca binário, porém em um documento menor

typedef struct data {
    int dia;
    int mes;
    int ano; //será lido com base na ordem de declaração
}Data;

int main(int argc, char* argv[]) {

    FILE* file_stream;
    Data aniversario;
    int inicio, meio, fim;
    int qtd, c;

    if(argc != 2) 
	{
		fprintf(stderr, "USO: %s [aniversário]\n", argv[0]);
		return 1;
	}

    file_stream = fopen("datas.dat", "rb");

    fseek(file_stream, 0, SEEK_SET);
    inicio = ftell(file_stream);

    fseek(file_stream, 0, SEEK_END);
    fim = ftell(file_stream) - 1;

    meio = (inicio + fim) / 2;

    fseek(file_stream, meio, SEEK_SET); //testando a lógica de implementação da busca binário dessa forma, porém agora em um documento bem menor para conseguir visualizar o que está acontecendo
    qtd = fread(&aniversario, sizeof(Data), 1, file_stream);

    while (qtd > 0) {
        c++;
		if(strncmp(argv[1],aniversario, sizeof(Data)) == 0)
		{
			printf("Dia: %d\tMês: %d\t Ano: %d\n", aniversario.dia, aniversario.mes, aniversario.ano);
			break;
		}
		else if(strncmp(argv[1], aniversario, sizeof(Data) ) < 0) {
			fim = meio - 1;
			meio = (inicio + fim) / 2; 
		}
		else if(strncmp(argv[1], aniversario, sizeof(Data)) > 0) {
			inicio = meio + 1;
			meio = (inicio + fim) / 2;
		}
		fseek(f, meio, SEEK_SET); 
		qt = fread(&aniversario,sizeof(Data),1,file_stream);
    }
    printf("Total Lido: %d\n", c); //quantas vezes a função fread() foi executada...
	fclose(f);
	return 0;
}