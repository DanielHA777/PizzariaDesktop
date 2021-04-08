package br.senai.sp.pizzariaOfc.view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.senai.sp.pizzariaOfc.dao.DAOCliente;
import br.senai.sp.pizzariaOfc.dao.connectionFactory;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.tableModel.ClienteTableModel;
import br.senai.sp.pizzariaOfc.util.ImageUtil;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JButton btnBuscar;
	//variavel do tipo cliente para utilizar na tela
	private Cliente cliente;
	private JTextArea taEndereco;
	private JFormattedTextField txtCep;
	private JTextField txtBuscar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JTextField txtNome;
	private JScrollPane scrollPane_2;
	private JTable tbClientes;
	private DAOCliente dao;
	//variavel do tipo list<cliente> para montar a tabela
	private List<Cliente> clientes;
	//variavel booleana para verificar se é um INAERCAO da tela do pedido
	private boolean insertUnico = false;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lbId;
	private JLabel lblPontos;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public CadCliente(String...telefone) {
		
		//instancia a dao 
		dao = new DAOCliente(); 
		setResizable(false);
		setForeground(Color.LIGHT_GRAY);
		setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\cli.jpg"));
		setName("FrmCadCliente");
		setTitle("Cadastro de clientes");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 503);
		contentPane = new JPanel();
		
		contentPane.setToolTipText("");
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(32, 63, 46, 14);
		contentPane.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBorder(new LineBorder(Color.BLACK));
		txtNome.setToolTipText("Informe o nome ");
		txtNome.setName("txtNome");
		txtNome.setBounds(80, 62, 157, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		// key typed caixa de texto, keylistener é uma interface, listener é oq escuta o código e executa os eventos quando necessário.
		// criando classe anonima quando da new e tem metodos q precisam ser implementados, class esem nome;
		// adapter é uma classe abstrata q implementa keylistener.
		txtTel = new JTextField();
		txtTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// guarda na variável cliente o retorno da busca pelo telefone
				try {
					cliente = dao.buscaTel(txtTel.getText().trim());
					//verifica se o cliente é nulo
					if(cliente == null) {
						JOptionPane.showMessageDialog(null, "cliente não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else {
						//popula o formulário com o o bj vcliente
						popular();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				if (!Character.isDigit(tecla.getKeyChar())) {
					tecla.consume();					// verifica se não é um digito
				}
				// verifica se játem 11 caractere na caixa de texto
				if(txtTel.getText().length() == 11) /* pegando o tamanho da caixa de texto*/ {
					tecla.consume(); // consome os numeros q excedem 11 caracteres
				}
			}		// System.out.println(tecla.getKeyChar());// mostra oq console esta sendo digitado na caixa de texto
		});
		txtTel.setBorder(new LineBorder(Color.BLACK));
		txtTel.setToolTipText("Informe o telefone");
		txtTel.setName("txtTelefone");
		txtTel.setBounds(552, 62, 131, 20);
		contentPane.add(txtTel);
		txtTel.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Telefone:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(485, 63, 79, 14);
		contentPane.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(Color.BLACK));
		txtEmail.setToolTipText("Informe o E-mail");
		txtEmail.setName("txtEmail");
		txtEmail.setBounds(302, 62, 173, 20);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);

		JLabel lblNewLabel_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(247, 63, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		lblNewLabel_1_1_1 = new JLabel("Endere\u00E7o:");
		lblNewLabel_1_1_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(34, 122, 68, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("CEP:");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(693, 63, 68, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		lbId = new JLabel("Id:");
		lbId.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lbId.setBounds(34, 11, 68, 14);
		contentPane.add(lbId);

		// máscara para caixa de texto
		MaskFormatter mskCep = null;

		try {
			mskCep = new MaskFormatter("#####-###");
			mskCep.setValueContainsLiteralCharacters(false); // retira os caracteres não numéricos da mascara
			mskCep.setPlaceholderCharacter('_'); // define o caractere q aparece na máscara
		} catch (ParseException e1) { // ordem dos catch primeiro as subclasses, por ultimo a super classe
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		} catch (NullPointerException e) { // catch sem limite de quantidade, com ele o programa n encerra
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

		// evento de caixa de texto focus, key, mouse...
		//
		txtCep = new JFormattedTextField(mskCep);
		txtCep.setBorder(new LineBorder(Color.BLACK));
		txtCep.setToolTipText("Informe o CEP");
		txtCep.setBounds(729, 62, 117, 20);
		contentPane.add(txtCep);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(590, 162, -64, 22);
		contentPane.add(textArea);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(337, 279, 79, -25);
		contentPane.add(scrollPane);

		lblPontos = new JLabel("Pontos:");
		lblPontos.setForeground(Color.RED);
		lblPontos.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPontos.setBounds(34, 38, 68, 14);
		contentPane.add(lblPontos);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 112, 181, 41);
		contentPane.add(scrollPane_1);

		taEndereco = new JTextArea();
		scrollPane_1.setViewportView(taEndereco);
		taEndereco.setLineWrap(true);
		taEndereco.setBorder(new LineBorder(Color.BLACK));
		// define o comportamento da tecla tab
		Set<KeyStroke> strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB"))); // keyStroke lista de teclas 
		taEndereco.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes);
		strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));  
		taEndereco.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes);

		// lógica do botão salvar
		btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Arial Black", Font.ITALIC, 14));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validação de campos
				if(txtTel.getText().isEmpty()) { // se o texto for vazio
					JOptionPane.showMessageDialog(null, "informe o telefone", "Aviso", JOptionPane.ERROR_MESSAGE);//componente pai null, mensagem da caixa de mensagem, titulo, icone de erro
					//retorna o focu para a caixa de texto
			txtTel.requestFocus();
				}else if(txtNome.getText().trim().isEmpty()) {   // trim tira os espaços em brqncos // isEmpty se retorna vazio
					JOptionPane.showMessageDialog(null, "informe o nome", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtNome.requestFocus();
				}
				else if(taEndereco.getText().trim().isEmpty()) {   // trim tira os espaços em brqncos // isEmpty se retorna vazio
					JOptionPane.showMessageDialog(null, "informe o endereço", "Aviso", JOptionPane.ERROR_MESSAGE);
					taEndereco.requestFocus();
				}else if(txtEmail.getText().trim().isEmpty()) {   // trim tira os espaços em brqncos // isEmpty se retorna vazio
					JOptionPane.showMessageDialog(null, "informe o E-mail", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocus();
				}else if(txtCep.getValue() == null) {   // trim tira os espaços em brqncos // isEmpty se retorna vazio
					JOptionPane.showMessageDialog(null, "informe o CEP", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtCep.requestFocus();
				}else {
					//verifica se o obj e nulo
					if(cliente == null) {
						cliente = new Cliente();
						cliente.setTelefone(txtTel.getText().trim());
					}
					// banco de dados // instancia obj cliente
					
					// popula o obj cliente
					cliente.setTelefone(txtTel.getText().trim());
					cliente.setNome(txtNome.getText().trim());
					cliente.setEmail(txtEmail.getText().trim());
					cliente.setEndereco(taEndereco.getText().trim());
					cliente.setCep(txtCep.getValue().toString());   // *************** erro			
					
				 try {
					 if(cliente.getId() == 0) {
						 dao.inserir(cliente);// envia  o cliente para a dao inserir no bd
					 }else {
						 dao.atualizar(cliente); // mesclou 2 dao em um botão só
					 }				
					//limpa o formulario após inserção
					limpar();
					// carrega a lista de clientes atraves da dao
					clientes = dao.listar();
					// monta a tabela
					criarTabela();
					if(insertUnico == true) {
						dispose();
					}
				} catch (SQLException erro) {
					// TODO Auto-generated catch block
					erro.printStackTrace();
					JOptionPane.showMessageDialog(null, erro.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
				}
				}
			} 
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setBounds(166, 392, 116, 41);
		contentPane.add(btnSalvar);

		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verifica se existe um cliente selecionado
				if(cliente == null) {
					//exibe mensagem pedindo para selecionar cliente
					JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir", "selecione", JOptionPane.WARNING_MESSAGE); 
				}else {
					//caso hja cliente selecionado 
					//emite um beep
					java.awt.Toolkit.getDefaultToolkit().beep();   
					//retornando showconfirmdialog 0 para sim e 1 para n
					int botao = JOptionPane.showConfirmDialog(null, "Deseja excluir o cliente "+ cliente.getNome()+ " ?" , "CONFIRMAR EXCLUSÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					//verifica se a variavel é igual a 0 o useja botao yes pressionado
					if(botao == 0) {
						try {
							//chama método excluir da dao
							dao.excluir(cliente);
							//recarrega a lista de clientes
							clientes = dao.listar();
							//cria tabela novamente
							criarTabela();
							//limpa formulário
							limpar();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.ITALIC, 14));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBounds(359, 392, 116, 41);
		contentPane.add(btnExcluir);

		btnLimpar = new JButton("LIMPAR");
		btnLimpar.setFont(new Font("Arial Black", Font.ITALIC, 14));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBounds(567, 392, 116, 41);
		contentPane.add(btnLimpar);

		txtBuscar = new JTextField();
		txtBuscar.setBorder(new LineBorder(Color.BLACK));
		txtBuscar.setBounds(80, 172, 131, 26);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Buscar:");
		lblNewLabel_1_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(23, 177, 79, 14);
		contentPane.add(lblNewLabel_1_2);

		// criar uma imagem a partir da pasta imagens
		btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBounds(221, 171, 68, 27);
		ImageIcon IconeLupa = new ImageIcon(getClass().getResource("/lupa.png"));
		btnBuscar.setIcon(ImageUtil.redimensiona(IconeLupa, btnBuscar.getWidth(), btnBuscar.getHeight())); // continuação
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					//carrega lista de clientes com o listar
					clientes = dao.listar(txtBuscar.getText().trim());
					//cria tabela
					criarTabela();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(btnBuscar);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		scrollPane_2.setBounds(32, 205, 809, 176);
		contentPane.add(scrollPane_2);
		
		tbClientes = new JTable();
		tbClientes.setFont(new Font("Arial Black", Font.PLAIN, 12));
		scrollPane_2.setViewportView(tbClientes);
		//carregar os clientes atraves da dao
		try {
			clientes = dao.listar();
			//chama o criar tabela para para montar a table
			criarTabela();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar os clientes", "erro", JOptionPane.ERROR_MESSAGE);
		}
// inicializa janela no centro da tela
		setLocationRelativeTo(null);
		
		// verifica se foi passado um tel no construtor
				if(telefone.length > 0) {
					txtTel.setText(telefone[0]);
					txtNome.requestFocus();
					//troca a variavel de controle para verdadeira
					insertUnico = true;
				}
	}
	private void limpar() {
		//limpa campos para inserir novo cliente
		txtNome.setText(null);
		txtTel.setText(null);
		taEndereco.setText(null);
		txtEmail.setText(null);
		txtCep.setValue(null);
		cliente = null;
		txtNome.requestFocus();
		tbClientes.clearSelection();
		txtTel.setEditable(true);
		lbId.setText("ID:");
		lblPontos.setText("Pontos:");
	}
	//cria nova tabela e aplica npo jtable
	private void criarTabela() {
		// cria um table model com a lista de clientes 
		ClienteTableModel model = new ClienteTableModel(clientes);
		//aplica o table model a tbclientes
		tbClientes.setModel(model);
		//define q apenas uma linha é selecionavel
		 tbClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 //define a largura da coluna
		 tbClientes.getColumnModel().getColumn(0).setPreferredWidth(280);
		 //define o comportamento da tabela ao selecionar objeto
		 tbClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// verifica se existe uma linha selecionada
				if(tbClientes.getSelectedRow() >= 0) {
					//pego o cliente na lista e guardo na variavel cliente
					cliente = clientes.get(tbClientes.getSelectedRow());
					//popula o formulario com o obj cliente
					popular();
				}
			}
		} );
	}
	// popula o formulario com as informações do obj cliente
	private void popular() {
		txtTel.setText(cliente.getTelefone());
		txtNome.setText(cliente.getNome());
		txtEmail.setText(cliente.getEmail());
		taEndereco.setText(cliente.getEndereco());
		txtCep.setValue(cliente.getCep());
		lbId.setText("ID: "+cliente.getId());
		lblPontos.setText("Pontos: "+ cliente.getPontos());
		txtTel.setEditable(false);
}}
