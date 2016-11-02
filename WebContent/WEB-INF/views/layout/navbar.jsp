<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse sidebar">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Application</a>
		</div>
		<div class="navbar-collapse collapse" id="navbar">
			<ul class="nav navbar-nav">
				<li id="nav-index"><a href="<c:url value='/index'/>">Index<span style="font-size: 16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
				<li id="nav-map"><a href="<c:url value='/map'/>">Map<span style="font-size: 16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-screenshot"></span></a></li>
				<li id="references" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Références <span class="caret"></span> <span style="font-size: 16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu forAnimate" role="menu">
						<li id="nav-ref-personnage"><a href="<c:url value='/references/RefPersonnage'/>">Personnage</a></li>
						<!-- <li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>-->
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>



