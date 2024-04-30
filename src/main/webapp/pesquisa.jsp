<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener</title>
    <link rel="stylesheet" href="css/pesquisa.css">
</head>

<body>
    <header>
        <div class="navpane">
            <div class="nav-logo">
                <a href="index.html"><h1 id="logo">Listener</h1></a>
            </div>

            <div class="nav-icons">
                <img src="assets/home.png" alt=""> <a href="">Inicio</a>
                <img src="assets/diagrama.png" alt=""> <a href="">Explorar</a>
                <img src="assets/comunidade.png" alt=""> <a href="">Comunidade</a>
                <img src="assets/trending.png" alt=""> <a href="">Em alta</a>
            </div>
            <div class="nav-search-box">
                <form action="/create-musica" method="POST">
                    <input type="text" name="musica" placeholder="Pesquise sua musica: ">
                    <input type="submit" value="Submit">
                </form>
            </div>

        </div>
    </header>
    <hr>
         <div class="header-container">
                <div class="inside-container">
                    <div class="title-container">
                        <h1>Resultados similares a </h1>
                    </div>
                    <div class="subtitle-container">
                        <p>Aproveite a seleção de artistas e músicas</p>
                    </div>
                </div>
         </div>
         <div class="art-container">
            <div class="under-container">
                <div class="artista-container">
                    <h1>Artistas com esse nome</h1>
                    <p>lorem ipsum</p>
                </div>
                <div class="musica-container">
                    <h1>Músicas com esse nome</h1>
                    <p>lorem ipsum</p>
                 </div>
            </div>
         </div>
     </hr>
     <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Acoes</th>
            </tr>
            <c:forEach var="musica" items="${musicas}">
                <tr>
                    <td>${musica.nome}</td>
                    <td>${musica.artista}</td>
                </tr>
            </c:forEach>

     </table>

</body>

</html>