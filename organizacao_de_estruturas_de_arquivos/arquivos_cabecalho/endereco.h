#ifndef ENDERECO_H //isto daqui são macros de pré-processamento
#define ENDERECO_H //utilizamos deste if para não inserirmos o mesmo arquivo de cabeçalho dentro de um arquivo

//este é um arquivo de cabeçalhos
//não é interessante definirmos as funções dentro deste arquivo!

//variáveis globais
FILE *file_indices, *file_cep;

typedef struct _Endereco
{	//todos esses caracteres juntos formam uma linha com 300 caracteres, justamente igual ao padrão presente dentro do arquivo cep.dat
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

int compara(const void *i1, const void *i2);

int intercalaBlocos(char *fileNameBlocoA, char *fileNameBlocoB);

#endif
