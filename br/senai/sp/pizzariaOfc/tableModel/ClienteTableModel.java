package br.senai.sp.pizzariaOfc.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.Cliente;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> cliente;
    
    //vetor com nomes das colunas
    private final String[] COLUNAS  =  {"nome", "telefone"};
    
    // construtor q recebe uma lista de clientes
    public ClienteTableModel(List<Cliente> lista) {
    	this.cliente = lista;
	}   
	
	@Override
	public int getRowCount() {     // quantas linhas
		//numero de linhas é o numero de clientes da lista
		return cliente.size();
	}

	@Override
	public int getColumnCount() { // saber quantas colunas
		// numero de colunas é o comprimento do vetor
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // saber quantas celulas //aceita qualquer retorno
		// retira da lista o obj na posição orwIndex
		Cliente c = cliente.get(rowIndex);
		//verifica qual coluna
		switch (columnIndex) {   // pra cada coluna no programa faz um case aqui
		//se for coluna 0, retorna o nome
		case 0: 
			return c.getNome();
			// se for coluna 1 retorna telefone
		case 1: 
			return c.getTelefone();
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		// retorna o valor da posição do vetor COLUNAS
		return COLUNAS[column]; // CABEÇALHO DA TABELA
	}

}
