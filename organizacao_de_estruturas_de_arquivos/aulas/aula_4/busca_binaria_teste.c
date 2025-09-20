#include <stdio.h>
#include <string.h>
//estou utilizando desse código para testar a busca binário, porém em um documento menor

typedef struct aluno {
    char nome[12]; 
	char aniversario[8]; //será lido com base na ordem de declaração
	char lixo[2]; //caractere de espaço no final da linha + caractere de quebra de linha (20 0A)
}Aluno;

int main(int argc, char* argv[]) { 

    FILE* file_stream;
    Aluno perfil_aluno;
    int inicio, meio, fim;
    int qtd, c;

    if(argc != 2) 
	{
		fprintf(stderr, "USO: %s [Nome do aluno]\n", argv[0]);
		return 1;
	}

    file_stream = fopen("datas_ordenadas.dat", "rb");

    fseek(file_stream, 0, SEEK_SET);
    inicio = ftell(file_stream);

    fseek(file_stream, 0, SEEK_END);
    fim = ftell(file_stream) - 1;

    meio = (inicio + fim) / 2; //com a implementação dessa lógica, o meio, na realidade, não estaria se referindo à estrutura do meio, mas sim ao byte que se encontra no meio do arquivo, o que pode acabar quebrando a divisão das estruturas que estamos analisando
	//PORTANTO, ESSA LÓGICA NÃOOOOO FUNCIONAAAAA!!!!
	
    fseek(file_stream, meio, SEEK_SET); //testando a lógica de implementação da busca binário dessa forma, porém agora em um documento bem menor para conseguir visualizar o que está acontecendo
    qtd = fread(&perfil_aluno, sizeof(Aluno), 1, file_stream);

    while (qtd > 0) { //fread() retorna 0 quando a leitura chega no final do arquivo
        c++;
		if(strncmp(argv[1], perfil_aluno.nome, 8) == 0)
		{
			printf("Aniversário do aluno: %s", perfil_aluno.aniversario);
			break;
		}
		else if(strncmp(argv[1], perfil_aluno.nome, 8) < 0) {
			fim = meio - 1;
			meio = (inicio + fim) / 2; 
		}
		else if(strncmp(argv[1], perfil_aluno.nome, 8) > 0) {
			inicio = meio + 1;
			meio = (inicio + fim) / 2;
		}
		fseek(file_stream, meio, SEEK_SET); 
		qtd = fread(&perfil_aluno,sizeof(Aluno),1,file_stream); //a última leitura não irá encontrar o \n final, portanto, a leitura irá ser finalizada por conta do fim do arquivo e não pelo fim da estrutura que está sendo lida
    }
    printf("Total Lido: %d\n", c); //quantas vezes a função fread() foi executada...
	fclose(file_stream);
	return 0;
}