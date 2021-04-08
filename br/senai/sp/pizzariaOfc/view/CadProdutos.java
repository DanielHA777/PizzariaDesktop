package br.senai.sp.pizzariaOfc.view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senai.sp.pizzariaOfc.dao.DAOProduto;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.TipoProduto;
import br.senai.sp.pizzariaOfc.tableModel.ClienteTableModel;
import br.senai.sp.pizzariaOfc.tableModel.ProdutoTableModel;
import br.senai.sp.pizzariaOfc.util.ImageUtil;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CadProdutos extends JInternalFrame{

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextField txtBuscar;
	private JComboBox cbTipo;

	private JCheckBox chbPromocao;
	private JButton btnLimpar;
	private Produto produto;
	private JTextArea taDesc;
	private JLabel lblImg;
	// variável para armazenar o vetor de bytes da foto
	private byte[] imgProduto;
	private DAOProduto dao;
	private JTable tbProdutos;
	private List<Produto> produtos;
	private JLabel lblCodigo;
	private JScrollPane scrollPane;   // **************************************************************
	private JComboBox cbBuscar;
	private JButton btnSalvar;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public CadProdutos() {
		setOpaque(true);
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					produtos = dao.listar();
					criarTabela();
					limpar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		dao = new DAOProduto();
		setBackground(SystemColor.inactiveCaptionBorder);
		setResizable(false);
		//tIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\prod.jpg"));
		setForeground(SystemColor.inactiveCaption);
		setTitle("Cadastro de Produtos");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 527);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação de campos
				if (txtNome.getText().isEmpty()) { // se o texto for vazio
					JOptionPane.showMessageDialog(null, "informe o nome", "Aviso", JOptionPane.ERROR_MESSAGE);// componente
																												// pai
																												// null,
																												// mensagem
																												// da
																												// caixa
																												// de
																												// mensagem,
																												// titulo																												// icone
																												// de
																												// erro
					// retorna o focu para a caixa de texto
					txtNome.requestFocus();
				} else if (txtNome.getText().trim().isEmpty()) { // trim tira os espaços em brqncos // isEmpty se
																	// retorna vazio
					JOptionPane.showMessageDialog(null, "informe o nome", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtNome.requestFocus();
				} else if (txtPreco.getText().trim().isEmpty()) { // trim tira os espaços em brqncos // isEmpty se
																	// retorna vazio
					JOptionPane.showMessageDialog(null, "informe o preço", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtPreco.requestFocus();
				} else if (cbTipo.getSelectedIndex() < 0) { // trim tira os espaços em brqncos // isEmpty se retorna
															// vazio
					JOptionPane.showMessageDialog(null, "informe o tipo", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbTipo.requestFocus();
				} else {
					// banco de dados // instancia obj produto
					if (produto == null) {
						produto = new Produto();
					}
					// popula o obj cliente
					produto.setPreco(Double.parseDouble(txtPreco.getText()));
					produto.setNome(txtNome.getText().trim());
					produto.setTipo((TipoProduto) cbTipo.getSelectedItem()); // pegando obj e vendo ele como tipoProduto
					produto.setDescricao(taDesc.getText());
					produto.setPromocao(chbPromocao.isSelected());
					produto.setImagem(imgProduto);
					// produto.setPreco();
					// funcionario.setCargo(cbCargo).);
					try {
						if (produto.getId() == 0) {
							dao.inserir(produto);// envia o cliente para a dao inserir no bd
						} else {
							dao.atualizar(produto); // mesclou 2 dao em um botão só
						}
						// limpa o formulario após inserção
						limpar();
						// carrega a lista de clientes atraves da dao
						produtos = dao.listar();
						// monta a tabela
						criarTabela();
					} catch (SQLException erro) {
						// TODO Auto-generated catch block
						erro.printStackTrace();
						JOptionPane.showMessageDialog(null, erro.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setBounds(188, 403, 111, 53);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// verifica se existe um cliente selecionado
				if (produto == null) {
					// exibe mensagem pedindo para selecionar cliente
					JOptionPane.showMessageDialog(null, "Selecione um produto para excluir", "selecione",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// caso hja cliente selecionado
					// emite um beep
					java.awt.Toolkit.getDefaultToolkit().beep();
					// retornando showconfirmdialog 0 para sim e 1 para n
					int botao = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o produto " + produto.getNome() + " ?", "CONFIRMAR EXCLUSÃO",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					// verifica se a variavel é igual a 0 o useja botao yes pressionado
					if (botao == 0) {
						try {
							// chama método excluir da dao
							dao.excluir(produto);
							// recarrega a lista de clientes
							produtos = dao.listar();
							// cria tabela novamente
							criarTabela();
							// limpa formulário
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
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBounds(373, 403, 111, 53);
		contentPane.add(btnExcluir);

		btnLimpar = new JButton("LIMPAR");
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(571, 403, 111, 53);
		contentPane.add(btnLimpar);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lblImg.setEnabled(true);
			}
		});

		txtNome.setBounds(83, 46, 116, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtPreco = new JTextField();
		txtPreco = new JTextField();
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				if (!Character.isDigit(tecla.getKeyChar()) && tecla.getKeyChar() != '.') {
					tecla.consume(); // verifica se não é um digito
				}
				// verifica se játem ponto caractere na caixa de texto
				if (tecla.getKeyChar() == '.' && txtPreco.getText().contains(".")) {
					/* pegando o tamanho da caixa de texto */ {
						tecla.consume(); // consome os pontos existemntes
					}
				}
				if (txtPreco.getText().length() == 6) {
					tecla.consume();
				}
			} // System.out.println(tecla.getKeyChar());// mostra oq console esta sendo
				// digitado na caixa de texto
		});
		txtPreco.setColumns(10);
		txtPreco.setBounds(250, 46, 86, 20);
		contentPane.add(txtPreco);

		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(126, 137, 124, 20);
		contentPane.add(txtBuscar);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(38, 47, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblPreo.setBounds(204, 48, 46, 14);
		contentPane.add(lblPreo);

		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblCodigo.setBounds(38, 14, 65, 14);
		contentPane.add(lblCodigo);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDescrio.setBounds(27, 87, 86, 14);
		contentPane.add(lblDescrio);

		chbPromocao = new JCheckBox("Promo\u00E7\u00E3o");
		chbPromocao.setFont(new Font("Arial Black", Font.PLAIN, 12));
		chbPromocao.setBounds(351, 45, 97, 23);
		contentPane.add(chbPromocao);

		taDesc = new JTextArea();
		taDesc.setLineWrap(true);
		taDesc.setBounds(102, 83, 272, 38);
		contentPane.add(taDesc);

		JLabel lblNewLabel_1 = new JLabel("Tipo:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(454, 49, 46, 14);
		contentPane.add(lblNewLabel_1);

		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(TipoProduto.values()));
		cbTipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbTipo.setBounds(488, 45, 105, 22);
		cbTipo.setSelectedIndex(-1);
		contentPane.add(cbTipo);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// carrega lista de clientes com o listar
					produtos = dao.listar(txtBuscar.getText(), (TipoProduto) cbBuscar.getSelectedItem()); // convertendo variavel obj em tipo produto
					// cria tabela
					criarTabela();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(btnBuscar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 205, 790, 176);
		contentPane.add(scrollPane);

		tbProdutos = new JTable();
		tbProdutos.setFont(new Font("Arial Black", Font.PLAIN, 11));
		scrollPane.setViewportView(tbProdutos);
		// carregar os clientes atraves da dao
		try {
			produtos = dao.listar();
			// chama o criar tabela para para montar a table
			criarTabela();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar os clientes", "erro",
					JOptionPane.ERROR_MESSAGE);
		}
// inicializa janela no centro da tela
	//etLocationRelativeTo(null);

		// });
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBounds(255, 137, 68, 27);
		ImageIcon IconeLupa = new ImageIcon(getClass().getResource("/lupa.png"));
		btnBuscar.setIcon(ImageUtil.redimensiona(IconeLupa, btnBuscar.getWidth(), btnBuscar.getHeight()));
		// btnBuscar.setBounds(321, 137, 77, 22);
		contentPane.add(btnBuscar);

		// btnBuscar.setBounds(183, 137, 65, 24);
		contentPane.add(btnBuscar);

		lblImg = new JLabel("");
		lblImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // obj e traz as informações sobre o evento
				// verifica se foi um duplo clique
				if (e.getClickCount() == 2) { // retorna quantidade de cliques escolhidos
					// criA um jFileChooser para escolher a imagem no hd
					JFileChooser fc = new JFileChooser();
					// define a pasta de abertura do filechooser
					fc.setCurrentDirectory(new File(System.getProperty("user.home")));
					// cria um filtro paea especificar apenas arquivos imagem
					javax.swing.filechooser.FileFilter filtroImg = new FileNameExtensionFilter("imagens",
							ImageIO.getReaderFileSuffixes());
					fc.setFileFilter(filtroImg);
					// verificA SE FOI CLICADO NO BOTÃO OPEN
					// variavel int para guardar o retorno do file chooser
					int retorno = fc.showOpenDialog(null);
					if (retorno == JFileChooser.APPROVE_OPTION) {
						// recupera arquivo selecionado
						File arqSelec = fc.getSelectedFile();
						try {
							// converte o file para imagem
							BufferedImage foto = ImageIO.read(arqSelec); // imageIo trabalha com imagens))
							// cria um imageicon atrvés do bufferedImage
							ImageIcon imgFoto = new ImageIcon(foto);
							// joga na lab3l A imagem redimensionada
							lblImg.setIcon(ImageUtil.redimensiona(imgFoto, lblImg.getWidth(), lblImg.getHeight()));
							// converte a imagem em vetor de byte
							ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // obj para armazenar vetor
																							// de byutes
							ImageIO.write(foto, "png", outStream); // escreveu a imagem com codificação png
							imgProduto = outStream.toByteArray(); // pegando obj e retornando vetor de bytes
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
					// fc.showOpenDialog(null);
				}
			}
		});
		lblImg.setToolTipText("Clique 2x para selecionar ");
		lblImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImg.setBounds(620, 80, 146, 115);
		ImageIcon fotoP = new ImageIcon(getClass().getResource("/salvaimg.jpg"));
		lblImg.setIcon(ImageUtil.redimensiona(fotoP, lblImg.getWidth(), lblImg.getHeight()));

		// lblImg.setBackground(Color.BLACK);
		// lblImg.setForeground(Color.BLACK);
		// lblImg.setBounds(524, 122, 111, 39);
		contentPane.add(lblImg);

		cbBuscar = new JComboBox();
		cbBuscar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		cbBuscar.setModel(new DefaultComboBoxModel(TipoProduto.values()));
		cbBuscar.setBounds(39, 137, 86, 22);
		cbBuscar.setSelectedIndex(-0); // assim o primeiro indice a aparecer na combobox é 0
		contentPane.add(cbBuscar);
		
		
		
	}

	private void limpar() {
		// limpa campos para inserir novo cliente
		txtNome.setText(null);
		//txtCodigo.setText(null);
		;
		;
		txtPreco.setText(null);
		cbTipo.setSelectedIndex(-1);
		;
		chbPromocao.setSelected(false);
		taDesc.setText(null);
		ImageIcon fotoP = new ImageIcon(getClass().getResource("/salvaimg.jpg"));
		lblImg.setIcon(ImageUtil.redimensiona(fotoP, lblImg.getWidth(), lblImg.getHeight()));
		imgProduto = null;
		produto = null;
		txtNome.requestFocus();
	}

	private void criarTabela() {
		// cria um table model com a lista de clientes
		ProdutoTableModel model = new ProdutoTableModel(produtos);
		// aplica o table model a tbclientes
		tbProdutos.setModel(model);
		// define q apenas uma linha é selecionavel
		tbProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// define a largura da coluna
		tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(280);
		// define o comportamento da tabela ao selecionar objeto
		tbProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				// verifica se existe uma linha selecionada
				if (tbProdutos.getSelectedRow() >= 0) {
					// pego o cliente na lista e guardo na variavel cliente
					produto = produtos.get(tbProdutos.getSelectedRow());
					// popula o formulario com o obj cliente
					popular();
				}
			}
		});
	}

	private void popular() {
		imgProduto = produto.getImagem();
		if(imgProduto != null) {
			ImageIcon icon = new ImageIcon(imgProduto);
			lblImg.setIcon(ImageUtil.redimensiona(icon, lblImg.getWidth(), lblImg.getHeight()));
		}
		else {
			ImageIcon fotoP = new ImageIcon(getClass().getResource("/salvaimg.jpg"));
			lblImg.setIcon(ImageUtil.redimensiona(fotoP, lblImg.getWidth(), lblImg.getHeight()));
		}
		txtNome.setText(produto.getNome());
		lblCodigo.setText("ID: " + produto.getId());
		txtPreco.setToolTipText(produto.getPreco() + "");
		cbTipo.setToolTipText(produto.getTipo() + "");
		taDesc.setText(produto.getDescricao());
		// chbPromocao.setText(produto.get);
		// lblImg.setToolTipText(produto.getImagem());

	}
	

}
