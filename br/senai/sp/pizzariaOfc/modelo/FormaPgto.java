package br.senai.sp.pizzariaOfc.modelo;

public enum FormaPgto {
DINHEIRO("Dinheiro"),
CARTAO_DEB("Cart�o de d�bito"),
CARTAO_CRED("Cart�o de Cr�dito"),
VALE_REFEICAO("Vale Refei��o");
	
	private String desc;
	
	FormaPgto(String desc){
		this.desc = desc;
	}
	
	public String toString(){
		return this.desc;
	}
}
