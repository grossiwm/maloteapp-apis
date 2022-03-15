<%@ page import="java.util.Objects" %>
<%@ page import="com.gabrielrossilopes.appmalote.dto.UsuarioDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/head.jsp"/>
<c:set var = "usuario" scope="request" value = '${requestScope["usuario"]}'/>
<body>
<jsp:include page="partials/menu.jsp"/>
<c:if test = "${transferenica.id != null}">
    <h1>Editar Transferencia</h1>
</c:if>
<c:if test = "${transferencia.id == null}">
    <h1>Nova Transferencia</h1>
</c:if>

<form
<c:if test = "${transferencia.id != null}">
    action="/usuario/alterar-transferencia"
</c:if>
<c:if test = "${transferencia.id == null}">
    action="/usuario/nova-transferencia/<c:out value='${transferencia.malote.id}' />"
</c:if>
method='POST'>
    <c:if test = "${error}">
        <div class="alert alert-danger" role="alert">
            Erro ao cadastrar transferencia
        </div>
    </c:if>
    <table>
        <input type="hidden" name="id" value="<c:out value='${transferencia.id}' />">
        <tr>
            <td>Conta de Origem:</td>
            <td><input type='text' name='contaOrigem' value="<c:out value='${transferencia.contaOrigem}' />"></td>
        </tr>
        <tr>
            <td>Conta de Destino:</td>
            <td><input type='text' name='contaDestino' value="<c:out value='${transferencia.contaDestino}' />"></td>
        </tr>
        <tr>
            <td>Valor:</td>
            <td><input type='text' name='valor' value="<c:out value='${transferencia.valor}' />"></td>
        </tr>
        <c:if test = "${transferencia.id != null}">
        <td><a href="/usuario/alterar-malote/<c:out value='${transferencia.malote.id}' />">ver malote</a></td>
        <input type='hidden' name='maloteId' value="<c:out value='${transferencia.malote.id}' />">
        </c:if>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>