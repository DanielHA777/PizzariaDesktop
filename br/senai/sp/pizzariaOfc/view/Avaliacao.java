package br.senai.sp.pizzariaOfc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.pizzariaOfc.dao.DAOAvaliacao;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.avaliacao;
import br.senai.sp.pizzariaOfc.util.ImageUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class Avaliacao extends JFrame {

	private JPanel contentPane;
	private avaliacao ava;
	private List<avaliacao> avas;
	private DAOAvaliacao dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Avaliacao frame = new Avaliacao();
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
	public Avaliacao() {
		dao = new DAOAvaliacao();
		setTitle("Avalia\u00E7\u00E3o do atendimento");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Avalia\u00E7\u00E3o do cliente");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(117, 79, 208, 14);
		contentPane.add(lblNewLabel);

		JButton btn1 = new JButton("");
		btn1.setBorderPainted(false);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ava == null) {
					ava = new avaliacao();
				}
				ava.setNota(1);
				try {
					if (ava.getId() == 0) {
						try {
							dao.inserir(ava);
							avas = dao.listar();
							JOptionPane.showMessageDialog(null, "avaliação de atendimento salva com sucesso");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {

				}
			}
		});
		btn1.setBounds(86, 104, 44, 37);
		ImageIcon IconeLupaaaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn1.setIcon(ImageUtil.redimensiona(IconeLupaaaaa, btn1.getWidth(), btn1.getHeight()));
		contentPane.add(btn1);

		JButton btn2 = new JButton("");
		btn2.setBorderPainted(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ava == null) {
					ava = new avaliacao();
				}
				ava.setNota(2);
				try {
					if (ava.getId() == 0) {
						try {
							dao.inserir(ava);
							avas = dao.listar();
							JOptionPane.showMessageDialog(null, "avaliação de atendimento salva com sucesso");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {

				}
			}
		});
		btn2.setBounds(140, 104, 44, 37);
		ImageIcon IconeLupaaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn2.setIcon(ImageUtil.redimensiona(IconeLupaaaa, btn2.getWidth(), btn2.getHeight()));
		contentPane.add(btn2);

		JButton btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ava == null) {
					ava = new avaliacao();
				}
				ava.setNota(3);
				try {
					if (ava.getId() == 0) {
						try {
							dao.inserir(ava);
							avas = dao.listar();
							JOptionPane.showMessageDialog(null, "avaliação de atendimento salva com sucesso");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {

				}
			}
		});
		btn3.setBounds(194, 104, 44, 37);
		ImageIcon IconeLupaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn3.setIcon(ImageUtil.redimensiona(IconeLupaaa, btn3.getWidth(), btn3.getHeight()));
		contentPane.add(btn3);

		JButton btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ava == null) {
					ava = new avaliacao();
				}
				ava.setNota(4);
				try {
					if (ava.getId() == 0) {
						try {
							dao.inserir(ava);
							avas = dao.listar();
							JOptionPane.showMessageDialog(null, "avaliação de atendimento salva com sucesso");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {

				}
			}
		});
		btn4.setBounds(248, 104, 44, 37);
		ImageIcon IconeLupaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn4.setIcon(ImageUtil.redimensiona(IconeLupaa, btn4.getWidth(), btn4.getHeight()));
		contentPane.add(btn4);

		JButton btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ava == null) {
					ava = new avaliacao();
				}
				ava.setNota(5);
				try {
					if (ava.getId() == 0) {
						try {
							dao.inserir(ava);
							avas = dao.listar();
							JOptionPane.showMessageDialog(null, "avaliação de atendimento salva com sucesso");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {

				}
			}
		});
		btn5.setBounds(302, 104, 44, 37);
		ImageIcon IconeLupaaaaaa = new ImageIcon(getClass().getResource("/estrela.png"));
		btn5.setIcon(ImageUtil.redimensiona(IconeLupaaaaaa, btn5.getWidth(), btn5.getHeight()));
		contentPane.add(btn5);
	}
}
