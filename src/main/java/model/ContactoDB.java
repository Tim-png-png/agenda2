package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoDB {
	public static void inserirContacto(JavaBeans contacto) {
		if(contacto == null) {
			System.out.println("Sem contacto");
			return;
		}
		String sql = "INSERT INTO `dbagenda`.`contacto` "
				+ "(`nome`, `fone`, `email`) VALUES (?, ?, ?)";
		try(Connection con = new DAO().getConnection();
			PreparedStatement ps = con.prepareCall(sql);) {
			ps.setString(1, contacto.getNome());
			ps.setString(2, contacto.getFone());
			ps.setString(3, contacto.getEmail());
			ps.executeUpdate();	
			System.out.println("ENTROU");
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("NAO ENTROU no insert");
	}
	
	public static List<JavaBeans> listarPorNome(){
		String sql = "SELECT * FROM contacto ORDER BY nome";
		List<JavaBeans> contactoList = new ArrayList<>();
		try(Connection connection = new DAO().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();){
			System.out.println("Entrou no Listar");
			while(rs.next()) {			
				contactoList.add(new JavaBeans(rs.getInt("idcontacto"),rs.getString("nome"),
						rs.getString("fone"), rs.getString("email")));
				System.out.println(contactoList);
			}
			System.out.println(contactoList);
			System.out.println("Depois do While");
			return contactoList;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;	
	}
	
	public static void selecionarContacto(JavaBeans contacto) {
		String sql = "SELECT * FROM contacto WHERE idcontacto = ?";
		try(Connection connection = new DAO().getConnection();
			PreparedStatement ps = connection.prepareCall(sql);) {
			ps.setInt(1, contacto.getIdcontacto());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				new JavaBeans(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4));
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static void updateContacto(JavaBeans contacto ) {
		String sql = "UPDATE `dbagenda`.`contacto` SET `nome` = ?, `fone`= ?, `email`= ?"
				+ "WHERE (`idcontacto` = ?)";
		try(Connection connection = new DAO().getConnection();
				PreparedStatement ps = connection.prepareCall(sql);){
			ps.setString(1, contacto.getNome());
			ps.setString(2, contacto.getFone());
			ps.setString(3, contacto.getEmail());
			ps.setInt(4, contacto.getIdcontacto());
			ps.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		}
	
	public void deletarContacto(JavaBeans num) {
		String sql = "DELETE FROM contacto WHERE idcontacto = ?";
		try(Connection connection = new DAO().getConnection();
				PreparedStatement ps = connection.prepareCall(sql);){
			ps.setInt(1, num.getIdcontacto());
			ps.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static List<JavaBeans> listarPorId(JavaBeans id){
		String sql = "SELECT * FROM dbagenda.contacto WHERE idcontacto = ?";
		List<JavaBeans> contactoList = new ArrayList<>();
		try(Connection connection = new DAO().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);){
			System.out.println("Entrou no Listar");
			ps.setInt(1, id.getIdcontacto());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {		
				
				contactoList.add(new JavaBeans(rs.getInt("idcontacto"),rs.getString("nome"),
						rs.getString("fone"), rs.getString("email")));
			}
			System.out.println(contactoList);
			System.out.println("Depois do While");
			rs.close();
			return contactoList;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;	
	}
}
