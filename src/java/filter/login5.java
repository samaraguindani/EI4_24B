package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDao;

@WebFilter(filterName = "login5", urlPatterns = {"/ex5/tela5.jsp"})
public class login5 implements Filter {

    private FilterConfig filterConfig = null;

    public login5() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // Se o usuário não está autenticado, verificar as credenciais
            String username = request.getParameter("user");
            String password = request.getParameter("pass");

            if (UserDao.verificarCredenciais(username, password)) {
                // Se as credenciais são válidas, criar uma sessão e redirecionar para a página desejada
                session = request.getSession(true);
                session.setAttribute("user", username);
                chain.doFilter(req, res);
            } else {
                // Se as credenciais são inválidas, redirecionar para a página de login
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ex5/index.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Se o usuário já está autenticado, continuar com a requisição normalmente
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
