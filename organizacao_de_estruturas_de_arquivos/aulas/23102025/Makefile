# Define as flags de compilação comuns
CFLAGS1 = -g -Wall 
CFLAGS2 = -g -Wall -lm
CC = gcc

# Define os diretórios de inclusão (seu -I)
INCLUDES = -I ../../arquivos_cabecalho

# Nome dos executáveis finais
TARGET1 = cria_indice_multinivel 
TARGET2 = consulta_registro

# Nome dos arquivos fonte
SOURCE1 = cria_indice_multinivel.c 
SOURCE2 = consulta_registro.c

# Alvo padrão: Ao executar 'make', ambos são construídos
all: $(TARGET1) $(TARGET2)

# Regra para construir o primeiro executável
$(TARGET1): $(SOURCE1)
	$(CC) $(CFLAGS1) $(INCLUDES)  $^ -o $@ 
    
# Regra para construir o segundo executável
$(TARGET2): $(SOURCE2)
	$(CC) $(INCLUDES) $^ -o $@ $(CFLAGS2)

# Regra para limpar os arquivos gerados
.PHONY: clean
clean:
	rm -f $(TARGET1) $(TARGET2)