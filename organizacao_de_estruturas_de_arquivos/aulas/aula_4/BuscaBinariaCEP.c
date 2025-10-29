#include <stdio.h>
#include <string.h>

typedef struct _Endereco Endereco; 

struct _Endereco
{	//todos esses caracteres juntos formam uma linha com 300 caracteres, justamente igual ao padrão presente dentro do arquivo cep.dat
	//mas e os caracteres nulos ??? onde que eles entram???
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
	inicio = ftell(f) / sizeof(Endereco); //divide-se tanto o início e o fim pelo tamanho da struct para que possamos ir pulando de estrutura para outra estrutura por meio da indexação
	if(inicio == -1) {
		fprintf(stderr, "Erro na função ftell()\n");
	}
	fseek(f, 0, SEEK_END);
	fim = (ftell(f) - 1) / sizeof(Endereco); //como iremos trabalhar com indexação, precisamos subtrair 1 para conseguir manipular posteriormente os indíces das strings
	if(fim == -1) {
		fprintf(stderr, "Erro na função ftell()\n");
	}
	meio = (inicio + fim) / 2; 
	fseek(f, (meio * sizeof(Endereco)), SEEK_SET); // se não multiplicamos meio por sizeof(Endereco), iremos acabar acessando uma região no arquivo que será de outra estrutura e não queremos isso!
	qt = fread(&e,sizeof(Endereco),1,f); //iremos ler a estrutura Endereco posicionada no meio do arquivo
	
	//chegará uma hora que, após tantas divisões terem sido realizadas, o meio acabará sendo igual o inicio e o fim, indicando que o arquivo está prestes a chegar ao fim
	//caso o CEP dado como argumento não seja encontrado, o fim do arquivo forçará a saída do looping
	while(qt > 0) {
		if((inicio == meio) || (fim == meio)) { //a verificação é feita logo no início porque garante que , se, por acaso, o único CEP que sobrou for compatível com o que está sendo buscado, esse match irá ocorrer e o looping seria encerrado na iteração anterior a essa
			fprintf(stderr, "O CEP que está sendo procurado não existe dentro do arquivo \"cep_ordenado.dat\"\n");
			return 1;
		}
		c++;
		if(strncmp(argv[1],e.cep,8)==0) {
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
		fseek(f, (meio * sizeof(Endereco)), SEEK_SET); 
		qt = fread(&e,sizeof(Endereco),1,f);		
	}
	printf("Total Lido: %d\n", c); //quantas vezes a função fread() foi executada...
	printf("Total de bytes que foram lidos durante o processo de busca do registro desejado: %ld\n", (c - 1) * sizeof(Endereco));
	fclose(f);
	return 0;
}

