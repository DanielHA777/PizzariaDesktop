package br.senai.sp.pizzariaOfc.modelo;

public enum DiaDaSemana {
	SABADO("Sábado"),
	DOMINGO("Domingo"),
	SEGUNDA("Segunda-feira"),
	TERCA("Terça-feira"),
	QUARTA("Quarta-feira"),
	QUINTA("Quinta-feira"),
	SEXTA("Sexta-feira");
	
	DiaDaSemana(String string) {
		this.nome = string;
	}

	private String nome;
	
	@Override
	public String toString() {
		return nome;
	}
}
