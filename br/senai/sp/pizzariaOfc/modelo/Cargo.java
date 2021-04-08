package br.senai.sp.pizzariaOfc.modelo;

public enum Cargo {
ATENDENTE("Atendente"), GERENTE("Gerente"), MOTOBOY("Motoboy"), PIZZAIOLO("Pizzaiolo");

Cargo(String string) {
	// TODO Auto-generated constructor stub
	this.nome = string;
}
private String nome;
public String toString() { // devolvendo nome 
	return nome;
}
}
