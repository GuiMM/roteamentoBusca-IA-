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

TLSE* percorre_no(Tgrafo* original , Tgrafo* atual , TLSE* listados){  //  procura quais nós o primeiro nó do grafico consegue alcançar. a cada passo recursivo o nó analisado é excluido permanentemente da lista (listados).
    Tgrafo* p = atual;
    Tviz* q = p->prim;
    if(!(busca_lista(listados, p->id))) return listados;

    listados = retira_lista(listados , p->id);  // retira o nó da lista de nós.

    while(q && listados){
        Tgrafo* noAtual = busca(original ,q->id);
        listados = percorre_no(original ,noAtual , listados);
        q=q->prox_viz;
    }
    return listados; //se retornar uma lista nula sabemos que o primeiro nó alcançou todos os demais nós.

}


int testa_conexo(Tgrafo *g){ // na verdade testa conexo
    TLSE *listados = NULL;
    listados = listando_nos(g);

    listados = percorre_no(g, g , listados);

    if(listados) //nao é nulo, ainda há nós nao alcançáveis pelo primeiro.
        return 0;
    else //todos os nós foram alcançados pelo primeiro e isso é suficiente pra garantir a conectividade do grafo.
        return 1;
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

void imprimindo_componentes(Tgrafo* g , int ncomponentes){
    TLSE *listados = NULL;
    listados = listando_nos(g);
    Tgrafo *pont = g;
    FILE *f = fopen("componentes.txt","w");
    if(!f){
        printf("Erro ao criar o arquivo componentes.txt!");
        fclose(f);
        return;
    }
    //imprimindo
    fprintf(f,"%d\n",ncomponentes);
    listados = listando_nos(g);
    int cont = 0;
    while(listados){
            pont = g;
            while(pont){
                    if(cont == pont->grupo){
                        fprintf(f,"%d  ", pont->id);
                        listados = retira_lista(listados,pont->id);
                    }
                    pont=pont->prox;
            }
            cont++;
            fprintf(f,"\n");
    }
    fclose(f);
}

void formar_grupos( Tgrafo *g){
    TLSE *listados = NULL;
    listados = listando_nos(g);
    int grupo = 0;

    while(listados){                                 //esse while apenas conta o numero de componentes.
        Tgrafo* p = busca(g, listados->info);
        listados = forma_grupo(g,p,listados,grupo);
        grupo++;
    }
    imprimindo_componentes( g , grupo);

}

void pontes(Tgrafo *g,char *str){

    FILE *f = fopen("pontes.txt","w");
    if(!f){
        printf("Erro ao criar o arquivo pontes.txt!");
        fclose(f);
        return;
    }
    Tgrafo *p = g;
    TLSE *listados = NULL; //lista onde fica armazenada as arestas passadas
    TLSE *listaImp = NULL; //lista para impressao de pontes
    int contaPonte = 0;
    while(p){
        Tviz *q = p->prim;
        while(q){
            if(!busca_lista(listados,q->id)){ //testa se o vizinho atual é um no visitado
                Tgrafo *copia = criaGrafo(str); //grafo criado para remoção de aresta sem interferir no fluxo do(s) while(s)
                retira_aresta(copia,p->id,q->id);
                if(!testa_conexo(copia)){
                    contaPonte++;
                    if(!busca_lista(listados,p->id))
                        listados = insere_lista(listados,p->id);
                    //insere os nos da aresta na lista de impressao
                    listaImp = insere_lista(listaImp,p->id);
                    listaImp = insere_lista(listaImp,q->id);
                }
                libera(copia);
            }
            q = q->prox_viz;
        }
        p = p->prox;
    }

    fprintf(f,"%d\n",contaPonte);
    TLSE *l = listaImp;
    while(l){ //passa a dupla de nos da aresta pro arquivo
        fprintf(f,"%d %d\n",l->prox->info,l->info);
        l = l ->prox->prox;
    }
    libera_lista(listados);
    libera_lista(listaImp);
    fclose(f);
}

void salvaGrafo(Tgrafo *g,char *nomeArq){

    char novoNome[101];
    sprintf(novoNome,"%s%s","novo-",nomeArq);
    FILE *f = fopen(novoNome,"w");
    if(!f){
        printf("Erro ao criar o arquivo %s!",nomeArq);
        fclose(f);
        return;
    }
    Tgrafo *p = g;
    int contaNo = 0;
    while(p){
        contaNo++;
        p = p -> prox;
    }//fim da contagem dos nos
    fprintf(f,"%d\n",contaNo);
    p = g; //Apontando pra percorrer grafo
    while(p){
        fprintf(f,"%d\n",p->id);
        p = p->prox;
    }
    TLSE *listados = NULL; //lista onde fica armazenada as arestas passadas
    p = g; //Apontando pra percorrer grafo
    while(p){
        Tviz *q = p->prim;
        while(q){
            //verifica se o vizinho atual é um nó passado
            if(!busca_lista(listados,q->id)){
                fprintf(f,"%d %d\n",p->id,q->id);
                if(!busca_lista(listados,p->id)){
                    listados = insere_lista(listados,p->id);
                }
            }
            q = q->prox_viz;
        }
        p = p->prox;
    }
    libera_lista(listados);
    fclose(f);
    printf("Arquivo %s gravado!\n",novoNome);
}

