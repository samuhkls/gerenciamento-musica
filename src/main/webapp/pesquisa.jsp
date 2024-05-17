<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener</title>
    <link rel="stylesheet" href="css/pesquisa.css?v=3.0">
</head>

<body>

    <header>
        <div class="navpane">
            <div class="nav-logo">
                <a href="index.jsp"><h1 id="logo">Listener</h1></a>
            </div>

            <div class="nav-icons">
                <img src="assets/home.png" alt=""> <a href="/home">Inicio</a>

                <img src="assets/comunidade.png" alt=""> <a href="/community">Comunidade</a>
                <img src="assets/profile.png" alt=""> <a href="/profile">Meu Perfil</a>

            </div>
            <div class="nav-search-box">
                <form action="/create-musica" method="POST">
                    <input type="text" name="musica" placeholder="Pesquise sua musica: ">
                    <button type="submit" value="submit">
                </form>
            </div>

        </div>
    </header>

         <div class="header-container">
                <div class="inside-container">
                    <div class="title-container">
                        <h1>Resultados similares a sua pesquisa</h1>
                    </div>
                    <div class="subtitle-container">
                        <p>Aproveite a selecao de artistas e musicas</p>
                    </div>
                </div>
         </div>

          <div class="page-content">
                  <div class="create-container">
                     <h1 id="title-pesquisa">Criar uma playlist</h1>
                         <div class="playlist-create">
                              <form action="/create-playlist" method="POST">
                                  <input type="text" name="playlistName" placeholder="Digite o nome da playlist">
                                  <button id="botao-create" type="submit">Criar playlist</button>
                              </form>
                         </div>
                  </div>
                 <div class="art-container">
                    <div class="under-container">
                        <div class="musica-container">
                            <h1 id="title-pesquisa">Historico de resultados</h1>
                         </div>
                    </div>
                 </div>
             </hr>
          <div class="faixas-container">

              <form action="/lista-musicas" method="GET">
               <c:forEach var="musica" items="${musicas}">
                   <div class="musica-item-container">
                       <span class="item-nome">${musica.nome}</span>
                       <span class="item-artista">${musica.artista}</span>
                       <span class="id-musica" id="id-musica">

                       <form action="/add-musica" method="POST">
                           <input type="hidden" name="musicaId" value="${musica.id}">
                           <select name="playlistName">
                           <option selected disabled>Selecione a playlist</option>
                               <c:forEach var="playlist" items="${playlists}">
                                   <option value="${playlist.nomePLaylist}">${playlist.nomePLaylist}</option>
                               </c:forEach>
                           </select>
                           <button id="botao-create" type="submit">Adicionar</button>
                       </form>
                   </div>
               </c:forEach>


               </form>
        </div>


</body>

</html>