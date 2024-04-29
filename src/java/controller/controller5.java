package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.util.HashMap;

@WebServlet(name = "Controller5", urlPatterns = {"/controller5", "/ex5/login5"})
public class controller5 extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        if (action.equals("/ex5/login5")) {

            String user = (String) request.getParameter("user");
            String pass = (String) request.getParameter("pass");

            if (user.equals("admin") && pass.equals("admin")) {

                HttpSession session = request.getSession(false);
                session.setAttribute("user", user);
                session.setAttribute("pass", pass);

                Cookie my_cookie = null;
                Cookie[] my_cookies = null;
                String page = "";
                
                my_cookies = request.getCookies();

                for (int i = 0; i < my_cookies.length; i++) {
                    my_cookie = my_cookies[i];

                    if (my_cookie.getValue().equals("sim")) {
                        page = my_cookie.getName();
                    }
                }

                if(page.equals("")){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ex5/tela5.jsp");
                    dispatcher.forward(request, response);
                }else{
                    System.out.println("/ex5/cama/"+page+".jsp");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ex5/cama/"+page+".jsp");
                    dispatcher.forward(request, response);
                    
                }
            } else {

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ex5/index.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user or password is wrong.</font>");
                dispatcher.include(request, response);

            }
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
