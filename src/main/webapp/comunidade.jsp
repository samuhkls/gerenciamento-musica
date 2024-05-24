<!DOCTYPE html>
<html lang="en">
<%@ page import="dao.PlaylistDAO"%>
<%@ page import="model.Playlist"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener</title>
    <link rel="stylesheet" href="css/comunidade.css?v=9.0">
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
                        <h1>Seja bem vindo a nossa aba de comunidade!</h1>
                    </div>
                    <div class="subtitle-container">
                        <p>Explore nossa vibe comunitaria, compartilhe batidas, descubra talentos e faca parte do groove musical da galera!</p>
                    </div>
                </div>
         </div>

    <div class="page-content">
        <div class="popular-songs-container">
            <div class="pl-title">
                <h2 id="comunidade-playlist-title">O que a galera mais anda escutando</h2>
            </div>
            <table class="tabela-grafico">
                <tr>
                    <th>Nome</th>
                    <th>Artista</th>
                    <th>Quantidade</th>
                </tr>
                 <c:forEach var="musica" items="${popularMusicas}">
                  <tr>
                    <td id="musica-table">${musica.nome}</td>
                    <td id="artista-table">${musica.artista}</td>
                    <td id="qtd-table">${musica.qtdPLaylists} playlists</td>
                  </tr>
                  </c:forEach>
            </table>
        </div>

        <div class="all-playlists-container">
             <div class="pl-title">
                <h2 id="comunidade-playlist-title">As playlists mais curtidas</h2>
             </div>
             <div  class="pl-wrap">
                <c:forEach var="playlist" items="${popularPlaylists}">
                <div class="playlist">
                    <div class="playlist-item">
                        <h3 id="titulo-playlist">${playlist.nomePLaylist}</h3>
                        <div class="separa-autor-musica">
                            <span id="autor-playlist">${playlist.autor}</span> <span id="playlist-quantidade">${playlist.quantidade} - musicas</span> <span id="playlist-quantidade">${playlist.qtdCurtidas} - curtidas</span>
                        </div>
                        <hr>
                        <c:forEach var="musica" items="${playlist.musicas}">
                        <p id="musica-nome-playlist">${musica.nome}</p>
                        <p id="musica-artista-playlist">${musica.artista}</p>
                        </c:forEach>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>

        <div class="all-playlists-container">
            <div class="pl-title">
            <h2 id="comunidade-playlist-title">Explore todas as Playlists</h2>
            </div>
            <div  class="pl-wrap">
                <c:forEach var="playlist" items="${allPlaylists}">
                    <div class="playlist">
                       <div class="playlist-item">
                            <h3 id="titulo-playlist">${playlist.nomePLaylist}</h3>
                            <div class="separa-autor-musica">
                            <span id="autor-playlist">${playlist.autor}</span> <span id="playlist-quantidade">${playlist.quantidade} - musicas</span>

                            <form id="botao-create-id" action="/curtir-playlist" method="POST">
                                <input type="hidden" name="playlistid" value="${playlist.id}">
                                <button id="botao-create" type="submit"><img id="heart-button" src="assets/heart.png">Curtir</button>
                            </form>

                            </div>
                            <hr>
                            <c:forEach var="musica" items="${playlist.musicas}">
                            <p id="musica-nome-playlist">${musica.nome}</p>
                            <p id="musica-artista-playlist">${musica.artista}</p>
                            </c:forEach>
                       </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</body>