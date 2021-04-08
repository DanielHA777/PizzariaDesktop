package br.senai.sp.pizzariaOfc.tableModel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;

public class PedidoTableModel extends AbstractTableModel {
    private List<Pedido> pedidos;
    
    //vetor com nomes das colunas
    private final String[] COLUNAS  =  {"N", "data", "cliente", "valor"};
    
    // construtor q recebe uma lista de clientes
    public PedidoTableModel(List<Pedido> lista) {
    	this.pedidos= lista;
	}   
	
	@Override
	public int getRowCount() {     // quantas linhas
		//numero de linhas é o numero de clientes da lista
		return pedidos.size();
	}

	@Override
	public int getColumnCount() { // saber quantas colunas
		// numero de colunas é o comprimento do vetor
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // saber quantas celulas //aceita qualquer retorno
		// retira da lista o obj na posição orwIndex
		Pedido p = pedidos.get(rowIndex);
		//verifica qual coluna
		switch (columnIndex) {   // pra cada coluna no programa faz um case aqui
		//se for coluna 0, retorna o nome
		case 0: 
			return p.getNumero();
			// se for coluna 1 retorna telefone
		case 1: 
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm" );
			return formatador.format(p.getData().getTime()); // calendar para get time	
		case 2: 
			return p.getCliente().getNome();
		case 3: 
			return String.format("R$ %6.2f",p.getvTotal());
	}return null;}
	@Override
	public String getColumnName(int column) {
		// retorna o valor da posição do vetor COLUNAS
		return COLUNAS[column]; // CABEÇALHO DA TABELA
	}

}
