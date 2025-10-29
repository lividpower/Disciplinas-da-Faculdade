#include <stdio.h>

// Função ruim que retorna um ponteiro da stack
int* minha_funcao_ruim() {
    int x = 10;
    printf("  [funcao_ruim] 'x' está em: %p\n", &x);
    return &x; //este retorno de um ponteiro local é dado como "warning" durante o momento da compilação!
} // 'x' (na stack) é liberado AQUI

// Uma função "inútil" apenas para "sujar" a stack
void outra_funcao() {
    int lixo[10]; // Esta variável vai usar o espaço
    lixo[0] = 999; // ... que 'x' costumava usar.
    printf("  [outra_funcao] 'lixo[0]' está em: %p\n", &lixo[0]);
}

int main(void) {
    // --- CENÁRIO A (Como o seu) ---
    // Provavelmente vai funcionar "por sorte"
    int *p1 = minha_funcao_ruim();
    printf("[Cenário A] O valor é: %d\n\n", *p1); // O 10 provavelmente ainda está lá


    // --- CENÁRIO B (Forçando a corrupção) ---
    // Quase certeza que vai falhar
    int *p2 = minha_funcao_ruim();
    
    // Agora, chamamos outra função. Ela vai
    // "fazer check-in" no quarto que 'x' usava.
    outra_funcao();
    
    // Agora o valor de p2 foi sobrescrito
    printf("[Cenário B] O valor é: %d\n", *p2); // Vai imprimir 999 ou lixo

    return 0;
}