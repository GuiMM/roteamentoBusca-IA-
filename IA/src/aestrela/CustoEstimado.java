package aestrela;

import java.util.List;

import grafo.Vertice;

public class CustoEstimado {
	
	private Vertice vertice;
	private int custoTotal;
	
	public CustoEstimado(Vertice vertice, int custoTotal){
		this.vertice = vertice;
		this.custoTotal = custoTotal;
	}
	
	public Vertice getVertice(){
		return this.vertice;
	}
	
	public int getCustoTotal(){
		return this.custoTotal;
	}
}
