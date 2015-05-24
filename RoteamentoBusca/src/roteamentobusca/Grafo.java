/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roteamentobusca;

// Este Ã© um exemplo simples de implementaÃ§Ã£o de grafo representado por lista
// de adjacÃªncias

import java.util.List;
import java.util.ArrayList;

public class Grafo {
    List<Vertice> vertices;
    List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }
   
    void addAresta(Vertice origem, Vertice destino, int custo) {// uma aresta a-b é igual a uma b-a
        Aresta a = new Aresta(origem, destino, custo);
        Aresta b = new Aresta(destino, origem, custo);
        origem.addAdj(a);
        destino.addAdj(b);
        arestas.add(a);
        arestas.add(b);
        
    }

    public Aresta getAresta(Vertice a, Vertice b) {
        for (Aresta arestaAtual: arestas) {                                      //passando por todas as arestas do vertice atual
            if (arestaAtual.origem==a&arestaAtual.destino==b | arestaAtual.origem==a&arestaAtual.destino==b) {
                return arestaAtual;
            }
        }
        return null;
    }
    
    public List<Vertice> buscaFronteira(Vertice v){
            List<Vertice> fronteira=new ArrayList<Vertice>();
            for (int i = 0; i < v.adj.size(); i++) {
                fronteira.add(v.adj.get(i).destino);
            }
            return fronteira;
        }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public static void main(String[] args) {
        Grafo g = new Grafo();
        RoteamentoBusca aux = new RoteamentoBusca();
        Solucao resp;
        Vertice s = g.addVertice("s");
        Vertice t = g.addVertice("t");
        Vertice y = g.addVertice("y");
        Vertice v = g.addVertice("v");
        Vertice d = g.addVertice("d");
        Vertice u = g.addVertice("u");
        g.addAresta(s, t , 15);
        //g.addAresta(s, y, 20);
        g.addAresta(t, y, 30);
        g.addAresta(t, v, 40);
        g.addAresta(y, d, 50);
        g.addAresta(v, u, 60);
        System.out.println(g);
        resp = aux.buscaProfundidade(g, u, d);
        System.out.println(resp.custoTotal);
    }
}
