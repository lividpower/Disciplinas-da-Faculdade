//devemos construir uma intercalação que divida o arquivo em n pedaços
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <endereco.h> 
#include <math.h>
#include <assert.h>
//Para compilar -> gcc -I ../../../arquivos_cabecalho/ -g -o intercala intercala_Nblocos.c 

//FIXME: Necessário verificar a questão de liberação de memória para os arrays fileName

int main(int argc, char **argv) {

    char **fileNamesBlocos = NULL, **fileNamesBlocoSaida = NULL;
    long n;
    n = 4;
    double totalBlocos = pow(2, n); //pow(2, n) = 2 * 2 * 2 * 2 * 2 ... (n vezes)

    //alocando memória para meu vetor de strings
    fileNamesBlocos = (char**) malloc(totalBlocos * sizeof(char*));
    assert(fileNamesBlocos != NULL); //erro na alocação de memória!

    //file_cep é variável global dentro de endereco.h
    file_cep = fopen("cep.dat", "rb"); //criando uma referência ao arquivo que, em teoria, não cabe na memória principal
    fseek(file_cep, 0, SEEK_END);
    long tamanhoArquivo = ftell(file_cep); //temos o tamanho, em bytes, do arquivo cep.dat
    long tamanhoBloco = tamanhoArquivo / totalBlocos; //aqui temos o tamanho de cada pedaço que iremos dividir o arquivo!
    long numRegistrosBloco = tamanhoBloco / sizeof(Endereco);

    //agora devemos começar a trabalhar em cima de cada um dos blocos separadamente
    //...
    fseek(file_cep, 0, SEEK_SET); //inicia-se a leitura do arquivo cep.dat
    criaBlocos(totalBlocos, numRegistrosBloco, fileNamesBlocos); //devemos verificar se o valor de "fileNamesBlocos" é alterado dentro da função, mesmo não sendo passado por referência...
    //a partir daqui já temos todos os arquivos de blocos criados...
    //agora devemos intercalá-los...

    int cont = 0; //quantas vezes a função intercalaBlocos() será chamada
    double totalParBlocos;
    do{
        cont++;
        totalParBlocos = intercalaBlocos(totalBlocos, fileNamesBlocos, fileNamesBlocoSaida, cont);
        totalBlocos = totalParBlocos; //os arquivos de saída passarão a ser o número de blocos restantes...
    }while(totalBlocos != 1);
    //se totalBlocos == 1, o arquivo de saída já é o arquivo final
    //podemos parar com as intercalações!
    
    int retorno = rename(fileNamesBlocoSaida[0], "cep-ordenado.dat"); //alterando o nome do arquivo de saída
    assert(retorno == 0);
    return 0;
}


int compara(const void *registro_1, const void *registro_2) {
    return strncmp(((Endereco*)registro_1)->cep, ((Endereco*)registro_2)->cep, 8); 
    //como neste caso temos um ponteiro para uma struct, devemos utilizar de "->"
    //deve-se converter o void pointer para um ponteiro do tipo Endereco antes de tentar acessar os dados 
}

void criaBlocos(double totalBlocos, long numRegistrosBloco, char **fileNamesBlocos) {

    for(int i = 0; i < (totalBlocos / 2); i = i + 2) { //estaremos pulando de dois em dois!


        //inicializando o nome dos arquivos para cada bloco
        int required_size1 = snprintf(NULL, 0, "bloco%d.dat", i + 1);
        int required_size2 = snprintf(NULL, 0, "bloco%d.dat", i + 2); //retorna o número de bytes necessários para escrever a string, excluindo o \0
        fileNamesBlocos[i] = (char*) malloc((required_size1 + 1) * sizeof(char));
        fileNamesBlocos[i + 1] = (char*) malloc((required_size2 + 1) * sizeof(char));
        assert(fileNamesBlocos[i] != NULL && fileNamesBlocos[i + 1] != NULL);
        snprintf(fileNamesBlocos[i], (required_size1 + 1), "bloco%d.dat", i + 1); //estou escrevendo na string
        snprintf(fileNamesBlocos[i + 1], (required_size2 + 1), "bloco%d.dat", i + 2); 
        //se eu tentasse escrever com sprintf() e o meu buffer não tivesse tamanho necessário para escrita, ocorreria um buffer overflow!

        //Bloco A
        Endereco* arrayEnderecos = (Endereco*) malloc(numRegistrosBloco * sizeof(Endereco));
        int qtd = fread(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, file_cep);
        assert(qtd == numRegistrosBloco);
        qsort(arrayEnderecos, numRegistrosBloco, sizeof(Endereco), compara); //ordenando os registros deste nosso array temporário
        FILE *fileBlocoA = fopen(fileNamesBlocos[i], "wb"); 
        qtd = fwrite(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, fileBlocoA); //escrevendo os registros dentro de um bloco A
        assert(qtd == numRegistrosBloco);
        free(arrayEnderecos); //liberamos a memória do bloco, logo, este bloco já não está mais ocupando espaço na memória

        //Bloco B
        arrayEnderecos = (Endereco*) malloc(numRegistrosBloco * sizeof(Endereco)); //malloc() retorna um void pointer
        //a cabeça de leitura já terá avançado e estará na posição adequada
        qtd = fread(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, file_cep);
        assert(qtd == numRegistrosBloco);
        qsort(arrayEnderecos, numRegistrosBloco, sizeof(Endereco), compara);
        FILE *fileBlocoB = fopen(fileNamesBlocos[i + 1], "wb"); 
        qtd = fwrite(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, fileBlocoB);
        assert(qtd == numRegistrosBloco);
        free(arrayEnderecos);
        fclose(fileBlocoA);
        fclose(fileBlocoB);
    }
    return;
}

