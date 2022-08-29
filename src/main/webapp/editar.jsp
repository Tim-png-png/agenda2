<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <jsp:useBean id="contactoList" class="model.ContactoDB" 
type="model.ContactoDB" scope="page"/>
<%@ page import="model.ContactoDB" %>
<%@ page import="model.JavaBeans" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de contactos</title>
<link rel="icon" href="imagens/telefone.jpg">
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<%
		int idInt = Integer.parseInt(request.getParameter("idcontacto"));
		JavaBeans id = new JavaBeans();
		id.setIdcontacto(idInt);
		List<JavaBeans> list = ContactoDB.listarPorId(id);
		%>
	
	<h1>Editar contacto</h1>
	<form name= "frmContacto" action="update" method="get">
	<table>
	<%for(JavaBeans j : list) {%>
		<tr>
		
			<td><input type= "text" name= "idcontacto" id= "caixa3" disabled="disabled"
			value="<%=id.getIdcontacto() %>" /></td>
		</tr>
		<tr >
			<td><input type= "text" name= "nome" required class= "caixa1" 
			autofocus="autofocus" value="<%=j.getNome() %>" /></td>
		</tr>
		<tr>
			<td><input type= "text" name= "fone" required class= "caixa2" 
			value="<%=j.getFone() %>"/></td>
		</tr>
		<tr>
			<td><input type= "text" name= "email" class= "caixa1"
			value="<%=j.getEmail() %>" /></td>
		</tr>
		<%} %>
	</table>
		<input type= "button" value= "Salvar"  class= "botao1" onclick= "validar()"/>
	</form>
	<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>