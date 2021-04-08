package br.senai.sp.pizzariaOfc.dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.pizzariaOfc.modelo.Cargo;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.FormaPgto;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.ItemPedido;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;

public class DAOPedido implements InterfaceCrud<Pedido> {
	private Connection conexao;

	public DAOPedido() {
		this.conexao = connectionFactory.getConnection();
	}

	@Override
	public void inserir(Pedido p) throws Exception {
		// desabilita o commit automatico
		conexao.setAutoCommit(false);
		String sql = "insert into pedido(cliente_id, end_entrega, retirada, tx_entrega, troco, vTotal, observacao, forma_pgto, func_cpf, motoboy) values(?,?,?,?,?,?,?,?,?,?)";
		// cria comando informando q as chaves geradas devem ser retornadas
		PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		comando.setInt(1, p.getCliente().getId());
		comando.setString(2, p.getEndEntrega());
		comando.setBoolean(3, p.isRetirada());
		comando.setDouble(4, p.getTxentrega());
		comando.setDouble(5, p.getTroco());
		comando.setDouble(6, p.getvTotal());
		comando.setString(7, p.getObservacao());
		comando.setInt(8, p.getFormaPgto().ordinal());
		comando.setString(9, p.getFuncionario().getCpf());
		comando.setString(10, p.getMotoboy());
		try {
			// executa o comando
			comando.execute();
			// resultser para obter as chaves (keys geradas
			ResultSet chaves = comando.getGeneratedKeys();
			// verifica se existe chave gerada
			if (chaves.next()) {
				// seta np pedido o nmero gerado pelo banco
				p.setNumero(chaves.getInt(1));
			} else {
				// çança um aexception
				throw new Exception("Erro ao inserir o pedido");
			}
			// string com a instrução para inserir o item do pedido
			String sql2 = "insert into itempedido(produto_id, qtd, total, observacao, pedido_numero, sabor) values(?,?,?,?,?, ?)";
//percorrer os itens do pedido 
			for (ItemPedido i : p.getItens()) {
				// cria um novo com
				PreparedStatement comando2 = conexao.prepareStatement(sql2);
				comando2.setInt(1, i.getProduto().getId());
				comando2.setInt(2, i.getQtd());
				comando2.setDouble(3, i.getTotal());
				comando2.setString(4, i.getObservacao());
				comando2.setInt(5, p.getNumero());
				comando2.setString(6, i.getProduto().getNome());
				comando2.execute();
				comando2.close();
				conexao.commit();
			}
		} catch (Exception e) {
			// desfaz as instruções
			conexao.rollback();
			// exibe o erro no consle
			e.printStackTrace();
			throw e; // joga o erro pra cima
		} finally {
			// habilita novamente o commit automatico
			conexao.setAutoCommit(true);
		}
	}

	@Override
	public void atualizar(Pedido p) throws SQLException {
		String sql = "update pedido set cliente_id = ? end_entrega =? retirada=? tx_entrega=? troco=? vTotal=? observacao=? forma_pgto=? func_cpf= ? motoboy = ? where id = ?"; // string
		// com
		// a
		// instrução
		// sql
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);// preparedStatement obriga q trate o erro
		// comando para o banco de dados ? por valor do obj
		comando.setInt(1, p.getCliente().getId());
		comando.setString(2, p.getEndEntrega());
		comando.setBoolean(3, p.isRetirada());
		comando.setDouble(4, p.getTxentrega());
		comando.setDouble(5, p.getTroco());
		comando.setDouble(6, p.getvTotal());
		comando.setString(7, p.getObservacao());
		comando.setInt(8, p.getFormaPgto().ordinal());
		comando.setString(9, "11111111111");
		comando.setString(10, p.getFuncionario().getNome());
		// comando.setString(11, p.getProduto().getNome());

