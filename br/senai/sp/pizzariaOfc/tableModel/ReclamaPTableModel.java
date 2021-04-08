package br.senai.sp.pizzariaOfc.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.ItemPedido;

public class ReclamaPTableModel extends AbstractTableModel {
	private List<ItemPedido> itens;
	private String[] colunas = { "Sabor", "qtd"};

	public ReclamaPTableModel(List<ItemPedido> lista) {
		this.itens = lista;
	}

	@Override
	public int getRowCount() {
		
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		
		return colunas.length;
	}
	 @Override
 	public String getColumnName(int column) {
 		
 		return colunas[column];
 	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		ItemPedido item = itens.get(linha);
		switch (coluna) {
		case 0:
			return item.getProduto().getNome();
		case 1: 
			return item.getQtd();
		default:
			return null;
		}
	}
		

}
