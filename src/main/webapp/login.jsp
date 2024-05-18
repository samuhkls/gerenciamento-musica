<!DOCTYPE html>
<html>
<head>
	<title>Listener | Log-in</title>
	<link rel="stylesheet" type="text/css" href="css/login.css?v=7.0">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup">
				<form action="/register" method="post">
					<label for="chk" aria-hidden="true">Listener</label>

                    <span class="erro-login">${requestScope.message}</span>

					<input type="text" name="user-register" placeholder="Usuario" required="">
					<input type="email" name="email-register" placeholder="Seu E-mail" required="">
					<input type="password" name="password-register" placeholder="Sua senha" required="">
					<button type="submit">Cadastrar</button>
				</form>
			</div>

			<div class="login">
				<form action="/login" method = "post">
					<label for="chk" aria-hidden="true">Log-in</label>
					<input type="text" name="user-login" placeholder="Usuario" required="">
					<input type="email" name="email-login" placeholder="Seu E-mail" required="">
					<input type="password" name="password-login" placeholder="Sua senha" required="">
					<button type="submit">Login</button>
				</form>
			</div>
	</div>
</body>
</html>