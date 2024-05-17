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

         <div class="playlist-container">
             <h2 class="playlist-h2">Minhas Playlists</h2>
             <hr>


             <c:forEach var="playlist" items="${playlists}">
             <div class="wrap-playlist">
                 <div class="playlist-item-container">
                    <div class="playlist-nome-div">
                     <span id="titulo-playlist" class="playlist-nome">${playlist.nomePLaylist}</span>
                    </div>
                     <span id="autor-playlist" class="playlist-autor">${playlist.autor} - </span>
                     <span class="playlist-quantidade">${playlist.quantidade} musicas</span>
                     <hr>
                     <div class="musicas-container">
                         <c:forEach var="musica" items="${playlist.musicas}">
                            <div class="musica-item-container">
                                <span id="musica-nome-playlist" class="musica-nome">${musica.nome}</span>
                                <span id="musica-artista-playlist" class="musica-artista">${musica.artista}</span>
                            </div>
                         </c:forEach>
                     </div>
               </div>
             </c:forEach>
           </div>
         </div>

    </div>