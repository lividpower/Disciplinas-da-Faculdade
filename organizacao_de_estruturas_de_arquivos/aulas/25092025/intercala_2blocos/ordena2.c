#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct _Endereco Endereco;

struct _Endereco
{
	char logradouro[72];
	char bairro[72];
	char cidade[72];
	char uf[72];
	char sigla[2];
	char cep[8];
	char lixo[2];
};

int compara(const void *e1, const void *e2)
{
	return strncmp(((Endereco*)e1)->cep,((Endereco*)e2)->cep,8);
}

int main(int argc, char**argv)
{
	FILE *f, *saida;
	Endereco *e;
	long posicao, qtd, metade;

	f = fopen("cep.dat","rb"); 
	fseek(f,0,SEEK_END);
	posicao = ftell(f);
	qtd = posicao/sizeof(Endereco);
	metade = qtd/2; //estamos dividindo o número de registros pela metade 
	//a metade, neste caso, seria o elemento pivô
	//aqui não deveríamos ter (metade - 1), posto que a metade realmente seria (0 + (n-1) / 2) ???
	e = (Endereco*) malloc(metade*sizeof(Endereco)); //estamos alocando espaço para metade dos registros existentes no arquivo
	rewind(f);
	if(fread(e,sizeof(Endereco),metade,f) == metade) //estamos realizando a leitura de metade dos registros do arquivo!
	{
		printf("Lido = OK\n");
	}
	qsort(e,metade,sizeof(Endereco),compara); //estamos ordenando a primeira metade do arquivo
	printf("Ordenado = OK\n");
	saida = fopen("cep_a.dat","wb"); //estamos criando um novo arquivo
	fwrite(e,sizeof(Endereco),metade,saida); //estamos escrevendo a primeira metade dos registros dentro deste arquivo
	fclose(saida); //estamos fechando esse novo arquivo que foi criado
	printf("Escrito = OK\n");
	free(e); //estamos liberando a memória alocada para armanezar a primeira metade dos registros

	e = (Endereco*) malloc((qtd-metade)*sizeof(Endereco)); //estamos alocando memória para os registros que sobraram na segunda metade
	if(fread(e,sizeof(Endereco),qtd-metade,f) == qtd-metade) //estamos lendo os registros da segunda metade
	{
		printf("Lido = OK\n");
	}
	qsort(e,qtd-metade,sizeof(Endereco),compara); //estamos ordenando a segunda metade do arquivo
	printf("Ordenado = OK\n");
	saida = fopen("cep_b.dat","wb"); //estamos abrindo um outro novo arquivo
	fwrite(e,sizeof(Endereco),qtd-metade,saida); //estamos armazenando a segunda metade nesse novo arquivo
	//agora temos dois arquivos, cada um com uma respectiva metade já ordenada!
	fclose(saida);
	printf("Escrito = OK\n");
	free(e);

	fclose(f);
}

