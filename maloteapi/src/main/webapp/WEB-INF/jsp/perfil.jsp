<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="partials/head.jsp"/>
    <body>
    <jsp:include page="partials/menu.jsp"/>
        <h1>Perfil</h1>
        <ul>
            <li>Nome: <c:out value="${usuario.nome}"/></li>
            <li>Email: <c:out value="${usuario.email}"/></li>
            <c:if test = '${usuario.empresa != null && usuario.empresa != ""}'>
                <li>Empresa: <c:out value="${usuario.empresa.nome}"/></li>
            </c:if>
        </ul>

    </body>
</html>