<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="layout/header.jsp"%>

<h1 class="titre_degrade">Hexagones</h1>
<table id="data" class="table datagrid table-bordered compact">
	<thead>
		<tr>
			<th width="20px">#</th>
			<th>Nom</th>
			<th width="20px">X</th>
			<th width="20px">Y</th>
			<th width="20px">Visible</th>
			<th width="20px">Royaume</th>
			<th width="20px">Route</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty hexagones}">
			<tr class="noData">
				<td colspan="7" align="center">Aucun hexagone</td>
			</tr>
		</c:if>

		<c:forEach items="${hexagones}" var="hexagone">
			<tr>
				<td>${hexagone.idHexagone}</td>
				<td>${hexagone.nom}</td>
				<td>${hexagone.x}</td>
				<td>${hexagone.y}</td>
				<td class="text-center">
					<c:if test="${hexagone.visible}"><i class="fff-tick" title="Oui"></i></c:if>
					<c:if test="${hexagone.visible == false}"><i class="fff-cross" title="Non"></i></c:if>
				</td>
				<td class="text-center">
					<c:if test="${hexagone.royaume}"><i class="fff-tick" title="Oui"></i></c:if>
					<c:if test="${hexagone.royaume == false}"><i class="fff-cross" title="Non"></i></c:if>
				</td>
				<td class="text-center">
					<c:if test="${hexagone.route}"><i class="fff-tick" title="Oui"></i></c:if>
					<c:if test="${hexagone.route == false}"><i class="fff-cross" title="Non"></i></c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@include file="layout/footer.jsp"%>

