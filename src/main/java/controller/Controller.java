package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.ContactoDB;
import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert",
		"/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contacto = new JavaBeans();
	ContactoDB contactoDB = new ContactoDB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contactos(request, response);

		} else if (action.equals("/insert")) {
			novoContacto(request, response);
		} else if (action.equals("/select")) {
			listarContactos(request, response);
		} else if (action.equals("/update")) {
			editarContacto(request, response);
		} else if (action.equals("/delete")) {
			deletarContacto(request, response);
		}else if (action.equals("/report")) {
			relatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("agenda.jsp");
		dispatcher.forward(request, response);
	}

	protected void novoContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");

		JavaBeans contacto = new JavaBeans(nome, fone, email);
		ContactoDB.inserirContacto(contacto);
		response.sendRedirect("main");
	}

	protected void listarContactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcontacto = request.getParameter("idcontacto");

		contacto.setIdcontacto(Integer.parseInt(idcontacto));
		ContactoDB.listarPorNome();
		ContactoDB.selecionarContacto(contacto);
		request.setAttribute("idcontacto", contacto.getIdcontacto());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("fone", contacto.getFone());
		request.setAttribute("email", contacto.getEmail());
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp?idcontacto="+idcontacto);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("idcontacto"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		contacto.setNome(request.getParameter("nome"));	
		contacto.setFone(request.getParameter("fone"));	
		contacto.setEmail(request.getParameter("email"));	
		
		ContactoDB.updateContacto(contacto);
		response.sendRedirect("main");
	}

	protected void deletarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idcontacto = Integer.parseInt(request.getParameter("idcontacto"));

		contacto.setIdcontacto(idcontacto);
		contactoDB.deletarContacto(contacto);
		response.sendRedirect("main");
	}
	protected void relatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename="
			+"contactos.pdf");
			
			PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			document.add(new Paragraph("Lista de contactos: "));
			document.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			
			List<JavaBeans> contactoList = ContactoDB.listarPorNome();	
			for(int i = 0; i < contactoList.size(); i++) {
				tabela.addCell(contactoList.get(i).getNome());
				tabela.addCell(contactoList.get(i).getFone());
				tabela.addCell(contactoList.get(i).getEmail());
			}
			document.add(tabela);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			document.close();
		}
	}
}
