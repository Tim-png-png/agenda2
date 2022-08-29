package model;

//import java.util.List;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<JavaBeans> contactoList = DAO.listarPorNome();
		//DAO.testConnection();
		JavaBeans j = new JavaBeans();
		j.setIdcontacto(4);
	ContactoDB.listarPorId(j);
	}

}
