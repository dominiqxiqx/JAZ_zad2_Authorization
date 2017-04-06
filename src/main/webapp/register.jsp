<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formularz rejestracyjny nowego użytkownika</title>
</head>
<body>
<h1>Formularz rejestracyjny nowego użytkownika</h1>
<br /><br />
<form action="register" method="post">
	<label>Nazwa użytkownika: &nbsp <input type="text" id="username" name="username" /></label><br />
		<label>Nazwa użytkownika: &nbsp <input type="text" id="username" name="username" /></label><br />
		<label>Hasło: &nbsp <input type="password" id="password" name="password" /></label><br />
		<label>Powtórz hasło: &nbsp <input type="password" id="passwordRepeat" name="passwordRepeat" /></label><br />
		<label>Adres email: &nbsp <input type="text" id="email" name="email" /></label><br />
		<label><input type="submit" value="zarejestruj" /></label><br />
</form>
</body>
</html>