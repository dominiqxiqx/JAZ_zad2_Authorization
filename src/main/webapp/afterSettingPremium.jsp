<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ustawiono użytkownika Premium</title>
</head>
<body>
<!-- Wczytuje z sesji nazwe nowego uzytkownika premium -->
<b><%  HttpSession sessionJSP = request.getSession(false); response.getWriter().println(sessionJSP.getAttribute("userPremium"));  %></b>&nbsp &nbsp jest teraz użytkownikiem <b>Premium.</b>
<br />
<br />
<form action="newPremium" method="post">
<input type="submit" value="dodaj kolejny" name="newPremium"/>
</form>
<form action="loginPage" method="post">
<input type="submit" value="strona logowania" name="loginPage"/>
</form>
</body>
</html>