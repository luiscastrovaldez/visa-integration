<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<Html>
<head>
<title>Pagina prueba Visa</title>
</head>

<Body onload="fm.submit();">
<form name="fm" method="post" action="http://cal2testing.sytes.net/formularioweb/formulariopago.aspx">
    <input type="hidden" name="ETICKET" value="${eTicket}" /><BR>
</form>
</Body>
</Html>