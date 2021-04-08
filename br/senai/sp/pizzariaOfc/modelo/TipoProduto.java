package br.senai.sp.pizzariaOfc.modelo;

public enum TipoProduto {
BEBIDAS("Bebida"),
PIZZA_DOCE("Pizza Doce"), 
PIZZA_SALGADA("Pizza Salgada"), 
BROTO_DOCE("Broto Doce"), 
BROTO_SALAGADA("Broto Salgado"), 
BORDA_RECHEADA("Borda Recheada");
	TipoProduto(String string) {
	// TODO Auto-generated constructor stub
		this.nome = string; // string recebe os nomes da enum
}

	private String nome;
    public String toString() { // devolvendo nome 
    	return nome;
    }
}
