package model;

public class JavaBeans {
	private Integer idcontacto;
	private String nome;
	private String fone;
	private String email;
	public JavaBeans() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "JavaBeans [idcontacto=" + idcontacto + ", "
				+ "nome=" + nome + ", fone=" + fone + ", email=" + email + "]\n";
	}



	public JavaBeans(String nome, String fone, String email) {
		super();
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public JavaBeans(Integer idcontacto, String nome, String fone, String email) {
		super();
		this.idcontacto = idcontacto;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	public Integer getIdcontacto() {
		return idcontacto;
	}
	public void setIdcontacto(Integer idcontacto) {
		this.idcontacto = idcontacto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
