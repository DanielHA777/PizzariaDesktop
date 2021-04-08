package br.senai.sp.pizzariaOfc.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import br.senai.sp.pizzariaOfc.modelo.Cargo;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.Pedido;

import java.sql.Connection;

public class DAOFuncionario implements InterfaceCrud<Funcionario> {
	// objeto para a conexão
	private Connection conexao;

	public DAOFuncionario() {
		// obtém a conexão da Fábrica de Conexões
		conexao = connectionFactory.getConnection();
	}

	@Override
	public void inserir(Funcionario objeto) throws SQLException {
		// string com a instrução SQL
		String sql = "insert into funcionario(cpf, nome, cargo, senha) values(?,?,?,?)";
		// cria um comando
		PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui as ? pelos valores do objeto a ser inserido
		comando.setString(1, objeto.getCpf());
		comando.setString(2, objeto.getNome());
		comando.setInt(3, objeto.getCargo().ordinal());
		comando.setString(4, objeto.getSenha());

		// executa o comando
		comando.execute();
		// encerra o comando
		comando.close();
	}

	@Override
	public void atualizar(Funcionario objeto) throws SQLException {
		// String sql com a instrução
		String sql = "update funcionario set nome = ?, cargo = ?, senha = ? where cpf = ?";
		// cria o comando
		PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui as ? pelos valores do objeto
		comando.setString(1, objeto.getNome());
		comando.setInt(2, objeto.getCargo().ordinal());
		comando.setString(3, objeto.getSenha());
		comando.setString(4, objeto.getCpf());
		// executa o comando
		comando.execute();
		// fecha o comando
		comando.close();
	}

	public void excluir(Funcionario objeto) throws SQLException {
		// String sql com a instrução
		String sql = "delete from funcionario where cpf = ?";
		// cria o comando
		PreparedStatement comando = conexao.prepareStatement(sql);
		// subtstitui a ?
		comando.setString(1, objeto.getCpf());
		// executa o comando
		comando.execute();
		// fecha o comando
		comando.close();

	}

	@Override
	public List<Funcionario> listar() throws SQLException {
		// cria a lista de clientes para retornar
		List<Funcionario> lista = new ArrayList<>();
		// string com a instrução SQL
		String sql = "select * from funcionario order by nome asc";
		// cria o comando
		PreparedStatement comando = conexao.prepareStatement(sql);
		// cria o ResultSet para percorrer os registros do BD
		ResultSet rs = comando.executeQuery();
		// enquanto houver linhas
		while (rs.next()) {
			// cria um objeto funcionario
			Funcionario f = new Funcionario();
			// popula o objeto funcionario
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.setCargo(Cargo.values()[rs.getInt("cargo")]);
			// adicionar o cliente à lista
			lista.add(f);
		}
		// fechar o ResultSet
		rs.close();
		// fechar o Statement
		comando.close();
		// retorna a lista de clientes
		return lista;
	}

	public List<Funcionario> listar(String parametro) throws SQLException {
		// cria a lista de clientes para retornar
		List<Funcionario> lista = new ArrayList<>();

		// string com a instrução SQL
		String sql = "select * from funcionario where nome like ? or telefone like ? or cep like ? order by nome asc";

		// cria o comando
		PreparedStatement comando = conexao.prepareStatement(sql);
		// substituindo as ?
		comando.setString(1, "%" + parametro + "%");
		comando.setString(2, "%" + parametro + "%");
		comando.setString(3, "%" + parametro + "%");

		// cria o ResultSet para percorrer os registros do BD
		ResultSet rs = comando.executeQuery();
		// enquanto houver linhas
		while (rs.next()) {
			// cria um objeto cliente
			Funcionario f = new Funcionario();
			// popula o objeto cliente
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.setCargo(Cargo.values()[rs.getInt("cargo")]);

			lista.add(f);
		}
		// fechar o ResultSet
		rs.close();
		// fechar o Statement
		comando.close();
		// retorna a lista de clientes
		return lista;
	}

	@Override
	public Funcionario selecionar(String usuario, String senha) throws SQLException {
		String sql = "select * from funcionario where cpf = ? and senha = ?";
		Funcionario f = null;
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, usuario);
		comando.setString(2, senha);
		ResultSet rs = comando.executeQuery();
		if (rs.next()) {
			f = new Funcionario();
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.setSenha(rs.getString("senha"));
			f.setCargo(Cargo.values()[rs.getInt("cargo")]);
		}
		rs.close();
		comando.close();
		return f;
	}

	public Funcionario quemLogou(Object object) throws SQLException {
		String sql = "select * from funcionario where nome = ?";
		Funcionario f = null;
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setNString(1, (String) object);
		ResultSet rs = comando.executeQuery();
		// caso encontre
		if (rs.next()) {
			f = new Funcionario();
			f.setNome(rs.getString("nome"));

		}
		rs.close();
		comando.close();
		return f;
	}
	public Funcionario buscaNome(Object object) throws SQLException { // metodo com mesmo nome mas assinatura difeente	
		// cria obj  de clientes para retornar 
		Funcionario f = null; // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from funcionario order by nome";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui  ? por telefone
		comando.setString(1,(String) object);
				
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		if(rs.next()) {
			//cria obj cliente
			 f = new Funcionario();
			//instancia o  obj cliente
			 f.setNome(rs.getString("nome"));
				f.setCpf(rs.getString("cpf"));
				f.setSenha(rs.getString("senha"));
				f.setCargo(Cargo.values()[rs.getInt("cargo")]);
				
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return f;
	}
	public List<Funcionario> buscaMotoboy(){
		List<Funcionario> lista = new ArrayList<Funcionario>();
		try {
			String sql = "select * from funcionario";
			PreparedStatement comando;
			comando = conexao.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				Funcionario f = new Funcionario();
				f.setNome(rs.getString("nome"));
				lista.add(f);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
	public List<Pedido> buscaMotoboyy(){
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			String sql = "select * from Funcionario where nome";
			PreparedStatement comando;
			comando = conexao.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			
			while(rs.next()) {
				Pedido f = new Pedido();
				f.setMotoboy( rs.getString("motoboy"));
				lista.add(f);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;	
	}
}