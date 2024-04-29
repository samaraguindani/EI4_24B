<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Seu Perfil</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar bg-body-success" style="background-color: #ebffe9">
        <div class="container-fluid">
          <a class="text-dark" href="tela5.jsp">Pagina Inicial</a>
        </div>
    </nav>
    
    <div class="container mt-4">
        <h1>Seu Perfil</h1>
        <% 
            String user = (String) session.getAttribute("user");
            out.println("<p>Nome de usuário: " + user + "</p>");

            Cookie my_cookie = null;
            Cookie[] my_cookies = null;
            my_cookies = request.getCookies();

            out.println("<h2>Cookies: Name and Value</h2>");
          
          for (int i = 0; i < my_cookies.length; i++) {
             my_cookie = my_cookies[i];
                out.println("<p>Visitado Recentemente: " + my_cookie.getName( ) + ",  " +  my_cookie.getValue( ) + "</p>");
               
            }
        %>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
