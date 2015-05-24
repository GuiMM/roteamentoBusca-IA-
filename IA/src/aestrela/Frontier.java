package aestrela;


import grafo.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Frontier {

	 private List<CustoEstimado> frontier;
	 
	 public Frontier(){
		 this.frontier = new ArrayList<CustoEstimado>();
	 }
	 
	 public void addCustoEstimado(Vertice vertice, int heuristic, int custoAresta){
		 int e = existe(frontier, vertice); // Retorna a posição na lista se existir, caso contrário retorna -1
		 if(e != -1){
			 if((heuristic+custoAresta) < frontier.get(e).getCustoTotal()){
				 frontier.remove(e);
				 frontier.add(new CustoEstimado(vertice, heuristic+custoAresta));
			 }
		 } else {frontier.add(new CustoEstimado(vertice, heuristic+custoAresta));}
		 ordenar(frontier);  
	 }
	 
	 public void removePrimeiro(){
		 frontier.remove(0);
	 }
	 
	 public void remove(int i){
		 frontier.remove(i);
	 }
	
	 public Vertice getPrimeiroVertice(){
		 return frontier.get(0).getVertice();
	 }
	 
	 public String getNomePrimeiroVertice(){
		 return frontier.get(0).getVertice().getNome();
	 }
	 
	 public int getCustoTotalPrimeiroVertice(){
		 return frontier.get(0).getCustoTotal();
	 }
	 
	 public void print(){
		 System.out.print("Frontier  ");
		 for(int i =0; i < this.frontier.size(); i++){
			 System.out.print(frontier.get(i).getVertice().getNome()+":"+frontier.get(i).getCustoTotal()+"   ");
		 }
		 System.out.println();
	 }
	 
	 public static int existe(List<CustoEstimado> frontier, Vertice vertice){
		 for(int i = 0; i < frontier.size(); i++){
			 if(frontier.get(i).getVertice().getNome().equals(vertice.getNome())){
				 return i;
			 }
		 }
		 return -1;
	 }
	 
	 private static void ordenar(List<CustoEstimado> frontier) {
			Collections.sort(frontier, new Comparator<CustoEstimado>() {  
		            public int compare(CustoEstimado o1, CustoEstimado o2) {  
			                return o1.getCustoTotal() < o2.getCustoTotal() ? -1 : (o1.getCustoTotal() > o2.getCustoTotal() ? +1 : 0);  
			        }  
			});
	}
}
