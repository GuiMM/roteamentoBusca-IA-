#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "grafo.h"

Tgrafo* inicializa(void){
    return NULL;
}
Tgrafo* busca(Tgrafo *g, int elem){
    Tgrafo*p = g;
    while((p)&&(p->id != elem))
        p=p->prox;
    return p;

}

Tgrafo *ins_no(Tgrafo *g, int no){
    if (busca(g,no))return g;
    Tgrafo *novo = (Tgrafo*) malloc(sizeof(Tgrafo));
    novo->id = no;
    novo->prim = NULL;
    novo->prox = g;
    return novo;

}



void ins_aresta(Tgrafo *g, int no1, int no2){
    Tgrafo *p = busca(g,no1);
    Tgrafo *q = busca(g,no2);
    if((!p)||(!q)) return;
    Tviz *v = p->prim;
    while(v){ //Evita arestas repetidas
        if(v->id == no2){
            return;
        }
        v = v->prox_viz;
    }
    Tviz *novo = (Tviz*) malloc (sizeof(Tviz));
    novo->id = no2;
    novo->prox_viz = p->prim;
    p->prim = novo;
    novo = (Tviz*) malloc(sizeof(Tviz));
    novo->id = no1;
    novo->prox_viz = q->prim;
    q->prim = novo;
}
void retira_aresta(Tgrafo* g,int id1, int id2){
    Tgrafo *p = busca(g,id1),*q = busca(g,id2);
    if((!p)||(!q)) return;
    Tviz *viz = p->prim, *ant = NULL;

    while((viz)&&(viz->id!=id2)){
        ant = viz;
        viz = viz->prox_viz;
    }
    if(!viz) return;
    if(!ant){
        p->prim = p->prim->prox_viz;
    }else{
        ant->prox_viz=viz->prox_viz;
    }
    free(viz);
    viz = q->prim;
    ant = NULL;
    while((viz)&&(viz->id!=id1)){
        ant = viz;
        viz = viz ->prox_viz;
    }
    if(!viz) return;
    if(!ant){
        q->prim= q->prim->prox_viz;
    }else{
        ant->prox_viz = viz->prox_viz;
    }
    free(viz);
}

Tgrafo* retira_no(Tgrafo* g, int elem){
    Tgrafo* p = g , *ant = NULL;
    while((p)&&(p->id !=elem)){
        ant=p;
        p=p->prox;

    }if(!p)return g;

    Tviz* q = p->prim;
    while(q){
        int id = q->id;
        q = q->prox_viz;
        retira_aresta(g,id,elem);

    }
    if(!ant){
        g=g->prox;
    }else{
        ant->prox=p->prox;
    }
    free(p);
    return g;

}



void libera(Tgrafo *g){
    Tgrafo *p = g;
    while(p){
        Tviz *v = p->prim;
        while(v){
            Tviz *q = v;
            v = v->prox_viz;
            free(q);
        }
        Tgrafo *r = p;
        p=p->prox;
        free(r);

    }

}

void imprime(Tgrafo *g){
    Tgrafo *p = g;
    while(p){
        printf("\nNo: %d \n Vizinhos: \n" , p->id);
        Tviz *q = p->prim;
        while(q){
            printf("Viz: %d \t  \n", q->id);
            q=q->prox_viz;

        }
        p=p->prox;
    }
    printf("\n");
}
TLSE* listando_nos(Tgrafo *g){ //lista os nos.
    Tgrafo *p = g;
    TLSE *listados = NULL;
    while(p){
        listados = insere_lista(listados ,p->id);
        p = p->prox;
    }

    return listados;
}

Tgrafo *criaGrafo(char *nomeArq){

    FILE *f = fopen(nomeArq,"r");
    if (!f) {
       printf ("Houve um erro ao abrir o arquivo.\n");
       fclose(f);
       return NULL;
    }
    Tgrafo * novo = inicializa();
    int componentes;

    fscanf(f,"%d",&componentes);
    int i;
    for(i=0;i<componentes;i++){
        int no;
        fscanf(f,"%d",&no);
        novo = ins_no(novo,no);
    }
    int no1,no2;
    while(!feof(f)){
        fscanf(f,"%d %d",&no1,&no2);
        ins_aresta(novo,no1,no2);
    }
    fclose(f);
    return novo;
}

TLSE* forma_grupo( Tgrafo* original, Tgrafo* atual, TLSE* listados, int grupo){
    Tgrafo* p = atual;
    Tviz* q = p->prim;
    if(!(busca_lista(listados, p->id))) return listados;
    listados = retira_lista(listados , p->id);  // retira o nó da lista de nós.
    atual->grupo = grupo;

    while(q && listados){
        Tgrafo* noAtual = busca(original ,q->id);
        listados = forma_grupo(original ,noAtual , listados, grupo);
        q=q->prox_viz;
    }
    return listados;
}


