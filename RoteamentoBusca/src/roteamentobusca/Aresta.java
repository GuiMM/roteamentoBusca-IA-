/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roteamentobusca;

/**
 *
 * @author Guilherme
 */
public class Aresta {
 
        Vertice origem;
        Vertice destino;
        int custo;

        Aresta(Vertice origem, Vertice destino, int custo) {
            this.origem = origem;
            this.destino = destino;
            this.custo = custo;
        }
        
}
