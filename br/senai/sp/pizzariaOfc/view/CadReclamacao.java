package br.senai.sp.pizzariaOfc.view;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import com.mysql.jdbc.Connection;

import Servico.FeedBack;
import br.senai.sp.pizzariaOfc.dao.DAOAvaliacao;
//import Email.FeedBack;
import br.senai.sp.pizzariaOfc.dao.DAOCliente;
import br.senai.sp.pizzariaOfc.dao.DAOFuncionario;
import br.senai.sp.pizzariaOfc.dao.DAOPedido;
import br.senai.sp.pizzariaOfc.dao.DAOProduto;
import br.senai.sp.pizzariaOfc.dao.DAOReclamacao;
import br.senai.sp.pizzariaOfc.dao.connectionFactory;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.DiaDaSemana;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.ItemPedido;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.Reclamacao;
import br.senai.sp.pizzariaOfc.modelo.RegistraPorVoz;
import br.senai.sp.pizzariaOfc.modelo.TipoReclama;
import br.senai.sp.pizzariaOfc.modelo.avaliacao;
import br.senai.sp.pizzariaOfc.tableModel.ReclamaTableModel;
import br.senai.sp.pizzariaOfc.tableModel.itemPedidoTableModel;
import br.senai.sp.pizzariaOfc.util.ImageUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class CadReclamacao extends JFrame{

	private JPanel contentPane;
	private JTextField txtPedido;
	private JTextField txtMoto;
	private JTextField txtData;
	private Atraso atraso;
	private JTable tbReclamacao;
	private JTextField txtDia;
	private Produto prodSelect;
	private List<Produto> produtos;

	private Cliente cliente;
	private Pedido pedido;
	private DAOReclamacao dao;
	private Funcionario func;
	private Connection conexao;
	private itemPedidoTableModel modelItem;
	private boolean insertUnico = false;
	private ItemPedido itemPedido;
	private JButton btnRegistrar;
	private ItemPedido itemSelect;
	
	private avaliacao ava;
	private List<Reclamacao> reclamacoes;
	private Produto produto;
	private List<DiaDaSemana> dia;
	private Reclamacao reclamacao;
	private JButton btn1;
	public JButton getBtn1() {
		return btn1;
	}
	

	private Pedido pedSelect;
	private JLabel lblId;
	private DAOPedido daoP;
	private DAOProduto daoPR;
	private List<avaliacao> avas;
	private DAOFuncionario daoF;
	private JButton btnBuscar;
	private JTextField txtBuscar;
	private List<ItemPedido> itens = new ArrayList<>();
	private JTextArea taReclama;
	private JComboBox cbTipo;
	private JLabel lblSabor;
	private DAOAvaliacao daoA;
	private JComboBox cbSabor;
	private JButton btnRel;
	private DAOCliente daoC;
	private JButton btnLimpar;
	private JButton Relatório;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadReclamacao frame = new CadReclamacao();
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
	public CadReclamacao(String... numero) {
		
		
		setResizable(false);
		dao = new DAOReclamacao();
		daoP = new DAOPedido();
		daoPR = new DAOProduto();
		daoC = new DAOCliente();
		daoA = new DAOAvaliacao();
		daoF = new DAOFuncionario();
		setTitle("Reclama\u00E7\u00F5es");
		setBounds(100, 100, 911, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		RegistraPorVoz RV = new RegistraPorVoz(this);
		btn1.addActionListener(RV);
		contentPane.setLayout(null);
		
		JButton btn1 = new JButton("");
		btn1.setBounds(776, 29, 44, 37);
		ImageIcon IconeLupaaaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn1.setIcon(ImageUtil.redimensiona(IconeLupaaaaa, btn1.getWidth(), btn1.getHeight()));
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("");
		btn2.setBounds(736, 29, 44, 37);
		ImageIcon IconeLupa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn2.setIcon(ImageUtil.redimensiona(IconeLupa, btn2.getWidth(), btn2.getHeight()));
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("");
		btn3.setBounds(696, 29, 44, 37);
		ImageIcon IconeLupaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn3.setIcon(ImageUtil.redimensiona(IconeLupaa, btn3.getWidth(), btn3.getHeight()));
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("");
		btn4.setBounds(656, 29, 44, 37);
		ImageIcon IconeLupaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn4.setIcon(ImageUtil.redimensiona(IconeLupaaa, btn4.getWidth(), btn4.getHeight()));
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("");
		btn5.setBounds(616, 29, 44, 37);
		ImageIcon IconeLup = new ImageIcon(getClass().getResource("/estrela.png"));
		btn5.setIcon(ImageUtil.redimensiona(IconeLup, btn5.getWidth(), btn5.getHeight()));
		contentPane.add(btn5);
		
		btn1.setVisible(true);
		btn2.setVisible(false);
		btn3.setVisible(false);
		btn4.setVisible(false);
		btn5.setVisible(false);
	/*try {
		int a = daoA.soma();
		
		if(a % 11 == 1) {
			btn5.setVisible(true);
		}else if(a / avas.size() == 2) {
			btn2.setVisible(true);
		}
	} catch (SQLException e2) {
		e2.printStackTrace();
	}	*/
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPedido.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "informe o número do pedido", "Aviso",
							JOptionPane.ERROR_MESSAGE);
                 Relatório.setVisible(true);
					txtPedido.requestFocus();
				} else {
					if (reclamacao == null) {
						reclamacao = new Reclamacao();
					}
					reclamacao.setPedido(pedido);
					reclamacao.setFunc(func);
					reclamacao.setProduto(produto);
					reclamacao.setReclamacao(taReclama.getText());
					reclamacao.setTipoRecla((TipoReclama) cbTipo.getSelectedItem());
				
					// reclamacao.setData(txtData.);
					try {
						if (reclamacao.getId() == 0) {
							//lblId.setText("ID: " + reclamacao.getId());
							dao.inserir(reclamacao);
							Relatório.setVisible(true);
						pedido.getCliente().adcionarPontosR();	
							// atualiza os pontos do cliente no BD 
							daoC.atualizaPontos(2, pedido.getCliente().getPontos());
							JOptionPane.showMessageDialog(null, "Foi acrescentado 5 pontos ao cliente " + reclamacao.getPedido().getCliente().getNome() +". Enviando E-mail.");
							FeedBack feed = new FeedBack();
							feed.notificarReclamacao(reclamacao);
						} else {
							JOptionPane.showMessageDialog(null, "Reclamação já consta no registro, por segurança dos dados, não pode ser alterada!");
						}
						//limpar();
						reclamacoes = dao.listar();
						criarTabela();
						if (insertUnico == true) {
							dispose();
						}						
					}

					catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRegistrar.setBounds(722, 446, 98, 36);
		contentPane.add(btnRegistrar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(614, 446, 98, 36);
		contentPane.add(btnLimpar);
		
		
		JButton btnAtraso = new JButton("Plano");
		//btnAtraso.setVisible(false);
		btnAtraso.setFont(new Font("Arial Black", Font.PLAIN, 15));
			btnAtraso.setBounds(258, 446, 117, 36);
			//ImageIcon IconeLupaa = new ImageIcon(getClass().getResource("/pedidoo.png"));
			contentPane.add(btnAtraso);
			btnAtraso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbTipo.getSelectedItem() == TipoReclama.Atraso_entrega) {
						//btnAtraso.setVisible(true);
					Atraso frame = new Atraso();
					frame.setVisible(true);
					}else if(cbTipo.getSelectedItem() == TipoReclama.Sabor_trocado) {
						SaborTrocado frame = new SaborTrocado();
						frame.setVisible(true);
					}else if(cbTipo.getSelectedItem() == TipoReclama.Pizza_fria) {
						PizzaFria frame = new PizzaFria();
						frame.setVisible(true);
					}else if(cbTipo.getSelectedItem() == TipoReclama.Pizza_queimada) {
						PizzaQueimada frame = new PizzaQueimada();
						frame.setVisible(true);
					}else if(cbTipo.getSelectedItem() == TipoReclama.Pizza_revirada) {
						PizzaRevirada frame = new PizzaRevirada();
						frame.setVisible(true);
					}else if(cbTipo.getSelectedItem() == TipoReclama.Faltou_refrigerante) {
						FaltouItem frame = new FaltouItem();
						frame.setVisible(true);
					}
				}
			});
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 250, 765, 175);
		contentPane.add(scrollPane);

		tbReclamacao = new JTable();
		scrollPane.setViewportView(tbReclamacao);

		lblId = new JLabel("Id:");
		lblId.setBounds(35, 11, 46, 14);
		contentPane.add(lblId);

		txtPedido = new JTextField();
		txtPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pedido = daoP.procuraa(txtPedido.getText());
					if (pedido != null) {
						Calendar c = Calendar.getInstance();
						DiaDaSemana dia = DiaDaSemana.values()[c.get(Calendar.DAY_OF_WEEK)];
						Calendar data = Calendar.getInstance();
						SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
						txtData.setText(formatador.format(data.getTime()));
						txtDia.setText(dia + "");
						carregaSabores();
						txtMoto.setText(pedido.getMotoboy());
						cbSabor.setSelectedItem(pedido.getItens());
						//lblId.setText("ID: " + reclamacao.getId());
						if (cbTipo.getSelectedItem() != TipoReclama.Atraso_entrega) {
							cbSabor.setVisible(true);
							lblSabor.setVisible(true);
						} else {
							cbSabor.setVisible(false);
							lblSabor.setVisible(false);
						}
					} else {
						int opcao = JOptionPane.showConfirmDialog(null, "Pedido não encontrado! Tente novamente",
								"Aviso", JOptionPane.WARNING_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		txtPedido.setBounds(97, 74, 117, 20);
		contentPane.add(txtPedido);
		txtPedido.setColumns(10);

		txtMoto = new JTextField();
		txtMoto.setBounds(691, 74, 129, 20);
		contentPane.add(txtMoto);
		txtMoto.setColumns(10);

		txtData = new JTextField();
		txtData.setBounds(278, 74, 129, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel = new JLabel("Pedido");
		lblNewLabel.setBounds(55, 77, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setBounds(237, 77, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Dia da semana:");
		lblNewLabel_2.setBounds(417, 77, 137, 14);
		contentPane.add(lblNewLabel_2);

		txtDia = new JTextField();
		txtDia.setBounds(514, 74, 109, 20);
		contentPane.add(txtDia);
		txtDia.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("MotoBoy:");
		lblNewLabel_3.setBounds(633, 77, 79, 14);
		contentPane.add(lblNewLabel_3);

		lblSabor = new JLabel("Sabor:");
		lblSabor.setBounds(55, 109, 46, 14);
		contentPane.add(lblSabor);

		JLabel lblNewLabel_5 = new JLabel("Observacao");
		lblNewLabel_5.setBounds(388, 109, 86, 14);
		contentPane.add(lblNewLabel_5);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(484, 106, 306, 55);
		contentPane.add(scrollPane_1);
		
				taReclama = new JTextArea();
				scrollPane_1.setViewportView(taReclama);

		btnBuscar = new JButton("Buscar:");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reclamacoes = dao.listar(txtBuscar.getText().trim());
					criarTabela();
					// criarTabelaPedido();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(55, 216, 89, 23);
		contentPane.add(btnBuscar);

		
		
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(169, 219, 328, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Reclama\u00E7\u00E3o:");
		lblNewLabel_6.setBounds(55, 147, 83, 14);
		contentPane.add(lblNewLabel_6);

		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel<>(TipoReclama.values()));
		// cbSabor.setModel(new DefaultComboBoxModel<>(pedido.getItens()));
		cbTipo.setBounds(125, 141, 175, 20);
		contentPane.add(cbTipo);

		/*cbSabor = new JComboBox();
		if (cbTipo = ) {
			
		}*/

		
		
		cbSabor = new JComboBox();
		cbSabor.setBounds(97, 106, 117, 20);
		contentPane.add(cbSabor);
		 cbSabor.setVisible(false);
	        lblSabor.setVisible(false);
		
		Relatório = new JButton("Gerar Relat\u00F3rio");
		Relatório.setVisible(false);;
		Relatório.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeraRela();
			}
		});
		Relatório.setBounds(458, 446, 117, 36);
		contentPane.add(Relatório);
		

		try {
			reclamacoes = dao.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar as reclamações", "erro",
					JOptionPane.ERROR_MESSAGE);
		}
// inicializa janela no centro da tela
		setLocationRelativeTo(null);

		if (numero.length > 0) {
			txtPedido.setText(numero[0]);
			txtPedido.requestFocus();
			// troca a variavel de controle para verdadeira
			insertUnico = true;
		}
	}

	private void limpar() {
		txtMoto.setText(null);
		txtDia.setText(null);
		txtPedido.setText(null);
		cbSabor.setSelectedIndex(-1);
		cbTipo.setSelectedIndex(-1);
		txtData.setText(null);
		taReclama.setText(null);
		reclamacao = null;
	}
	private void popular() {
		txtPedido.setText(reclamacao.getPedido().getNumero() + "");
		//cbSabor.setSelectedItem(reclamacao.getPedido().getSabor());
		// cbSabor.setText(reclamacao.getProduto().getNome());
		txtData.setText(reclamacao.getData() + "");
		taReclama.setText(reclamacao.getReclamacao());
		Calendar c = Calendar.getInstance();
		DiaDaSemana dia = DiaDaSemana.values()[c.get(Calendar.DAY_OF_WEEK)];
		Calendar data = Calendar.getInstance();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		txtData.setText(formatador.format(data.getTime()));
		txtDia.setText("" +dia);
		lblId.setText("ID: " + reclamacao.getId());
		txtMoto.setText(reclamacao.getFunc().getNome());
		// lblPontos.setText("Pontos: "+ cliente.getPontos()); mixme**************
	}

	private void criarTabela() {
		ReclamaTableModel model = new ReclamaTableModel(reclamacoes);
		tbReclamacao.setModel(model);
		tbReclamacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReclamacao.getColumnModel().getColumn(0).setPreferredWidth(280);
		tbReclamacao.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbReclamacao.getSelectedRow() >= 0) {
					reclamacao = reclamacoes.get(tbReclamacao.getSelectedRow());
					popular();
				}
			}
		});
	}

	public void carregaSabores() throws SQLException {
		cbSabor.removeAllItems();
		DefaultComboBoxModel<ItemPedido> model = new DefaultComboBoxModel<ItemPedido>();
		for (ItemPedido i : pedido.getItens()) {
			model.addElement(i);
		}
		cbSabor.setModel(model);
		cbSabor.setSelectedIndex(-1);
	}
	
	
	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}
	public void GeraRela() {
		try {
			conexao = (Connection) connectionFactory.getConnection();
			Map parametros = new HashMap();
			JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("/RelReclamacoes.jasper").getFile(), parametros, conexao);
			JasperViewer jw = new JasperViewer(jp);
			jw.setVisible(true);
			//JasperExportManager.exportReportToPdfFile(jp,"/RelReclamacoes.xml");
			FileWriter arq;
            try {
            	  arq = new FileWriter(System.getProperty("user.home") + "/Documents/PIZZARIA_RECLAMACOES.txt", true);
              //  arq = new FileWriter(new File(System.getProperty("user.home") + "/Documents/PIZZARIA_RECLAMACOES.txt"));
                arq.write(lblId.getText() + "," +txtData.getText()+";"+txtDia.getText()+";"+txtPedido.getText() + ","+ cbTipo.getSelectedItem()+";"+cbSabor.getSelectedItem()+";"+txtMoto.getText()+"\n");
                arq.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
