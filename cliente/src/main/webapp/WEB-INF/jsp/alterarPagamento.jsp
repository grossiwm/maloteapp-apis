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
<c:if test = "${pagamento.id != null}">
    <h1>Editar Pagamento</h1>
</c:if>
<c:if test = "${pagamento.id == null}">
    <h1>Novo Pagamento</h1>
</c:if>

<form
<c:if test = "${pagamento.id != null}">
    action="/usuario/alterar-pagamento"
</c:if>
<c:if test = "${pagamento.id == null}">
    action="/usuario/novo-pagamento/<c:out value='${pagamento.malote.id}' />"
</c:if>
method='POST'>
    <c:if test = "${error}">
        <div class="alert alert-danger" role="alert">
            Erro ao cadastrar pagamento
        </div>
    </c:if>
    <table>
        <input type="hidden" name="id" value="<c:out value='${pagamento.id}' />">
        <tr>
            <td>CNPJ do Recebedor:</td>
            <td><input type='text' name='cnpjRecebedor' value="<c:out value='${pagamento.cnpjRecebedor}' />"></td>
        </tr>
        <tr>
            <td>Valor:</td>
            <td><input type='text' name='valor' value="<c:out value='${pagamento.valor}' />"></td>
        </tr>
        <c:if test = "${pagamento.id != null}">
            <input type='hidden' name='maloteId' value="<c:out value='${pagamento.malote.id}' />">
            <td><a href="/usuario/alterar-malote/<c:out value='${pagamento.malote.id}' />">ver malote</a></td>
        </c:if>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>