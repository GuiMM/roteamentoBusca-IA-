package aestrela;

import java.util.ArrayList;
import java.util.List;

import grafo.Grafo;
import grafo.Vertice;

public class Exe {
	
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
	     
	     Frontier frontier = new Frontier();
	     List<Visitados> visitados = new ArrayList<Visitados>();
	     visitados.add(new Visitados(inicio, inicio, 0));
	     frontier.addCustoEstimado(inicio, inicio.getHeuristic(), 0);
	     frontier.print();
	    
	    do{
	    	
		     Vertice v = frontier.getPrimeiroVertice();
		     int custoCaminho = frontier.getCustoTotalPrimeiroVertice();
		     int heristicPai = v.getHeuristic();
		     frontier.removePrimeiro();
		     
		     for(int i = 0; i < v.getNumeroDeVizinhos(); i++){
		    	 int custoTotal = custoCaminho+v.getCustoAresta(i)-heristicPai;
		    	 Vertice filho = v.getVerticeFilho(i);
		    	 frontier.addCustoEstimado(filho, filho.getHeuristic(), custoTotal);
		    	 
		    	 // Verificando se nó já foi visitado
		    	 int w = existe(visitados, filho, custoTotal);
		    	 if(w == -1){visitados.add(new Visitados(filho,v,custoTotal));} 
		    	 else {
		    		 if(w != -2){
		    			 visitados.remove(w);
		    			 visitados.add(new Visitados(filho,v,custoTotal));
		    		 }
		    	 }
		    	 
		    }
	     	     
		    frontier.print();
		     
	    }while(!frontier.getNomePrimeiroVertice().equals(destino.getNome()));
	    
	    imprimirResultado(visitados, inicio);
	   
	  }
	 	
	public static int existe(List<Visitados> visitados, Vertice filho, int custoTotal){
	   	 int tamanhoVisitados = visitados.size();   	
		 for(int z = 0; z < tamanhoVisitados; z++){
    		 if(visitados.get(z).getVertice().getNome().equals(filho.getNome())){
    			 if(visitados.get(z).getCustoTotal() > custoTotal){ 
					 return z;		
				 } else {
					 return -2;
				 }
    		 }
    	 }
		 return -1;
   }
	
	 public static void imprimirResultado(List<Visitados> visitados, Vertice inicio){
		int i = visitados.size()-1;
		Vertice vertice = visitados.get(i).getVertice();
		Vertice aux = visitados.get(i).getVertice();
		System.out.println("Com o custo total de "+visitados.get(i).getCustoTotal()+", o caminho minímo é:");
		do{
		  if(vertice.getNome().equals(aux.getNome())){
			  System.out.print(vertice.getNome()+" <- ");
			  vertice =  visitados.get(i).getPai();
		  }
		  i--;
		  aux = visitados.get(i).getVertice();
		}while(!inicio.getNome().equals(vertice.getNome()));
		System.out.println(inicio.getNome());
	 }
}
