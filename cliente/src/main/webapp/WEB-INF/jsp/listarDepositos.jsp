<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
<jsp:include page="partials/menu.jsp"/>
<h3>Depósitos</h3>
<hr size="4" color="gray"/>
<table>
<c:forEach items="${depositos}" var="d">
    <tr>
        <td>Id: <c:out value="${d.id}"/></td>
        <td>Nome do Beneficiário: <c:out value="${d.nomeBeneficiario}"/></td>
        <td>CPF do Beneficiário: <c:out value="${d.cpfBeneficiario}"/></td>
        <td>Valor: <c:out value="${d.valor}"/></td>
        <c:if test = "${malote != null}">
            <td>Malote: <c:out value="${d.malote.id}"/></td>
        </c:if>
        <td><a href="/usuario/alterar-deposito/<c:out value="${d.id}"/>">Editar</a></td>
        <td><a href="/usuario/remove-deposito/<c:out value="${d.id}"/>">Remover</a></td>
        <td><a href="/usuario/alterar-malote/<c:out value='${d.malote.id}' />"> malote: <c:out value='${d.malote}' /></a></td>

    </tr>
</c:forEach>
</table>
</body>
</html>