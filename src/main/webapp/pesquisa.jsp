<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener</title>
    <link rel="stylesheet" href="css/pesquisa.css?v=1.0">
</head>

<body>

    <header>
        <div class="navpane">
            <div class="nav-logo">
                <a href="index.jsp"><h1 id="logo">Listener</h1></a>
            </div>

            <div class="nav-icons">
                <img src="assets/home.png" alt=""> <a href="index.jsp">Inicio</a>
                <img src="assets/diagrama.png" alt=""> <a href="">Explorar</a>
                <img src="assets/comunidade.png" alt=""> <a href="">Comunidade</a>
                <img src="assets/trending.png" alt=""> <a href="">Em alta</a>
            </div>
            <div class="nav-search-box">
                <form action="/create-musica" method="POST">
                    <input type="text" name="musica" placeholder="Pesquise sua musica: ">
                    <button type="submit" value="submit">
                </form>
            </div>

        </div>
    </header>
    <hr>
         <div class="header-container">
                <div class="inside-container">
                    <div class="title-container">
                        <h1>Resultados similares a <%= request.getParameter("musica") %></h1>
                    </div>
                    <div class="subtitle-container">
                        <p>Aproveite a selecao de artistas e musicas</p>
                    </div>
                </div>
         </div>

          <div class="page-content">
                 <div class="art-container">
                    <div class="under-container">
                        <div class="musica-container">
                            <h1>Pesquisar resultados</h1>
                         </div>
                    </div>
                 </div>
             </hr>
          <div class="faixas-container">
              <p class="faixas-subtitle">Faixas</p>
               <c:forEach var="musica" items="${musicas}">
                    <div class="musica-item-container">
                      <span class="item-nome">${musica.nome}</span>
                      <span class="item-artista">${musica.artista}</span>
                    </div>
               </c:forEach>
        </div>

</body>

</html>