package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.pizzariaOfc.dao.DAOFuncionario;
import br.senai.sp.pizzariaOfc.dao.connectionFactory;
import br.senai.sp.pizzariaOfc.modelo.Cargo;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.util.ImageUtil;

import javax.swing.JLabel;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame {
	private PainelPizza contentPane;
	private static JButton btnFuncionario;
	private static JButton btnPedidos;
	private DAOFuncionario daoFunc;
	public static Funcionario funcLogar;
	private Funcionario Func;
	private JTextField txtLogado;
	public static JLabel lblLogado;
	private JLabel lblCargo;
	private CadCliente cadCliente;
	private CadFuncionario cadFuncionario;
	private CadPedido cadPedido;
	private CadProdutos cadProduto;
	private static JButton btnProdutos;
	private JMenuItem itemProduto;
	private JMenuItem itemFuncionario;
	private JMenuItem ItemCliente;
	private JMenuItem itemPedidoo;
	private static JButton btnClientes;
	private JButton btnSair;
	private JButton btnVerPedido;
	private VerPedidos verPedido;
	private JMenu MenuCadastro;
	private JMenuItem itemVer;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) { // uma tread
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// TelaPrincipal frame = new TelaPrincipal();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TelaPrincipal() throws SQLException {
		daoFunc = new DAOFuncionario();
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				connectionFactory.closeConnection();
			}
		});
		
		
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Menu Pizzaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 80, 1091, 256);
		contentPane = new PainelPizza();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1974, 21);
		contentPane.add(menuBar);

		MenuCadastro = new JMenu("Cadastros");
		menuBar.add(MenuCadastro);

		itemProduto = new JMenuItem("Cadastro de Produtos");
		itemProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProdutos.doClick();
			}
		});
		MenuCadastro.add(itemProduto);

		itemPedidoo = new JMenuItem("Pedidos");
		itemPedidoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPedidos.doClick();
			}
		});
		MenuCadastro.add(itemPedidoo);

		ItemCliente = new JMenuItem("Cadastro de clientes");
		ItemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCliente frame = new CadCliente();
				frame.setVisible(true);
			}
		});

		itemFuncionario = new JMenuItem("Cadastro de Funcion\u00E1rio");
		itemFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFuncionario.doClick();
			}
		});
		MenuCadastro.add(itemFuncionario);

		itemVer = new JMenuItem("Ver Pedidos");
		MenuCadastro.add(itemVer);
		itemVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerPedido.doClick();
			}
		});

		MenuCadastro.add(ItemCliente);

		btnClientes = new JButton("Cadastro Clientes");
		btnClientes.setEnabled(false);
		btnClientes.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCliente frame = new CadCliente();
				frame.setVisible(true);
			}
		});

		btnClientes.setBounds(30, 62, 222, 162);
		ImageIcon IconeLupaaaaa = new ImageIcon(getClass().getResource("/valor.png"));
		btnClientes.setIcon(ImageUtil.redimensiona(IconeLupaaaaa, btnClientes.getWidth(), btnClientes.getHeight()));
		contentPane.add(btnClientes);

		btnProdutos = new JButton("Cadastro Produtos");
		btnProdutos.setIcon(new ImageIcon("C:\\Users\\PSMP\\Downloads\\prod.jpg"));
		btnProdutos.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cadProduto == null) {
					cadProduto = new CadProdutos();
					contentPane.add(cadProduto);
					// cadProduto.setVisible(true);
				}
				cadProduto.show();
			}

		});
		btnProdutos.setBounds(257, 405, 239, 162);
		contentPane.add(btnProdutos);

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\pizza.png"));
		/// ImageIcon fotoP = new ImageIcon(getClass().getResource("/pizzaaa.jpg"));

		btnFuncionario = new JButton("Cadastro Funcionarios");
      btnFuncionario.setEnabled(false);
		btnFuncionario.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnFuncionario.setBounds(144, 235, 230, 162);
		ImageIcon IconeLupa = new ImageIcon(getClass().getResource("/func.png"));
		btnFuncionario
				.setIcon(ImageUtil.redimensiona(IconeLupa, btnFuncionario.getWidth(), btnFuncionario.getHeight()));
		contentPane.add(btnFuncionario);
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verificar se a tela é nula
				if (cadFuncionario == null) {
					cadFuncionario = new CadFuncionario();
					contentPane.add(cadFuncionario);
				}
				cadFuncionario.show(); // faz aparecer
			}
		});

		btnPedidos = new JButton("Pedidos");
		btnPedidos.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnPedidos.setBounds(377, 578, 226, 162);
		ImageIcon IconeLupaa = new ImageIcon(getClass().getResource("/pedidoo.png"));
		btnPedidos.setIcon(ImageUtil.redimensiona(IconeLupaa, btnPedidos.getWidth(), btnPedidos.getHeight()));
		contentPane.add(btnPedidos);
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verificar se a tela é nula
				if (cadPedido == null) {
					cadPedido = new CadPedido();
					contentPane.add(cadPedido);
				}
				cadPedido.show(); // faz aparecer
			}
		});
		lblLogado = new JLabel("");
		lblLogado.setOpaque(true);
		lblLogado.setForeground(Color.BLACK);
		lblLogado.setBackground(Color.LIGHT_GRAY);
		lblLogado.setBounds(1428, 108, 119, 26);
		contentPane.add(lblLogado);
		lblLogado.setText(funcLogar.getNome());

		JLabel lblNewLabel = new JLabel("Colaborador:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(1309, 109, 114, 21);
		contentPane.add(lblNewLabel);

		lblCargo = new JLabel((String) null);
		lblCargo.setOpaque(true);
		lblCargo.setForeground(Color.BLACK);
		lblCargo.setBackground(Color.LIGHT_GRAY);
		lblCargo.setBounds(1428, 156, 119, 26);
		contentPane.add(lblCargo);
		lblCargo.setText(funcLogar.getCargo().toString());
		
		if(TelaPrincipal.funcLogar.getCargo() == funcLogar.getCargo().GERENTE) {
			btnClientes.setEnabled(true);
			btnFuncionario.setEnabled(true);
		}else if(TelaPrincipal.funcLogar.getCargo() == funcLogar.getCargo().MOTOBOY) {
			btnPedidos.setEnabled(false);
			btnClientes.setEnabled(false);
			btnFuncionario.setEnabled(false);
			btnProdutos.setEnabled(false);
		}

		JLabel lblCargo_1 = new JLabel("Cargo:");
		lblCargo_1.setOpaque(true);
		lblCargo_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCargo_1.setBounds(1309, 157, 114, 21);
		contentPane.add(lblCargo_1);

		btnSair = new JButton("Logoff");
		btnSair.setFont(new Font("Arial Black", Font.PLAIN, 11));

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(1516, 801, 163, 52);
		ImageIcon IconeLupaaa = new ImageIcon(getClass().getResource("/exitt.png"));
		btnSair.setIcon(ImageUtil.redimensiona(IconeLupaaa, btnSair.getWidth(), btnSair.getHeight()));
		contentPane.add(btnSair);

		btnVerPedido = new JButton();
		btnVerPedido.setText("ver pedidos");
		btnVerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerPedidos frame = new VerPedidos();
				frame.setVisible(true);
			}
		});
		btnVerPedido.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnVerPedido.setBounds(486, 745, 239, 162);
		ImageIcon IconeLupaaaa = new ImageIcon(getClass().getResource("/contrato.png"));
		btnVerPedido.setIcon(ImageUtil.redimensiona(IconeLupaaaa, btnVerPedido.getWidth(), btnVerPedido.getHeight()));
		contentPane.add(btnVerPedido);

		JLabel lblNewLabel_1 = new JLabel("Cadastro de Funcion\u00E1rios");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(384, 300, 266, 31);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Cadastro de Produtos");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(506, 467, 266, 31);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Realizar Pedidos");
		lblNewLabel_1_2.setOpaque(true);
		lblNewLabel_1_2.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(613, 633, 266, 31);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Pedidos realizados");
		lblNewLabel_1_2_1.setOpaque(true);
		lblNewLabel_1_2_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(735, 796, 266, 31);
		contentPane.add(lblNewLabel_1_2_1);
		
		lblNewLabel_2 = new JLabel("Cadastro de Clientes");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(262, 116, 266, 31);
		contentPane.add(lblNewLabel_2);
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

	class PainelPizza extends JDesktopPane {
		@Override
		protected void paintComponent(Graphics g) {
			// extrair do graphics g uma referencia para graphics 2d
			Graphics2D g2d = (Graphics2D) g;
			// carregar a imagem do background
			try {
				BufferedImage imagem = ImageIO.read(getClass().getResource("/background.jpg"));
				g2d.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
