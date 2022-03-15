<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
<jsp:include page="partials/menu.jsp"/>
<h3>Transferencias</h3>
<hr size="4" color="gray"/>
<table>
<c:forEach items="${transferencias}" var="t">
    <tr>
        <td>Id: <c:out value="${t.id}"/></td>
        <td>Conta de Destino: <c:out value="${t.contaDestino}"/></td>
        <td>Conta de Origem: <c:out value="${t.contaOrigem}"/></td>
        <td>Valor: <c:out value="${t.valor}"/></td>
        <c:if test = "${malote != null}">
            <td>Malote: <c:out value="${t.malote.id}"/></td>
        </c:if>
        <td><a href="/usuario/alterar-transferencia/<c:out value="${t.id}"/>">Editar</a></td>
        <td><a href="/usuario/remove-transferencia/<c:out value="${t.id}"/>">Remover</a></td>
        <td><a href="/usuario/alterar-malote/<c:out value='${t.malote.id}' />"> malote: <c:out value='${t.malote}' /></a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>