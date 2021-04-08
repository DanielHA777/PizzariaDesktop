package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Atraso extends JFrame {

	private JPanel contentPane;
	private JLabel lblAtraso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atraso frame = new Atraso();
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
	public Atraso() {
		setTitle("Atraso na Entrega");
		setBounds(100, 100, 702, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String s = "<html> <head> <title> <center> <h1>Plano de ação: Atraso na entrega </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "O motorista leva tempo demais para chegar ao destino\r\n" + 
				"Esse é um dos problemas com as entregas que mais acontece. Quando essa demora tem causas concretas, como a distância ou problemas crônicos no trânsito local, isso não representa um transtorno, já que o cliente estará ciente e não ficará esperando pela carga antes do tempo previsto.\r\n" + 
				"\r\n" + 
				"A crise se dá quando esse atraso se traduz em um incomodo junto ao cliente, que fica esperando pelos produtos em determinado dia ou horário, mas eles não chegam. Nessa situação, o atraso do motorista pode ter principalmente duas causas: rotas mal formuladas ou desvios não programados. O primeiro passo, portanto, é identificar a origem do problema.\r\n" + 
				"\r\n" + 
				"Rotas mal formuladas podem obrigar o motorista a fazer vai-e-vens desnecessários que, além de atrasos, representam prejuízos. Quando há o uso de um roteirizador isso dificilmente acontece, já que a ferramenta escolhe os melhores caminhos e otimiza o trabalho do motorista. Dessa forma, é possível prever e avisar o cliente a hora que provavelmente ele precisará ficar aguardando a chegada da encomenda.\r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, informar que o pedido esta a caminho e o motivo do atraso deve ter sido causado por trânsito ou erro de rota, isso não irá se repetir. Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		lblAtraso = new JLabel(s);
		lblAtraso.setBounds(25, 11, 640, 460);
		contentPane.add(lblAtraso);
		
	}
}
