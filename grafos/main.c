#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "grafo.h"

int main()
{
    printf("------------Grafos--------------\n");
    //printf("Digite o nome do arquivo: ");
    char nomeArquivo[101];
    //scanf("%100[^\n]", nomeArquivo); // Só vai pegar até 100 caracteres + delimitador
    Tgrafo *novo = inicializa();

    printf("Digite a opcao desejada:\n\n");
    printf("1- Carregar novo grafo\n");
    printf("2- Inserir no\n");
    printf("3- Retirar no\n");
    printf("4- Inserir aresta\n");
    printf("5- Retirar aresta\n");
    printf("6- Imprime grafo\n");
    printf("7- Verifica Conexidade\n");
    printf("8- Sair\n\n");

    int opcao;

    do{

        int valor,no1,no2;
        printf("Digite a opcao: ");
        scanf("%d",&opcao);
        //fflush(stdin); //esvazia o buffer de entrada, sem ele BUGA GERAL!
        switch(opcao){
            case 1:
                printf("Digite o nome do arquivo: ");
                scanf(" %100[^\n]", nomeArquivo); // Só vai pegar até 100 caracteres + delimitador
                novo = criaGrafo(nomeArquivo);
                if(novo)
                    printf("Grafo carregado!\n");
                printf("\n");
                break;
            case 2:
                if(novo){
                    printf("Digite o valor do no a inserir: ");
                    scanf("%d",&valor);
                    novo = ins_no(novo,valor);
                    salvaGrafo(novo,nomeArquivo);
                    printf("\n");
                }else{
                    printf("Grafo nao carregado!\n");
                }
                break;
            case 3:
                if(novo){
                    printf("Digite o valor do no a remover: ");
                    scanf("%d",&valor);
                    novo = retira_no(novo,valor);
                    salvaGrafo(novo,nomeArquivo);
                    printf("\n");
                }else{
                    printf("Grafo nao carregado!\n");
                }
                break;
            case 4:
                if(novo){
                    printf("Digite o valor do No1 e do No2 da aresta a formar: ");
                    scanf("%d %d",&no1,&no2);
                    ins_aresta(novo,no1,no2);
                    salvaGrafo(novo,nomeArquivo);
                    printf("\n");
                }else{
                    printf("Grafo nao carregado!\n");
                }
                break;
            case 5:
                if(novo){
                    printf("Digite o valor do No1 e do No2 para remover a aresta: ");
                    scanf("%d %d",&no1,&no2);
                    retira_aresta(novo,no1,no2);
                    salvaGrafo(novo,nomeArquivo);
                    printf("\n");
                }else{
                    printf("Grafo nao carregado!\n");
                }
                break;
            case 6:
                if(novo){
                    imprime(novo);
                }else{
                    printf("Grafo nao carregado!\n");
                }
                break;
            case 7:
               if(novo){
                    char novoNome[101];
                    sprintf(novoNome,"%s%s","novo-",nomeArquivo);
                    if(testa_conexo(novo)){
                        printf("Grafo Conexo\n");
                        pontes(novo,novoNome);
                        printf("Arquivo pontes.txt criado!\n");
                    }else{
                        printf("Grafo Nao conexo\n");
                        formar_grupos(novo);
                        printf("Arquivo componentes.txt criado!\n");
                    }
                }else{
                    printf("Grafo nao carregado!\n");
                }
                printf("\n");
                break;
            case 8:
                if(novo)
                    libera(novo);
                return 0;
                break;
            default:
                printf("Opcao invalida!\n");
                break;
        }//fim switch
        //fflush(stdin); //esvazia o buffer de entrada, sem ele BUGA GERAL!
    }while(1);
    return 0;
}
