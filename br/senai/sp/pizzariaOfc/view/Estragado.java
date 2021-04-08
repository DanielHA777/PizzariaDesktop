package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Estragado extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estragado frame = new Estragado();
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
	public Estragado() {
		setTitle("Produto Estragado");
		setBounds(100, 100, 624, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Produto Estragado </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido a uma má gestão de estoque dos produtos, com foco na data de validade, sem assim, "
				+ "se faz necessário um sistema de controle eficaz com profissionais orientados a qualidade dos produtos.  \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, pedir desculpas pelo ocorrido e informar que enviara uma outra pizza, isso não irá se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		
		JLabel lblEstragado = new JLabel(s);
		lblEstragado.setBounds(44, 11, 511, 328);
		contentPane.add(lblEstragado);
	}

}
