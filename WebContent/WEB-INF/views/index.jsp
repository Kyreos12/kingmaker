<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">

	<!-- colonne gauche -->
	<div class="col-sm-2">
		<ul class="nav nav-pills nav-stacked">
			<li role="presentation"><a href="rechercheParAdresse"> Par
					adresse</a></li>
			<li role="presentation"><a href="rechercheParNumeroDossier">Par
					numéro de dossier</a></li>
			<li role="presentation"><a href="rechercheParDate">Par date</a></li>
		</ul>
	</div>
	<!-- end well -->
<!--background-image:
		url(http://img08.deviantart.net/17bd/i/2012/081/0/e/pathfinder_kingmaker_campaign_kingdom_map_by_mackarious-d4tmkki.jpg);-->
	<style type="text/css">
canvas {
	
	background-position: -40px;
}
</style>

	<canvas id="hexmap" width="1560" height="657"></canvas>
	
        <script>
			var couleurBordure = "#000000";
			var couleurRemplissage = "rgba(0, 0, 0, 0.09)";
			var couleurFond = "#000000";

			var hexHeight, hexRadius, hexRectangleHeight, hexRectangleWidth;
			var hexagoneAngle = 0.525;
			var longueur = 43.5;
			var nbX = 10;
			var nbY = 10;
				
			// Calcul spécifique au héxagones
			hexHeight = Math.sin(hexagoneAngle) * longueur;
			hexRadius = Math.cos(hexagoneAngle) * longueur;
			hexRectangleHeight = longueur + 2 * hexHeight;
			hexRectangleWidth = 2 * hexRadius;
		
			/*
			*	Structure de données permettant de récupérer les coordonnées de la souris du canvas
			*/
			function getPosition(event) {
				var x = event.offsetX || event.layerX;
				var	y = event.offsetY || event.layerY;
				var	hexY = Math.floor(y / (hexHeight + longueur));
				var hexX = Math.floor((x - (hexY % 2) * hexRadius) / hexRectangleWidth);
				var screenX = hexX * hexRectangleWidth + ((hexY % 2) * hexRadius);
				var screenY = hexY * (hexHeight + longueur);
				
				var position = {
					x: x,
					y: y,
					hexX: hexX,
					hexY: hexY,
					screenX: screenX,
					screenY: screenY
				};
			
				return position;
			}
			
			function dessinerHexagone(canvasContext, x, y, fill, couleur) {           
				var fill = fill || false;

				ctx.fillStyle = couleur;
				
				canvasContext.beginPath();
				canvasContext.moveTo(x + hexRadius, y); // 
				canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight);
				canvasContext.lineTo(x + hexRectangleWidth, y + hexHeight + longueur);
				canvasContext.lineTo(x + hexRadius, y + hexRectangleHeight);
				canvasContext.lineTo(x, y + longueur + hexHeight);
				canvasContext.lineTo(x, y + hexHeight);
				canvasContext.closePath();
				
				

				if(fill) {
					canvasContext.fill();
				} else {
					canvasContext.stroke();
				}
			}
			
			function dessinerContour(canvasContext, width, height) {
				var i,
					j;

				for(i = 0; i < width; ++i) {
					for(j = 0; j < height; ++j) {
						
						dessinerHexagone(ctx, 
							i * hexRectangleWidth + ((j % 2) * hexRadius), 
							j * (longueur + hexHeight), 
							false, "rgba(0, 0, 0, 0.09)"
						);
					}
				}
			}
			
			function addForest(canvasContext, width, height) {

				for(i = 0; i < width; ++i) {
					for(j = 0; j < height; ++j) {
						
						if (i == 1 || i == 3) {
							
							var x = i * hexRectangleWidth + ((j % 2) * hexRadius);
							var y = j * (longueur + hexHeight);
							
							base_image = new Image();
							base_image.src = 'weather_rain.png';
							canvasContext.drawImage(base_image, x + hexRadius +24, y + (hexRectangleHeight / 2));
							dessinerHexagone(canvasContext, x, y, true, "rgba(100, 150, 237, 0.3)")
						}
						
					}
				}
			}
		
			
			
			var canvas = document.getElementById('hexmap');
			var ctx = canvas.getContext('2d');
			
			
			ctx.strokeStyle = couleurRemplissage;
			dessinerContour(ctx, nbX, nbY);
			addForest(ctx, nbX, nbY);
				
			canvas.addEventListener("mousedown", function(event) {
				
				alert('aaaa');
				var pos = getPosition(event);
				//alert(hexX + ' : ' + hexY);
			});

			canvas.addEventListener("mousemove", function(event) {
				
				var pos = getPosition(event);
				
				// Efface les éléments existants
				ctx.clearRect(0, 0, canvas.width, canvas.height);
				dessinerContour(ctx, nbX, nbY);
				addForest(ctx, nbX, nbY);
				
				dessinerHexagone(ctx, pos.screenX, pos.screenY, true, "rgba(0, 0, 0, 0.09)");
			});
			
			

				

				

			
		</script>

</div>
<!-- end row -->