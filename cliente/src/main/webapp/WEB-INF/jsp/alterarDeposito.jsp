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

<c:if test = "${deposito.id != null}">
    <h1>Editar Depósito</h1>
</c:if>
<c:if test = "${deposito.id == null}">
    <h1>Novo Depósito</h1>
</c:if>


<form
<c:if test = "${deposito.id != null}">
action="/usuario/alterar-deposito"
</c:if>
<c:if test = "${deposito.id == null}">
    action="/usuario/novo-deposito/<c:out value='${deposito.malote.id}' />"
</c:if>
method='POST'>
    <c:if test = "${error}">
        <div class="alert alert-danger" role="alert">
            Erro ao cadastrar depósito
        </div>
    </c:if>
    <table>
        <input type="hidden" name="id" value="<c:out value='${deposito.id}' />">
        <tr>
            <td>CPF do Beneficiário:</td>
            <td><input type='text' name='cpfBeneficiario' value="<c:out value='${deposito.cpfBeneficiario}' />"></td>
        </tr>
        <tr>
            <td>Nome do Beneficiário:</td>
            <td><input type='text' name='nomeBeneficiario' value="<c:out value='${deposito.nomeBeneficiario}' />"></td>
        </tr>
        <tr>
            <td>Valor:</td>
            <td><input type='text' name='valor' value="<c:out value='${deposito.valor}' />"></td>
        </tr>
        <c:if test = "${deposito.id != null}">
        <tr>
            <td><a href="/usuario/alterar-malote/<c:out value='${deposito.malote.id}' />">ver malote</a></td>
            <input type='hidden' name='maloteId' value="<c:out value='${deposito.malote.id}' />">
        </tr>
        </c:if>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
    <a href="/usuario/listar-depositos">ver todos</a>
</form>
</body>
</html>