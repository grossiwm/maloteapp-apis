<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp"/>
<body>
<jsp:include page="../partials/menu.jsp"/>
<h3>Solicitações</h3>
<hr size="4" color="gray"/>
<table>
    <c:forEach items="${usuarios}" var="usuario">
        <tr>
            <td>Email: <c:out value="${usuario.email}"/></td>
            <td>Empresa: <c:out value="${usuario.empresa.nome}"/></td>
            <td><a href=/admin/aceitar-usuario/<c:out value="${usuario.id}"/>>aceitar</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>