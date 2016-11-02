function htmlbodyHeightUpdate(){
	var height3 = $( window ).height();
	var height1 = $('.nav').height()+50;
	height2 = $('.main').height();
	
	if(height2 > height3){
		$('html').height(Math.max(height1,height3,height2)+10);
		$('body').height(Math.max(height1,height3,height2)+10);
	} else {
		$('html').height(Math.max(height1,height3,height2));
		$('body').height(Math.max(height1,height3,height2));
	}
	
}
$(document).ready(function () {
	htmlbodyHeightUpdate()
	$( window ).resize(function() {
		htmlbodyHeightUpdate()
	});
	$( window ).scroll(function() {
		height2 = $('.main').height()
			htmlbodyHeightUpdate()
	});
});

// gestion des datagrids (grille avec pagination, filtre et tri)
$.fn.dataTable['datagrid'] = {
	"iDisplayLength" : 10,
    "aLengthMenu" : [ 10, 25, 50, 100, 250 ],
    "language": {
    	decimal:		",",
    	processing:     "Traitement en cours...",
    	search:         "Rechercher&nbsp;:",
    	lengthMenu:    "Afficher _MENU_ &eacute;l&eacute;ments",
    	info:           "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
    	infoEmpty:      "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
    	infoFiltered:   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
    	infoPostFix:    "",
    	loadingRecords: "Chargement en cours...",
    	zeroRecords:    "Aucun &eacute;l&eacute;ment &agrave; afficher",
    	emptyTable:     "Aucune donnée disponible dans le tableau",
    	paginate: {
    		first:      "Premier",
    		previous:   "Pr&eacute;c&eacute;dent",
    		next:       "Suivant",
    		last:       "Dernier"
    	},
    	aria: {
    		sortAscending:  ": activer pour trier la colonne par ordre croissant",
    		sortDescending: ": activer pour trier la colonne par ordre décroissant"
    	}
    }
};

$(document).ready(function() {
	$('.datagrid').dataTable($.fn.dataTable['datagrid']);
});