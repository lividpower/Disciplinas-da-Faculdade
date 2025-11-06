#ifndef ENDERECO_H //isto daqui são macros de pré-processamento
#define ENDERECO_H //utilizamos deste if para não inserirmos o mesmo arquivo de cabeçalho dentro de um arquivo

//este é um arquivo de cabeçalhos
//não é interessante definirmos as funções dentro deste arquivo!

//variáveis globais
FILE *file_indices, *file_cep;

typedef struct _Endereco
{
	//esses caracteres juntos formam uma linha com 300 caracteres, justamente igual ao padrão presente dentro do arquivo cep.dat
	//mas e os caracteres nulos? neste arquivo, não temos caracteres nulos! devemos tomar cuidado com isso ao manipular esses vetores de caractere
	char logradouro[72]; 
	char bairro[72];
	char cidade[72];
	char uf[72];
	char sigla[2];
	char cep[8];
	char lixo[2]; // caractere de espaço no final da linha + caractere de quebra de linha (20 0A)
} Endereco;

typedef struct _Indice {
	long posicao_bytes; //posicionamento em relação aos bytes
	char cep[8]; 
} Indice;

typedef struct IndiceMultinivel {
	long indexBlocoIndice;
	long posicaoRegistro; //posição em bytes
	char cep[8];
} IndiceMulti;


int compara(const void *i1, const void *i2);

void criaBlocos(double totalBlocos, long numRegistrosBloco, char ***fileNamesBlocos);

void intercalaBlocos(double totalBlocos, double *totalParBlocos, char ***fileNamesBlocos, char ***fileNamesBlocoSaida, int cont);

long buscaSequencialIndice(int *qtd, char *cep, long num_indices);
long buscaBinariaIndice(int *qtd, char *cep, long num_indices);

int buscaSequencialIndiceMulti(int *qtd, char *cep, IndiceMulti *buffer, int indexBloco, long numIndicesBloco);
int buscaBlocoIndiceMulti(int *qtd, char *cep, long numIndicesBloco, long numBlocos, IndiceMulti *buffer);
int buscaBinariaIndiceMulti(int *qtd, char *cep, IndiceMulti *buffer, int indexBloco, long numIndicesBloco);

#endif
