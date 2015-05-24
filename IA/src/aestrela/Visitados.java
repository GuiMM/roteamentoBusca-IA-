package aestrela;

import java.util.List;

import grafo.Vertice;

public class Visitados {
	
	private Vertice vertice;
	private Vertice pai;
	private int custoTotal;
	
	public Visitados(Vertice vertice, Vertice pai, int custoTotal){
		this.vertice = vertice;
		this.pai = pai;
		this.custoTotal = custoTotal;
	}
	
	public Vertice getVertice(){
		return this.vertice;
	}
	
	public Vertice getPai(){
		return this.pai;
	}
	
	public int getCustoTotal(){
		return this.custoTotal;
	}
	
}
