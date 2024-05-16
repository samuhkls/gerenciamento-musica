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
    <link rel="stylesheet" href="css/profile.css?v=4.0">
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

         <div class="playlist-container">
             <h2>Minhas Playlists</h2>

             <c:forEach var="playlist" items="${playlists}">
                 <div class="playlist-item-container">
                     <span class="playlist-nome">${playlist.nomePLaylist}</span>
                     <span class="playlist-autor">Autor: ${playlist.autor}</span>
                     <span class="playlist-quantidade">Quantidade de musicas: ${playlist.quantidade}</span>

                     <div class="musicas-container">
                         <h3>Musicas:</h3>
                         <c:forEach var="musica" items="${playlist.musicas}">
                            <div class="musica-item-container">
                                <span class="musica-nome">${musica.nome}</span>
                                <span class="musica-artista">Artista: ${musica.artista}</span>
                            </div>
                         </c:forEach>
                     </div>
                 </div>
             </c:forEach>
         </div>

    </div>