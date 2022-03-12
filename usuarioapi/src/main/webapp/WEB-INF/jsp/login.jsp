<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="partials/head.jsp"/>
<body>
   <h1>Login</h1>
   <jsp:include page="partials/menu.jsp"/>
   <form name='f' action="/login" method='POST'>
      <c:if test = "${param.error != null}">
         <div class="alert alert-danger" role="alert">
            Credenciais incorretas

         </div>
      </c:if>
      <table>
         <tr>
            <td>Email:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Senha:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>
</body>
</html>