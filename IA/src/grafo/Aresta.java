package grafo;

public class Aresta {
	 
    private Vertice origem;
    private Vertice destino;
    private int custo;

    public Aresta(Vertice origem, Vertice destino, int custo) {
        this.origem = origem;
        this.destino = destino;
        this.custo = custo;
    }
    
    public Vertice getOrigem(){
    	return this.origem;
    }
    
    public Vertice getDestino(){
    	return this.destino;
    }
    
    public int getCusto(){
    	return this.custo;
    }
    
}