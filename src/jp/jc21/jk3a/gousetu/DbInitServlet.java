package jp.jc21.jk3a.gousetu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.jc21.jk3a.gousetu.JDBC.*;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/DbInit")
public class DbInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DbInitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		boolean isEntryCompanyCreated = EntryCompany.drop();
		out.append("<H3>EntryCompany�e�[�u��:drop</H3> ").append(isEntryCompanyCreated + "<br>");
		boolean isEntryCreated = Entry.drop();
		out.append("<H3>Entry�e�[�u��:drop</H3> ").append(isEntryCreated + "<br>");
		boolean isGakuseiCreated = Gakusei.drop();
		out.append("<h3>GAKUSEI�e�[�u��:DROP</h3> ").append(isGakuseiCreated + "<br>");
		boolean isGakkaCreated = Gakka.drop();
		out.append("<H3>GAKKA�e�[�u��:drop</H3> ").append(isGakkaCreated + "<br>");
		boolean isCompanyCreated = Company.drop();
		out.append("<H3>Company�e�[�u��:drop</H3> ").append(isCompanyCreated + "<br>");
		boolean isEventCreated = Event.drop();
		out.append("<H3>Event�e�[�u��:drop</H3> ").append(isEventCreated + "<br>");

		isEventCreated = Event.create();
		out.append("<H3>Event�e�[�u��:CREATE</H3> ").append(isGakkaCreated + "<br>");
		isGakkaCreated = Gakka.create();
		isCompanyCreated = Company.create();
		out.append("<H3>Company�e�[�u��:CREATE</H3> ").append(isCompanyCreated + "<br>");
		out.append("<H3>GAKKA�e�[�u��:CREATE</H3> ").append(isGakkaCreated + "<br>");
		isGakuseiCreated = Gakusei.create();
		out.append("<h3>GAKUSEI�e�[�u��:CREATE</h3> ").append(isGakuseiCreated + "<br>");
		isEntryCreated = Entry.create();
		out.append("<H3>Entry�e�[�u��:CREATE</H3> ").append(isEntryCreated + "<br>");
		isEntryCompanyCreated = Entry.create();
		out.append("<H3>EntryCompany�e�[�u��:CREATE</H3> ").append(isEntryCompanyCreated + "<br>");

	}

}
