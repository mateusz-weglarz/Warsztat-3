package pl.coderslab.users;

import pl.coderslab.Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("user", userDao.readId(id));
        getServletContext().getRequestDispatcher("/user/userDelete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();
        String aprove = request.getParameter("aprove");
        if(aprove.equals("yes")){
            request.setAttribute("user", userDao.readId(id));
            userDao.deleteId(id);
            getServletContext().getRequestDispatcher("/user/userDeleted.jsp").forward(request, response);

        }else if (aprove.equals("no")){
            response.sendRedirect("/user/list");
        }
    }
}