		// executa o comando
		comando.execute();
		// encerra comando
		comando.close();

	}

	@Override
	public void excluir(Pedido objeto) throws SQLException {
		String sql = "delete from pedido where numero = ?";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getNumero());
		comando.execute();
		comando.close();
	}

	@Override
	public List<Pedido> listar() throws SQLException {
		String sql = "select * from  view_pedidos order by numero desc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		List<Pedido> pedidos = new ArrayList<>();
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			// cria pedido
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			// criar calendar
			Calendar dataPed = Calendar.getInstance();
			// ajusta data no calendar
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); // recebe um long // fixme mes q vem
			// seta calendar no pedido
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("forma_pgto")]);
			p.setMotoboy(rs.getString("motoboy"));
	       // p.setItens(rs.getString("itens"));

			// cria clinte
			Cliente c = new Cliente();
			c.setNome(rs.getString("nome_cliente"));
			// c.setNome(rs.getString("nome"));
			c.setTelefone(rs.getString("telefone"));
			// seta o cliente no pedido
			p.setCliente(c);
			// cria funcionario
			Funcionario f = new Funcionario();
			// popula o funcionario
			f.setNome(rs.getString("nome_func"));
			// seta funcionario no pedido
			p.setFuncionario(f);
			// criar um alista de itens
			List<ItemPedido> itens = new ArrayList<>();
			do {
				// verifica se n é o mesmo pedido da linha anterior
				if (p.getNumero() != rs.getInt("numero")) {
					// volta um a linha no result set
					// rs.first() primeira linha
					// rs.beforeFirst();
					// rs.last() volta pra ultima linha

					rs.previous();
					// encerraa do while...
					break;
				}
				// cria um item
				ItemPedido item = new ItemPedido();
				item.setId(rs.getInt("id"));
				item.setQtd(rs.getInt("qtd"));
				item.setTotal(rs.getDouble("total"));
				item.setObservacao(rs.getString("obs_item"));
				//item.setSabor(rs.getString("sabor"));
				// cria o produto do item
				Produto pr = new Produto();
				pr.setNome(rs.getString("produto"));
				// seta o produto no item
				item.setProduto(pr);
				// add o item a lista
				itens.add(item);
			} while (rs.next());
			// setar iten sdo pedido
			p.setItens(itens);
			// adciona pedido a lista
			pedidos.add(p);
		}
		rs.close();
		comando.close();
		return pedidos;
	}

	public List<Pedido> procurar(String parametro) throws SQLException {
		String sql = "select * from  view_pedidos where nome_cliente like ? or numero like ? order by numero desc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		comando.setString(2, "%" + parametro + "%");
		List<Pedido> pedidos = new ArrayList<>();
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			// cria pedido
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			// criar calendar
			Calendar dataPed = Calendar.getInstance();
			// ajusta data no calendar
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); // recebe um long // fixme mes q vem
			// seta calendar no pedido
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("forma_pgto")]);
			p.setMotoboy(rs.getString("motoboy"));
		//	p.setSabor(rs.getString("sabor"));
			// p.setSabor(rs.getString("sabor"));
			// cria clinte
			Cliente c = new Cliente();
			c.setNome(rs.getString("nome_cliente"));
			// c.setNome(rs.getString("nome"));
			c.setTelefone(rs.getString("telefone"));
			// seta o cliente no pedido
			p.setCliente(c);
			// cria funcionario
			Funcionario f = new Funcionario();
			// popula o funcionario
			f.setNome(rs.getString("nome_func"));
			// seta funcionario no pedido
			p.setFuncionario(f);
			// criar um alista de itens
			List<ItemPedido> itens = new ArrayList<>();
			do {
				// verifica se n é o mesmo pedido da linha anterior
				if (p.getNumero() != rs.getInt("numero")) {
					// volta um a linha no result set
					// rs.first() primeira linha
					// rs.beforeFirst();
					// rs.last() volta pra ultima linha

					rs.previous();
					// encerraa do while...
					break;
				}
				// cria um item
				ItemPedido item = new ItemPedido();
				item.setId(rs.getInt("id"));
				item.setQtd(rs.getInt("qtd"));
				item.setTotal(rs.getDouble("total"));
				item.setObservacao(rs.getString("obs_item"));
				//item.setSabor(rs.getString("sabor"));
				// cria o produto do item
				Produto pr = new Produto();
				pr.setNome(rs.getString("produto"));
				// seta o produto no item
				item.setProduto(pr);
				// add o item a lista
				itens.add(item);
			} while (rs.next());
			// setar iten sdo pedido
			p.setItens(itens);
			// adciona pedido a lista
			pedidos.add(p);
		}
		rs.close();
		comando.close();
		return pedidos;
	}

	public List<Pedido> procura(String parametro) throws SQLException {
		String sql = "select * from  produto where nome like ? order by nome desc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		List<Pedido> pedidos = new ArrayList<>();
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			// cria pedido
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			// criar calendar
			Calendar dataPed = Calendar.getInstance();
			// ajusta data no calendar
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); // recebe um long // fixme mes q vem
			// seta calendar no pedido
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("forma_pgto")]);
			p.setMotoboy(rs.getString("motoboy"));
			//p.setSabor(rs.getString("sabor"));
			// p.setSabor(rs.getString("sabor"));
			// cria clinte
			Cliente c = new Cliente();
			c.setNome(rs.getString("nome_cliente"));
			// c.setNome(rs.getString("nome"));
			c.setTelefone(rs.getString("telefone"));
			// seta o cliente no pedido
			p.setCliente(c);
			// cria funcionario
			Funcionario f = new Funcionario();
			// popula o funcionario
			f.setNome(rs.getString("nome_func"));
			// seta funcionario no pedido
			p.setFuncionario(f);
			// criar um alista de itens
			List<ItemPedido> itens = new ArrayList<>();
			do {
				// verifica se n é o mesmo pedido da linha anterior
				if (p.getNumero() != rs.getInt("numero")) {
					// volta um a linha no result set
					// rs.first() primeira linha
					// rs.beforeFirst();
					// rs.last() volta pra ultima linha

					rs.previous();
					// encerraa do while...
					break;
				}
				// cria um item
				ItemPedido item = new ItemPedido();
				item.setId(rs.getInt("id"));
				item.setQtd(rs.getInt("qtd"));
				item.setTotal(rs.getDouble("total"));
				item.setObservacao(rs.getString("obs_item"));
				//item.setSabor(rs.getString("sabor"));
				// cria o produto do item
				Produto pr = new Produto();
				pr.setNome(rs.getString("produto"));
				// seta o produto no item
				item.setProduto(pr);
				// add o item a lista
				itens.add(item);
			} while (rs.next());
			// setar iten sdo pedido
			p.setItens(itens);
			// adciona pedido a lista
			pedidos.add(p);
		}
		rs.close();
		comando.close();
		return pedidos;
	}

	public Pedido procuraa(String numero) throws SQLException { /// criar outro procura para buscar só o item do pedido
																/// nome do produto
		Pedido p = null;
		String sql = "select * from  view_pedidossss where numero = ?";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, numero);
		ResultSet rs = comando.executeQuery();
		if (rs.next()) {
			// cria pedido
			p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			// criar calendar
			Calendar dataPed = Calendar.getInstance();
			// ajusta data no calendar
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); // recebe um long // fixme mes q vem
			// seta calendar no pedido
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("forma_pgto")]);
			p.setMotoboy(rs.getString("motoboy"));

			// cria clinte
			Cliente c = new Cliente();
			c.setNome(rs.getString("nome_cliente"));
			// c.setNome(rs.getString("nome"));
			c.setTelefone(rs.getString("telefone"));
			c.setEmail(rs.getString("email"));
			c.setPontos(rs.getInt("pontos"));
			// seta o cliente no pedido
			p.setCliente(c);
			// cria funcionario
			Funcionario f = new Funcionario();
			// popula o funcionario
			f.setNome(rs.getString("nome_func"));
			// seta funcionario no pedido
			p.setFuncionario(f);
			// criar um alista de itens
			List<ItemPedido> itens = new ArrayList<>();
			do {
				// cria um item
				ItemPedido item = new ItemPedido();
				item.setId(rs.getInt("id"));
				item.setQtd(rs.getInt("qtd"));
				item.setTotal(rs.getDouble("total"));
				item.setObservacao(rs.getString("obs_item"));
				//item.setSabor(rs.getString("sabor"));
				// cria o produto do item
				Produto pr = new Produto();
				pr.setNome(rs.getString("produto"));
				// seta o produto no item
				item.setProduto(pr);
				// add o item a lista
				itens.add(item);
			} while (rs.next());
			p.setItens(itens);
		}
		rs.close();
		comando.close();
		return p;
	}

	public List<Pedido> listaSabor(String sabor) throws SQLException {
		String sql = "select * from  pedido where sabor = ? order by sabor desc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, sabor);
		List<Pedido> pedidos = new ArrayList<>();
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			// cria pedido
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			// criar calendar
			Calendar dataPed = Calendar.getInstance();
			// ajusta data no calendar
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); // recebe um long // fixme mes q vem
			// seta calendar no pedido
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("forma_pgto")]);
			p.setMotoboy(rs.getString("motoboy"));
			//p.setSabor(rs.getString("sabor").toString());

			pedidos.add(p);
		}
		rs.close();
		comando.close();
		return pedidos;
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
  public List<Funcionario> buscaMotoboy(){
	  List<Funcionario> lista = new ArrayList<Funcionario>();
		try {
			  String sql = "select * from funcionario where cargo = ?";
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, Cargo.MOTOBOY.ordinal());
			ResultSet rs = comando.executeQuery();
			
			if(rs.next()) {
				Funcionario p = new Funcionario();
				p.setNome(rs.getString("nome"));
				lista.add(p);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;  
  }
}
