package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class FaltouItem extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaltouItem frame = new FaltouItem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FaltouItem() {
		setTitle("Item Ausente");
		setBounds(100, 100, 624, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Faltou item </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido a falta de organização no momento do preparo do pedido para entrega,"
				+ " investir em meios de conferência antes de sair pode inibir esse problema."
				+ "Sempre buscar verificar os itens do pedido no sistema.  \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, pedir desculpas pelo ocorrido e se ele desejar podemos enviar os itens ausentes, isso não irá se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		
		
		contentPane.setLayout(null);
		JLabel lblFaltou = new JLabel(s);
		lblFaltou.setBounds(33, 11, 533, 436);
		contentPane.add(lblFaltou);
	}

}
