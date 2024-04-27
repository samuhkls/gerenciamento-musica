<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Space Grotesk' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >Listener</title>
    <link rel="stylesheet" href="css/home.css">
</head>

<body>
    <header>
        <div class="navpane">
            <div class="nav-logo">
                <a href="index.html"><h1 id="logo">Listener</h1></a>
            </div>

            <div class="nav-icons">
                <img src="assets/home.png" alt=""> <a href="">Início</a>
                <img src="assets/diagrama.png" alt=""> <a href="">Explorar</a>
                <img src="assets/comunidade.png" alt=""> <a href="">Comunidade</a>
                <img src="assets/trending.png" alt=""> <a href="">Em alta</a>
            </div>
            <div class="nav-search-box">
                <form action="/home" method="POST">
                    <input type="text" name="musica" placeholder="Pesquise sua música: ">
                    <input type="submit" value="Submit">
                </form>
                <a href="login.html"><img src="assets/profile.png" alt=""></a>
            </div>
        </div>
    </header>
    <hr>

    <div class="header-container">
        <div class="inside-container">

            <div class="title-container">
                <h1>Iorem Ipsum</h1>
            </div>

            <div class="subtitle-container">
                <h3>Ipsum</h3>
            </div>
            </div>
        </div>
    </div>

    <div>
        <p>${musica.artista}</p>
    </div>

</body>

</html>
