<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="contactoList" class="model.ContactoDB" 
type="model.ContactoDB" scope="page"/>
<%@ page import="model.ContactoDB" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de contactos</title>
<link rel="icon" href="imagens/telefone.jpg">
<link rel="stylesheet" href="style.css">
</head>
<body>

	<h1>Agenda de contacto</h1>
	<a href="novo.html" class="botao1">Novo contacto</a>
	<a href="report" class ="botao2" target="_blank">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < ContactoDB.listarPorNome().size(); i++) { %>
				<tr>
					<td><%= ContactoDB.listarPorNome().get(i).getIdcontacto() %></td>
					<td><%= ContactoDB.listarPorNome().get(i).getNome() %></td>
					<td><%= ContactoDB.listarPorNome().get(i).getFone() %></td>
					<td><%= ContactoDB.listarPorNome().get(i).getEmail() %></td>
					<td><a href="select?idcontacto=
					<%=ContactoDB.listarPorNome().get(i).getIdcontacto()  
					%>" class="botao1">Editar</a>
					<a href ="javascript: confirmar(<%=
							ContactoDB.listarPorNome().get(i).getIdcontacto()%>)">
					Excluir</a></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>