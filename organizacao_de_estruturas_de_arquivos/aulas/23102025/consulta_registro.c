#include <stdio.h> 
#include <endereco.h>
#include <string.h>
#include <stdlib.h>
//Para compilar -> gcc -I/home/mathe/Cefet-RJ/Disciplinas_da_Faculdade/organizacao_de_estruturas_de_arquivos/arquivos_cabecalho -g -o consulta consulta_registro.c


//deve-se realizar a consulta por um determinado cep utilizando do arquivo de índice para facilitar a busca!
//irei verificar se o CEP é maior ou menor do que os últimos elementos de cada bloco do índice, posto que desta forma saberei a qual bloco ele pertence e daí poderei implementar uma busca dentro desse respectivo bloco

int buscaSequencialIndiceMulti(int *qtd, char *cep, IndiceMulti *buffer, int indexBloco, long numIndicesBloco) {
    //devemos posicionar o arquivo de índices de acordo com o index do bloco que devemos averiguar
    fseek(file_indices, ((indexBloco * numIndicesBloco) * sizeof(IndiceMulti)), SEEK_SET);
    for(int i = 0; i < numIndicesBloco; i++) { //averiguando cada índice deste respectivo bloco
        //realizando a busca pelo cep de forma sequencial dentro do meu arquivo de índices
        *qtd += fread(buffer, sizeof(IndiceMulti), 1, file_indices); //o cursor de leitura irá avançar automaticamente para o próximo índice após cada leitura!
        if(strncmp(buffer->cep, cep, 8) == 0) { //perceba que apenas comparamos os 8 bytes, posto que argv[1] possui um \0 que não gostaríamos de comparar
            return 0;
        }
    }
    return -1; //caso o registro não seja encontrado!
}

int buscaBinariaIndiceMulti(int *qtd, char *cep, IndiceMulti *buffer, int indexBloco, long numIndicesBloco) {
    long inicio, meio, fim;
    inicio = 0; 
    fim = numIndicesBloco - 1; 
    meio = (inicio + fim) /2;

    fseek(file_indices, ((meio + (indexBloco * numIndicesBloco)) * sizeof(IndiceMulti)), SEEK_SET); //posicionando o cursor de leitura do arquivo no meio do respectivo bloco
    *qtd += fread(buffer, sizeof(IndiceMulti), 1, file_indices);
    int i = 0; //contador
    while(i < numIndicesBloco)  { //se o número de índices lidos extrapolar o número de índices existente, estaríamos lendo índices repetidos, o que indica que a busca binária poderá ser finalizada!
        i++;
        if(strncmp(buffer->cep, cep, 8) == 0) {
            return 0;
        }
        else if(strncmp(buffer->cep, cep, 8) > 0) {
            fim = meio - 1;
            meio = (inicio + fim) / 2;
        }
        else if(strncmp(buffer->cep, cep, 8) < 0) {
            inicio = meio + 1;
            meio = (inicio + fim) / 2;
        }
        fseek(file_indices, ((meio + (indexBloco * numIndicesBloco)) * sizeof(IndiceMulti)), SEEK_SET);
        *qtd += fread(buffer, sizeof(IndiceMulti), 1, file_indices);
    }
    //caso chegue até este ponto, significa que o cep não foi encontrado dentro do arquivo de índices
    //logo, i >= numIndicesBloco
    return -1; //código para sinalização de erro
    //a mensagem de erro será detalhada dentro da main como forma de manter uma organização do código

}

int buscaBlocoIndiceMulti(int *qtd, char *cep, long numIndicesBloco, long numBlocos, IndiceMulti *buffer) {
    for(int i = 0; i < numBlocos; i++) {
        fseek(file_indices, ((numIndicesBloco - 1) + (i * numIndicesBloco)) * sizeof(IndiceMulti), SEEK_SET); //preparando o cursor para a leitura do último índice de cada bloco
        *qtd += fread(buffer, sizeof(IndiceMulti), 1, file_indices); 
        if(strncmp(buffer->cep, cep, 8) == 0) {
            return -1; //caso já tenhamos encontrado o registro que queríamos
        }
        else if(strncmp(buffer->cep, cep, 8) < 0) {
            //o CEP está presente em algum dos blocos acima
            continue;
        }
        else if(strncmp(buffer->cep, cep, 8) > 0) {
            //o CEP está justamente neste bloco que estamos procurando
            return i;
        }
    }
    return -2; //o CEP não está contido em nenhum dos blocos
}



int main(int argc, char **argv) {

    if(argc == 3 && strlen(argv[1]) == 8) { //caso a entrada do CEP também não seja válida!
        
        //file_cep e file_indices são variáveis globais, logo, todas as funções tem acesso a elas!
        file_indices = fopen("indices_multinivel.dat", "rb");
        file_cep = fopen("cep-ordenado.dat", "rb"); //como nós preenchemos o arquivo de índices utilizando do arquivo cep-ordenado.dat, também devemos utilizá-lo durante a consulta
        if(file_indices == NULL || file_cep == NULL) {
            fprintf(stderr, "Erro durante a criação de uma referência para o arquivo.\n");
            return 1;
        }
        
        //neste arquivo, iremos fazer uma busca sequencial pelo cep dentro do arquivo de índices
        fseek(file_indices, 0, SEEK_END);
        long tamanhoArquivoIndices = ftell(file_indices);
        long numIndices = tamanhoArquivoIndices / sizeof(IndiceMulti);
        long numBlocos = strtol(argv[2],NULL,10); //esta conversão não é segura para caso existam entradas maliciosas do usuário!
        long numIndicesBloco = numIndices / numBlocos;
        IndiceMulti bufferIndice; //utilizaremos deste buffer para conseguir manipular os índices que estivermos lendo dentro de outras funções
        int qtd_bytes = 0;
        
        rewind(file_indices); //voltando com o cursor para o início do arquivo e resetando as flags de 
        int retorno = buscaBlocoIndiceMulti(&qtd_bytes, argv[1], numIndicesBloco, numBlocos, &bufferIndice);
        int teste;
        if(retorno >= 0) { //caso retorno == -1, já teremos encontrado o índice do CEP!
            //encontramos o bloco do respectivo índice que contém o CEP que foi passado como argumento
            //devemos então realizar a busca por este índice!
            int indexBloco = retorno;
            //teste = buscaSequencialIndiceMulti(&qtd_bytes, argv[1], &bufferIndice, indexBloco, numIndicesBloco);
            teste = buscaBinariaIndiceMulti(&qtd_bytes, argv[1], &bufferIndice, indexBloco, numIndicesBloco);
        }

        if(retorno != -2 && teste != -1) { //se retorno == -2, temos que o CEP não foi encontrado no arquivo de índices
            printf("Total de bytes lido durante a busca pelo cep: %ld\n\n", qtd_bytes * sizeof(IndiceMulti)); //esta operação retorna um long, logo, é necessário usar de %ld

            //após termos encontrado a posição do cep dentro do arquivo original, agora sim podemos realizar a leitura do registro referente ao respectivo cep!
            fseek(file_cep, bufferIndice.posicaoRegistro, SEEK_SET); //a posição do registro já é dada em bytes!
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
    else if (argc != 3) {
        fprintf(stderr, "USO: %s [CEP] [numBlocosIndice]\n", argv[0]); //mensagens direcionadas ao stderr são escritas imediatamente na saída padrão, para que seja possível vê-las antes de um crash!
        return 1;
    }
    else if (strlen(argv[1]) != 8) {
        fprintf(stderr, "O CEP possui o seguinte formato: 99999-999. Por favor, corrija sua entrada.\n");
        return 1;
    }
}