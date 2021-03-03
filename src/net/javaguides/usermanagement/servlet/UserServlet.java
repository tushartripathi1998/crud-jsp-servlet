package net.javaguides.usermanagement.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.model.User;
import net.javaguides.usermanagement.userdao.Dao;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private Dao userDao;

    public void init() {
        userDao = new Dao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	List<User> listUser = userDao.getUsersList();
    	RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
    	request.setAttribute("listUser", listUser);
    	rd.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
    	request.setAttribute("user", null);
    	rd.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
    	int userId = Integer.parseInt(request.getParameter("id"));
    	User user = userDao.getUser(userId);
    	RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
    	request.setAttribute("user", user);
    	rd.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	User user = new User();
    	user.setName(request.getParameter("name"));
    	user.setEmail(request.getParameter("email"));
    	user.setCountry(request.getParameter("country"));
    	userDao.saveData(user);
    	response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	User user = new User();
    	user.setId(Integer.parseInt(request.getParameter("id")));
    	user.setName(request.getParameter("name"));
    	user.setEmail(request.getParameter("email"));
    	user.setCountry(request.getParameter("country"));
    	userDao.updateData(user);
    	response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	int userId = Integer.parseInt(request.getParameter("id"));
    	userDao.delete(userId);
    	response.sendRedirect("list");
    }
}
