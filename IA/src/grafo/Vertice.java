package grafo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
  
        private String nome;
        private int heuristic;
        private List<Aresta> arestas;

        public Vertice(String nome, int heuristic) {
            this.nome = nome;
            this.heuristic = heuristic;
            this.arestas = new ArrayList<Aresta>();
        }

        public String getNome(){
        	return this.nome;
        }
        
        public List<Aresta> getArestas(){
        	return this.arestas;
        }
        
        public void addAresta(Aresta e) {
            this.arestas.add(e);
        }
        
        public int getNumeroDeVizinhos(){
        	return this.arestas.size();
        }
        
        public int getHeuristic(){
        	return this.heuristic;
        }
        
        public Aresta getAresta(int i){
        	return this.arestas.get(i);
        }
        
        public int getCustoAresta(int i){
        	return this.arestas.get(i).getCusto();
        }
        
        public String getNomeVizinhos(int i){
        	return this.arestas.get(i).getDestino().getNome();
        }
        
        public Vertice getVerticeFilho(int i){
        	return this.arestas.get(i).getDestino();
        }
        
        public Vertice getVerticePai(int i){
        	return  this.arestas.get(i).getOrigem();
        }
        

}  
