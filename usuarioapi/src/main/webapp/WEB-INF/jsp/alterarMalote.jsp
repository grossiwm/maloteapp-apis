<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="./partials/head.jsp"/>
<body>
<jsp:include page="./partials/menu.jsp"/>
<h3>Malote número <c:out value="${malote.id}"/> da empresa <c:out value="${malote.empresa.nome}"/> </h3>
<hr size="4" color="gray"/>
<form action="/usuario/alterar-malote/<c:out value="${malote.id}"/>" method='POST'>
<fieldset>
    <legend>escolha os depósitos:</legend>
    <c:forEach items="${depositos}" var="deposito">
        <div>
            <input type = "checkbox" id = '<c:out value="${deposito.id}"/>' name = "deposito-id" value = "${deposito.id}">
            <label> <c:out value="${deposito.id}"/> - <c:out value="${deposito}"/> </ label>
        </div>
    </c:forEach>
    </fieldset>
    <input name="submit" type="submit" value="submit" />
</form>
    <legend>depósitos nesse malote:</legend>
    <c:forEach items="${malote.depositos}" var="deposito">
        <div>
            <label> <c:out value="${deposito.id}"/> - <c:out value="${deposito}"/> <a href="/usuario/malote/${malote.id}/remover-deposito/${deposito.id}">remover</a> </ label>

        </div>
    </c:forEach>
    </fieldset>
</body>
</html>