package largura;

import java.util.ArrayList;
import java.util.List;

import grafo.Vertice;

public class Expansao {
	
	private List<Vertice> vertices;
	
	public Expansao(){
		this.vertices = new ArrayList<Vertice>();
	}
	
	public void addVertice(Vertice vertice){
		vertices.add(vertice);
	}
	
	public int tamanho(){
		return vertices.size();
	}
	
	public void remove(){
		vertices.remove(0);
	}
	
	public Vertice getPrimeiro(){
		return vertices.get(0);
	}
	
	public boolean existe(Vertice vertice){
		for(int i = 0; i < vertices.size(); i++){
			if(vertice.getNome().equals(vertices.get(i).getNome())){
				return true;
			}
		}
		return false;
	}
	
	 public void print(){
		 System.out.print("Expansão  ");
		 for(int i =0; i < vertices.size(); i++){
			 System.out.print(vertices.get(i).getNome()+" ");
		 }
		 System.out.println();
	 }
}
