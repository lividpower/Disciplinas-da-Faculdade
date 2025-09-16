#include <stdio.h>
#include <string.h>

typedef struct _Endereco Endereco; 
//declarar o typedef antes de definir a struct é uma ação válida
//o compilador está sendo avisado que futuramente dentro do programa existirá uma estrutura _Endereco

struct _Endereco
{	//todos esses caracteres juntos formam uma linha com 300 caracteres, justamente igual ao padrão presente dentro do arquivo cep.dat
	char logradouro[72];
	char bairro[72];
	char cidade[72];
	char uf[72];
	char sigla[2];
	char cep[8];
	char lixo[2]; // caractere de espaço no final da linha + caractere de quebra de linha (20 0A)
};

int main(int argc, char**argv)
{
	FILE *f;
	Endereco e; //declaração de um buffer que será utilizado para leitura de cada uma das linhas do arquivo cep.dat
	int qt;
	int c;

	if(argc != 2) 
	{
		fprintf(stderr, "USO: %s [CEP]\n", argv[0]);
		return 1;
	}

	c = 0;
	printf("Tamanho da Estrutura: %ld\n\n", sizeof(Endereco)); //%ld é utilizado para variáveis do tipo "long int" que podem possuir de 32 até 64 bits
	f = fopen("cep.dat","rb"); //como sabemos que o arquivo .dat pode não é necessariamente formatado como um arquivo texto, é mais seguro abrí-lo em formato binário
	qt = fread(&e,sizeof(Endereco),1,f); //será lido uma linha do arquivo por vez
	while(qt > 0) //a variável qt irá indicar o número de itens lido, que no caso deverá ser 1
	{
		c++; //contabiliza quantos fread() foram executados para conseguir encontrar a informação dentro do arquivo
		// argv[1] < e.cep  => strcmp(argv[1],e.cep) < 0
		// argv[1] > e.cep  => strcmp(argv[1],e.cep) > 0
		// argv[1] == e.cep  => strcmp(argv[1],e.cep) == 0
		// pode usar o strstr
		if(strncmp(argv[1],e.cep,8)==0)
		{
			printf("%.72s\n%.72s\n%.72s\n%.72s\n%.2s\n%.8s\n",e.logradouro,e.bairro,e.cidade,e.uf,e.sigla,e.cep);
			break;
		}
		qt = fread(&e,sizeof(Endereco),1,f);		
	}
	printf("Total Lido: %d\n", c); //quantas vezes a função fread() foi executada...
	fclose(f);
}

