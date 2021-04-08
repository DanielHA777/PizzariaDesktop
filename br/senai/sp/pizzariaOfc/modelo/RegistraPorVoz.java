package br.senai.sp.pizzariaOfc.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.senai.sp.pizzariaOfc.view.CadReclamacao;

public class RegistraPorVoz implements ActionListener, ControladaPorVoz{

	public JButton btn1;
	public JButton btnRegistrar;
	public JPanel panel;
	private CadReclamacao tela;
	
	public RegistraPorVoz(CadReclamacao cadReclamacao) {
		this.tela = tela;
		this.btn1 = tela.getBtn1();
		this.btnRegistrar = tela.getBtnRegistrar();
		this.panel = tela.getPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		registra();
		
	}
	
	public void registra() {
		btn1.setText("Carregando...");
		ThreadVoz threadVoz = new ThreadVoz(this, btn1);
		threadVoz.execute();
	}
	
	@Override
	public void executaComandoPorVoz(String oQueFoiFalado) {
		if(oQueFoiFalado.equals("doClick")) {
			btnRegistrar.doClick();
			btn1.setVisible(false);
	}else if(oQueFoiFalado.equals("close")) {
			System.exit(0);
		}
	}

}
