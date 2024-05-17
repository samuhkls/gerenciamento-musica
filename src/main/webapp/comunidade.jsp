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
    <link rel="stylesheet" href="css/profile.css?v=7.0">
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
    <hr>
         <div class="header-container">
                <div class="inside-container">
                    <div class="title-container">
                        <h1>Ola, ${username}. Seja bem-vindo ao seu perfil!</h1>
                    </div>
                    <div class="subtitle-container">
                        <p>Aproveite o nosso site, interaja, pesquise, faca amigos na comunidade, mergulhe na experiencia que a musica oferece.</p>
                    </div>
                </div>
         </div>

    <div class="page-content">
         <div class="profile-container">
            <img src="assets/profile-icon.png" class="profile-image">
            <div class="profile-data">
            <h2>${username}</h2>
            <p>(${email})</p>
            </div>
         </div>
    </div>

    <div class="popular-songs-container">
        <h2>Musicas mais populares entre os usuarios</h2>
        <table>
            <tr>
                <th>Nome</th>
                <th>Artista</th>
                <th>Playlists</th>
            </tr>
             <c:forEach var="musica" items="${popularMusicas}">
              <tr>
                <td>${musica.nome}</td>
                <td>${musica.artista}</td>
                <td>${musica.qtdPLaylists} playlists</td>
              </tr>
              </c:forEach>
        </table>
    </div>

    <div class="all-playlists-container">
        <h2>Explore todas as Playlists</h2>
        <c:forEach var="playlist" items="${allPlaylists}">
        <div class="playlist">
            <h3>${playlist.nomePLaylist}</h3>
            <p>Autor: ${playlist.autor}</p>
            <p>Quantidade de musicas: ${playlist.quantidade}</p>
            <ul>
                <c:forEach var="musica" items="${playlist.musicas}">
                <li>${musica.nome} por ${musica.artista}</li>
                </c:forEach>
            </ul>
        </div>
        </c:forEach>
    </div>
</body>