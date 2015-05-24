package guloso;

import java.util.ArrayList;
import java.util.List;

import grafo.Vertice;

public class Caminho {

		private List<Vertice> vertices;
		private int custo;
		
		public Caminho(){
			this.vertices = new ArrayList<Vertice>();
			this.custo = 0;
		}
		
		public void addVertice(Vertice vertice){
			vertices.add(vertice);
		}
		
		public Vertice getVertice(int i){
			return vertices.get(i);
		}
		
		public int getCusto(){
			return this.custo;
		}
		
		public void somaCusto(int custo){
			this.custo += custo;
		}
		
		public void print(){
			 System.out.print("Caminho  ");
			 for(int i =0; i < vertices.size(); i++){
				 System.out.print(vertices.get(i).getNome()+" ");
			 }
		}
}
