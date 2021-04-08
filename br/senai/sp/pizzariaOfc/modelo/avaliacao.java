package br.senai.sp.pizzariaOfc.modelo;

public class avaliacao {
private int id;
private Cliente cliente;
private int nota;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNota() {
	return nota;
}
public void setNota(int nota) {
	this.nota = nota;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

}
