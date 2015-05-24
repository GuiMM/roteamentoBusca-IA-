package guloso;

import grafo.Grafo;
import grafo.Vertice;

public class main {

	private final static int INF = 99; 
	
	public static void main(String[] args) {
		Grafo g = new Grafo();
		 
	     Vertice s = g.addVertice("S", 8);
	     Vertice a = g.addVertice("A", 8);
	     Vertice b = g.addVertice("B", 4);
	     Vertice c = g.addVertice("C", 3);
	     Vertice d = g.addVertice("D", INF);
	     Vertice e = g.addVertice("E", INF);
	     Vertice f = g.addVertice("F", 0);
	     
	     g.addAresta(s, a , 1);
	     g.addAresta(s, b, 5);
	     g.addAresta(s, c, 8);
	     
	     g.addAresta(a, d, 3);
	     g.addAresta(a, e, 7);
	     g.addAresta(a, f, 9);
	     
	     g.addAresta(b, f, 4);
	     
	     g.addAresta(c, f, 5);
	     
	     System.out.println(g.toString());	
	     
	     Vertice inicio = s;
	     Vertice destino = f;    
	     System.out.println("inicio: "+inicio.getNome()+" -> destino: "+destino.getNome());
	     
	     Vertice vertice = inicio;
	     int aux = 0;
	     Caminho caminho = new Caminho();
	     caminho.addVertice(vertice);
	     caminho.somaCusto(vertice.getHeuristic());
	     
	     while(!vertice.getNome().equals(destino.getNome())){
	    	 int vizinhos = vertice.getNumeroDeVizinhos();
	    	 int menorCusto = INF;
	    	 
	    	 for(int i = 0; i < vizinhos; i++){
	    		if(!vertice.getVerticeFilho(i).getNome().equals(destino.getNome())){
		    		 if(menorCusto > vertice.getVerticeFilho(i).getHeuristic()){
		    			 menorCusto = vertice.getVerticeFilho(i).getHeuristic();
		    			 aux = i;
		    		 }
	    		} else {
	    			aux = i;
	    			menorCusto = vertice.getVerticeFilho(i).getHeuristic();
	    			break;
	    		}
	    	 }
	    	 
	    	 vertice = vertice.getVerticeFilho(aux);
	    	 caminho.addVertice(vertice);
	    	 caminho.somaCusto(menorCusto);
	     }
	     caminho.print();
	     System.out.println(": "+caminho.getCusto());
	}

}
