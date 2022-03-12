<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp"/>
<body>
<jsp:include page="../partials/menu.jsp"/>
<h3>Empresas</h3>
<hr size="4" color="gray"/>
<table>
<c:forEach items="${empresas}" var="empresa">
    <tr>
        <td>Id: <c:out value="${empresa.id}"/></td>
        <td>Nome: <c:out value="${empresa.nome}"/></td>
        <td>Cnpj: <c:out value="${empresa.cnpj}"/></td>
        <td><a href="/admin/editar-empresa/<c:out value="${empresa.id}"/>">Editar</a></td>
        <td><a href="/admin/remove-empresa/<c:out value="${empresa.id}"/>">Remover</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>