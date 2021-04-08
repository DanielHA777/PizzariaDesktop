package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class PizzaQueimada extends JFrame {

	private JPanel contentPane;
	private JLabel lblPizzaQ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzaQueimada frame = new PizzaQueimada();
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
	public PizzaQueimada() {
		setTitle("Pizza Queimada");
		setBounds(100, 100, 629, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Pizza Queimada </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido a falta de calibragem do forno, excesso de tempo no forno, a solução mais plausível é realizar uma administração rigida de tempo de preparo das pizzas, "
				+ "introduzir um forno elétrico com timer também pode solucionar o problema.  \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, pedir desculpas pelo ocorrido e se ele desejar podemos enviar outra pizza, isso não irá se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		
		
		contentPane.setLayout(null);
		lblPizzaQ = new JLabel(s);
		lblPizzaQ.setBounds(48, 11, 522, 329);
		contentPane.add(lblPizzaQ);
	}
}
