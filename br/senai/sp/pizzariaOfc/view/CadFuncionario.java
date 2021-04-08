package br.senai.sp.pizzariaOfc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.senai.sp.pizzariaOfc.dao.DAOFuncionario;
import br.senai.sp.pizzariaOfc.modelo.Cargo;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.tableModel.FuncionarioTableModel;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;


public class CadFuncionario extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfCpf;
	private JLabel lbNome;
	private JTextField tfNome;
	private JLabel lblCargo;
	private JComboBox cbCargo;
	private JLabel lblSenha;
	private JPasswordField tfSenha;
	private JTable tbFuncionarios;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private DAOFuncionario dao;
	private FuncionarioTableModel tableModel;


	/**
	 * Create the frame.
	 */
	public CadFuncionario() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				limpar();
				try {
					funcionarios = dao.listar();
					criarTabela();
					tfCpf.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PSMP\\Downloads\\cli.jpg"));
		dao = new DAOFuncionario();

		setResizable(false);
		setTitle("Cadastro de Funcion\u00E1rio");		
		setBounds(100, 100, 918, 512);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbCpf = new JLabel("Cpf:");
		lbCpf.setForeground(Color.BLACK);
		lbCpf.setFont(new Font("Candara", Font.BOLD, 18));
		lbCpf.setBounds(10, 14, 91, 25);
		contentPane.add(lbCpf);

		tfCpf = new JTextField();
		tfCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				// verifica se NÃO é um dígito (caractere numérico)
				if (!Character.isDigit(tecla.getKeyChar())) {
					// bloqueia o caractere
					tecla.consume();
				}
				// verifica se já tem 11 caracteres na caixa de texto
				if (tfCpf.getText().length() == 11) {
					tecla.consume();
				}
			}
		});
		tfCpf.setToolTipText("Informe o telefone");
		tfCpf.setHorizontalAlignment(SwingConstants.CENTER);
		tfCpf.setFont(new Font("Candara Light", Font.PLAIN, 16));
		tfCpf.setBounds(111, 11, 150, 31);
		contentPane.add(tfCpf);

		lbNome = new JLabel("Nome:");
		lbNome.setForeground(Color.BLACK);
		lbNome.setFont(new Font("Candara", Font.BOLD, 18));
		lbNome.setBounds(10, 56, 91, 25);
		contentPane.add(lbNome);

		tfNome = new JTextField();
		tfNome.setToolTipText("Informe o telefone");
		tfNome.setHorizontalAlignment(SwingConstants.LEFT);
		tfNome.setFont(new Font("Candara Light", Font.PLAIN, 16));
		tfNome.setBounds(111, 53, 313, 31);
		contentPane.add(tfNome);

		lblCargo = new JLabel("Cargo:");
		lblCargo.setForeground(Color.BLACK);
		lblCargo.setFont(new Font("Candara", Font.BOLD, 18));
		lblCargo.setBounds(10, 104, 91, 25);
		contentPane.add(lblCargo);

		cbCargo = new JComboBox();
		cbCargo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbCargo.setModel(new DefaultComboBoxModel(Cargo.values()));
		cbCargo.setFont(new Font("Candara Light", Font.PLAIN, 16));
		cbCargo.setBounds(111, 100, 150, 31);
		contentPane.add(cbCargo);

		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Candara", Font.BOLD, 18));
		lblSenha.setBounds(458, 59, 91, 25);
		contentPane.add(lblSenha);

		tfSenha = new JPasswordField();
		tfSenha.setFont(new Font("Candara Light", Font.PLAIN, 16));
		tfSenha.setBounds(521, 53, 150, 31);
		contentPane.add(tfSenha);

		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfCpf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o cpf do funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
					tfCpf.requestFocus();
				} else if (tfNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o nome do funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
					tfNome.requestFocus();
				} else if (cbCargo.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "Informe o cargo do funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
					cbCargo.requestFocus();
				} else if (tfSenha.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Informe a senha do funcionário", "Erro",
							JOptionPane.ERROR_MESSAGE);
					tfSenha.requestFocus();
				} else {
					try {
						if (funcionario == null) {
							funcionario = new Funcionario();
							funcionario.setCpf(tfCpf.getText());
							funcionario.setNome(tfNome.getText());
							funcionario.setCargo((Cargo) cbCargo.getSelectedItem());
							funcionario.setSenha(new String(tfSenha.getPassword()));
							dao.inserir(funcionario);
						} else {
							funcionario.setNome(tfNome.getText());
							funcionario.setCargo((Cargo) cbCargo.getSelectedItem());
							funcionario.setSenha(new String(tfSenha.getPassword()));
							dao.atualizar(funcionario);
						}
						funcionarios = dao.listar();
						criarTabela();
						limpar();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}

		});
		btnSalvar.setMnemonic('S');
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(166, 402, 125, 46);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se existe um cliente selecionado
				if(funcionario == null) {
					// exibe msg pedindo para selecionar o funcionario
					JOptionPane.showMessageDialog
						(null, "Selecione um funcionário para excluir","Selecione",JOptionPane.WARNING_MESSAGE);
				}else {
					// caso exista um funcionario selecionado...
					// emite um beep
					java.awt.Toolkit.getDefaultToolkit().beep();
					// exibe uma caixa de confirmação e guarda o botão clicado na variável botao
					int botao = JOptionPane.showConfirmDialog
							(null, "Deseja excluir o funcionario "+funcionario.getNome()+"?","Confirmar exclusão",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					// verifica se a variável é igual a 0 (ou seja, botão Yes pressionado)
					if(botao == 0) {
						try {
							// chama o método excluir da DAO
							dao.excluir(funcionario);
							// recarrega a lista de clientes
							funcionarios = dao.listar();
							// cria a tabela novamente
							criarTabela();
							// limpa o formulário 
							limpar();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog
								(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnExcluir.setMnemonic('E');
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(370, 402, 125, 46);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setMnemonic('L');
		btnLimpar.setForeground(Color.BLACK);
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(576, 402, 125, 46);
		contentPane.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 164, 815, 227);
		contentPane.add(scrollPane);
		
		tbFuncionarios = new JTable();
		tbFuncionarios.setFont(new Font("Arial Black", Font.PLAIN, 12));
		tbFuncionarios.setRequestFocusEnabled(false);
		scrollPane.setViewportView(tbFuncionarios);
		
		try {
			funcionarios = dao.listar();
			criarTabela();	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void limpar() {
		funcionario = null;
		tfNome.setText(null);
		tfCpf.setText(null);
		cbCargo.setSelectedIndex(-1);
		tfSenha.setText(null);
		tfCpf.setEnabled(true);
	}

	private void criarTabela() {
		tableModel = new FuncionarioTableModel(funcionarios);
		tbFuncionarios.setModel(tableModel);
		tbFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(220);
		tbFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbFuncionarios.getSelectionModel().addListSelectionListener(e -> {
			if (tbFuncionarios.getSelectedRow() >= 0) {
				funcionario = funcionarios.get(tbFuncionarios.getSelectedRow());
				popular();
			}
		});
	}

	private void popular() {
		tfCpf.setEnabled(false);
		tfNome.setText(funcionario.getNome());
		tfCpf.setText(funcionario.getCpf());
		cbCargo.setSelectedItem(funcionario.getCargo());
	}

}
