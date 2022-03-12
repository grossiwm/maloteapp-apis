<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp"/>
<c:set var = "empresa" scope="request" value = '${requestScope["empresa"]}'/>
<body>
<h1>Cadastrar empresa</h1>
<form action="/admin/cadastrar-empresa" method='POST'>
    <c:if test = "${error}">
        <div class="alert alert-danger" role="alert">
            Erro ao cadastrar empresa
        </div>
    </c:if>
    <table>
        <tr>
            <td>Nome:</td>
            <td><input type='text' name='nome' value="<c:out value='${empresa.nome}' />"></td>
        </tr>
        <tr>
            <td>Cnpj:</td>
            <td><input type='text' name='cnpj' value="<c:out value='${empresa.cnpj}' />" /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>