<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../layout/header.jsp" %>

<a role="button" data-toggle="modal" data-target="#editRefPersonnage">
	<i class="fff-add"></i> Créer un personnage
</a>

<h1>Liste des personnages</h1>


<table id="personnagesTable"
	class="table table-bordered table-striped table-condensed datagrid">
	<thead>
		<tr>
			<th class="col-md-1">#</th>
			<th class="col-md-10">Nom</th>
			<th class="col-md-1">Supprimer</th>
		</tr>
	</thead>

	<tbody>

		<c:if test="${empty personnages}">
			<tr class="noData">
				<td colspan="3" align="center">Aucun personnage</td>
			</tr>
		</c:if>

		<c:forEach items="${personnages}" var="personnage">
			<tr>
				<td>${personnage.idPersonnage}</td>
				<td>${personnage.nom}</td>
				<td><i class="fff-cross" title="Non"></i></td>
			</tr>
		</c:forEach>

	</tbody>
</table>

${modal}

<%@include file="../layout/footer.jsp" %>
