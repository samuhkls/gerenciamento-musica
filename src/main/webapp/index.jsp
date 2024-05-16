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
                <img src="assets/comunidade.png" alt=""> <a href="">Comunidade</a>
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
                    <c:forEach var="musica" items="${musicas}">
                        <div class="musica-item-container">
                            <img src="${musica.imageURL}" class="item-image"></img>
                            <span class="item-nome">${musica.nome}</span>
                            <span class="item-artista">${musica.artista}</span>
                        </div>
                    </c:forEach>
                </div>
              </div>

              <div class="top-artists">
                  <div class="title-musics-container">
                     <h3>Artistas em Alta</h3>
                     <h4>Os Artistas mais acessados em todo o mundo.</h4>
                  </div>
                  <div class="top-artists-container">

                  </div>
              </div>
         </div>

         <div class="recommendations-container">
              <div class="recommendations-musics">

                    <div class="title-musics-container">
                      <h3>Musicas Recomendadas</h3>
                      <h4>Os As Musicas que mais se assemelham ao seus gostos.</h4>
                    </div>

                    <div class="recommend-musics-container">
                        <c:forEach var="recomendacoesAleatorias" items="${recomendacoesAleatorias}">
                          <span class="item-nome">${musica.nome}</span>
                          <span class="item-artista">${musica.artista}</span>
                        </c:forEach>
                    </div>

              </div>

              <div class="recommendations-artists">
                     <div class="title-musics-container">
                       <h3>Artistas Recomendados</h3>
                       <h4>Os Artistas que mais se assemelham ao seu gostos.</h4>
                     </div>
               </div>
         </div>

         <footer class="footer-container">
          <p>Desenvolvido usando as tecnologias Maven, JSP, H2, Java e LastFM API.</p>
        </footer>
    </div>

</body>

</html>