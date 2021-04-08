package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senai.sp.pizzariaOfc.util.ImageUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

import br.senai.sp.pizzariaOfc.dao.DAOCliente;
import br.senai.sp.pizzariaOfc.dao.DAOFuncionario;
import br.senai.sp.pizzariaOfc.dao.DAOPedido;
import br.senai.sp.pizzariaOfc.dao.DAOProduto;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.FormaPgto;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.ItemPedido;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.TipoProduto;
import br.senai.sp.pizzariaOfc.modelo.avaliacao;
import br.senai.sp.pizzariaOfc.tableModel.ProdutoTableModel;
import br.senai.sp.pizzariaOfc.tableModel.itemPedidoTableModel;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CadPedido extends JInternalFrame {

	private JPanel contentPane;
	private JTable tbProdutos;
	private JComboBox cbPag;
	private JButton btnFinalizar;
	private JTextField txtTroco;
	private JTextField txtTaxa;
	private JTextField txtEndereco;
	private JTextField txtCliente;
	private JTextField txtBuscar;
	private JLabel lblimg;
	private byte[] imgProduto;
	private JTable tbItens;
	// declaração das DAO
	private DAOProduto daoProduto;
	private DAOCliente daoCliente;
	private DAOPedido daoPedido;
	private List<Produto> produtos;
	private List<Cliente> clientes;
	// variavel para o produto selecionado
	private Produto prodSelect;
	// variavel para o cliente do pedido
	private Cliente cliente;
	private JTextField txtNumero;

	private JTextField txtTelefone;
	private JTextArea taObs;
	private JButton btnindefinido;
	private JCheckBox chRetirada;
	private JLabel lblValorTotal;
	private JButton btnVoltar;
	private JButton btnPassar;
	private DAOFuncionario daoFunc;
	private JSpinner spinner;
	private JLabel lblNmero;
	private Funcionario funcionario;
	private JLabel lblTroco;
	// variavel para o pedido q sera gerado
	private Pedido pedido;
	private ItemPedido itemP;
	// variavel para lista de itens do pedido
	private List<ItemPedido> itens = new ArrayList<>();
	// variavel para o table odelitempedido
	private itemPedidoTableModel modelItem;
	// variavel para o item selecionado
	private ItemPedido itemSelect;
	private JScrollPane scrollPane_2;
	private JTextField txtTelefone_1;
	private JButton btnBrinde;
	
	private DAOPedido dao;
	private JLabel lblImg;
	private JLabel lblMoto;
	private JComboBox cbMotoboy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadPedido frame = new CadPedido();
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
	public CadPedido(String... nome) {
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				// fixme limpar();
			
              limpar();			
			
			}
		});
		// instancia daos
		daoProduto = new DAOProduto();
		daoPedido = new DAOPedido();
		daoFunc = new DAOFuncionario();
		daoCliente = new DAOCliente();
		setResizable(false);
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\pizza.png"));
		setForeground(Color.BLACK);
		setTitle("Pedido:");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 934, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon fotoP = new ImageIcon(getClass().getResource("/pizzaFundo.jfif"));

		cbPag = new JComboBox();
		cbPag.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbPag.setFont(new Font("Arial Black", Font.PLAIN, 13));
		cbPag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbPag.getSelectedItem() == FormaPgto.DINHEIRO) { /// fixme *************
					lblTroco.setVisible(true);
					txtTroco.setVisible(true);
				} else {
					lblTroco.setVisible(false);
					txtTroco.setVisible(false);
				}
			}
		});
		cbPag.setBounds(463, 393, 138, 22);
		cbPag.setModel(new DefaultComboBoxModel(FormaPgto.values()));
		// cbPag.setSelectedIndex(-1);
		contentPane.add(cbPag);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 362, 228);
		contentPane.add(scrollPane);

		tbProdutos = new JTable();
		tbProdutos.setFont(new Font("Arial Black", Font.PLAIN, 13));
		scrollPane.setViewportView(tbProdutos);
		try {
			produtos = daoProduto.listar();
			// criando tabela de produtos no cadpedido
			criarTabelaProdutos();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	
		
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(10, 58, 83, 18);
		contentPane.add(lblNewLabel);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Informe o cliente para realizar um pedido",
							"cliente não informado", JOptionPane.ERROR_MESSAGE);
				} else if (txtEndereco.getText().trim().isEmpty() && !chRetirada.isSelected()) {
					JOptionPane.showMessageDialog(null, "Informe o endereço de entrega",
							"endereço não informado não informado", JOptionPane.ERROR_MESSAGE);
				} else if (itens.size() == 0) {
					JOptionPane.showMessageDialog(null, "O pedido deve ter pelo menos um item", "Pedido não informado",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// instancia o o bj pedido
					pedido = new Pedido();
					// setar o cliente no pedido
					pedido.setCliente(cliente);
					pedido.setFuncionario(funcionario);
					// setar endereço
					pedido.setEndEntrega(txtEndereco.getText());
					pedido.setTxentrega(Double.parseDouble(txtTaxa.getText()));
					pedido.setFormaPgto((FormaPgto) cbPag.getSelectedItem());
					pedido.setObservacao(taObs.getText());
					pedido.setRetirada(chRetirada.isSelected());
					pedido.setMotoboy(cbMotoboy.getSelectedItem().toString());
					
					
					if (!txtTroco.getText().isEmpty()) {
						pedido.setTroco(Double.parseDouble(txtTroco.getText()));
					}
					pedido.setItens(itens);
					pedido.setvTotal(atualizaTotal());
					pedido.setFuncionario(TelaPrincipal.funcLogar);
				
					// atualiza os pontos do pedido
					try {
						daoPedido.inserir(pedido);
						//adiciona os pontos ao cliente
						cliente.adcionarPontos(pedido.getPontos());
						// atualiza os pontos do cliente no BD 
						daoCliente.atualizaPontos(cliente.getId(), cliente.getPontos());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro ao inserir pedido", "Erro", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Salvo Com Sucesso");
					Random tempo = new Random();
					JOptionPane.showMessageDialog(null, "Tempo para entrega em " + cliente.getEndereco().toString() + " é " + tempo.nextInt(30)+ " minutos");
				Avaliacao ava = new Avaliacao();
				ava.setVisible(true);
				}
			}
		});
		btnFinalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinalizar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnFinalizar.setFocusCycleRoot(true);
		btnFinalizar.setBounds(794, 476, 111, 60);
		contentPane.add(btnFinalizar);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setOpaque(true);
		lblEndereo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblEndereo.setBackground(Color.LIGHT_GRAY);
		lblEndereo.setBounds(276, 58, 83, 18);
		contentPane.add(lblEndereo);

		JLabel lblNewLabel_1 = new JLabel("Itens do Pedido:");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(604, 109, 138, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Forma de Pag.");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(345, 393, 111, 18);
		contentPane.add(lblNewLabel_2);

		lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setOpaque(true);
		lblNmero.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNmero.setBackground(Color.LIGHT_GRAY);
		lblNmero.setBounds(10, 11, 83, 18);
		contentPane.add(lblNmero);

		JLabel lblData = new JLabel("Telefone");
		lblData.setOpaque(true);
		lblData.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblData.setBackground(Color.LIGHT_GRAY);
		lblData.setBounds(560, 58, 66, 18);
		contentPane.add(lblData);
		txtTelefone_1 = new JTextField();
		txtTelefone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// joga na variável cliente o retorno do método buscarPorTelefone
					cliente = daoCliente.buscaTel(txtTelefone_1.getText());
					// verifica a variável cliente, se é nula
					if (cliente != null) {
						// joga na tfNome o nome do cliente
						txtCliente.setText(cliente.getNome());
						// joga na taEndereco o endereço do cliente
						txtEndereco.setText(cliente.getEndereco());
						if(cliente.isBrinde()) {
							btnBrinde.setVisible(true);;
						}else {
							btnBrinde.setVisible(false);
						}
					} else {
						// abre um confirmDialog e guarda na variável a opção escolhida
						int opcao = JOptionPane.showConfirmDialog(null, "Cliente não encontrado! Deseja cadastrá-lo?",
								"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						// se a opcao for de cadastrar
						if (opcao == 0) {
							// abre uma janela de Cadastro de Cliente
							CadCliente frame = new CadCliente(txtTelefone_1.getText());
							frame.setVisible(true);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtTelefone_1.setBounds(636, 59, 126, 20);
		contentPane.add(txtTelefone_1);
		txtTelefone_1.setColumns(10);

		lblTroco = new JLabel("Troco:");
		lblTroco.setOpaque(true);
		lblTroco.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTroco.setBackground(Color.LIGHT_GRAY);
		lblTroco.setBounds(611, 393, 83, 18);
		contentPane.add(lblTroco);

		txtTroco = new JTextField();
		txtTroco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtTroco.setColumns(10);
		txtTroco.setBounds(703, 394, 116, 20);
		contentPane.add(txtTroco);

		JLabel lblRetirada = new JLabel("Retirada:");
		lblRetirada.setOpaque(true);
		lblRetirada.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblRetirada.setBackground(Color.LIGHT_GRAY);
		lblRetirada.setBounds(20, 389, 66, 18);
		contentPane.add(lblRetirada);

		JLabel lblTaxaDeEntrega = new JLabel("Taxa de entrega:");
		lblTaxaDeEntrega.setOpaque(true);
		lblTaxaDeEntrega.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTaxaDeEntrega.setBackground(Color.LIGHT_GRAY);
		lblTaxaDeEntrega.setBounds(119, 389, 117, 18);
		contentPane.add(lblTaxaDeEntrega);

		txtTaxa = new JTextField();
		txtTaxa.setText("7.00");

		txtTaxa.setColumns(10);
		txtTaxa.setBounds(246, 390, 89, 20);
		contentPane.add(txtTaxa);

		lblValorTotal = new JLabel("R$ 0.00");

		lblValorTotal.setOpaque(true);
		lblValorTotal.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorTotal.setBackground(Color.LIGHT_GRAY);
		lblValorTotal.setBounds(579, 433, 163, 18);
		contentPane.add(lblValorTotal);

		JLabel lblNewLabel_2_1 = new JLabel("Observa\u00E7\u00E3o:");
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNewLabel_2_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(10, 418, 111, 18);
		contentPane.add(lblNewLabel_2_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(137, 418, 382, 44);
		contentPane.add(scrollPane_1);

		taObs = new JTextArea();
		scrollPane_1.setViewportView(taObs);

		txtTelefone_1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if (txtTelefone_1.getText().length() == 11) {
					e.consume();
				}

			}
		});

		btnPassar = new JButton(">>");
		btnPassar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se existe um produto selecionado
				if (prodSelect == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao pedido", "Erro",
							JOptionPane.ERROR_MESSAGE);
					// verifica se foi informada a quantidade
				} else if ((int) spinner.getValue() <= 0) {
					JOptionPane.showMessageDialog(null, "Informe a quantidade a ser adicionada", "Erro",
							JOptionPane.ERROR_MESSAGE);			
				}
				
				else {
					// cria um novo item do pedido
					ItemPedido item = new ItemPedido();
					item.setProduto(prodSelect);
				//	txtSabor.setText(prodSelect.getNome());
					// atribui a quantidade ao item
					item.setQtd((int) spinner.getValue());
					// atribui um aobservacao ao item
					String observacao = JOptionPane.showInputDialog(null, "Observação do item" + prodSelect.getNome(),
							"Observação", JOptionPane.QUESTION_MESSAGE);
					item.setObservacao(observacao);
					// adciona o item a lista de items
					itens.add(item);
					// avisa ao table model que a lista foi atualizada
					modelItem.fireTableDataChanged();
					// resetar o valor da spinner
					spinner.setValue(0);
					prodSelect = null;
					tbProdutos.clearSelection();
					atualizaTotal();
				//	btnBrinde.setVisible(false);
				//	cliente.retirarBrinde();
				}
			}
		});
		btnPassar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPassar.setBounds(395, 200, 89, 39);
		contentPane.add(btnPassar);

		btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se existe um item selecionado
				if (itemSelect == null) {
					JOptionPane.showMessageDialog(null, "Selecione um item do pedido para remover", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					//verifica se é brinde 
					if(itemSelect.getTotal() == 0) {
						btnBrinde.setVisible(true);
						cliente.adcionarPontos(15);
					}
					// remove o item da lista
					itens.remove(itemSelect);
					// avisa p tablemodel q os dados foram alterados
					modelItem.fireTableDataChanged();
					// zera avariavel itemseletc
					itemSelect = null;
					// limpa selecao da tabela
					tbItens.clearSelection();
					atualizaTotal();
				}
			}
		});
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.setBounds(395, 329, 89, 39);
		contentPane.add(btnVoltar);

		btnindefinido = new JButton("1/2");
		btnindefinido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnindefinido.setEnabled(false);
		btnindefinido.setBounds(395, 265, 89, 39);
		contentPane.add(btnindefinido);

		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setBounds(369, 59, 181, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtCliente = new JTextField();
		txtCliente.setEnabled(false);
		txtCliente.setColumns(10);
		txtCliente.setBounds(103, 59, 158, 20);
		contentPane.add(txtCliente);

		JLabel lblProdutos = new JLabel("Produtos:");
		lblProdutos.setOpaque(true);
		lblProdutos.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProdutos.setBackground(Color.LIGHT_GRAY);
		lblProdutos.setBounds(10, 87, 111, 18);
		contentPane.add(lblProdutos);

		JComboBox cbProduto = new JComboBox();
		cbProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbProduto.setFont(new Font("Arial Black", Font.PLAIN, 13));
		cbProduto.setModel(new DefaultComboBoxModel(TipoProduto.values()));  /// fixme**************
		cbProduto.setBounds(9, 107, 158, 23);
		contentPane.add(cbProduto);

		txtBuscar = new JTextField();
		txtBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se tem um produto selecionado
				if (cbProduto.getSelectedIndex() >= 0) {
					// carrega lista de produtos
					try {
						produtos = daoProduto.listar(txtBuscar.getText(), (TipoProduto) cbProduto.getSelectedItem());
						criarTabelaProdutos();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		txtBuscar.setBounds(178, 108, 181, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		scrollPane_2.setBounds(516, 141, 375, 228);
		contentPane.add(scrollPane_2);

		tbItens = new JTable();
		tbItens.setFont(new Font("Arial Black", Font.PLAIN, 11));
		scrollPane_2.setViewportView(tbItens);

		lblimg = new JLabel("New label");
		lblimg.setVerticalAlignment(SwingConstants.TOP);
		lblimg.setRequestFocusEnabled(false);
		lblimg.setFocusTraversalKeysEnabled(false);
		lblimg.setFocusable(false);
		lblimg.setBackground(Color.WHITE);
		lblimg.setBounds(775, 14, 116, 110);
		contentPane.add(lblimg);

		lblimg.setToolTipText("Clique 2x para selecionar ");
		lblimg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		ImageIcon fotoPp = new ImageIcon(getClass().getResource("/salvaimg.jpg"));
		lblimg.setIcon(ImageUtil.redimensiona(fotoPp, lblimg.getWidth(), lblimg.getHeight()));

		chRetirada = new JCheckBox("");
		chRetirada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chRetirada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se está selecionada
				if (chRetirada.isSelected()) {
					txtTaxa.setText("0.00");
					txtTaxa.setEnabled(false);
					cbMotoboy.setVisible(false);
					lblMoto.setVisible(false);
				} else {
					txtTaxa.setText("7.00");
					txtTaxa.setEnabled(true);
					cbMotoboy.setVisible(true);
					lblMoto.setVisible(true);
				}
				atualizaTotal();
			}
		});

		chRetirada.setContentAreaFilled(false);
		chRetirada.setBounds(92, 389, 21, 23);
		contentPane.add(chRetirada);

		spinner = new JSpinner();
		spinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinner.setBounds(395, 147, 89, 42);
		contentPane.add(spinner);
		
		btnBrinde = new JButton("Brinde");
		btnBrinde.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBrinde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(prodSelect == null || (prodSelect.getTipo() != TipoProduto.PIZZA_DOCE && prodSelect.getTipo() != TipoProduto.PIZZA_SALGADA)) {
					JOptionPane.showMessageDialog(null, "Selecione uma pizza para o brinde", "Selecione", JOptionPane.WARNING_MESSAGE);
				}else {
					// cria um item do pedido com o prodselect
					ItemPedido item = new ItemPedido();
					item.setBrinde(prodSelect);
					
					// atribui um aobservacao ao item
					String observacao = JOptionPane.showInputDialog(null, "Observação do item" + prodSelect.getNome(),
							"Observação", JOptionPane.QUESTION_MESSAGE);
					// adciona o item a lista de items
					itens.add(item);
					// avisa ao table model que a lista foi atualizada
					modelItem.fireTableDataChanged();
					// resetar o valor da spinner
					spinner.setValue(0);
					prodSelect = null;
					tbProdutos.clearSelection();
					atualizaTotal();
					btnBrinde.setVisible(false);
				cliente.retirarBrinde();
				}
			}		
		});
		btnBrinde.setVisible(false);
		btnBrinde.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnBrinde.setFocusCycleRoot(true);
		btnBrinde.setBounds(662, 476, 111, 60);
		contentPane.add(btnBrinde);
								
								lblMoto = new JLabel("Motoboy:");
								lblMoto.setOpaque(true);
								lblMoto.setBounds(369, 111, 73, 14);
								contentPane.add(lblMoto);
																
																cbMotoboy = new JComboBox();
																cbMotoboy.setBounds(452, 110, 111, 20);
																contentPane.add(cbMotoboy);
																
																		lblImg = new JLabel();
																		lblImg.setBackground(Color.LIGHT_GRAY);
																		//lblImg.setToolTipText("Troco:");
																		lblImg.setFocusable(false);
																		lblImg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
																		lblImg.setBounds(0, 0, 928, 565);
																		lblImg.setIcon(ImageUtil.redimensiona(fotoP, lblImg.getWidth(), lblImg.getHeight()));
																		// lblImg.setBounds(0, 0, 770, 407);
																		contentPane.add(lblImg);
		criarTabelaItens();
		
		try {
			
			List<Funcionario> lista = daoPedido.buscaMotoboy();
			for (Funcionario funcionario: lista) {
				cbMotoboy.addItem(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void criarTabelaProdutos() {
		// cria o tablemodel sob a lista de produtos
		ProdutoTableModel model = new ProdutoTableModel(produtos);
		// aplicar o model na tabela de produtos
		tbProdutos.setModel(model);
		// ajusta largura da tabela
		// tbProdutos.setAutoResizeMode();
		/*
		 * tbProdutos.getColumnModel() .getColumn(0).setPreferredWidth(220);
		 */
		// define o tipo de seleção
		tbProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// define comportamento ao selecionar uma linha na tabela
		tbProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// verifica se a uma linha selecionada
				if (tbProdutos.getSelectedRow() >= 0) {
					// guarda na prodSelet o produto da linha correspondente na lista de produtos
					prodSelect = produtos.get(tbProdutos.getSelectedRow());
					// cria um imageicon atraves do vetor de bytes
					if (prodSelect.getImagem() != null) {
						ImageIcon fotoProduto = new ImageIcon(prodSelect.getImagem());
						// joga na label a foto do produto
						lblimg.setIcon(ImageUtil.redimensiona(fotoProduto, lblimg.getWidth(), lblimg.getHeight()));
					} else {
						// joga um icone nulo na lable
						// lblimg.setIcon(null);
						ImageIcon fotoPp = new ImageIcon(getClass().getResource("/salvaimg.jpg"));
						lblimg.setIcon(ImageUtil.redimensiona(fotoPp, lblimg.getWidth(), lblimg.getHeight()));
					}
				}
			}
		});
	}

	private void criarTabelaItens() {
		// instancia o tableModel
		modelItem = new itemPedidoTableModel(itens);
		// aplica o model na tabela de itens
		tbItens.setModel(modelItem);
		// ajusta a largura das colunas
		tbItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbItens.getColumnModel().getColumn(2).setPreferredWidth(200);
		tbItens.getColumnModel().getColumn(3).setPreferredWidth(60);
		tbItens.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbItens.getSelectedRow() >= 0) {
					itemSelect = itens.get(tbItens.getSelectedRow());
				}
			}
		});
	}

	private double atualizaTotal() {
		// variável para o valor total
				double total = 0;
				// percorre todos os itens da lista de itens do pedido
				for(ItemPedido i : itens) {
					// acrescentar na variável total o total do item
					total += i.getTotal(); 
				}
				// variável para tx de entrega convertendo o texto da tfTxEntrega
				double txEntrega = Double.parseDouble(txtTaxa.getText());
				// acrescenta a tx de entrega no total
				total += txEntrega;
				// joga o valor total na label
				lblValorTotal.setText(String.format("R$ %7.2f", total));
				// retorna o total
				return total;
	}
	private double atualizaMeia() {   // fixme ********************************
		
		double total = 0;
		// percorre todos os itens da lista de itens do pedido
		for(ItemPedido i : itens) {
			// acrescentar na variável total o total do item
			total += i.getTotal(); 
		}
		// variável para tx de entrega convertendo o texto da tfTxEntrega
		double txEntrega = Double.parseDouble(txtTaxa.getText());
		// acrescenta a tx de entrega no total
		total += txEntrega;
		// joga o valor total na label
		lblValorTotal.setText(String.format("R$ %7.2f", total));
		// retorna o total
		return total / 2;
	}
	private void limpar() {
		try {
			cliente = null;
			txtTelefone_1.setText(null);
			prodSelect = null;
			produtos = daoProduto.listar();
			criarTabelaProdutos();
			itens = new ArrayList<>();
			criarTabelaItens();
			txtCliente.setText(null);
			txtEndereco.setText(null);
			//lblimg.setIcon(null);
			btnBrinde.setVisible(false);
			taObs.setText(null);
			chRetirada.setSelected(false);
			cbPag.setSelectedIndex(1);
			txtTroco.setText(null);
			txtTaxa.setText("7.00");
			atualizaTotal();
			txtTelefone_1.requestFocus();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
