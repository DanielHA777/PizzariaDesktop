package br.senai.sp.pizzariaOfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import br.senai.sp.pizzariaOfc.util.PasswordUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import br.senai.sp.pizzariaOfc.modelo.Cargo;
import br.senai.sp.pizzariaOfc.modelo.Cliente;
import br.senai.sp.pizzariaOfc.modelo.FormaPgto;
import br.senai.sp.pizzariaOfc.modelo.Funcionario;
import br.senai.sp.pizzariaOfc.modelo.ItemPedido;
import br.senai.sp.pizzariaOfc.modelo.Pedido;
import br.senai.sp.pizzariaOfc.modelo.Produto;
import br.senai.sp.pizzariaOfc.modelo.Reclamacao;
import br.senai.sp.pizzariaOfc.modelo.TipoReclama;

public class DAOReclamacao implements InterfaceCrud<Reclamacao> {
	private Connection conexao;

	public DAOReclamacao() {
		this.conexao = connectionFactory.getConnection();
	}

	@Override
	public void inserir(Reclamacao objeto) throws SQLException, Exception {
		String sql = "insert into reclamacao(pedido_numero, reclamacao, sabor, motoboy, tipoReclama) values(?, ?, ?, ?, ?)";  
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
	    comando.setInt(1, objeto.getPedido().getNumero());
	    comando.setString(2, objeto.getReclamacao());
	    comando.setString(3, objeto.getPedido().getItens().toString());
	    comando.setString(4, objeto.getPedido().getMotoboy());
	    comando.setInt(5, objeto.getTipoRecla().ordinal());
		comando.execute();
		comando.close();
		
	}

	@Override
	public void atualizar(Reclamacao objeto) throws SQLException {
		String sql = "update reclamacao set Re_numero = ? ,reclamacao = ?, sabor = ?, motoboy = ?, tipoReclama = ?, where id = ?"; 
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		 comando.setInt(1, objeto.getPedido().getNumero());
		   comando.setString(2, objeto.getReclamacao());
		   comando.setString(3, objeto.getProduto().getNome());
		    comando.setString(4, objeto.getFunc().getNome());
		    comando.setInt(8, objeto.getTipoRecla().ordinal());
		   
		comando.setInt(5, objeto.getId());
		comando.execute();
		comando.close();
		
	}

	@Override
	public void excluir(Reclamacao objeto) throws SQLException {
		String sql = "delete from reclamacao where id = ?";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getId());
		comando.execute();
		comando.close();
	}

	@Override
	public List<Reclamacao> listar() throws SQLException {
		List<Reclamacao> lista = new ArrayList<>();
		String sql = "select * from reclamacao order by data asc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Reclamacao f = new Reclamacao();
			Calendar dataPed = Calendar.getInstance();
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); 
			f.setData(dataPed);
			 f.setTipoRecla(TipoReclama.values()[rs.getInt("tipoReclama")]);
			f.setReclamacao(rs.getString("reclamacao"));
			f.setId(rs.getInt("id"));
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("pedido_numero"));
			f.setPedido(p);
			Funcionario ff  = new Funcionario();
			ff.setNome(rs.getString("motoboy"));
			f.setFunc(ff);
			Produto pp = new Produto();
			pp.setNome(rs.getString("sabor"));
			f.setProduto(pp);
			lista.add(f);
		}
		rs.close();
		comando.close();
		return lista;
	}
	public List<Reclamacao> listar(String parametro) throws SQLException { 
		List<Reclamacao> lista = new ArrayList<>(); 
		String sql = "select * from reclamacao where id like ?  order by data asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1,"%" +parametro+"%");
		ResultSet rs = comando.executeQuery(); 
		while(rs.next()) {
			Reclamacao c = new Reclamacao();
			c.setId(rs.getInt("id"));
			Calendar dataPed = Calendar.getInstance();
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); 
			c.setData(dataPed);
			 c.setTipoRecla(TipoReclama.values()[rs.getInt("TipoReclama")]);
			c.setReclamacao(rs.getString("reclamacao"));
			Pedido p = new Pedido();
			p.setNumero(rs.getInt("pedido_numero"));
			c.setPedido(p);
			Funcionario ff  = new Funcionario();
			ff.setNome(rs.getString("motoboy"));
			c.setFunc(ff);
			Produto pp = new Produto();
			pp.setNome(rs.getString("sabor"));
			c.setProduto(pp);
			lista.add(c);
		}
		rs.close();
		
		comando.close();
		
		return lista;
	}

	@Override
	public Funcionario selecionar(String usuario, String senha) throws SQLException {
		
		return null;
	}
	
	public Reclamacao buscaNumero(String numero) throws SQLException { 	
		List<Reclamacao> reclamacoes = new ArrayList<>();
		Reclamacao r = null; 
		String sql = "select * from reclamacao  where pedido_numero = ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1,numero);			
		ResultSet rs = comando.executeQuery(); 
		if(rs.next()) {
			 r = new Reclamacao();
			 Calendar dataPed = Calendar.getInstance();
				dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); 
				r.setData(dataPed);
				 r.setTipoRecla(TipoReclama.values()[rs.getInt("tipoReclama")]);
				r.setReclamacao(rs.getString("reclamacao"));
				Funcionario f = null;
				f= new Funcionario();
				f.setNome(rs.getString("motoboy"));
				r.setFunc(f);
				Produto p = null;
				p = new Produto();
				p.setNome(rs.getString("sabor"));
				r.setProduto(p);
		}
		rs.close();
		comando.close();
		return r;
	}
	
	public Pedido procura(String numero) throws SQLException {
		List<Pedido> pedidos = new ArrayList<>();
		Pedido p = null;
		String sql = "select * from  pedido where numero = ?";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, numero);
		ResultSet rs = comando.executeQuery();
		if (rs.next()) {
		 p = new Pedido();
			p.setNumero(rs.getInt("numero"));
			Calendar dataPed = Calendar.getInstance();
			dataPed.setTimeInMillis(rs.getTimestamp("data").getTime()); 
			p.setData(dataPed);
			p.setEndEntrega(rs.getString("end_entrega"));
			p.setRetirada(rs.getBoolean("retirada"));
			p.setTxentrega(rs.getDouble("tx_entrega"));
			p.setTroco(rs.getDouble("troco"));
			p.setvTotal(rs.getDouble("vtotal"));
			p.setObservacao(rs.getString("observacao"));
			p.setFormaPgto(FormaPgto.values()[rs.getInt("tipoReclama")]);
			Funcionario f = new Funcionario();
			f.setNome(rs.getString("motoboy"));
			p.setFuncionario(f);
			Produto pp = new Produto();
			pp.setNome(rs.getString("sabor"));
			p.setProduto(pp);
		}
		rs.close();
		comando.close();
		return p;
	}
	public List<Reclamacao> geraRel() {
		try {
			net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport("/view/RelReclamacoes.jasper", null, conexao);
			JasperViewer.viewReport(print,false);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2);
		}
		return null;
	}
	
}