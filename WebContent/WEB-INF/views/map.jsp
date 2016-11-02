<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="layout/header.jsp" %>

<style>
	#hexmap {
		background-image:
			url(http://img08.deviantart.net/17bd/i/2012/081/0/e/pathfinder_kingmaker_campaign_kingdom_map_by_mackarious-d4tmkki.jpg);
			
		background-position: -40px;
	}
</style>

<canvas id="hexmap" width="1560" height="657"></canvas>

${modal}
<!-- <div class="modal fade" id="editHexagone" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 id="editTitle" class="modal-title">Détails de l'héxagone</h4>
			</div>
			
			<div id="dialog_alert"></div>
			
			<div class="modal-body">
				<form class="form-horizontal" role="form" >
					
					<div class="form-group">
	                    <div class="col-sm-6"><input type="hidden" class="form-control" id="editX" readonly/></div>
	                    <div class="col-sm-6"><input type="hidden" class="form-control" id="editY" readonly/></div>
	                </div>
	                
					<div class="form-group">
	                	<label  class="col-sm-2 control-label" for="nom">Nom</label>
	                    <div class="col-sm-10"><input type="text" class="form-control" id="editNom" placeholder="Nom"/></div>
	                </div>
	                
	                <div class="form-group">
	                	<div class="col-sm-offset-1 col-sm-11">
	                		<div class="checkbox"><label><input id= "editVisible" type="checkbox"/>Visible</label></div>
	                		<div class="checkbox"><label><input id= "editRoyaume" type="checkbox"/>Royaume</label></div>
	                		<div class="checkbox"><label><input id= "editRoute" type="checkbox"/>Route</label></div>
                    	</div>
                  	</div>
				</form>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" onclick="return updateHexagone()">Save changes</button>
			</div>
		</div>
	</div>
</div>-->

<link rel="stylesheet" type="text/css" href="<c:url value='/lib/application/css/map.css'/>" />
<%@include file="layout/footer.jsp" %>
