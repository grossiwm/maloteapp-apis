<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var = "usuarioLogado" scope="session" value = "${sessionScope['scopedTarget.usuarioLogadoSession']}"/>
<!DOCTYPE html>
<html>
<jsp:include page="./partials/head.jsp"/>
<body>
    <jsp:include page="./partials/menu.jsp"/>
    <h3>Malote número <c:out value="${malote.id}"/> da empresa <c:out value="${malote.empresa.nome}"/> </h3>
    <hr size="4" color="gray"/>
    <form action="/usuario/alterar-malote/<c:out value="${malote.id}"/>" method='POST'>
        <input type="hidden" value="usuarioLogado.id">
        <div>
            <label> Data de criação: <c:out value="${dataCriacao}"/>
        </div>
        <div>
            Valor total de Transações: R$ <c:out value="${malote.valorTotal}"/>
        </div>
    </form>
    <c:if test = "${malote.id != null}">
    <div>
           <legend>depósitos:</legend>

            <a href='/usuario/novo-deposito/<c:out value="${malote.id}"/>'>incluir depósito</a>
           <c:forEach items="${malote.depositos}" var="deposito">
               <div>
                   <c:out value="${deposito.id}"/> - <c:out value="${deposito}"/>
                   <a href="/usuario/remove-deposito/${deposito.id}">remover</a>
                  <a href="/usuario/alterar-deposito/${deposito.id}">alterar</a>
               </div>
           </c:forEach>
           </div>
           <div>
          <legend>transferências:</legend>
        <a href='/usuario/nova-transferencia/<c:out value="${malote.id}"/>'>incluir transferencia</a>
          <c:forEach items="${malote.transferencias}" var="transferencia">
              <div>
                  <label> <c:out value="${transferencia.id}"/> - <c:out value="${transferencia}"/>
                  <a href="/usuario/remove-transferencia/${transferencia.id}">remover</a> </ label>
                  <a href="/usuario/alterar-transferencia/${transferencia.id}">alterar</a> </ label>
              </div>
          </c:forEach>
          </div>
          <div>
          <legend>Pagamentos:</legend>
          <a href='/usuario/novo-pagamento/<c:out value="${malote.id}"/>'>incluir pagamento</a>
        <c:forEach items="${malote.pagamentos}" var="pagamento">
                <div>
                    <label> <c:out value="${pagamento.id}"/> - <c:out value="${pagamento}"/>
                    <a href="/usuario/remove-pagamento/${pagamento.id}">remover</a> </ label>
                  <a href="/usuario/alterar-pagamento/${pagamento.id}">alterar</a> </ label>

                </div>
            </c:forEach>
           <br/>
           </div>
    </c:if>

</body>
</html>