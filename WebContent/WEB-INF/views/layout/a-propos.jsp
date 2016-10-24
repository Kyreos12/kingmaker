<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<div class="modal fade" id="a-propos-dialog" tabindex="-1" role="dialog">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">A propos de Intr@Geo</h3>
    </div>

    <div class="modal-body">
        <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <address>
                    Gfi &ndash; Géosphère<br>
                    1 rue Champeau<br>
                    21800 Quétigny
                </address>

                <i class="fff-telephone"></i> 03.80.60.84.84<br>
                <i class="fff-email"></i> <a target="_blank" href="http://support-gis.gfi.fr">Support</a><br>
                <i class="fff-link"></i> <a href="http://www.gfi.fr">www.gfi.fr</a><br><br>

                Recherche urbanisme
            </div>
            <div class="col-sm-6">
             <!--  -<img class="col-sm-12"
                    src="<c:url value='/images/logos/gfi.png'/>"
                    style="max-height: 250px"/>-->
            </div>
        </div>
        </div>
    </div>

    <div class="modal-footer">
        <button class="btn btn-sm btn-default" data-dismiss="modal" aria-hidden="true">Fermer</button>
    </div>
</div>
