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
		
		String s = "<html> <head> <title> <center> <h1>Plano de a��o: Atraso na entrega </h1></center> </title></head><br> <body> "
				+ "<h2>- O que pode ter causado e como solucionar: </h2> <br>"
				+ "O motorista leva tempo demais para chegar ao destino\r\n" + 
				"Esse � um dos problemas com as entregas que mais acontece. Quando essa demora tem causas concretas, como a dist�ncia ou problemas cr�nicos no tr�nsito local, isso n�o representa um transtorno, j� que o cliente estar� ciente e n�o ficar� esperando pela carga antes do tempo previsto.\r\n" + 
				"\r\n" + 
				"A crise se d� quando esse atraso se traduz em um incomodo junto ao cliente, que fica esperando pelos produtos em determinado dia ou hor�rio, mas eles n�o chegam. Nessa situa��o, o atraso do motorista pode ter principalmente duas causas: rotas mal formuladas ou desvios n�o programados. O primeiro passo, portanto, � identificar a origem do problema.\r\n" + 
				"\r\n" + 
				"Rotas mal formuladas podem obrigar o motorista a fazer vai-e-vens desnecess�rios que, al�m de atrasos, representam preju�zos. Quando h� o uso de um roteirizador isso dificilmente acontece, j� que a ferramenta escolhe os melhores caminhos e otimiza o trabalho do motorista. Dessa forma, � poss�vel prever e avisar o cliente a hora que provavelmente ele precisar� ficar aguardando a chegada da encomenda.\r\n" + 
				"\r\n" 
				+ "<br>"
				+ "<h2> - Ao Cliente: </h2> <br>"
				+ "<h4>Solicitar calma, informar que o pedido esta a caminho e o motivo do atraso deve ter sido causado por tr�nsito ou erro de rota, isso n�o ir� se repetir. Seguir com o procedimento dos pontos.</h4>"
				+ "</body> </html>";
		lblAtraso = new JLabel(s);
		lblAtraso.setBounds(25, 11, 640, 460);
		contentPane.add(lblAtraso);
		
	}
}
