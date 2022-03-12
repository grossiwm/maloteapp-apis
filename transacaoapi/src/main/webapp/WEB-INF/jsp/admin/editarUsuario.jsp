<%@ page import="java.util.Objects" %>
<%@ page import="com.gabrielrossilopes.appmalote.dto.UsuarioDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp"/>
<c:set var = "usuario" scope="request" value = '${requestScope["usuario"]}'/>
<body>
<jsp:include page="../partials/menu.jsp"/>
<h1>Editar usuário</h1>
<form action="/admin/editar-usuario" method='POST'>
    <c:if test = "${error}">
        <div class="alert alert-danger" role="alert">
            Erro ao cadastrar usuário
        </div>
    </c:if>
    <table>
        <input type="hidden" name="id" value="<c:out value='${usuario.id}' />">
        <tr>
            <td>Nome:</td>
            <td><input type='text' name='email' value="<c:out value='${usuario.nome}' />"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type='text' name='email' value="<c:out value='${usuario.email}' />"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type='password' name='senha'
                       value="<c:out value='${usuario.senha}' />"/></td>
        </tr>
        <tr>
            <td>Empresa:</td>
            <td>
                <select name="empresa">
                    <c:forEach items="${empresas}" var="empresa">
                        <option value="${empresa.id}">
                                ${empresa.nome}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>