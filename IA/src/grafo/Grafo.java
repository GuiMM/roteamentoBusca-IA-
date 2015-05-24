package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	    private List<Vertice> vertices;
	    private List<Aresta> arestas;

	    public Grafo() {
	        this.vertices = new ArrayList<Vertice>();
	        this.arestas = new ArrayList<Aresta>();
	    }

	    public Vertice addVertice(String nome, int heuristic) {
	        Vertice v = new Vertice(nome, heuristic);
	        this.vertices.add(v);
	        return v;
	    }
	   
	    public void addAresta(Vertice origem, Vertice destino, int custo) {// uma aresta a-b é igual a uma b-a
	        Aresta a = new Aresta(origem, destino, custo);
	 //     Aresta b = new Aresta(destino, origem, custo);
	        origem.addAresta(a);
	//    	destino.addAdj(b);
	        this.arestas.add(a);
	//    	this.arestas.add(b);
	        
	    }
	    
	    public List<Vertice> buscaFronteira(Vertice v){
	    	List<Vertice> fronteira = new ArrayList<Vertice>();
	        int tam = v.getNumeroDeVizinhos();
	        for (int i = 0; i < tam; i++) {
	            fronteira.add(v.getAresta(i).getDestino());
	        }
	        return fronteira;
	    }
	    
	    public int quantFronteira(Vertice v){
	        int tam = v.getNumeroDeVizinhos();
	        return tam;
	    }
	    
	    public String toString() {
	        String r = "";
	        for (Vertice u : this.vertices) {
	            r += u.getNome()+":"+u.getHeuristic()+" -> ";
	            for (Aresta e : u.getArestas()) {
	                Vertice v = e.getDestino();
	                r += v.getNome()+"-"+e.getCusto()+", ";
	            }
	            r += "\n";
	        }
	        return r;
	    }

}
