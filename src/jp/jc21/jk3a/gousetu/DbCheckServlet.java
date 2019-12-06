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
@WebServlet("/DBCheck")
public class DbCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DbCheckServlet() {
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

		{
			boolean isGakkaCreated = Gakka.isCreated();
			out.append("<H3>GAKKA�e�[�u��:</H3> ").append(isGakkaCreated + "<br>");
			if (isGakkaCreated == true) {
				isGakkaCreated = Gakka.isStored();
				out.append("<H3>GAKKA�f�[�^:</H3> ").append(isGakkaCreated + "<br>");
			}
			if (isGakkaCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}

		{
			boolean isGakuseiCreated = Gakusei.isCreated();
			out.append("<h3>GAKUSEI�e�[�u��:</h3> ").append(isGakuseiCreated + "<br>");
			if (isGakuseiCreated == true) {
				isGakuseiCreated = Gakusei.isStored();
				out.append("<H3>GAKUSEI�f�[�^:</H3> ").append(isGakuseiCreated + "<br>");
			}
			if (isGakuseiCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}

		{
			boolean isEventCreated = Event.isCreated();
			out.append("<h3>Event�e�[�u��:</h3> ").append(isEventCreated + "<br>");
			if (isEventCreated == true) {
				isEventCreated = Event.isStored();
				out.append("<H3>Event�f�[�^:</H3> ").append(isEventCreated + "<br>");
			}
			if (isEventCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}

		{
			boolean isCompanyCreated = Company.isCreated();
			out.append("<h3>Company�e�[�u��:</h3> ").append(isCompanyCreated + "<br>");
			if (isCompanyCreated == true) {
				isCompanyCreated = Company.isStored();
				out.append("<H3>Company�f�[�^:</H3> ").append(isCompanyCreated + "<br>");
			}
			if (isCompanyCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}
		{
			boolean isEntryCreated = Entry.isCreated();
			out.append("<h3>Entry�e�[�u��:</h3> ").append(isEntryCreated + "<br>");
			if (isEntryCreated == true) {
				isEntryCreated = Company.isStored();
				out.append("<H3>Entry�f�[�^:</H3> ").append(isEntryCreated + "<br>");
			}
			if (isEntryCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}
		{
			boolean isEntryCompanyCreated = Entry.isCreated();
			out.append("<h3>EntryCompany�e�[�u��:</h3> ").append(isEntryCompanyCreated + "<br>");
			if (isEntryCompanyCreated == true) {
				isEntryCompanyCreated = Company.isStored();
				out.append("<H3>EntryCompany�f�[�^:</H3> ").append(isEntryCompanyCreated + "<br>");
			}
			if (isEntryCompanyCreated == false) {
				out.append("<p><a href='./DbInit'>DB������</a></p> ");
			}
			out.flush();
		}

	}

}
