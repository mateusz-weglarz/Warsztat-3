package pl.coderslab.users;

import pl.coderslab.Dao.UserDao;
import pl.coderslab.utils.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("user", userDao.readId(id));
        getServletContext().getRequestDispatcher("/user/userEdit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.readId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        userDao.update(user);
        response.sendRedirect("/user/list");
    }
}
