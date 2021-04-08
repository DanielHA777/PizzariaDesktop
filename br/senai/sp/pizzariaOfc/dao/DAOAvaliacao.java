package br.senai.sp.pizzariaOfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.FormaPgto;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.ItemPedido;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.avaliacao;

public class DAOAvaliacao implements InterfaceCrud<avaliacao> {
	private Connection conexao;

	public DAOAvaliacao() {
		conexao = connectionFactory.getConnection();

	}

	@Override
	public void inserir(avaliacao objeto) throws SQLException, Exception {
		String sql = "insert into avaliacao(notas) values(?)";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getNota());
		// comando.setInt(2, objeto.getCliente().getId());
		comando.execute();
		comando.close();
	}

	@Override
	public void atualizar(avaliacao objeto) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(avaliacao objeto) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<avaliacao> listar() throws SQLException {

		List<avaliacao> lista = new ArrayList<>();
		String sql = "select * from avaliacao order by notas asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			avaliacao c = new avaliacao();
			c.setId(rs.getInt("id"));
			c.setNota(rs.getInt("notas"));
			lista.add(c);
			Cliente cc = new Cliente();
			cc.setId(rs.getInt("clientes_id"));
		}
		rs.close();
		comando.close();
		return lista;
	}

	@Override
	public Funcionario selecionar(String usuario, String senha) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int soma() throws SQLException {
	//	int p = null;
		String sql = "select sum(notas) from avaliacao";
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery();
		if (rs.next()) {
		avaliacao p = new avaliacao();
		//p.setId(rs.getInt("id"));
			p.setNota(rs.getInt("notas"));
			rs.close();
			comando.close();
		return soma() ;
	}
		return soma();
}}
