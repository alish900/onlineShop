<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).on('click', '.buy', function(){
		var btn = $(this);
		var dataStr = "id_good="+$(btn).attr('id');
		$.ajax({
			type : "POST",
			url : "Goods",
			data : dataStr,
			success : function() {
				$(btn).text('В корзине');
				$(btn).attr('disabled', true);
			}
		});
	});
</script>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<title>Каталог товаров</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
    <div class='header'>
    <nav>
    	ПРИПРАВЫ.НСК | 
    	☎ +7-913-321-2322  |
        <a href="/shop/Goods">Товары</a> 
        <a href="/shop/Basket" style="margin-left: 200px">Корзина</a> |
        <a  href="#">Вход</a>
     </nav>   
    </div>
    <div class='content'>
	   <div id='catalog'>
	<h1>Каталог товаров</h1>
		<c:forEach var = "good" items = "${goods}">
			<div class='good'>
				<img src='${good.image}' width="300" height="300"><br>
				${good.name}, ${good.weight}г<br>
				${good.price}₽ <button class="buy" id='${good.id}'>Купить</button>
			</div>
		</c:forEach>
	   </div>
    </div>
    <div class='footer'></div>
</body>
</html>