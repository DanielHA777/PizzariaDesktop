package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.pizzariaOfc.dao.DAOFuncionario;
import br.senai.sp.pizzariaOfc.dao.connectionFactory;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.util.PasswordUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private PainelPizza contentPane;
	public static JTextField txtCpf;
	private JPasswordField pfSenha;
   private DAOFuncionario daoFunc;
   private Funcionario Func;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		daoFunc = new DAOFuncionario();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\login.png"));
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 400);
		contentPane = new PainelPizza();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(170, 131, 88, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setOpaque(true);
		lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSenha.setBounds(170, 176, 88, 29);
		contentPane.add(lblSenha);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(268, 124, 197, 35);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {	
					String senha = new String(pfSenha.getPassword());
					senha = PasswordUtil.criptografa256(senha);
					Func = daoFunc.selecionar(txtCpf.getText(),senha );
					if(Func != null) {
						JOptionPane.showMessageDialog(null,"Bem vindo"+ " " +Func.getNome());
						TelaPrincipal.funcLogar = Func;
						TelaPrincipal frame = new TelaPrincipal();
						frame.setVisible(true);		
						
						
					}else {
							JOptionPane.showMessageDialog(null, "Senha ou Usuário incorretos", "Erro", JOptionPane.ERROR_MESSAGE);						
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				if (!Character.isDigit(tecla.getKeyChar())) {
					tecla.consume();					// verifica se não é um digito
				}
				// verifica se játem 11 caractere na caixa de texto
				if(txtCpf.getText().length() == 11) /* pegando o tamanho da caixa de texto*/ {
					tecla.consume(); // consome os numeros q excedem 11 caracteres
				}
			}		// System.out.println(tecla.getKeyChar());// mostra oq console esta sendo digitado na caixa de texto
		});
		btnLogin.setBounds(268, 216, 197, 38);
		contentPane.add(btnLogin);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pfSenha = new JPasswordField();
		pfSenha.setBounds(268, 170, 197, 35);
		contentPane.add(pfSenha);
		txtCpf.setText("00011122233");
		pfSenha.setText("12345");

	}
	class PainelPizza extends JDesktopPane {
		@Override
		protected void paintComponent(Graphics g) {
			//extrair do graphics g uma referencia para graphics 2d
			Graphics2D g2d = (Graphics2D) g;
			//carregar a imagem do background 
			try {
				BufferedImage imagem = ImageIO.read(getClass().getResource("/logo.jpg"));
				g2d.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
}
