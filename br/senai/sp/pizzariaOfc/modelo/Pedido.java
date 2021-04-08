package br.senai.sp.pizzariaOfc.modelo;


import java.util.Calendar;
import java.util.List;

public class Pedido {
	private int numero;
	private Cliente cliente;
	private String endEntrega;
	private Calendar data;
	private boolean retirada;
	private double txentrega;
	private double troco;
	private Produto produto;
	private String motoboy;
	
	private double vTotal;
	//private ItemPedido itemPedido;
	private String observacao;
	private FormaPgto formaPgto;
	private List<ItemPedido> itens;
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEndEntrega() {
		return endEntrega;
	}

	public void setEndEntrega(String endEntrega) {
		this.endEntrega = endEntrega;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public boolean isRetirada() {
		return retirada;
	}

	public void setRetirada(boolean retirada) {
		this.retirada = retirada;
	}

	public double getTxentrega() {
		return txentrega;
	}

	public void setTxentrega(double txentrega) {
		this.txentrega = txentrega;
	}

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public double getvTotal() {
		return vTotal;
	}

	public void setvTotal(double vTotal) {
		this.vTotal = vTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
   
	
	public int getPontos() {
		int contador = 0;
		for(ItemPedido i: itens) {
			//verifica se é pizza doce ou salgada
			if(i.getProduto().getTipo() == TipoProduto.PIZZA_DOCE || i.getProduto().getTipo() == TipoProduto.PIZZA_SALGADA)
		contador += i.getQtd();
		}
		return contador;
	}
	public int getPontosR() {
		int contador = 0;
		for(ItemPedido i: itens) {
			//verifica se é pizza doce ou salgada
			if(i.getProduto().getTipo() == TipoProduto.PIZZA_DOCE || i.getProduto().getTipo() == TipoProduto.PIZZA_SALGADA)
		contador += i.getQtd();
		}
		return contador ;
	}
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	
	public String getMotoboy() {
		return motoboy;
	}

	public void setMotoboy(String motoboy) {
		this.motoboy = motoboy;
	}

}
