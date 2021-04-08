package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SaborTrocado extends JFrame {
   
	private JPanel contentPane;
	private JLabel lblTrocado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaborTrocado frame = new SaborTrocado();
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
	public SaborTrocado() {
		setTitle("Sabor trocado");
		setBounds(100, 100, 651, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Sabor trocado </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "Pode ocorrer devido  a  alta demanda de pedidos e um sistema de organização  e <br> "
				+ "despacho dos pedidos que não esteja sendo eficiente,"
				+ " buscar melhorias no setor só tem <br> a acrescentar no atendimento da pizzaria.. "
				+ "Falha na comunicação entre atendente e pizzaiolo, <br>utilizar uma forma de comunicação simples e direta,"
				+ " onde não deixe pontas soltas ou questões em aberto referente aos pedidos e observações do cliente."
				+ "Orietar sempre o motoboy a separar os pedidos de forma coerente as suas entregas, evitando assim erros de troca. \r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, informar que o pedido correto já esta sendo enviado, isso não irá se repetir."
				+ " Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		
		
		contentPane.setLayout(null);
		
		lblTrocado = new JLabel(s);
		lblTrocado.setBounds(21, 11, 640, 403);
		contentPane.add(lblTrocado);
	}
}
