# Define as flags de compilação comuns
CFLAGS = -g -Wall -lm

# Define os diretórios de inclusão (seu -I)
INCLUDES = -I ../../../arquivos_cabecalho

# Nome do executável final
TARGET = intercala

# Nome do seu arquivo fonte principal
SOURCE = intercala_Nblocos.c

# Regra principal para construir o executável
$(TARGET): $(SOURCE)
	gcc $(INCLUDES) $(SOURCE) -o $(TARGET) $(CFLAGS)

# Regra para limpar os arquivos gerados
clean:
	rm -f $(TARGET) *.o