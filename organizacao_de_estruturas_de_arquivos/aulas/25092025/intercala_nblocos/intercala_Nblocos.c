//devemos construir uma intercalação que divida o arquivo em n pedaços
#include <stdio.h>
#include <stdlib.h>
#include <endereco.h> 
#include <math.h>
#include <assert.h>


int main(int argc, char **argv) {

    long n, numBlocos;
    n = 4;
    double totalBlocos = pow(2, n); //pow(2, n) = 2 * 2 * 2 * 2 * 2 ... (n vezes)

    FILE* fileCep = fopen("cep.dat", "rb"); //criando uma referência ao arquivo que, em teoria, não cabe na memória principal
    fseek(file_cep, 0, SEEK_END);
    long tamanhoArquivo = ftell(file_cep); //temos o tamanho, em bytes, do arquivo cep.dat
    long tamanhoBloco = tamanhoArquivo / totalBlocos; //aqui temos o tamanho de cada pedaço que iremos dividir o arquivo!
    long numRegistrosBloco = tamanhoBloco / sizeof(Endereco);

    //agora devemos começar a trabalhar em cima de cada um dos blocos separadamente
    //...
    fseek(file_cep, 0, SEEK_SET); //inicia-se a leitura do arquivo cep.dat
    int i; //declarando ele fora do for() para poder conferir algumas condições após o for()...
    //iremos utilizar de i = 1 ao invés de i = 0 para facilitar algumas comparações futuras!
    for(i = 1; i <= (totalBlocos / 2); i++) { //iremos fazer a intercalação em pares!

        //Bloco A
        arrayEnderecos = (Endereco*) malloc(numRegistrosBloco * sizeof(Endereco));
        int qtd = fread(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, file_cep);
        assert(qtd != numRegistrosBloco);
        qsort(arrayEnderecos, numRegistrosBloco, sizeof(Endereco), compara); //ordenando os registros deste nosso array temporário
        fileBlocoA = fopen("bloco_A.dat", "wb"); //sempre que este arquivo for aberto dentro do looping, ele será sobrescrito!!!
        qtd = fwrite(arrayEnderecos, sizeof(Endereco), numRegistrosBloco, fileBlocoA); //escrevendo os registros dentro de um bloco A
        assert(qtd != numRegistrosBloco);
        free(arrayEnderecos); //liberamos a memória do bloco, logo, este bloco já não está mais ocupando espaço na memória

        //Bloco B
        arrayEnderecos = (Endereco*) malloc(numRegistrosBloco * sizeof(Endereco)); //malloc() retorna um void pointer
        //a cabeça de leitura já terá avançado e estará na posição adequada
        qtd = fread(arrayEnderrecos, sizeof(Endereco), numRegistrosBloco, file_cep);
        assert(qtd != numRegistrosBloco);
        qsort(arrayEnderecos, numRegistrosBloco, sizeof(Endereco), compara);
        fileBlocoB = fopen("bloco_B.dat", "wb"); //sempre que este arquivo for aberto dentro do looping, ele será sobrescrito!!!
        qtd = fwrite(arrayEnderecos, sizeof(Endereco), numregistrosBloco, fileBlocoB);
        assert(qtd != numRegistrosBloco);
        free(arrayEnderecos);
        fclose(file_cep); //cep.dat não será utilizado nas operações abaixo

        //intercalação dos blocos A e B
        //...

        char *fileName;
        sprintf(fileName, "intercala_blocos%d.dat", i); //estou escrevendo na string
        saida = fopen(fileName, "wb"); //criando um novo arquivo para conseguir intercalar os blocos A e  //estaremos sempre criando um arquivo diferente!

        //colocando a cabeça de leitura dos respectivos blocos de volta ao início
        rewind(fileBlocoA);
        rewind(fileBlocoB);
        Endereco A, B; 
        //lendo o primeiro registro de cada um dos respectivos blocos
        fread(&A, sizeof Endereco, 1, fileBlocoA);
        fread(&B, sizeof Endereco, 1, fileBlocoB);
        
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
        while(!feof(fileBlocoA) {
            fwrite(&A, sizeof(Endereco), 1, fileBlocoA);
            fread(&A, sizeof(Endereco), 1, fileBlocoA);
        }

        while(!feof(fileBlocoB)) {
            fwrite(&B, sizeof(Endereco), 1, fileBlocoB);
            fread(&B, sizeof(Endereco), 1, fileBlocoB);
        }
        //fim da intercalação
        fclose(saida);
        fclose(fileBlocoA);
        remove(fileBlocoA);
        fclose(fileBlocoB);
        remove(fileBlocoB); //exclui o arquivo do diretório caso não exista mais nenhuma referência a este arquivo dentre os processos existentes...
    }
}


int compara(const void *registro_1, const void *registro_2) {
    return strncmp(((Endereco*)registro_1)->cep, ((Endereco*)registro_2)->cep, 8); 
    //como neste caso temos um ponteiro para uma struct, devemos utilizar de "->"
    //deve-se converter o void pointer para um ponteiro do tipo Endereco antes de tentar acessar os dados 
}


