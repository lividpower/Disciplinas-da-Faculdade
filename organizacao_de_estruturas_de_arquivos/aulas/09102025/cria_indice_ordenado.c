#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <endereco.h> //devemos, neste caso, incluir onde que devemos encontrar este arquivo de cabeçalho 
//utiliza-se de -I caminho_diretorio/ para conseguir indicar onde devemos procurar por este arquivo de cabeçalho
//caminho_diretorio = /home/mathe/Faculdade/Disciplinas_da_Faculdade/organizacao_de_estruturas_de_arquivos/arquivos_cabecalho
//caminho_diretorio = ../../arquivos_cabecalho
//Para compilar -> gcc -I/home/mathe/Faculdade/Disciplinas_da_Faculdade/organizacao_de_estruturas_de_arquivos/arquivos_cabecalho -g -o cria_indice cria_index.c 

//devemos criar um índice
//devemos fazer a leitura dos CEPs e de suas respectivas posições no arquivo de índice
//devemos ordenar o arquivo de índice
//devemos conseguir ler um registro específico a partir de um determinado CEP

int main(int argc, char* argv[]) {

    //declarações

    long tamanho_arquivo, num_registros; //ftell retorna um long
    int teste_retorno; //fseek retorna um int

    file_indices = fopen("indices_cep.dat", "wb"); //criando um novo arquivo
    if(file_indices == NULL) {
        fprintf(stderr, "Erro ao abrir o arquivo\n");
        return 1;
    }
    file_cep = fopen("cep.dat", "rb");
    if(file_cep == NULL) {
        fprintf(stderr, "Erro ao abrir o arquivo\n");
        return 1;
    }
    teste_retorno = fseek(file_cep, 0, SEEK_END); //colocando o cursor de leitura ao final do arquivo
    tamanho_arquivo = ftell(file_cep); 
    num_registros = tamanho_arquivo / 300;
    //Indice indices[num_registros]; //tentar criar este array em memória estática estouraria o limite de memória existente na pilha, gerando um stack overflow
    Indice *indices;
    indices = (Indice*) malloc(num_registros * sizeof(Indice));
    if(indices == NULL) {
        fprintf(stderr, "Erro na alocação de memória dinâmica\n");
        return 1;
    }

    //leitura do arquivo
    for(int i = 0; i < num_registros; i++) {
        Endereco buffer_registro;
        fseek(file_cep, (i * sizeof(Endereco)), SEEK_SET); //percorrendo os registros do arquivo
        indices[i].posicao_bytes = ftell(file_cep); //ftell retorna um tipo long
        fread(&buffer_registro, sizeof(Endereco), 1, file_cep);
        strncpy(indices[i].cep, buffer_registro.cep, 8); //esta função altera o valor da string
    }

    //ordenação e escrita dos índices
    qsort(indices, num_registros, sizeof(Indice), compara);
    fwrite(indices, sizeof(Indice), num_registros, file_indices);

    fclose(file_cep);
    fclose(file_indices); //liberando a memória alocada para a estrutura FILE referente ao arquivo
}

// compara: função que compara dois endereços
int compara(const void *i1, const void *i2) {
    return strncmp(((Indice*)i1)->cep, ((Indice*)i2)->cep, 8);
}
