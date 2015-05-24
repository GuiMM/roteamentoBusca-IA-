/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roteamentobusca;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class RoteamentoBusca {
//    public Solucao caminhoGuloso(Grafo grafo, Vertice atual, Vertice destino){
//        ArrayList<Vertice> visitados = new ArrayList<Vertice>();
//        return gulosoDeFato( grafo,  visitados,  atual,  destino, 0);
//    
//    }
//    
//    private Solucao gulosoDeFato(Grafo grafo, ArrayList<Vertice> visitados, Vertice atual, Vertice destino, int custoColetado ){
//        Solucao solucao = new Solucao();
//        int melhorCusto =0;
//        ArrayList<Vertice> fronteira;                //guardará os ve´tices que fazem fronteira com o vertice atual
//        visitados.add(atual);                        //bota o vertice atual na lista de vertices já visitados.
//        ArrayList caminho = visitados;      
//        if (atual==destino) {
//            solucao.setCaminho(caminho);
//            solucao.setCustoTotal(custoColetado);
//            return solucao;
//        }else{
//            fronteira = calculaFronteira(atual);
//            fronteira = eliminaVisitados(fronteira,visitados);
//            fronteira = ordenaFronteira(grafo, atual, fronteira);
//        
//            //melhorCusto=grafo.buscaAresta(atual , fronteira.get(0));          //começamos a ideia de colher o melhor caminho(o de menor custo)apartir da busca pela aresta atual ->fronteira.get(i)
//            for (int i = 0; i < fronteira.size(); i++) {
//                melhorCusto=grafo.getAresta(atual , fronteira.get(i)).custo;        //pega o custo do caminho para o proximo vertice da fronteira(lebrando que a fronteira foi ordenada pelo seu custo)
//                custoColetado +=melhorCusto; 
//                return gulosoDeFato(grafo,visitados,fronteira.get(i),destino,custoColetado);
//            }
//        
//    
//        }
//        
//    }
    public Solucao buscaProfundidade(Grafo grafo, Vertice atual, Vertice destino){
        ArrayList<Vertice> visitados = new ArrayList<Vertice>();
        ArrayList<Vertice> caminho = new ArrayList<Vertice>();
        Solucao solucao = new Solucao();
        caminho = profundidade(grafo,visitados,caminho,atual,destino);
        caminho.add(0, atual);                                                  //dibre para adicionar o vertice origem ao caminho.
        solucao.setCaminho(caminho);
        solucao.setCustoTotal(somaCusto(grafo, solucao.caminho));               //seta o custo total a partir do metodo somacusto(pega o caminho e soma os custos).
        return solucao;
    }
    public ArrayList<Vertice> profundidade(Grafo grafo, ArrayList<Vertice> visitados, ArrayList<Vertice> caminho, Vertice atual, Vertice destino){
        //Solucao solucao = new Solucao();
        visitados.add(atual);                        //bota o vertice atual na lista de vertices já visitados.
        ArrayList<Vertice> fronteira;                //guardará os ve´tices que fazem fronteira com o vertice atual
        
        if (atual==destino) {
            //solucao.setCaminho(caminho);
            return caminho;
        }else{
            fronteira = calculaFronteira(atual);
            fronteira = eliminaVisitados(fronteira,visitados);
            for (int i = 0; i < fronteira.size(); i++) {
                   caminho.add(fronteira.get(i));
                   profundidade(grafo,visitados,caminho, fronteira.get(i),destino); 
                   if (caminho.get(caminho.size()-1)==destino) {
                    break;
                }
            }
        
        }
        if (caminho.get(caminho.size()-1)!=destino) {
           caminho.remove(atual);                                               //remove o vertice se o ramo acabou e nao chegou ao destino.
        }
        
        return caminho;                                                         //dibre do dibre dibroso, nao sei como funciona (dibre).                        
    }
//    public ArrayList<Vertice> larguraIterativa(Grafo grafo, ArrayList<Vertice> visitados, Vertice origem, Vertice destino){
//        ArrayList<Vertice> caminho = new ArrayList<>();
//        Vertice atual = origem;
//        caminho.add(atual);
//        while(caminho.get(caminho.size()-1)!=destino){
//            ArrayList<Vertice> fronteira;
//            fronteira = calculaFronteira(atual);
//            fronteira = eliminaVisitados(fronteira,visitados);
//            while(fronteira.size()!=0){
//                if (fronteira.get(0)==destino) {
//                    caminho.add(destino);
//                    return caminho;
//                }
//                fronteira.remove(fronteira.get(0));
//            }
//        
//        }
//        return caminho;
//    }

    private ArrayList<Vertice> calculaFronteira(Vertice atual) {
        ArrayList<Vertice> fronteira = new ArrayList<>();                //guardará os vértices que fazem fronteira com o vertice atual
        
        for (Aresta v: atual.adj) {                                      //passando por todas as arestas do vertice atual
            fronteira.add(v.destino);                                    //colhe o vertice ligado ao vertice atual.
        }
        return fronteira;
    }

    private ArrayList<Vertice> eliminaVisitados(ArrayList<Vertice> fronteira, ArrayList<Vertice> visitados) {
         ArrayList<Vertice> novaFronteira = new ArrayList<Vertice>();                                
         
         for (Vertice a: fronteira) {                                             //passando por todos os vertices da fronteira
            novaFronteira.add(a);
             for (Vertice b: visitados) {                                      //se o vertice 'a' em fronteira ja foi visitado ele nao fara mais parte da fronteira...
                if (a==b) {
                    novaFronteira.remove(a);
                }
            }
        }
         return novaFronteira;
    }

    private ArrayList<Vertice> ordenaFronteira(Grafo grafo , Vertice atual ,ArrayList<Vertice> fronteira) {
        ArrayList<Vertice> novaFronteira = new ArrayList<>();
        int melhorCusto;
         
        while(fronteira.size()!=0){ 
            melhorCusto=grafo.getAresta(atual , fronteira.get(0)).custo;
            Vertice ProxMenorCaminho=fronteira.get(0);
            for (int i = 0; i < fronteira.size(); i++) {                                //passando por todos os vertices do que resta da fronteira.
                int custoDoCaminho = grafo.getAresta(atual,fronteira.get(i)).custo;
                if (custoDoCaminho<melhorCusto) {
                    ProxMenorCaminho=fronteira.get(i);
                }
            }
            novaFronteira.add(ProxMenorCaminho);
            fronteira.remove(ProxMenorCaminho);
        }
        return novaFronteira;
    }

    private int somaCusto(Grafo grafo, List<Vertice> caminho) {
        Vertice destino = caminho.get(caminho.size()-1);
        int custoColetado=0;
        for (int i = 0; i < caminho.size()-1; i++) {
           int custoPedacoCaminho = grafo.getAresta(caminho.get(i) , caminho.get(i+1)).custo;        //pega o custo do pedaço de caminho 
           custoColetado +=custoPedacoCaminho; 
        }
         return custoColetado;
    }

    
    
}
