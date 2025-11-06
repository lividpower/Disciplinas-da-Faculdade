#include <stdio.h>
#include <endereco.h>
#include <string.h>
#include <stdlib.h>


//devemos realizar a leitura da posição de cada um dos registros e dos seus respectivos CEPs
//o arquivo de índices já terá os CEPs ordenados
//devemos entender como funcionará a busca pelos blocos 

int main(int argc, char **argv) {

    if(argc == 2) {
        file_cep = fopen("cep-ordenado.dat", "rb"); //optei por já realizar a leitura do arquivo com os registros ordenados!
        file_indices = fopen("indices_multinivel.dat", "wb"); //criando um novo arquivo
        
        fseek(file_cep, 0, SEEK_END);
        long tamanhoArquivoCEP = ftell(file_cep); //tamanho, em bytes
        long numRegistros = tamanhoArquivoCEP / sizeof(Endereco);
        long numIndices = numRegistros;
        long tamanhoArquivoIndices = numIndices * sizeof(IndiceMulti); //tamanho, em bytes
        long numBlocos = strtol(argv[1],NULL,10); //strtol() com estes determinados parâmetros não é segura contra entradas maliciosas de usuários //este número de blocos é personalizável...podemos dividir o arquivo de índices em n blocos e daí indexá-los!!!
        long tamanhoBloco = tamanhoArquivoIndices / numBlocos; //tamanho, em bytes
        long numIndicesBloco = tamanhoBloco / sizeof(IndiceMulti);

        for(long i = 0; i < numBlocos; i++) { //indexando cada bloco
            for(long j = 0; j <  numIndicesBloco; j++) { //realizando a leitura dos índices para cada bloco
                Endereco bufferEndereco;
                IndiceMulti bufferIndice;
                fseek(file_cep, (j + (i * numIndicesBloco)) * sizeof(Endereco), SEEK_SET); //posicionando o cursor para a leitura dos registros
                bufferIndice.indexBlocoIndice = i;
                bufferIndice.posicaoRegistro = ftell(file_cep); //devemos armazenar a posição no arquivo cep.dat antes de realizar, de fato, a leitura do registro
                fread(&bufferEndereco, sizeof(Endereco), 1, file_cep);
                strncpy(bufferIndice.cep, bufferEndereco.cep, 8); //copiando o CEP para o meu buffer de índice, ignorando a ausência do \0
                fwrite(&bufferIndice, sizeof(IndiceMulti), 1, file_indices); //o cursor vai se movendo automaticamente conforme realizamos a escrita
            }
        } //a partir daqui, já temos o arquivo de índices preenchido!

        fclose(file_cep);
        fclose(file_indices);
    }
    else {
        fprintf(stderr, "USO: ./cria_indice_multinivel [numBlocos]");
        return 1;
    }
}