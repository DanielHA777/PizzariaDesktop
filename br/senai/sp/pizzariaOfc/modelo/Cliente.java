package br.senai.sp.pizzariaOfc.modelo;

public class Cliente {
	private String nome;
	private String endereco;
	private String email;
	private String telefone;
	private int pontos;
	private String cep;
	private int id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String string) {
		this.cep = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean isBrinde() {
		// se o cliente tem mais de 15 pontos 
		if(this.pontos >= 15) {
			return true;
		}else {
			return false;
		}
	}
	public void retirarBrinde() {
		//diminui em 15 os pontos
		this.pontos -=15;
	}
	//aumenta os pontos do cliente
public void adcionarPontos(int pontos) {
	this.pontos += pontos;
}
public void adcionarPontosR() {
	this.pontos +=5;
}
}
