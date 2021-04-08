package br.senai.sp.pizzariaOfc.modelo;

import java.util.Calendar;

public class Reclamacao implements ControladaPorVoz{

	private int id;
	private Pedido pedido;
	private String reclamacao;
	private Calendar data;
	private Cliente cliente;
	private Produto produto;
	private Funcionario func;
	private String Observacao;
	private TipoReclama tipoRecla;
	private DiaDaSemana dia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public String getReclamacao() {
		return reclamacao;
	}
	public void setReclamacao(String reclamacao) {
		this.reclamacao = reclamacao;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Funcionario getFunc() {
		return func;
	}
	public void setFunc(Funcionario func) {
		this.func = func;
	}
	public void setPedido(String text) {
		// TODO Auto-generated method stub
		
	}
	public String getObservacao() {
		return Observacao;
	}
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	public TipoReclama getTipoRecla() {
		return tipoRecla;
	}
	public void setTipoRecla(TipoReclama tipoRecla) {
		this.tipoRecla = tipoRecla;
	}
	public DiaDaSemana getDia() {
		return dia;
	}
	public void setDia(DiaDaSemana dia) {
		this.dia = dia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public void executaComandoPorVoz(String oQueFoiFalado) {
		
		
	}
	
	
}
