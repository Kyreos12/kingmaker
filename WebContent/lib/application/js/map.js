// Récpuère le canvas hexmap
var canvas = $("#hexmap")[0];

if (canvas != null) {

	// Défini la page comme active dans le menu
	$("#map").addClass("active");
	// Paramètres couleurs
	var hexagones = [];
	var color_hide = "rgba(230, 230, 230, 1)";
	var color_hoover = "rgba(0, 100, 0, 0.1)";
	var color_visible = "rgba(255, 255, 255, 0)";
	var color_kingdom = "rgba(100, 150, 237, 0.3)";
	var color_contour = "rgba(0, 0, 0, 0.1)";

	var canvasContext = canvas.getContext('2d');

	canvas.addEventListener("click", function(event) {

		var pos = getPosition(event);
		var hexagone = hexagones[pos.hexX + '' + pos.hexY];

		if (hexagone != null) {
			$("#dialog_alert").empty();
			$("#editTitle").text("Héxagone " + hexagone.x + "-" + hexagone.y + ":" + hexagone.id);
			$("#editX").val(hexagone.x);
			$("#editY").val(hexagone.y);
			$("#editNom").val(hexagone.nom);
			$("#editVisible").prop("checked", hexagone.visible);
			$("#editRoyaume").prop("checked", hexagone.royaume);
			$("#editRoute").prop("checked", hexagone.route);
			$('#editHexagone').modal('show');
		}
	});

	canvas.addEventListener("mousemove", function(event) {

		canvasContext.clearRect(0, 0, canvas.width, canvas.height);
		drawAndFillHex(canvasContext);

		var pos = getPosition(event);
		var x = pos.screenX;
		var y = pos.screenY;

		canvasContext.beginPath();
		canvasContext.moveTo(x + hexRadius, y); // 
		canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight);
		canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight + longueur);
		canvasContext.lineTo(x + hexRadius, y + hexRectangleHeight);
		canvasContext.lineTo(x, y + longueur + hexHeight);
		canvasContext.lineTo(x, y + hexHeight);
		canvasContext.closePath();

		canvasContext.fillStyle = color_hoover;
		canvasContext.fill();
	});

	refreshHexagones();
}

var nbX = 20;
var nbY = 10;
var hexHeight, hexRadius, hexRectangleHeight, hexRectangleWidth;
var hexagoneAngle = 0.525;
var longueur = 43.5;

// Calcul spécifique au héxagones
hexHeight = Math.sin(hexagoneAngle) * longueur;
hexRadius = Math.cos(hexagoneAngle) * longueur;
hexRectangleHeight = longueur + 2 * hexHeight;
hexRectangleWidth = 2 * hexRadius;

function drawAndFillHex(canvasContext) {

	for (var i = 0; i < nbX; ++i) {
		for (var j = 0; j < nbY; ++j) {

			var x = i * hexRectangleWidth + ((j % 2) * hexRadius);
			var y = j * (longueur + hexHeight);

			canvasContext.strokeStyle = color_contour;
			canvasContext.fillStyle = hexagones[i + '' + j].bg_color;
			var ajoutRoute = hexagones[i + '' + j].route;
			

			canvasContext.beginPath();
			canvasContext.moveTo(x + hexRadius, y); // 
			canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight);
			canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight
					+ longueur);
			canvasContext.lineTo(x + hexRadius, y + hexRectangleHeight);
			canvasContext.lineTo(x, y + longueur + hexHeight);
			canvasContext.lineTo(x, y + hexHeight);
			canvasContext.closePath();

			canvasContext.stroke();
			canvasContext.fill();
			
			/*if (ajoutRoute) {
				
				base_image = new Image(); 
				base_image.src = 'lib/application/css/accept.png';
				canvasContext.drawImage(base_image, x, y);
			}*/
		}
	}
}

function getPosition(event) {

	var x = event.offsetX || event.layerX;
	var y = event.offsetY || event.layerY;
	var hexY = Math.floor(y / (hexHeight + longueur));
	var hexX = Math.floor((x - (hexY % 2) * hexRadius) / hexRectangleWidth);
	var screenX = hexX * hexRectangleWidth + ((hexY % 2) * hexRadius);
	var screenY = hexY * (hexHeight + longueur);
	var centerX = screenX + hexRadius;
	var centerY = screenY + hexHeight;

	var position = {
		x : x,
		y : y,
		hexX : hexX,
		hexY : hexY,
		screenX : screenX,
		screenY : screenY,
		centerX : centerX,
		centerY : centerY
	};

	return position;
}

// Récupère les Héxagones
function refreshHexagones() {
	$.ajax({
		url : "getHaxagones",
		type : "POST",
		dataType : "json"
	}).done(function(response) {

		for (var i = 0; i < response.length; i++) {

			var myObj = response[i];
			var x = myObj.x;
			var y = myObj.y;
			var bg_color = "";

			if (!myObj.visible) {
				bg_color = color_hide;
			} else if (myObj.royaume) {
				bg_color = color_kingdom;
			} else {
				bg_color = color_visible;
			}

			var hexagone = {
				id : myObj.idHexagone, 
				x : x,
				y : y,
				nom : myObj.nom,
				visible : myObj.visible,
				royaume : myObj.royaume,
				route : myObj.route,
				bg_color : bg_color
			};

			hexagones[x + '' + y] = hexagone;
		}

		drawAndFillHex(canvasContext);
	});
}

/**
 * Quand on clique sur le bouton de la fenêtre d'ajout d'un Etablissement.
 */
function updateHexagone() {

	$
			.ajax({
				url : "updateHexagone",
				data : {
					x : $("#editX").val(),
					y : $("#editY").val(),
					nom : $("#editNom").val(),
					visible : $("#editVisible").prop("checked"),
					royaume : $("#editRoyaume").prop("checked"),
					route : $("#editRoute").prop("checked")
				},
				type : "POST",
				dataType : "html",
				success : function(data) {

					$("#dialog_alert")
							.html(
									"<div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><strong>Succès ! </strong>"
											+ data + "</div>");
					refreshHexagones();
					$('#editHexagone').modal('toggle');

				},
				error : function(xhr, status) {
					$("#dialog_alert")
							.html(
									"<div class=\"alert alert-danger\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><strong>Danger!</strong>"
											+ "Une erreur est survenue.</div>");
				},
				complete : function(xhr, status) {
					// $('#showresults').slideDown('slow')
				}
			});
}