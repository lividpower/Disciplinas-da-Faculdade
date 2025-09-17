#include <stdio.h>
#include <string.h>

typedef struct _Endereco Endereco; 

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
	Endereco e; 
	int qt;
	int c;
	int inicio, meio, fim; // criando variáveis para implementação da busca binária

	if(argc != 2) 
	{
		fprintf(stderr, "USO: %s [CEP]\n", argv[0]);
		return 1;
	}
   
	c = 0;
	printf("Tamanho da Estrutura: %ld\n\n", sizeof(Endereco)); 
	f = fopen("cep_ordenado.dat","rb"); //para que consigamos implementar a busca binária, devemos ter a sequência previamente ordenanda

	//inicializando as variáveis da busca binária
	fseek(f, 0, SEEK_SET);
	inicio = ftell(f);
	inicio = inicio / sizeof(Endereco);
	if(inicio == -1) {
		fprintf(stderr, "Erro na função ftell()\n");
	}
	fseek(f, 0, SEEK_END);
	fim = ftell(f) - 1; //como iremos trabalhar com indexação, precisamos subtrair 1 para conseguir manipular posteriormente os indíces das strings
	fim = fim / sizeof(Endereco); //estamos agora trabalhando com a indexação das diferentes estruturas Endereco que existem dentro do arquivo cep_ordenado.dat
	if(fim == -1) {
		fprintf(stderr, "Erro na função ftell()\n");

	}
	meio = (inicio + fim) / 2; //dúvida em relação ao posicionamento dentro do array
	fseek(f, meio * sizeof(Endereco), SEEK_SET);
	qt = fread(&e,sizeof(Endereco),1,f); //iremos ler a estrutura Endereco posicionada no meio do arquivo
	
	//chegará uma hora que, após tantas divisões terem sido realizadas, o meio acabará sendo igual o inicio e o fim, indicando que o arquivo está prestes a chegar ao fim
	//caso o CEP dado como argumento não seja encontrado, o fim do arquivo forçará a saída do looping
	while(qt > 0) {
		c++;
		if(strncmp(argv[1],e.cep,8)==0)
		{	//o CEP estará justamente no meio do arquivo
			printf("%.72s\n%.72s\n%.72s\n%.72s\n%.2s\n%.8s\n",e.logradouro,e.bairro,e.cidade,e.uf,e.sigla,e.cep);
			break;
		}
		else if(strncmp(argv[1],e.cep,8) < 0) {
			fim = meio - 1;
			meio = (inicio + fim) / 2; 
		}
		else if(strncmp(argv[1],e.cep,8) > 0) {
			inicio = meio + 1;
			meio = (inicio + fim) / 2;
		}
		fseek(f, meio * sizeof(Endereco), SEEK_SET); 
		qt = fread(&e,sizeof(Endereco),1,f);		
	}
	printf("Total Lido: %d\n", c); //quantas vezes a função fread() foi executada...
	fclose(f);
}

