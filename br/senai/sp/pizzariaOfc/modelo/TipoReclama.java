package br.senai.sp.pizzariaOfc.modelo;

public enum TipoReclama {
	Atraso_entrega("Atraso na entrega"),
	Sabor_trocado("Sabor trocado"),
	Pizza_fria("Pizza fria"),
	Pizza_queimada("Pizza queimada"),
	Ingrediente_estragado("Ingrediente estragado"),
	Pizza_revirada("Pizza Revirada"),
	Faltou_refrigerante("Faltou item"),
	pouco_recheio("Pouco Recheio");
	
private String desc;
	
	TipoReclama(String desc){
		this.desc = desc;
	}
	
	public String toString(){
		return this.desc;
	}
}
