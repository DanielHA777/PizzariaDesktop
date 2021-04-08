package Servico;

import br.senai.sp.pizzariaOfc.modelo.Reclamacao;

public class FeedBack {
public void notificarReclamacao(Reclamacao reclamacao) {
	String emailFomatado = formarEmail(reclamacao);
	String destinatario = reclamacao.getPedido().getCliente().getEmail();
	Email email = new Email("FeedBack da reclama��o - PizzariaTeste", emailFomatado , destinatario);
	email.enviar();
}

private String formarEmail(Reclamacao reclamacao) {
	String nomeCliente = reclamacao.getPedido().getCliente().getNome();
	String pedido = reclamacao.getPedido().getNumero()+"";
	String data = reclamacao.getData()+"";
	String reclama = reclamacao.getTipoRecla().ordinal()+"";
	return "Ol� " + nomeCliente + ","+"\n" + "\n" +"Recebemos sua reclama��o e lamentamos o ocorrido com seu pedido n: " + pedido +", todas as medidas para que isso n�o ocorra novamente est�o sendo tomadas. "
			+ "\n"+"Visando a perman�ncia de nossa parceria estamos "
			+ "lhe ofertando 5 pontos extras para amenizar esse empecilho." + "\n" +"\n" +"Agrademos a compreens�o.";
}
}
