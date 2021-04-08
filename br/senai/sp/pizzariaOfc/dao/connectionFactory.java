package br.senai.sp.pizzariaOfc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionFactory {
	private static Connection conexao;
    private static String usuario = "root";
    private static String senha = "root";
   private static String strConexao = "jdbc:mysql://localhost:3306/dbPizzaria?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";  //localhost 3606   ssl estudar  // tutorecconect = true, quando a conexão cai ela volta automatico

   
   public static Connection getConnection() {
		//verifica se a conexão é nula
	   if(conexao == null) {
		   // abre a conexão
		   try {
			conexao = DriverManager.getConnection(strConexao, usuario, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // linha do erro, tratado aqui dentro
			System.out.println("ERRO DE CONEXÃO");
		}
	   }
	   //retorna o objeto conexao
	   return conexao;
	}
  public static void closeConnection() {   // fecha conexao
	  if(conexao != null) {
		  try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRO DE CONEXÃO");
		}
	  }
  }
  /*
  public static void main(String[] args) {
	connectionFactory.getConnection();
	connectionFactory.closeConnection();
	System.out.println("ok");
}*/
}
