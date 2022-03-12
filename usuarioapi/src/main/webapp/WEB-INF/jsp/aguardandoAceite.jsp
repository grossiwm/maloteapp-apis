<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
<jsp:include page="partials/menu.jsp"/>
    <h2>Prezado <c:out value="${username}"/>, sua solicitação está sendo analisada e em breve vc terá uma resposta</h2>
    <a href="/logout">Sair</a>
</body>
</html>