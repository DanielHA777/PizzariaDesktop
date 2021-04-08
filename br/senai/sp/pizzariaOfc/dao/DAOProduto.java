package br.senai.sp.pizzariaOfc.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;

import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.TipoProduto;
import br.senai.sp.pizzariaOfc.tableModel.ClienteTableModel;
import br.senai.sp.pizzariaOfc.tableModel.ProdutoTableModel;

public class DAOProduto implements InterfaceCrud<Produto> {
private java.sql.Connection conexao;

public DAOProduto() {
	conexao = connectionFactory.getConnection();
}
	@Override
	public void inserir(Produto objeto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into Produto(nome, descricao, tipo,promocao, imagem, preco) values(?,?,?,?,?,?)";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, objeto.getNome());
		comando.setString(2, objeto.getDescricao());
		comando.setInt(3, objeto.getTipo().ordinal()); // ordinal retorna um inteiro refereente a enumeração
		comando.setBoolean(4, objeto.isPromocao());
		comando.setBytes(5, objeto.getImagem());
		comando.setDouble(6, objeto.getPreco());
		comando.execute();
	}

	@Override
	public void atualizar(Produto objeto) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql = "update Produto set nome = ?, descricao = ?, promocao = ?, tipo = ?, imagem = ?, preco = ? where id = ?";  // string com a instrução sql
				java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
				// comando para o banco de dados	? por valor do obj	
				comando.setString(1, objeto.getNome());
				comando.setString(2, objeto.getDescricao()); // ordinal retorna um inteiro refereente a enumeração
				comando.setBoolean(3, objeto.isPromocao());
				comando.setInt(4, objeto.getTipo().ordinal());
				comando.setBytes(5, objeto.getImagem());
				comando.setDouble(6, objeto.getPreco());
				comando.setInt(7, objeto.getId());
				
				//executa o comando
				comando.execute();
				// encerra comando
				comando.close();
	}

	@Override
	public void excluir(Produto objeto) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql = "delete from Produto where id = ?";  // string com a instrução sql
				// comando para o banco de dados		
				java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
				comando.setInt(1, objeto.getId());  // 1 primeia ?
				comando.execute();
				// encerra comando
				comando.close();
	}

	@Override
	public List<Produto> listar() throws SQLException {
		// TODO Auto-generated method stub
		List<Produto> lista = new ArrayList<>(); // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from Produto order by nome asc";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		while(rs.next()) {
			//cria obj cliente
			Produto p = new Produto();
			
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setDescricao(rs.getString("descricao"));
			p.setPromocao(rs.getBoolean("Promocao"));
			p.setTipo(TipoProduto.values()[rs.getInt("tipo")]); // transformar valor inteiro em enumeração, exibe ela na forma de vetor através do values 
			p.setImagem(rs.getBytes("imagem"));
			p.setPreco(rs.getDouble("preco"));
			//popula o  obj cliente
			lista.add(p);
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return lista;
	}
	public List<Produto> listar(String parametro, TipoProduto tipo) throws SQLException { // metodo com mesmo nome mas assinatura difeente	
		// cria lista de clientes para retornar 
		List<Produto> lista = new ArrayList<>(); // passa linha por linha no bd no cliente e retorna
		// Strin gcom a instrução sql
		String sql = "select * from produto where tipo = ? and  nome like ? order by nome asc";
		//cria comando
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		// substitui as 3 ?
		comando.setInt(1, tipo.ordinal());
		comando.setString(2,"%" +parametro+"%");
				
		//cria o resultSet para percorrer os registros do banco
		ResultSet rs = comando.executeQuery(); // query consulta
		//percorrer o result Set, ele começa na linha -1
		//enquanto houver linhas
		while(rs.next()) {
			//cria obj cliente
			Produto p = new Produto();
			//popula o  obj cliente
			
			p.setNome(rs.getString("nome"));
			p.setId(rs.getInt("id"));
			p.setDescricao(rs.getNString("descricao"));
			p.setPreco(rs.getDouble("preco"));
			p.setPromocao(rs.getBoolean("Promocao"));
			p.setTipo(rs.getString("tipo"));
			
			
			lista.add(p);
		}
		//fechar resultSet
		rs.close();
		//fechar o statement
		comando.close();
		//retorna liosta
		return lista;
	}
	public Funcionario selecionar(Funcionario objeto) throws SQLException {
		return objeto;
		
	}
	@Override
	public Funcionario selecionar(String usuario, String senha) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	}
	

