package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class PizzaRevirada extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzaRevirada frame = new PizzaRevirada();
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
	public PizzaRevirada() {
		setTitle("Pizza Revirada");
		setBounds(100, 100, 627, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Pizza Revirada </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido a falha no momento de armazenagem do pedido, "
				+ "investir em suportes e caixas adequadas para este processe podem inibir esse problema."
				+ "O motoboy precisa evitar rotas que causem transtorno a estabalidade da moto, evitando que a pizza corra o risco de revirar"
				+ "  \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, pedir desculpas pelo ocorrido e se ele desejar podemos enviar outra pizza, isso não irá se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		JLabel lblRevirada = new JLabel(s);
		lblRevirada.setBounds(25, 11, 576, 433);
		contentPane.add(lblRevirada);
	}

}