int intercalaBlocos(double totalBlocos, char **fileNamesBlocos, char **fileNamesBlocoSaida, int cont) {

    int i;
    double totalParBlocos = totalBlocos / 2;
    fileNamesBlocoSaida = (char**) malloc(totalParBlocos  * sizeof(char*));

    for(i = 0; i < totalParBlocos; i++) {

        //inicializando os nomes dos arquivos de saída
        //precisamos diferenciar os arquivos de saída de acordo com a quantidade de vezes que esta função for chamada, evitando a sobrescrita de arquivos! Daí utiliza-se de "cont"
        int required_size = snprintf(NULL, 0, "arquivo_saida%d_%d.dat", i + 1, cont); 
        fileNamesBlocoSaida[i] = (char*) malloc((required_size + 1) * sizeof(char)); 
        snprintf(fileNamesBlocoSaida[i], (required_size + 1), "arquivo_saida%d_%d.dat", i + 1, cont); //estou escrevendo na string 
        
        //intercalação dos blocos A e B
        //...
        FILE *fileBlocoA = fopen(fileNamesBlocos[i], "rb");
        FILE *fileBlocoB = fopen(fileNamesBlocos[i + 1], "rb");
        FILE *saida = fopen(fileNamesBlocoSaida[i], "wb"); //criando um novo arquivo para conseguir intercalar os blocos A e B //estaremos sempre criando um arquivo diferente!

        Endereco A, B; 
        //lendo o primeiro registro de cada um dos respectivos blocos
        fread(&A, sizeof(Endereco), 1, fileBlocoA);
        fread(&B, sizeof(Endereco), 1, fileBlocoB);
        
        //lembrando que estamos ordenando o arquivo em ordem crescente!
        while(!feof(fileBlocoA) && !feof(fileBlocoB)) { //caso algum dos arquivos chegue ao fim, iremos sair do looping!
            if(strncmp(A.cep, B.cep, 8) > 0) { // B < A
                fwrite(&B, sizeof(Endereco), 1, saida);
                fread(&B, sizeof(Endereco), 1, fileBlocoB); //lendo o próximo registro do bloco B
            }
            else { // A < B
                fwrite(&A, sizeof(Endereco), 1, saida);
                fread(&A, sizeof(Endereco), 1, fileBlocoA);
        }
        }

        //agora faremos a verificação 
        while(!feof(fileBlocoA)) {
            fwrite(&A, sizeof(Endereco), 1, saida);
            fread(&A, sizeof(Endereco), 1, fileBlocoA);
        }

        while(!feof(fileBlocoB)) {
            fwrite(&B, sizeof(Endereco), 1, saida);
            fread(&B, sizeof(Endereco), 1, fileBlocoB);
        }
        //fim da intercalação
        fclose(saida);
        fclose(fileBlocoA);
        remove(fileNamesBlocos[i]); //é necessário passar o nome do arquivo!
        fclose(fileBlocoB);
        remove(fileNamesBlocos[i + 1]); //exclui o arquivo do diretório caso não exista mais nenhuma referência a este arquivo dentre os processos existentes...
    }
    if(totalParBlocos != 1) {
        //substituindo os blocos pelos arquivos de saída para que possa ocorrer a próxima intercalação!
        free(fileNamesBlocos);
        fileNamesBlocos = (char**) malloc(sizeof(fileNamesBlocoSaida));
        for(int x = 0; x < totalParBlocos; x++) {
            fileNamesBlocos[x] = fileNamesBlocoSaida[x]; //passando a referência de um para outro!
        }
        free(fileNamesBlocoSaida); //liberando a memória que não será mais necessária...os blocos de saída serão redefinidos na próxima intercalação
    }
    else {
        //o arquivo de saída já será o arquivo final!
        free(fileNamesBlocos); //já não precisaremos mais dos blocos anteriores!
        //não liberamos o fileNamesBlocoSaida porque precisaremos dele dentro da main!
    }
    return totalParBlocos; //iremos retornar quantos blocos de saída nós produzimos durante esta função!
}