<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<tiles:insertAttribute name="header" />

<noscript>
	<p>Si votre navigateur ne supporte pas les scripts, il ne sera pas
		possible d'utiliser cette application.</p>
	<p>Le paramétrage de votre navigateur doit être modifié pour
		autoriser l'exécution de scripts JavaScript.</p>
</noscript>

<tiles:insertAttribute name="navbar" />

<!-- colonne gauche -->
<div class="col-sm-2">
	<ul class="nav nav-pills nav-stacked">
		<li id="map" role="presentation"><a href="map">Map</a></li>
	</ul>
</div>

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="a-propos" />

<tiles:insertAttribute name="footer" />