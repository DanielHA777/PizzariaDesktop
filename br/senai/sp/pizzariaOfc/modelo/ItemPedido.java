package br.senai.sp.pizzariaOfc.modelo;

public class ItemPedido {
	private int id;
	private Produto produto;
	private int qtd;

	private double total;
	private String observacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
		// se for um item de pedido novo
		if (this.id == 0) {
			// se o produto estiver em promoção
			if (this.getProduto().isPromocao()) {
				// atualiza o total concedendo 20% de desconto
				this.total = this.getProduto().getPreco() * 0.8 * qtd;
			} else {
				// atualiza o total com o preço do produto
				this.total = this.getProduto().getPreco() * qtd;
			}
		}

	}

	public void setQtdFromBf(int qtd) {
		this.qtd = qtd;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		// fixme
		this.total = total;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setBrinde(Produto produto) {
//fixme
		this.produto = produto;
		this.qtd = 1;
		this.total = total;
	}
	
	@Override
	public String toString() {	
		return this.qtd + " - "+this.getProduto().getNome();
	}

}
