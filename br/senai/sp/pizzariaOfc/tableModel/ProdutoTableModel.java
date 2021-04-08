package br.senai.sp.pizzariaOfc.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Produto;

public class ProdutoTableModel extends AbstractTableModel {
    private List<Produto> produtos;
    
    //vetor com nomes das colunas
    private final String[] COLUNAS  =  {"nome", "tipo", "preco"};
    
    // construtor q recebe uma lista de clientes
    public ProdutoTableModel(List<Produto> lista) {
    	this.produtos= lista;
	}   
	
	@Override
	public int getRowCount() {     // quantas linhas
		//numero de linhas é o numero de clientes da lista
		return produtos.size();
	}

	@Override
	public int getColumnCount() { // saber quantas colunas
		// numero de colunas é o comprimento do vetor
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // saber quantas celulas //aceita qualquer retorno
		// retira da lista o obj na posição orwIndex
		Produto p = produtos.get(rowIndex);
		//verifica qual coluna
		switch (columnIndex) {   // pra cada coluna no programa faz um case aqui
		//se for coluna 0, retorna o nome
		case 0: 
			return p.getNome();
			// se for coluna 1 retorna telefone
		case 1: 
			return p.getTipo();
		
		case 2: 
			return String.format("R$ %6.2f",p.getPreco());
	}return null;}
	@Override
	public String getColumnName(int column) {
		// retorna o valor da posição do vetor COLUNAS
		return COLUNAS[column]; // CABEÇALHO DA TABELA
	}

}
