#include <stdio.h>
#include <stdlib.h>
#include "lista.h"

TLSE* insere_lista(TLSE *l, int elem){
  //if(busca_lista(l , elem))return l;              //nao permite elementos repetidos.
  TLSE *novo = (TLSE *) malloc(sizeof(TLSE));
  novo->prox = l;
  novo->info = elem;
  return novo;
}

void imprime_lista(TLSE *l){
  TLSE *p = l;
  while(p){
    printf("% d ", p->info);
    p = p->prox;
  }
}

void libera_lista(TLSE *l){
  TLSE *p = l, *q;
  while(p){
    q = p;
    p = p->prox;
    free(q);
  }
}

TLSE* retira_lista(TLSE *l, int elem){
  TLSE *p = l, *ant = NULL;
  while((p) && (p->info != elem)){
    ant = p;
    p = p->prox;
  }
  if(!p) return l;
  if(!ant) l = l->prox;
  else ant->prox = p->prox;
  free(p);
  return l;
}

TLSE* busca_lista(TLSE *l, int elem){
  TLSE *p = l;
  while((p) && (p->info != elem)) p = p->prox;
  return p;
}


