<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
<jsp:include page="partials/menu.jsp"/>
<h1>Cadastrar usuário</h1>
<form action="/usuario/solicitar-acesso" method='POST'>
    <table>
        <tr>
            <td>Nome:</td>
            <td><input type='text' name='nome' value=''></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type='text' name='email' value=''></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type='password' name='senha' /></td>
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