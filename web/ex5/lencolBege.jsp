<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lençol Bege</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar bg-body-success" style="background-color: #ebffe9">
            <div class="container-fluid">
              <a class="text-dark" href="tela5.jsp">Pagina Inicial</a>
            </div>
        </nav>
        
        <h1 style="margin-left: 30px">Lençol Bege</h1>
        
         <%
        Cookie cookie = new Cookie("Bege", "lencolBege.jsp"); 
        cookie.setMaxAge(20);
        response.addCookie(cookie);
        %>
        
        <div style="display: flex; flex-direction: row;">
            
            <img src="images/lencolBege.jpg" class="card-img-top" alt="lencol bege" style="width: 50%; margin-left: 30px">
            
            <div class="card-body" >
                  <h5 class="card-title">Lençol Bege</h5>
                  <p class="card-text">R$321,90</p>
                  <a href="#" class="btn btn-success">Comprar</a>
            </div>
        </div>
        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
