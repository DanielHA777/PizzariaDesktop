package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class PizzaFria extends JFrame {

	private JPanel contentPane;
	private JLabel lblPizzaFria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzaFria frame = new PizzaFria();
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
	public PizzaFria() {
		setTitle("Pizza Fria");
		setBounds(100, 100, 627, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		String s = "<html> <head> <title> <center> <h1>Plano de a��o: Pizza Fria </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido a falta de organiza��o entre o tempo p�s preparo do pedido e entrega da pizza, "
				+ "um intervalo muito grande faz com que a pizza esfrie. "
				+ "O mal isolamento do pedido fora de um ambiente t�rmico, tamb�m � prejudicial a temperatura da pizza.  \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, pedir desculpas pelo ocorrido e se ele desejar podemos enviar outra pizza, isso n�o ir� se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		
		
		contentPane.setLayout(null);
		
		lblPizzaFria = new JLabel(s);
		lblPizzaFria.setBounds(57, 11, 531, 344);
		contentPane.add(lblPizzaFria);
	}

}
