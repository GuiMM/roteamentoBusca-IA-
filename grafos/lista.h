#include <stdio.h>
#include <stdlib.h>

typedef struct lista{
  int info;
  struct lista *prox;
} TLSE;

TLSE* insere_lista(TLSE *l, int elem);

void imprime_lista(TLSE *l);

void libera_lista(TLSE *l);

TLSE* retira_lista(TLSE *l, int elem);

TLSE* busca_lista(TLSE *l, int elem);

