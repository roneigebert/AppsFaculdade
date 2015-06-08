<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Game</title>
	
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/css.css" rel="stylesheet">
	
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	
	<script>
	
		var labirinto = null;
		var ratazana = null;
		
		$(function(){	
			$.ajax({
			  	dataType: "json",
			  	url: "game/ajax/?labirinto=true",
			  	success: function(data){
			  		//alert("Status ok");
			  		labirinto = data.labirinto;
			  		createLabirinto(labirinto);
			  	},
			  	error: function(jqXHR, textStatus, errorThrown ){
			  		alert("An error is occured, contact the developer");
			  	}
			});
		});
		
		function createLabirinto(labirinto){
			
			for (var lineIndex = 0; lineIndex < labirinto.length; lineIndex++) {
				$('#table_labirinto').append('<tr id="line_'+lineIndex+'"></tr>');
				var line = labirinto[lineIndex];
				var cellHTML = "";
				for (var columnIndex = 0; columnIndex < line.length; columnIndex++) {
					var position = line[columnIndex];
					var positionClasses = generateClasses(position);
					cellHTML += '<td id="cell_'+columnIndex+'" class="'+positionClasses+'"></td>';
				}
				$("#line_"+lineIndex).html(cellHTML);
			}
			
			ratazana = $("#rato");
			moveRatazana();
		}		
		
		
		function moveRatazana(){
			setTimeout(function(){
				$.ajax({
				  	dataType: "json",
				  	url: "game/ajax/",
				  	success: function(data){
				  		//alert("Status ok");
				  		ratazana.show();
				  		if (data.morrer){
				  			ratazana.css({"top":(data.linha * 32)+"px", "left":(data.coluna * 32)+"px"});
				  			game_over(data.linha, data.coluna);
				  		}else if (data.saida){
				  			ratazana.css({"top":(data.linha * 32)+"px", "left":(data.coluna * 32)+"px"});
				  			celebrate();
				  		}else{
				  			console.log(data.queijosComidos);
				  			animateMove(data.linha, data.coluna, data.linhaAnterior, data.colunaAnterior, data.queijosComidos);
				  			var cell = $("#line_"+data.linha).find("#cell_"+data.coluna);
				  			if (cell.hasClass('c')){
				  				setTimeout(function(){
				  					cell.removeClass('c');
					  				document.getElementById('arroto').play();
				  				}, 400);		
				  			}
				  			moveRatazana();
				  		}
				  		
				  	},
				  	error: function(jqXHR, textStatus, errorThrown ){
				  		alert("An error is occured, contact the developer.");
				  	}
				});
			}, 600);
		}
		
		function animateMove(line, column, lineA, columnA, queijosComidos){
			
			var tipoRato = "r_";
			if (queijosComidos > 3){
				tipoRato = "r"+tipoRato;
			}
			
			if (line > lineA){
				animateBottom(lineA, columnA, 1, tipoRato+"bottom_");
			}else if (line < lineA){
				animateTop(lineA, columnA, 1, tipoRato+"top_");
			}else if (column > columnA){
				animateRight(lineA, columnA, 1, tipoRato+"right_");
			}else {
				animateLeft(lineA, columnA, 1, tipoRato+"left_");
			}
			
		}
		
		function animateBottom(line, column, quantMove, className){
			var movePixels = 8 * quantMove;
			ratazana.css({"top":((line * 32) + movePixels)+"px", "left":(column * 32)+"px"});
  			ratazana.attr("class", className+quantMove);
  			
			if (quantMove < 4){
				setTimeout(function(){
					animateBottom(line, column, quantMove+1, className);
				}, 150);
			}
		}
		
		function animateTop(line, column, quantMove, className){
			var movePixels = 8 * quantMove;
			ratazana.css({"top":((line * 32) - movePixels)+"px", "left":(column * 32)+"px"});
  			ratazana.attr("class", className+quantMove);
  			
			if (quantMove < 4){
				setTimeout(function(){
					animateTop(line, column, quantMove+1, className);
				}, 150);
			}
		}
		
		function animateLeft(line, column, quantMove, className){
			var movePixels = 8 * quantMove;
			ratazana.css({"top":(line * 32)+"px", "left":((column * 32)-movePixels)+"px"});
  			ratazana.attr("class", className+quantMove);
  			
			if (quantMove < 4){
				setTimeout(function(){
					animateLeft(line, column, quantMove+1, className);
				}, 150);
			}
		}
		
		function animateRight(line, column, quantMove, className){
			var movePixels = 8 * quantMove;
			ratazana.css({"top":(line * 32)+"px", "left":((column * 32)+movePixels)+"px"});
  			ratazana.attr("class", className+quantMove);
  			
			if (quantMove < 4){
				setTimeout(function(){
					animateRight(line, column, quantMove+1, className);
				}, 150);
			}
		}
		
		var cat = null;
		
		function celebrate(){
			var lines = $("#table_labirinto tr").length / 2;
			var columns = $("#table_labirinto tr:first td").length / 2;
		
			cat = $("#gato");
			cat.css({"top":((lines * 32) - 20)+"px", "left":"0"});
			cat.show();
			
			move_cat(lines, 0, columns, 1, false);
		}
		
		function explosion(num){
			if (num == 1){
				document.getElementById('bomba').play();
			}
			
			if (num < 16){
				setTimeout(function(){
					cat.attr("class", "e_"+num);
					explosion(num + 1);
				}, 40);	
			}else{
				cat.attr("class", "só sei que nada sei");
				document.getElementById('comemoracao').play();
			}
		}
		
		function game_over(line, column){
			cat = $("#gato");
			cat.css({"top":((line * 32) - 20)+"px", "left":"0px"});
			cat.show();
			move_cat(line, 0, column, 1, true);
		}
		
		function move_cat(line, column, cRato, pos, game_over){
			pos = (pos > 6) ? 1 : pos;
			column = column + 16;
			if (column < ((cRato * 32) - 50)){
				setTimeout(function(){
					cat.css({"top":((line*32) - 20)+"px", "left":(column)+"px"});
					cat.attr("class", "c_"+pos);
					move_cat(line, column, cRato, pos+1, game_over);
				}, 40);	
			}else{
				if (game_over){
					ratazana.hide();
					document.getElementById('arroto').play();
				}else{
					explosion(1);
				}
			}
		}
		
		function generateClasses(position){
			//comida 3
			//armadilha 4
			//labirinto 1
			
			var classes = "cell ";
			if (!position.acima){
				classes += "l_top ";
			}
			if (!position.abaixo){
				classes += "l_bottom ";
			}
			if (!position.esquerda){
				classes += "l_left ";
			}
			if (!position.direita){
				classes += "l_right ";
			}
			if (position.self == 3){
				classes += "c ";
			}else if (position.self == 4){
				classes += "a ";
			}else if (position.self == 1){
				classes += "l ";
			}
			return classes;
		}
		
	</script>
	
	<style type="text/css">
		body {
			background-color: #eee;
		}
	</style>
	
</head>
<body>
	<table id="table_labirinto"></table>
	<div id="rato" class="r_bottom_1" style="position: absolute; display: none;"></div>
	<div id="gato" class="c_1" style="position: absolute; display: none;"></div>
	<div style="display: none;">
		<audio id="arroto" src="audio/arroto.wav"></audio>
		<audio id="comemoracao" src="audio/comemoracao.wav"></audio>
		<audio id="bomba" src="audio/bomb.wav"></audio>
	<div>
</body>
</html>