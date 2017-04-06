<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formularz logowania użytkownika</title>
</head>
<body>
<h1>Formularz logowania użytkownika</h1>
<br /><br />
logowanie jako administrator: <br />
username: <b>admin</b> <br />
password: <b>admin</b>
<br /><br />
<form action="login" method="post">
	<label>Nazwa użytkownika: &nbsp <input type="text" id="username" name="username" /></label><br />
	<label>Hasło: &nbsp <input type="password" id="password" name="password" /></label><br />
	<label><input type="submit" value="zaloguj" /></label><br />
</form>
<br /><br />
<form action="redirectToRegister" method="post">
<label><b>Nowy użytkownik:</b><Input type="submit" name="register" value="zarejestruj"></label>
</form>
<br /><br />
<form action="viewAll" method="post">
<input type="submit" value="pokaż użytkowników" name="viewAll" />
</form>
</body>
</html>