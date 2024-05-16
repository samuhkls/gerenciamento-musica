<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener | Home</title>
    <link rel="stylesheet" href="css/home.css?v=4.0">
</head>

<body>
    <header>
        <div class="navpane">
            <div class="nav-logo">
                <a href="/home"><h1 id="logo">Listener</h1></a>
            </div>

            <div class="nav-icons">
                <img src="assets/home.png" alt=""> <a href="/home">Inicio</a>
                <img src="assets/diagrama.png" alt=""> <a href="">Explorar</a>
                <img src="assets/comunidade.png" alt=""> <a href="/comunidade">Comunidade</a>
                <img src="assets/trending.png" alt=""> <a href="">Em alta</a>
            </div>

            <div class="nav-search-box">
                <form action="/create-musica" method="POST">
                    <input type="text" name="musica" placeholder="Pesquise sua musica: " >
                    <button type="submit" value="Submit">
                </form>
            </div>

        </div>
    </header>
    <hr>

    <div class="header-container">
        <div class="inside-container">

            <div class="title-container">
                <h1>Explore suas musicas favoritas</h1>
            </div>

            <div class="subtitle-container">
                <p>Reunimos todos os seus servicos musicais preferidos e nos juntamos a eles, ouvindo, assistindo e compartilhando para conectar o seu mundo musical. Explore.</p>
            </div>
        </div>
    </div>

    <div class="page-content">
         <div class="trending-container">
              <div class="top-musics">
                <div class="title-musics-container">
                    <h3>Musicas em Alta</h3>
                    <h4>As musicas mais acessadas em todos os lugares do mundo.</h4>
                </div>
                <div class="top-musics-content">
                    <c:forEach var="populares" items="${popularMusicas}">
                        <div class="musica-item-container">
                            <img src="${populares.imageURL}" class="item-image"></img>
                            <span class="item-nome">${populares.nome}</span>
                            <span class="item-artista">${populares.artista}</span>
                        </div>
                    </c:forEach>
                </div>
              </div>



         <footer class="footer-container">
          <p>Desenvolvido usando as tecnologias Maven, JSP, H2, Java e LastFM API.</p>
        </footer>
    </div>

</body>

</html>