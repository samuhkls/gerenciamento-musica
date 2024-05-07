<!DOCTYPE html>
<html>
<head>
	<title>Listener | Log-in</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup">
				<form>
					<label for="chk" aria-hidden="true">Listener</label>

					<input type="text" name="txt" placeholder="Usuario" required="">
					<input type="email" name="email" placeholder="Seu E-mail" required="">
					<input type="password" name="pswd" placeholder="Sua senha" required="">
					<button>Cadastrar</button>
				</form>
			</div>

			<div class="login">
				<form action="/login" method = "post">
					<label for="chk" aria-hidden="true">Log-in</label>
					<input type="email" name="email" placeholder="Seu E-mail" required="">
					<input type="password" name="pswd" placeholder="Sua senha" required="">
					<button>Login</button>
				</form>
			</div>
	</div>
</body>
</html>