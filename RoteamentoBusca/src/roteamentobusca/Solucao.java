/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roteamentobusca;

import java.util.ArrayList;
import java.util.List;

public class Solucao{
    int custoTotal;
    List<Vertice> caminho;
    
    Solucao(){
        custoTotal =0;
        caminho = new ArrayList<>();
    }

    public void setCustoTotal(int custoTotal) {
        this.custoTotal = custoTotal;
    }

    public void setCaminho(List<Vertice> caminho) {
        this.caminho = caminho;
    }

    public int getCustoTotal() {
        return custoTotal;
    }

    public List<Vertice> getCaminho() {
        return caminho;
    }
}

