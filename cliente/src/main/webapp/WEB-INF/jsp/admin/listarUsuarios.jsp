<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp"/>
<body>
<jsp:include page="../partials/menu.jsp"/>
<h3>Usuários</h3>
<c:if test = "${aviso != null}">
    <div class="alert alert-warning" role="alert">
        <c:out value="${aviso}"/>
    </div>
</c:if>
<hr size="4" color="gray"/>
<c:if test = "${usuarioLogado.isAdmin()}">
    <a href="/admin/cadastrar-usuario">Novo Usuário</a>
</c:if>
<table>
<c:forEach items="${usuarios}" var="usuario">
    <tr>
        <td>Id: <c:out value="${usuario.id}"/></td>
        <td>Nome: <c:out value="${usuario.nome}"/></td>
        <td>Email: <c:out value="${usuario.email}"/></td>
        <td>Empresa: <c:out value="${usuario.empresa.nome}"/></td>
        <c:if test = "${usuarioLogado.isAdmin()}">
            <td><a href="/admin/editar-usuario/<c:out value="${usuario.id}"/>">Editar</a></td>
            <td><a href="/admin/remove-usuario/<c:out value="${usuario.id}"/>">Remover</a></td>
        </c:if>
    </tr>
</c:forEach>
</table>
</body>
</html>