package br.senai.sp.pizzariaOfc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;

public class DAOCliente implements InterfaceCrud<Cliente> {
// objeto para conexao
	private Connection conexao;
	public DAOCliente() {
		// obtém conexao da fabrica de conexao
		conexao = connectionFactory.getConnection();
	}
	
	@Override
	// inserir dados do cliente no bd 
	public void inserir(Cliente objeto) throws SQLException {  // lançando erro pra cima
		String sql = "insert into cliente(nome, endereco, telefone, email, cep) values(?, ?, ?, ?, ?)";  // string com a instrução sql
		// comando para o banco de dados		
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
		comando.setString(1, objeto.getNome());  // 1 primeia ?
		comando.setString(2, objeto.getEndereco());  // 2 segunda ?
		comando.setString(3, objeto.getTelefone());  // ...
		comando.setString(4, objeto.getEmail());
		comando.setString(5, objeto.getCep());
		//executa o comando
		comando.execute();
		// encerra comando
		comando.close();
	}
	
		
	@Override
	public void atualizar(Cliente objeto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update cliente set nome = ?, email = ?, endereco = ?, cep = ? where id = ?";  // string com a instrução sql
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
		// comando para o banco de dados	? por valor do obj	
		
		comando.setString(1, objeto.getNome());  // 1 primeia ?
		comando.setString(2, objeto.getEndereco());  // 2 segunda ?
		//comando.setString(3, objeto.getTelefone());  // ...
		comando.setString(3, objeto.getEmail());
		comando.setString(4, objeto.getCep());
		comando.setInt(5, objeto.getId());
		//executa o comando
		comando.execute();
		// encerra comando
		comando.close();
	}

	@Override
	public void excluir(Cliente objeto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from cliente where id = ?";  // string com a instrução sql
		// comando para o banco de dados		
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
		comando.setInt(1, objeto.getId());  // 1 primeia ?	
		//executa o comando
		comando.execute();
		// encerra comando
		comando.close();		
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		// TODO Auto-generated method stub
		// cria lista de clientes para retornar 
		List<Cliente> lista = new ArrayList<>(); // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from cliente order by nome asc";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		while(rs.next()) {
			//cria obj cliente
			Cliente c = new Cliente();
			//popula o  obj cliente
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEndereco(rs.getString("endereco"));
			c.setEmail(rs.getNString("email"));
			c.setTelefone(rs.getString("telefone"));
			c.setPontos(rs.getInt("pontos"));
			//adcionar o cliente a lista
			lista.add(c);
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return lista;
	}
	
	public List<Cliente> listar(String parametro) throws SQLException { // metodo com mesmo nome mas assinatura difeente	
		// cria lista de clientes para retornar 
		List<Cliente> lista = new ArrayList<>(); // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from cliente where nome like ? or telefone like ? or cep like ? order by nome asc";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui as 3 ?
		comando.setString(1,"%" +parametro+"%");
		comando.setString(2,"%" +parametro+"%");
		comando.setString(3,"%" +parametro+"%");
				
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		while(rs.next()) {
			//cria obj cliente
			Cliente c = new Cliente();
			//popula o  obj cliente
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEndereco(rs.getString("endereco"));
			c.setEmail(rs.getNString("email"));
			c.setTelefone(rs.getString("telefone"));
			c.setPontos(rs.getInt("pontos"));
			//adcionar o cliente a lista
			lista.add(c);
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return lista;
	}
	//busca pelo telefone
	public Cliente buscaTel(String telefone) throws SQLException { // metodo com mesmo nome mas assinatura difeente	
		// cria obj  de clientes para retornar 
		Cliente c = null; // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from cliente where telefone = ?";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui  ? por telefone
		comando.setString(1,telefone);
				
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		if(rs.next()) {
			//cria obj cliente
			 c = new Cliente();
			//instancia o  obj cliente
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setEndereco(rs.getString("endereco"));
			c.setEmail(rs.getNString("email"));
			c.setTelefone(rs.getString("telefone"));
			c.setPontos(rs.getInt("pontos"));		
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return c;
	}
	public void atualizaPontos(int idCliente, int pontos) throws SQLException {
		//string com a instrução
		String sql = "update cliente set pontos  = ? where id = ?";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
	comando.setInt(1, pontos);
	comando.setInt(2, idCliente);
	comando.execute();
	comando.close();
	}

	public Funcionario selecionar(Funcionario objeto) throws SQLException {
		return objeto;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcionario selecionar(String usuario, String senha) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
