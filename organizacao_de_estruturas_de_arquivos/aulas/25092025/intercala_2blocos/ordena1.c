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

int compara(const void *e1, const void *e2) //função cujo endereço (no caso, endereço esse que armazenará o segmento de código da função) será passado como argumento da função qsort()
{
	return strncmp(((Endereco*)e1)->cep,((Endereco*)e2)->cep,8); //comparando dois registros com base no CEP
}

int main(int argc, char**argv)
{
	FILE *f, *saida;
	Endereco *e;
	long posicao, qtd, metade;

	f = fopen("cep.dat","rb");
	fseek(f,0,SEEK_END);
	posicao = ftell(f); //retorna o tamanho do arquivo
	qtd = posicao/sizeof(Endereco); //aqui temos o número de estruturas Endereco existentes dentro do arquivo
	e = (Endereco*) malloc(qtd*sizeof(Endereco)); //aqui temos a criação do que seria um array de Enderecos (Endereco array_enderecos[qtd])
	//essa criação desse ponteiro e é feita de forma dinâmica, posto que a memória que essa variável terá supera o limite de memória existente para variáveis na pilha
	//no entanto, perceba que ao alocarmos toda essa memória de uma vez só, assumimos que nossa memória RAM irá dar conta disso!
	rewind(f);
	if(fread(e,sizeof(Endereco),qtd,f) == qtd) //aqui estamos realizando a leitura de todo o arquivo de uma vez e, logo após, conferindo se o que foi lido corresponde de fato com o tamanho do arquivo
	{
		printf("Lido = OK\n");
	}
	qsort(e,qtd,sizeof(Endereco),compara); //função que ordena nosso array_enderecos
	printf("Ordenado = OK\n");
	saida = fopen("cep-ordenado.dat","wb"); //estamos criando um novo arquivo vazio
	fwrite(e,sizeof(Endereco),qtd,saida); //realizando a escrita dos registros dentro deste arquivo, mas já de forma ordenada de acordo com o CEP
	fclose(saida);
	free(e);
	return 0;
}

