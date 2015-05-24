#include <stdio.h>
#include <stdlib.h>
#include "lista.h"

typedef struct viz{
    int id;
    struct viz *prox_viz;
} Tviz;

typedef struct grafo {
    int id;
    int grupo;
    Tviz *prim;
    struct grafo *prox;

} Tgrafo;

Tgrafo* inicializa(void);

Tgrafo* busca(Tgrafo *g, int elem);

Tgrafo *ins_no(Tgrafo *g, int no);

void ins_aresta(Tgrafo *g, int no1, int no2);

void retira_aresta(Tgrafo* g,int id1, int id2);

Tgrafo* retira_no(Tgrafo* g, int elem);

void libera(Tgrafo *g);

void imprime(Tgrafo *g);

TLSE* listando_nos(Tgrafo *g);

TLSE* percorre_no(Tgrafo* original , Tgrafo* atual , TLSE* listados);

int testa_conexo(Tgrafo *g);

Tgrafo *criaGrafo(char *nomeArq);

TLSE* forma_grupo( Tgrafo* original,Tgrafo* atual, TLSE* listados , int grupo);

void formar_grupos(Tgrafo *g);

void pontes(Tgrafo *g,char *nomeArq);

void salvaGrafo(Tgrafo *g,char *nomeArq);
