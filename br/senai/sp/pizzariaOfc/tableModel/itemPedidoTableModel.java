package br.senai.sp.pizzariaOfc.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.ItemPedido;

public class itemPedidoTableModel extends AbstractTableModel {
private List<ItemPedido> itens;
private String[] colunas = {"qtd", "produto", "Observação", "total"};

public itemPedidoTableModel(List<ItemPedido> lista) {
	this.itens = lista;
}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
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
			return item.getQtd();
		case 1:
			return item.getProduto().getNome();
		case 2:
			return item.getObservacao();
		case 3:
			return String.format("R$ %6.2f", item.getTotal());
		default:
			return null;
		}
	}


}
