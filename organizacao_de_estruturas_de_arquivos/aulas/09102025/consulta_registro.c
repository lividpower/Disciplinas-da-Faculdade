#include <stdio.h> 
#include <endereco.h>
#include <string.h>
//Para compilar -> gcc -I/home/mathe/Cefet-RJ/Disciplinas_da_Faculdade/organizacao_de_estruturas_de_arquivos/arquivos_cabecalho -g -o consulta consulta_registro.c


//deve-se realizar a consulta por um determinado cep utilizando do arquivo de índice para facilitar a busca!

long buscaSequencialIndice(int *qtd, char *cep, long num_indices) {
    long posicao_cep;
    for(int i = 0; i < num_indices; i++) {
        //realizando a busca pelo cep de forma sequencial dentro do meu arquivo de índices
        Indice buffer_indice;
        *qtd += fread(&buffer_indice, sizeof(Indice), 1, file_indices);
        if(strncmp(buffer_indice.cep, cep, 8) == 0) { //perceba que apenas comparamos os 8 bytes, posto que argv[1] possui um \0 que não gostaríamos de comparar
            posicao_cep = buffer_indice.posicao_bytes;
            return posicao_cep;
        }
    }
}

long buscaBinariaIndice(int *qtd, char *cep, long num_indices) {
    long inicio, meio, fim, posicao_cep;
    Indice buffer_indice;
    inicio = 0; 
    fim = num_indices - 1; //iremos indexar cada um desses índices
    meio = (inicio + fim) /2;

    //necessário posicionar o cursor no meio do arquivo
    fseek(file_indices, meio * sizeof(Indice), SEEK_SET);
    *qtd += fread(&buffer_indice, sizeof(Indice), 1, file_indices);
    int i = 0; //contador
    while(i < num_indices)  { //se o número de índices lidos extrapolar o número de índices existente, estaríamos lendo índices repetidos, o que indica que a busca binária poderá ser finalizada!
        i++;
        if(strncmp(buffer_indice.cep, cep, 8) == 0) {
            posicao_cep = buffer_indice.posicao_bytes;
            return posicao_cep; 
        }
        else if(strncmp(buffer_indice.cep, cep, 8) > 0) {
            fim = meio - 1;
            meio = (inicio + fim) / 2;
        }
        else if(strncmp(buffer_indice.cep, cep, 8) < 0) {
            inicio = meio + 1;
            meio = (inicio + fim) / 2;
        }
        fseek(file_indices, sizeof(Indice) * meio, SEEK_SET);
        *qtd += fread(&buffer_indice, sizeof(Indice), 1, file_indices);
    }
    //caso chegue até este ponto, significa que o cep não foi encontrado dentro do arquivo de índices
    //logo, i >= num_indices
    return -1; //código para sinalização de erro
    //a mensagem de erro será detalhada dentro da main como forma de manter uma organização do código

}

int main(int argc, char **argv) {

    if(argc == 2 && strlen(argv[1]) == 8) { //caso a entrada do CEP também não seja válida!
        long tamanho_arquivo_indices, num_indices;

        file_indices = fopen("indices_cep.dat", "rb");
        file_cep = fopen("cep.dat", "rb"); //criamos uma referência para o arquivo, porém, ainda não o carregamos em memória
        if(file_indices == NULL || file_cep == NULL) {
            fprintf(stderr, "Erro durante a criação de uma referência para o arquivo.\n");
            return 1;
        }
        
        //neste arquivo, iremos fazer uma busca sequencial pelo cep dentro do arquivo de índices
        fseek(file_indices, 0, SEEK_END);
        tamanho_arquivo_indices = ftell(file_indices);
        num_indices = tamanho_arquivo_indices / sizeof(Indice);
        long posicao_cep;
        int qtd_bytes = 0;
        
        rewind(file_indices); //voltando com o cursor para o início do arquivo e resetando as flags de erro
        //posicao_cep = buscaSequencialIndice(&qtd_bytes, argv[1], num_indices);
        posicao_cep = buscaBinariaIndice(&qtd_bytes, argv[1], num_indices);
        if(posicao_cep != -1) {
            printf("Total de bytes lido durante a busca pelo cep: %ld\n\n", qtd_bytes * sizeof(Indice)); //esta operação retorna um long, logo, é necessário usar de %ld

            //após termos encontrado a posição do cep dentro do arquivo original, agora sim podemos realizar a leitura do registro referente ao respectivo cep!
            fseek(file_cep, posicao_cep, SEEK_SET); //a posição é dada em bytes!
            Endereco buffer_registro;
            fread(&buffer_registro, sizeof(Endereco), 1, file_cep);
            //printando as informações do registro que queríamos consultar
            printf("%.72s\n%.72s\n%.72s\n%.72s\n%.2s\n%.8s\n",buffer_registro.logradouro,buffer_registro.bairro,buffer_registro.cidade,buffer_registro.uf,buffer_registro.sigla,buffer_registro.cep);
        }
        else {
            fprintf(stderr, "O CEP informado não existe ou ainda não foi incluído dentro do arquivo.\n");
            return 1;
        }
        //fechamento dos arquivos
        fclose(file_cep);
        fclose(file_indices);
    }
    else if (argc > 2) {
        fprintf(stderr, "USO: %s [CEP]\n", argv[0]); //mensagens direcionadas ao stderr são escritas imediatamente na saída padrão, para que seja possível vê-las antes de um crash!
        return 1;
    }
    else if (strlen(argv[1]) != 8) {
        fprintf(stderr, "O CEP possui o seguinte formato: 99999-999. Por favor, corrija sua entrada.\n");
        return 1;
    }
}