<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="layout/header.jsp" %>

<div class="alert alert-warning">
La ressource spécifiée n'existe pas.

Retournez sur la <a href="<c:url value='/index'/>">page d'accueil</a>.
</div>

<%@include file="layout/footer.jsp" %>
