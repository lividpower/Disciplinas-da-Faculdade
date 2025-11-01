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

int main(int argc, char** argv)
{
	FILE *a, *b, *saida;
	Endereco ea, eb;

	//agora é hora de intercalarmos os arquivos com os blocos de registro em um único só, mantendo a ordenação
	a = fopen("cep_a.dat","rb");
	b = fopen("cep_b.dat","rb"); 
	saida = fopen("saida.dat","wb");

	//estamos lendo o primeiro registro de cada um dos arquivos 
	fread(&ea,sizeof(Endereco),1,a); 
	fread(&eb,sizeof(Endereco),1,b);

	// eof -> End Of File
	while(!feof(a) && !feof(b)) 
	{
		//enquanto não alcançarmos o fim de um dos arquivos...
		//caso um arquivo termine antes do outro, sairemos deste looping
		if(compara(&ea,&eb)<0) // ea < eb
		{
			fwrite(&ea,sizeof(Endereco),1,saida); //se ea < eb, escreve-se ea no arquivo final
			fread(&ea,sizeof(Endereco),1,a); //fazemos a leitura de um novo ea, no caso, este sendo o próximo registro dentro do cep_a
		}
		else // ea >= eb
		{
			//o mesmo acontece nesse caso, mas agora com eb!
			fwrite(&eb,sizeof(Endereco),1,saida);
			fread(&eb,sizeof(Endereco),1,b);
		}
	}

	//caso um dos arquivos acabe antes do outro, temos estes dois próximos loopings para compensar isso!
	//por meio deles, garantimos que iremos escrever todos os registros dos respectivos blocos no arquivo final de saída
	while(!feof(a)) 
	{
		fwrite(&ea,sizeof(Endereco),1,saida);
		fread(&ea,sizeof(Endereco),1,a); //caso a leitura do arquivo acabe, sairemos do looping		
	}
	
	while(!feof(b))
	{
		fwrite(&eb,sizeof(Endereco),1,saida);
		fread(&eb,sizeof(Endereco),1,b); //caso a leitura do arquivo acabe, sairemos do looping	
	}

	fclose(a);
	fclose(b);
	fclose(saida);
	//fim da intercalação
	//ao final, conseguiu-se chegar a um arquivo de saída completamente ordenado de forma crescente!
}

