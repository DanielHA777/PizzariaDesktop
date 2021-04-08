package br.senai.sp.pizzariaOfc.tableModel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Reclamacao;

public class ReclamaTableModel extends AbstractTableModel{
	private List<Reclamacao> reclamacoes;
	 private final String[] COLUNAS  =  {"pedido_numero", "data", "motoboy", "sabor", "reclamacao"  };
	 
	 public ReclamaTableModel(List<Reclamacao> lista) {
	    	this.reclamacoes = lista;
		}   
	@Override
	public int getRowCount() {
	
		return reclamacoes.size();
	}

	@Override
	public int getColumnCount() {
		
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reclamacao r = reclamacoes.get(rowIndex);
		//verifica qual coluna
		switch (columnIndex) {   
		case 0: 
			return r.getPedido().getNumero();
		case 1: 
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm" );
			return formatador.format(r.getData().getTime()); 	
		case 2: 
			return r.getFunc().getNome();
		case 3: 
			return r.getProduto().getNome();
		case 4: 
			return r.getReclamacao();
	}return null;
}
		
	public String getColumnName(int column) {
		return COLUNAS[column]; 
	}

}
