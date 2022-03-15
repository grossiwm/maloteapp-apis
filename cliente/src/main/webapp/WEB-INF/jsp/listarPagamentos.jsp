<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
<jsp:include page="partials/menu.jsp"/>
<h3>Pagamentos</h3>
<hr size="4" color="gray"/>
<table>
<c:forEach items="${pagamentos}" var="p">
    <tr>
        <td>Id: <c:out value="${p.id}"/></td>
        <td>CNPJ do Beneficiário: <c:out value="${p.cnpjRecebedor}"/></td>
        <td>Valor: <c:out value="${p.valor}"/></td>
        <c:if test = "${malote != null}">
            <td>Malote: <c:out value="${p.malote.id}"/></td>
        </c:if>
        <td><a href="/usuario/alterar-pagamento/<c:out value="${p.id}"/>">Editar</a></td>
        <td><a href="/usuario/remove-pagamento/<c:out value="${p.id}"/>">Remover</a></td>
        <td><a href="/usuario/alterar-malote/<c:out value='${p.malote.id}' />"> malote: <c:out value='${p.malote}' /></a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>